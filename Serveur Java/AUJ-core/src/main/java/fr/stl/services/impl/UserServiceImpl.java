package fr.stl.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import fr.stl.client.ClientGet;
import fr.stl.dto.LeaguePositionDTO;
import fr.stl.dto.SummonerDTO;
import fr.stl.entity.ContactEntity;
import fr.stl.entity.RiotAccountEntity;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.AuthentificationException;
import fr.stl.exceptions.FonctionnelleException;
import fr.stl.exceptions.TechniqueException;
import fr.stl.manager.ContactManager;
import fr.stl.manager.RiotAccountManager;
import fr.stl.manager.UserManager;
import fr.stl.services.UserService;
import fr.stl.util.PasswordUtils;
import fr.stl.util.RegexValidator;

/**
 * Implémentation de l'interface UserService
 */
public class UserServiceImpl implements UserService {

    /** UserManager */
    UserManager userManager = new UserManager();

    /** RiotAccountManager */
    RiotAccountManager riotAccountManager = new RiotAccountManager();
    
    /** ContactManager */
    ContactManager contactManager = new ContactManager();

    /** Le Client Get d'appelle à Riot */
    ClientGet client = new ClientGet();

    @Override
    public UserEntity authenticate(String login, String password) throws TechniqueException, AuthentificationException {
        // Récupération du User
        UserEntity userEntity = userManager.findByLogin(login);

        // Vérification que l'utilisateur existe
        if (userEntity == null) {
            throw new AuthentificationException("Erreur, l'identifiant est inconnu.");
        }

        // Vérification mot de passe
        if (!PasswordUtils.decodeSha1(userEntity.getPassword(), password)) {
            throw new AuthentificationException("Erreur, le mot de passe est incorrecte.");
        }

        return userEntity;
    }

    @Override
    public void creerCompteUtilisateur(String email, String password, String username, String nomInvocateur,
                    String role) throws FonctionnelleException, TechniqueException {
        // Récupération information Riot
        ClientGet client = new ClientGet();
        SummonerDTO summoner = client.getSummonerBySummonerName(nomInvocateur);

        if (summoner == null) {
            throw new FonctionnelleException("Erreur, le nom d'invocateur est inconnu.");
        }

        // Enregistrement en base
        RiotAccountEntity riotAccount = new RiotAccountEntity();
        riotAccount.setPseudo(summoner.getName());
        riotAccount.setRole(role);
        riotAccount = riotAccountManager.persiste(riotAccount);

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(PasswordUtils.cryptSha512(password));
        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setRiotAccount(riotAccount);
        userManager.persiste(userEntity);

    }

    @Override
    public List<UserEntity> getSuggestion(long idUser) throws TechniqueException {
        Set<UserEntity> listUserFound = new HashSet<>();

        // Récupération du User concerné
        UserEntity userEntity = userManager.findById(idUser);
        if (userEntity == null) {
            throw new TechniqueException("Erreur, l'utilisateur n'est pas connecté.");
        }

        // Récupération des utilisateurs qui n'ont pas le même rôle que l'utilisateur
        // et qui ne font pas parti de sa liste de contact ou de demande en cours
        List<UserEntity> temp1 = userManager.listContactWithoutSameRoleAndWithoutContactWithUser(userEntity);
        List<UserEntity> temp2 = new ArrayList<>();
        
        // Filtre des contacts
        List<ContactEntity> listContactUser = contactManager.findContactByUser(userEntity);
        for (UserEntity user : temp1) {
            for (ContactEntity cont : listContactUser) {
                if (!cont.getMe().getId().equals(user.getId()) && !cont.getMyContact().getId().equals(user.getId())) {
                    temp2.add(user);
                }
            }
        }
        
        // Récupération des informations de Riot à propos du User
        SummonerDTO summonerConnected = client.getSummonerBySummonerName(userEntity.getRiotAccount().getPseudo());
        LeaguePositionDTO leaguePositionSummConnected = getRankedQueueSolo5v5FromSummoner(summonerConnected);
        
        // Si l'utilisateur n'a pas de rank, il peut jouer avec des joueurs de tous lvl non ranké
        if (leaguePositionSummConnected == null) {
            // Récupération des informations de chaque User + filtre sur les rangs et lvl
            for (UserEntity userFound : temp2) {
                SummonerDTO summoner = client.getSummonerBySummonerName(userFound.getRiotAccount().getPseudo());
                List<LeaguePositionDTO> listLeagueDto = client
                                .getLeaguePositionInAllQueuesBySummonerId(String.valueOf(summoner.getId()));
                
                // Si l'utilisateur n'a pas de league, c'est qu'il n'est pas ranké
                if (listLeagueDto.isEmpty()) {
                    listUserFound.add(userFound);
                }
            }
        } else {
            // Récupération des informations de chaque User + filtre sur les rangs
            for (UserEntity userFound : temp2) {
                SummonerDTO summoner = client.getSummonerBySummonerName(userFound.getRiotAccount().getPseudo());
                LeaguePositionDTO leaguePosSumm = getRankedQueueSolo5v5FromSummoner(summoner);
                if (leaguePosSumm != null) {
                    if (leaguePosSumm.getTier().equals(leaguePositionSummConnected.getTier())) {
                        listUserFound.add(userFound);
                    }
                    break;
                }
            }
        }

        return new ArrayList<>(listUserFound);
    }

    /**
     * Récupération de la Queue Ranked Solo 5v5 d'un Summoner
     * @param summ le SummonerDTO
     * @return une LeaguePositionDTO
     */
    private LeaguePositionDTO getRankedQueueSolo5v5FromSummoner(SummonerDTO summ) {
        List<LeaguePositionDTO> listLeagueDto = client
                        .getLeaguePositionInAllQueuesBySummonerId(String.valueOf(summ.getId()));
        for (LeaguePositionDTO league : listLeagueDto) {
            if (league.getQueueType().equals("RANKED_SOLO_5x5")) {
                return league;
            }
        }
        return null;
    }
    
    @Override
    public boolean miseAJourCompteUtilisateur(UserEntity userEntity1, UserEntity userEntity2) throws FonctionnelleException, TechniqueException {
        boolean isUpdate = false;
       
        List<UserEntity> listToCompare = null;

        
        if (!userEntity2.getUsername().equals(userEntity1.getUsername())) {
            if (StringUtils.isBlank(userEntity2.getUsername())) {
                if (userEntity2.getUsername().length() <= 20) {
                    if(!userEntity2.getUsername().contains("#\\/;,.%ù*$^")){
                        if (listToCompare == null) {
                            listToCompare = userManager.findAll();
                        }
                        boolean exist = false;
                        for(UserEntity userEntity : listToCompare) {
                            if(userEntity.getUsername().equals(userEntity2.getUsername())) {
                                exist = true;
                                break;
                            }
                        }
                        if (!exist) {
                            userEntity1.setUsername(userEntity2.getUsername());
                            isUpdate = true;
                        } else {
                            throw new FonctionnelleException("Erreur, le nom d'utilisateur n'est pas disponible.");
                        }
                    } else {
                        throw new FonctionnelleException("Erreur, le nom d'utilisateur comporte des caractères non valides.");
                    }
                } else {
                    throw new FonctionnelleException("Erreur, le nom d'utilisateur n'est pas de taille valide.");
                }
            } else {
                throw new FonctionnelleException("Erreur, le nom d'utilisateur est vide.");
            }
        }
        
        if (!userEntity2.getAdditionalInformation().equals(userEntity1.getAdditionalInformation())) {
            if (StringUtils.isBlank(userEntity2.getUsername())) {
                if (userEntity2.getUsername().length() <= 255) {
                    userEntity1.setAdditionalInformation(userEntity2.getAdditionalInformation());
                    isUpdate = true;
                } else {
                    throw new FonctionnelleException("Erreur, les informations additionnelles ne sont pas de taille valide.");
                }
            } else {
                throw new FonctionnelleException("Erreur, les informations additionnelles sont vides.");
            }
        }
        
        if (!userEntity2.getEmail().equals(userEntity1.getEmail())) {
            RegexValidator regex = new RegexValidator(RegexValidator.EMAIL_PATTERN);
            if (!regex.validateValue(userEntity2.getEmail())) {
                throw new FonctionnelleException("Erreur, l'email n'est pas conforme.");
            }
            if (listToCompare == null) {
                listToCompare = userManager.findAll();
            }
            boolean exist = false;
            for(UserEntity userEntity : listToCompare) {
                if(userEntity.getEmail().equals(userEntity2.getEmail())) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                userEntity1.setEmail(userEntity2.getEmail());
                isUpdate = true;
            } else {
              throw new FonctionnelleException("Erreur, l'email est déjà utilisé");  
            }
        }
        
        return isUpdate;
    }

    @Override
    public void miseAJourMotDePasse(long id, String password) throws TechniqueException, FonctionnelleException {
        if (!StringUtils.isBlank(password)) {
            RegexValidator regex = new RegexValidator(RegexValidator.PASSWORD_PATTERN);
            if (!regex.validateValue(password)) {
                throw new FonctionnelleException("Erreur, le mot de passe saisi n'est pas conforme.");
            }
            UserEntity user = userManager.findById(id);
            String cryptedPassword = user.getPassword();
            if (!PasswordUtils.decodeSha1(cryptedPassword, password)) {
                user.setPassword(PasswordUtils.cryptSha512(password));
            }else{
                throw new FonctionnelleException("Erreur, le mot de passe saisi est identique à votre ancien mot de passe");
            }
        } else {
            throw new FonctionnelleException("Erreur, le mot de passe saisi est vide.");
        }
    }

    @Override
    public UserEntity getProfil(long id) throws TechniqueException {
        return userManager.findById(id);
    }

}
