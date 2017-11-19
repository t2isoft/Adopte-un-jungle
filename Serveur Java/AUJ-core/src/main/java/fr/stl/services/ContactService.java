package fr.stl.services;

import java.util.List;

import fr.stl.entity.ContactEntity;
import fr.stl.exceptions.FonctionnelleException;
import fr.stl.exceptions.TechniqueException;

/**
 * Interface des méthodes de type ContactService
 */
public interface ContactService {

    /**
     * trouve les contacts d'un utilisateur
     * @param idUser l'id du User
     * @return une liste de contact
     * @throws TechniqueException erreur technique
     */
    List<ContactEntity> findContactForUser(Long idUser) throws TechniqueException;

    /**
     * Ajoute un contact à un utilisateur
     * @param idUser id de l'utilisateur
     * @param idContact id du contact
     * @throws TechniqueException erreur technique
     * @throws FonctionnelleException erreur fonctionnelle
     */
    void ajouterUnContact(Long idUser, Long idContact) throws TechniqueException, FonctionnelleException;
    
    /**
     * Accepte un contact demandé par le contact
     * @param idUser
     * @param idContact
     * @throws TechniqueException
     * @throws FonctionnelleException
     */
    void accepterUnContact(Long idUser, Long idContact) throws TechniqueException, FonctionnelleException;
    
    /**
     * Supprime un contact
     * @param idUser
     * @param idContact
     * @throws TechniqueException
     * @throws FonctionnelleException
     */
    void supprimerUnContact(Long idUser, Long idContact) throws TechniqueException, FonctionnelleException;

}
