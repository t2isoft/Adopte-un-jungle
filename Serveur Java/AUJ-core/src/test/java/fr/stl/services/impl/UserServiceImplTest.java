package fr.stl.services.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.stl.AbstractTest;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.AuthentificationException;
import fr.stl.exceptions.FonctionnelleException;
import fr.stl.exceptions.TechniqueException;
import fr.stl.services.AUJServiceFunctionnalFactory;

/**
 * Classe de test du Service UserServiceImpl
 */
public class UserServiceImplTest extends AbstractTest {

    /**
     * Test de creerCompteUtilisateur cas nominal
     * @throws TechniqueException erreur technique
     * @throws FonctionnelleException erreur fonctionnelle
     */
    @Test
    public void testCreerCompteUtilisateurNominal() throws FonctionnelleException, TechniqueException {
        AUJServiceFunctionnalFactory.getInstance().getUserService().creerCompteUtilisateur("didjo972@gmail.com",
                        "Abcde/#", "darkamerica", "didjo972", "ADC");
    }
    
    /**
     * Test de authenticate cas nominal
     * @throws TechniqueException erreur technique
     * @throws FonctionnelleException erreur fonctionnelle
     * @throws AuthentificationException erreur authentification
     */
    @Test
    public void testAuthenticateNominal() throws FonctionnelleException, TechniqueException, AuthentificationException {
        // Paramètres d'entrées
        String username = "UserToAuthenticate";
        String password = "Abcde/#";
        
        AUJServiceFunctionnalFactory.getInstance().getUserService().creerCompteUtilisateur(username+"@gmail.com",
                        password, username, "didjo972", "ADC");
        
        // Appelle de la méthode à tester
        UserEntity userAuth = AUJServiceFunctionnalFactory.getInstance().getUserService().authenticate(username, password);
    
        // Assertions
        Assert.assertNotNull(userAuth);
    }
    
    /**
     * Test de getSuggestionNominal
     * @throws TechniqueException erreur technique
     */
    @Test
    public void testGetSuggestionNominal() throws TechniqueException {
        // Parametres d'entrée
        Long idUser = 1L;
        
        // Appelle de la méthode à tester
        List<UserEntity> listUserEntity = AUJServiceFunctionnalFactory.getInstance().getUserService().getSuggestion(idUser);
        
        // Assertions
        Assert.assertNotNull(listUserEntity);
        Assert.assertEquals(3, listUserEntity.size());
    }
    
    /**
     * Test de mise à jour du Compte utilisateur
     * @throws FonctionnelleException erreur fonctionnelle
     * @throws TechniqueException erreur technique
     */
    @Test
    public void testMiseAJourDuCompteNominal() throws TechniqueException, FonctionnelleException{
        
//        UserManager userManager = new UserManager();
//        
//        // FIXME Doit on mettre à jour le nom d'invocateur ?
//        AUJServiceFunctionnalFactory.getInstance().getUserService().creerCompteUtilisateur("matt.charpy@gmail.com",
//                        "Bcde/#", "matthieu", "mattCharpy", "SUPPORT");
//        AUJServiceFunctionnalFactory.getInstance().getUserService().creerCompteUtilisateur("alizee.charpy@gmail.com",
//                        "Cde/#", "alizee", "alizeeCharpy", "MID");
//        
//        // Profils to update
//        UserEntity me = userManager.findByLogin("thibo");
//        Assert.assertNotNull(me);
//        
//        // Profils with new data
//        UserEntity newUser = new UserEntity();
//        String newUsername = "newUsername";
//        String newMail = "newMail@gmail.com";
//        String newAddInfo = "Mes nouvelles informations additionnelles !";
//        newUser.setUsername(newUsername);
//        newUser.setEmail(newMail);
//        newUser.setAdditionalInformation(newAddInfo);
//        
//        newUser.setUsername("matthieu");
//        
//        try {
//            // Test username which already exist
//            AUJServiceFunctionnalFactory.getInstance().getUserService().miseAJourCompteUtilisateur(me, newUser);
//            Assert.assertFalse(true);
//        }catch(FonctionnelleException fe) {
//            Assert.assertTrue(true);
//        }
//        
//        newUser.setUsername("");
//        
//        try {
//            // Test empty username
//            AUJServiceFunctionnalFactory.getInstance().getUserService().miseAJourCompteUtilisateur(me, newUser);
//            Assert.assertFalse(true);
//        }catch(FonctionnelleException fe) {
//            Assert.assertTrue(true);
//        }
//        
//        newUser.setUsername("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        
//        try {
//            // Test username which is too long
//            AUJServiceFunctionnalFactory.getInstance().getUserService().miseAJourCompteUtilisateur(me, newUser);
//            Assert.assertFalse(true);
//        }catch(FonctionnelleException fe) {
//            Assert.assertTrue(true);
//        }
//        
//        newUser.setUsername(newUsername);
//        newUser.setEmail("");
//        
//        try {
//            // Test empty email
//            AUJServiceFunctionnalFactory.getInstance().getUserService().miseAJourCompteUtilisateur(me, newUser);
//            Assert.assertFalse(true);
//        }catch(FonctionnelleException fe) {
//            Assert.assertTrue(true);
//        }
//        
//        newUser.setEmail("alizee.charpy@gmail.com");
//        
//        try {
//            // Test email which already exist
//            AUJServiceFunctionnalFactory.getInstance().getUserService().miseAJourCompteUtilisateur(me, newUser);
//            Assert.assertFalse(true);
//        }catch(FonctionnelleException fe) {
//            Assert.assertTrue(true);
//        }
//        
//        newUser.setEmail("alizee.charpy.gmail.com");
//        
//        try {
//            // Test invalid email
//            AUJServiceFunctionnalFactory.getInstance().getUserService().miseAJourCompteUtilisateur(me, newUser);
//            Assert.assertFalse(true);
//        }catch(FonctionnelleException fe) {
//            Assert.assertTrue(true);
//        }
//        
//        newUser.setEmail(newMail);
//        newUser.setAdditionalInformation("");
//        
//        try {
//            // Test empty additional information
//            AUJServiceFunctionnalFactory.getInstance().getUserService().miseAJourCompteUtilisateur(me, newUser);
//            Assert.assertFalse(true);
//        }catch(FonctionnelleException fe) {
//            Assert.assertTrue(true);
//        }
//        
//        newUser.setAdditionalInformation("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//
//        try {
//            // Test additional information which is too long
//            AUJServiceFunctionnalFactory.getInstance().getUserService().miseAJourCompteUtilisateur(me, newUser);
//            Assert.assertFalse(true);
//        }catch(FonctionnelleException fe) {
//            Assert.assertTrue(true);
//        }
//        
//        newUser.setAdditionalInformation(newAddInfo);
//        AUJServiceFunctionnalFactory.getInstance().getUserService().miseAJourCompteUtilisateur(me, newUser);
//        Assert.assertNotNull(me);
//        Assert.assertTrue(newUsername.equals(me.getUsername()));
//        Assert.assertTrue(newMail.equals(me.getEmail()));
//        Assert.assertTrue(newAddInfo.equals(me.getAdditionalInformation()));
        
    }

}
