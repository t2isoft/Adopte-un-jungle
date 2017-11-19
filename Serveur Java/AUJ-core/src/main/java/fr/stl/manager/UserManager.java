package fr.stl.manager;

import java.util.List;

import fr.stl.dao.DAOFactory;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.FonctionnelleException;
import fr.stl.exceptions.TechniqueException;

/**
 * Classe de management de la transaction
 */
public class UserManager {

    /**
     * FindAll
     * @return une liste de UserEntity
     * @throws TechniqueException erreur technique
     */
    public List<UserEntity> findAll() throws TechniqueException {
        return DAOFactory.getInstance().getUserDAO().findAll();
    }

    /**
     * FindByLogin
     * @param login le login
     * @return un UserEntity
     * @throws TechniqueException erreur technique
     */
    public UserEntity findByLogin(String login) throws TechniqueException {
        return DAOFactory.getInstance().getUserDAO().findByLogin(login);
    }

    /**
     * Persiste
     * @param userEntity un UserEntity
     * @return un UserEntity
     * @throws TechniqueException erreur technique
     * @throws FonctionnelleException erreur fonctionnelle
     */
    public UserEntity persiste(UserEntity userEntity) throws TechniqueException, FonctionnelleException {
        // Before Persister
        beforePersister(userEntity);
        return DAOFactory.getInstance().getUserDAO().persiste(userEntity);
    }

    /**
     * Before persister un UserEntity
     * @param userEntity le User
     * @throws FonctionnelleException erreur fonctionnelle
     * @throws TechniqueException erreur technique
     */
    private void beforePersister(UserEntity userEntity) throws FonctionnelleException, TechniqueException {
        UserEntity userFound = findByLoginOrByEmail(userEntity.getUsername(), userEntity.getEmail());
        if (userFound != null) {
            if (userFound.getEmail().equalsIgnoreCase(userEntity.getEmail())) {
                throw new FonctionnelleException("Erreur, un utilisateur utilise déjà cette email.");
            }
            if (userFound.getUsername().equalsIgnoreCase(userEntity.getUsername())) {
                throw new FonctionnelleException("Erreur, un utilisateur utilise déjà ce nom d'utilisateur.");
            }
        }
    }

    /**
     * Récupération des Utilisateur avec le même username ou email
     * @param username le nom d'utilisateur
     * @param email l'email
     * @return l'utilisateur trouvé
     * @throws TechniqueException erreur technique
     */
    public UserEntity findByLoginOrByEmail(String username, String email) throws TechniqueException {
        return DAOFactory.getInstance().getUserDAO().findByLoginOrByEmail(username, email);
    }

    /**
     * FindById
     * @param id l'id de l'entite
     * @return un UserEntity
     * @throws TechniqueException erreur technique
     */
    public UserEntity findById(Long id) throws TechniqueException {
        return DAOFactory.getInstance().getUserDAO().findById(id);
    }
    
    /**
     * Liste des contact sans le même rôle et sans contact avec l'utilisateur
     * @param userEntity l'utilisateur
     * @return une liste de UserEntity
     * @throws TechniqueException erreur technique
     */
    public List<UserEntity> listContactWithoutSameRoleAndWithoutContactWithUser(UserEntity userEntity) throws TechniqueException {
        return DAOFactory.getInstance().getUserDAO().listContactWithoutSameRoleAndWithoutContactWithUser(userEntity);
    }
    
}
