package fr.stl.services.impl;

import org.junit.Assert;
import org.junit.Test;

import fr.stl.entity.UserEntity;
import fr.stl.exceptions.AuthentificationException;
import fr.stl.exceptions.FonctionnelleException;
import fr.stl.exceptions.TechniqueException;
import fr.stl.services.AUJServiceFunctionnalFactory;

/**
 * Classe de test du Service UserServiceImpl
 */
public class UserServiceImplTest {

    /**
     * Test de creerCompteUtilisateur cas nominal
     * @throws TechniqueException erreur technique
     * @throws FonctionnelleException erreur fonctionnelle
     */
    @Test
    public void testCreerCompteUtilisateurNominal() throws FonctionnelleException, TechniqueException {
        AUJServiceFunctionnalFactory.getInstance().getUserService().creerCompteUtilisateur("didjo972@gmail.com",
                        "123456789", "darkamerica", "didjo972", "ADC", "EUW1");
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
        String password = "123456789";
        
        AUJServiceFunctionnalFactory.getInstance().getUserService().creerCompteUtilisateur(username+"@gmail.com",
                        password, username, "didjo972", "ADC", "EUW1");
        
        // Appelle de la méthode à tester
        UserEntity userAuth = AUJServiceFunctionnalFactory.getInstance().getUserService().authenticate(username, password);
    
        // Assertions
        Assert.assertNotNull(userAuth);
    }

}
