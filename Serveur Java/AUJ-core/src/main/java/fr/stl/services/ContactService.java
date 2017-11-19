package fr.stl.services;

import java.util.List;

import fr.stl.entity.ContactEntity;
import fr.stl.exceptions.TechniqueException;

/**
 * Interface des méthodes de type ContactService
 */
public interface ContactService {

    /**
     * Find Contact For a User 
     * @param idUser l'id du User
     * @return une liste de contact
     * @throws TechniqueException erreur technique
     */
    List<ContactEntity> findContactForUser(Long idUser) throws TechniqueException;

}
