package fr.stl.dao;

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
    
}
