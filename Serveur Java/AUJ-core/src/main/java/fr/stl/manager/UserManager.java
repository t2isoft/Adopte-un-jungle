package fr.stl.manager;

import java.util.List;

import fr.stl.dao.DAOFactory;
import fr.stl.entity.UserEntity;
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
     */
    public UserEntity persiste(UserEntity userEntity) throws TechniqueException {
        return DAOFactory.getInstance().getUserDAO().persiste(userEntity);
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
    
}
