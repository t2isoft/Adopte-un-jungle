package fr.stl.services.impl;

import java.util.List;

import fr.stl.entity.ContactEntity;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.TechniqueException;
import fr.stl.manager.ContactManager;
import fr.stl.manager.UserManager;
import fr.stl.services.ContactService;

/**
 * Implémentation de l'interface ContactService
 */
public class ContactServiceImpl implements ContactService {

    @Override
    public List<ContactEntity> findContactForUser(Long idUser) throws TechniqueException {
        // Récupération du User concerné
        UserManager userManager = new UserManager();
        UserEntity userEntity = userManager.findById(idUser);
        if (userEntity == null) {
            throw new TechniqueException("Erreur, l'utilisateur n'est pas connecté.");
        }
        
        // Récupération des contacts
        ContactManager contactManager = new ContactManager();
        return contactManager.findContactByUser(userEntity);
    }

}
