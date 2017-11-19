package fr.stl.services.impl;

import fr.stl.client.ClientGet;
import fr.stl.dto.SummonerDTO;
import fr.stl.entity.RiotAccountEntity;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.AuthentificationException;
import fr.stl.exceptions.FonctionnelleException;
import fr.stl.exceptions.TechniqueException;
import fr.stl.manager.RiotAccountManager;
import fr.stl.manager.UserManager;
import fr.stl.services.UserService;
import fr.stl.util.PasswordUtils;

/**
 * Implémentation de l'interface UserService
 */
public class UserServiceImpl implements UserService {

    @Override
    public UserEntity authenticate(String login, String password) throws TechniqueException, AuthentificationException {
        UserManager manager = new UserManager();
        
        // Récupération du User
        UserEntity userEntity = manager.findByLogin(login);
        
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
                    String role, String server) throws FonctionnelleException, TechniqueException {
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
        riotAccount.setServeur(server);
        RiotAccountManager riotAccountManager = new RiotAccountManager();
        riotAccount = riotAccountManager.persiste(riotAccount);
        
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(PasswordUtils.cryptSha1(password));
        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setRiotAccount(riotAccount);
        UserManager userManager = new UserManager();
        userManager.persiste(userEntity);
        
    }

}
