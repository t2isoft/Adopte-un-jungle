package fr.stl.dao.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.stl.AbstractTest;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.TechniqueException;
import fr.stl.manager.UserManager;

/**
 * Classe de test de la DAO User
 */
public class UserDAOTest extends AbstractTest {

    /** Manager */
    UserManager userManager = new UserManager();

    /**
     * Test de la méthode findAll cas nominal
     * @throws TechniqueException erreur technique
     */
    @Test
    public void testFindAllNominal() throws TechniqueException {
        // Appelle de la méthode à tester
        List<UserEntity> res = userManager.findAll();

        // Assertions
        Assert.assertNotNull(res);
        Assert.assertEquals(10, res.size());
    }

    /**
     * Test de la méthode findByLogin cas nominal
     * @throws TechniqueException erreur technique
     */
    @Test
    public void testFindByLoginNominal() throws TechniqueException {
        // Appelle de la méthode à tester
        UserEntity user = userManager.findByLogin("didjo972");

        // Assertions
        Assert.assertNotNull(user);
    }

    /**
     * Test de listContactWithoutSameRoleAndWithoutContactWithUser cas nominal
     * @throws TechniqueException erreur technique
     */
    @Test
    public void testListContactWithoutSameRoleAndWithoutContactWithUserNominal() throws TechniqueException {
        // Paramètres d'entrée
        UserEntity userEntity = userManager.findById(6L);

        // Appelle de la méthode à tester
        List<UserEntity> listSuggestion = userManager
                        .listContactWithoutSameRoleAndWithoutContactWithUser(userEntity);

        // Assertions
        Assert.assertNotNull(listSuggestion);
        Assert.assertEquals(8, listSuggestion.size());
    }

    /**
     * Test de findAllWithPostNominal
     * @throws TechniqueException erreur technique
     */
    @Test
    public void testFindAllWithPostNominal() throws TechniqueException {
        // Appelle de la méthode à tester
        List<UserEntity> res = userManager.findAll();

        // Assertions
        Assert.assertNotNull(res);
        Assert.assertEquals(10, res.size());
        for (UserEntity user : res) {
            if (user.getPosts() != null && user.getPosts().size() > 0) {
                return;
            }
        }
        Assert.fail();
    }

}
