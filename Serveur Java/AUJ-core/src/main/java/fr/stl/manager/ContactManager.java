package fr.stl.manager;

import java.util.List;

import fr.stl.dao.DAOFactory;
import fr.stl.entity.ContactEntity;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.FonctionnelleException;
import fr.stl.exceptions.TechniqueException;

/**
 * Classe de management de la transaction
 */
public class ContactManager {

    /**
     * FindAll
     * @return une liste de ContactEntity
     * @throws TechniqueException erreur technique
     */
    public List<ContactEntity> findAll() throws TechniqueException {
        return DAOFactory.getInstance().getContactDAO().findAll();
    }

    /**
     * Persiste
     * @param contactEntity un ContactEntity
     * @return un ContactEntity
     * @throws TechniqueException erreur technique
     * @throws FonctionnelleException erreur fonctionnelle
     */
    public ContactEntity persiste(ContactEntity contactEntity) throws TechniqueException, FonctionnelleException {
        // Before Persister
        beforePersister(contactEntity);
        return DAOFactory.getInstance().getContactDAO().persiste(contactEntity);
    }

    /**
     * Before Persister
     * @param contactEntity le ContactEntity
     * @throws FonctionnelleException erreur fonctionnelle
     * @throws TechniqueException erreur technique
     */
    private void beforePersister(ContactEntity contactEntity) throws FonctionnelleException, TechniqueException {
        if (findCoupleOfContact(contactEntity) != null) {
            throw new FonctionnelleException("Erreur, ces deux utilisateurs sont déjà en contact.");
        }
    }

    /**
     * Récupérer un couple de contact
     * @param contactEntity
     * @return un contacEntity
     * @throws TechniqueException erreur technique
     */
    public ContactEntity findCoupleOfContact(ContactEntity contactEntity) throws TechniqueException {
        UserEntity user1 = contactEntity.getUtilisateur1();
        UserEntity user2 = contactEntity.getUtilisateur2();
        return DAOFactory.getInstance().getContactDAO().findCoupleOfContact(user1, user2);
    }

    /**
     * Récupère la liste des contacts pour un user
     * @param userEntity un UserEntity
     * @return une liste de ContactEntity
     * @throws TechniqueException erreur technique
     */
    public List<ContactEntity> findContactByUser(UserEntity userEntity) throws TechniqueException {
        return DAOFactory.getInstance().getContactDAO().findContactByUser(userEntity);
    }

}
