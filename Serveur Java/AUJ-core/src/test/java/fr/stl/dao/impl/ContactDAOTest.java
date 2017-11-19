package fr.stl.dao.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.stl.AbstractTest;
import fr.stl.entity.ContactEntity;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.FonctionnelleException;
import fr.stl.exceptions.TechniqueException;
import fr.stl.manager.ContactManager;
import fr.stl.manager.UserManager;

/**
 * Classe de test de la DAO Contact
 */
public class ContactDAOTest extends AbstractTest {
    
    /** Manager de Contact */
    ContactManager contactManager = new ContactManager();
    
    /** Manager de User */
    UserManager userManager = new UserManager();

    /**
     * Test de persiste cas nominal
     * @throws TechniqueException erreur technique
     * @throws FonctionnelleException erreur fonctionnelle
     */
    @Test
    public void testPersisteNominal() throws TechniqueException, FonctionnelleException {
        // Paramètres d'entrée
        UserEntity me = userManager.findById(2L);
        UserEntity myContact = userManager.findById(3L);
        
        if (me == null || myContact == null) {
            Assert.fail("Les utilisateurs n'ont pas été récupéré.");
        }
        
        // Paramètres d'entrées
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setMe(me);
        contactEntity.setMyContact(myContact);
        contactEntity.setStatus(0);
        
        // Appelle de la méthode à tester
        contactEntity = contactManager.persiste(contactEntity);
        
        // Assertions
        Assert.assertNotNull(contactEntity);
        Assert.assertNotNull(contactEntity.getId());
    }
    
    /**
     * Test de persiste cas Contact déjà fait
     * @throws TechniqueException erreur technique
     * @throws FonctionnelleException erreur fonctionnelle
     */
    @Test(expected=FonctionnelleException.class)
    public void testPersisteContactAlreadyDid() throws TechniqueException, FonctionnelleException {
        // Paramètres d'entrée
        UserEntity me = userManager.findById(4L);
        UserEntity myContact = userManager.findById(5L);
        
        if (me == null || myContact == null) {
            Assert.fail("Les utilisateurs n'ont pas été récupéré.");
        }
        
        // Paramètres d'entrées
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setMe(me);
        contactEntity.setMyContact(myContact);
        contactEntity.setStatus(0);
        
        // Appelle de la méthode à tester
        contactManager.persiste(contactEntity);
        
        // Assertion
        Assert.fail("Erreur, il y aurait du y avoir une exception");
    }
    
    /**
     * Test de findAll cas nominal
     * @throws TechniqueException erreur technique
     */
    @Test
    public void testFindAllNominal() throws TechniqueException {
     // Appelle de la méthode à tester
        List<ContactEntity> res = contactManager.findAll();
        
        // Assertions
        Assert.assertNotNull(res);
        Assert.assertTrue(res.size() > 0);
    }
    
    /**
     * Test de findContactByUser cas nominal
     * @throws TechniqueException erreur technique
     */
    @Test
    public void testFindContactByUserNominal() throws TechniqueException {
        // Paramètres d'entrées
        UserEntity userEntity = userManager.findById(4L);
        // Appelle de la méthode à tester
        List<ContactEntity> listContact = contactManager.findContactByUser(userEntity);
        
        // Assertions
        Assert.assertNotNull(listContact);
        Assert.assertEquals(4, listContact.size());
    }
    
}
