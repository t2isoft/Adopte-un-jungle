package fr.stl.services.impl;

import java.util.List;

import fr.stl.entity.ContactEntity;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.FonctionnelleException;
import fr.stl.exceptions.TechniqueException;
import fr.stl.manager.ContactManager;
import fr.stl.manager.UserManager;
import fr.stl.services.ContactService;

/**
 * Implémentation de l'interface ContactService
 */
public class ContactServiceImpl implements ContactService {
    
    /** UserManager */
    private UserManager userManager = new UserManager();
    
    /** ContactManager */
    private ContactManager contactManager = new ContactManager();

    @Override
    public List<ContactEntity> findContactForUser(Long idUser) throws TechniqueException {
        // Récupération du User concerné
        UserEntity userEntity = userManager.findById(idUser);
        if (userEntity == null) {
            throw new TechniqueException("Erreur, l'utilisateur n'est pas connecté.");
        }
        
        // Récupération des contacts
        return contactManager.findContactByUser(userEntity);
    }

    @Override
    public void ajouterUnContact(Long idUser, Long idContact) throws TechniqueException, FonctionnelleException {
        // Récupération de l'utilisateur concerné
        UserEntity concernedUser = userManager.findById(idUser);
        if (concernedUser == null) {
            throw new TechniqueException("Erreur, l'utilisateur n'existe pas.");
        }
        // Récupération de l'entité utilisateur du contact concerné
        UserEntity newContact = userManager.findById(idContact);
        if (newContact == null) {
            throw new TechniqueException("Erreur, l'utilisateur n'existe pas.");
        }
        // Creation du contact
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setMe(concernedUser);
        contactEntity.setMyContact(newContact);
        contactEntity.setStatus(0);
        // Status mis à zéro car il faut que le contact accepte aussi de son coté
        contactManager.persiste(contactEntity);
    }

    @Override
    public void accepterUnContact(Long idUser, Long idContact) throws TechniqueException{
        // Récupération de l'utilisateur concerné
        UserEntity user = userManager.findById(idUser);
        if (user == null)
            throw new TechniqueException("Erreur, l'utilisateur n'existe pas.");
        
        // Récupération de l'entité utilisateur du contact concerné
        UserEntity userContact = userManager.findById(idContact);
        if (userContact == null)
            throw new TechniqueException("Erreur, l'utilisateur n'existe pas.");
        
        List<ContactEntity> contacts = contactManager.findContactByCoupleOfUser(user, userContact);
        if (null != contacts && !contacts.isEmpty()) {
            ContactEntity contact = contacts.get(0);
            if (null == contact)
                throw new TechniqueException("Erreur, le contact n'existe pas.");
            // Verifier que le statut est à 0
            if (0 == contact.getStatus()) {
                contact.setStatus(1);
            } else {
                throw new TechniqueException("Erreur, les utilisateur sont déjà en contact");
            }
        } else {
          throw new TechniqueException("Erreur, le contact n'existe pas.");
        }
    }

    @Override
    public void supprimerUnContact(Long idUser, Long idContact) throws TechniqueException, FonctionnelleException {
     // Récupération de l'utilisateur concerné
        UserEntity user = userManager.findById(idUser);
        if (user == null)
            throw new TechniqueException("Erreur, l'utilisateur n'existe pas.");
        
        // Récupération de l'entité utilisateur du contact concerné
        UserEntity userContact = userManager.findById(idContact);
        if (userContact == null)
            throw new TechniqueException("Erreur, l'utilisateur n'existe pas.");
        
        List<ContactEntity> contacts = contactManager.findContactByCoupleOfUser(user, userContact);
        if (null != contacts && !contacts.isEmpty()) {
            ContactEntity contact = contacts.get(0);
            if (null == contact)
                throw new TechniqueException("Erreur, le contact n'existe pas.");
            // Verifier que le statut est à 0
            if (1 == contact.getStatus()) {
                //TODO : manager pour supprimer !
            } else {
                throw new TechniqueException("Erreur, les utilisateur sont déjà en contact");
            }
        } else {
          throw new TechniqueException("Erreur, le contact n'existe pas.");
        }
    }

}
