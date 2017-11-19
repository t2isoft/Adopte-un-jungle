package fr.stl.dao;

import java.util.List;

import fr.stl.entity.ContactEntity;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.TechniqueException;
import fr.stl.generics.GenericDAO;

/**
 * Interface ContactDAO
 */
public interface ContactDAO extends GenericDAO<ContactEntity, Long> {

    /**
     * FindCoupleOfContact
     * @param user1 utilisateur1
     * @param user2 utilisateur2
     * @return un ContactEntity
     * @throws TechniqueException erreur technique
     */
    ContactEntity findCoupleOfContact(UserEntity user1, UserEntity user2) throws TechniqueException;

    /**
     * Récupère la liste des contacts pour un user
     * @param userEntity un UserEntity
     * @return une liste de ContactEntity
     * @throws TechniqueException erreur technique
     */
    List<ContactEntity> findContactByUser(UserEntity userEntity) throws TechniqueException;

}
