package fr.stl.dao;

import java.util.List;

import org.hibernate.exception.DataException;

import fr.stl.entity.UserEntity;
import fr.stl.exceptions.TechniqueException;
import fr.stl.generics.GenericDAO;

/**
 * Interface UserDAO
 */
public interface UserDAO extends GenericDAO<UserEntity, Long> {

	/**
	 * Retourne un user par son nom d'utilisateur
	 * @param login le login
	 * @return un UserEntity
	 * @throws DataException exception d'accès à la base
	 * @throws TechniqueException erreur technique
	 */
    public UserEntity findByLogin(String login) throws DataException, TechniqueException;

    /**
     * Retourne un utilisateur avec le même username ou email
     * @param username le username
     * @param email l'email
     * @return un UserEntity
     * @throws TechniqueException erreur technique
     */
    public UserEntity findByLoginOrByEmail(String username, String email) throws TechniqueException;
   
    /**
     * Liste des contact sans le même rôle et sans contact avec l'utilisateur
     * @param userEntity l'utilisateur
     * @return une liste de UserEntity
     * @throws TechniqueException erreur technique
     */
    public List<UserEntity> listContactWithoutSameRoleAndWithoutContactWithUser(UserEntity userEntity) throws TechniqueException;
}
