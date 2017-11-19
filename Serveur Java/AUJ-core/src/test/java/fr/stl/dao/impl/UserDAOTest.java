package fr.stl.dao.impl;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.stl.entity.UserEntity;
import fr.stl.exceptions.TechniqueException;
import fr.stl.manager.UserManager;

/**
 * Classe de test de la DAO User
 */
public class UserDAOTest {
    
    /** Manager */
    UserManager manager = new UserManager();
    
    /**
     * Test de la méthode findAll cas nominal
     * @throws TechniqueException erreur technique
     */
    @Test
    public void testFindAllNominal() throws TechniqueException {
        // Appelle de la méthode à tester
        List<UserEntity> res = manager.findAll();
        
        // Assertions
        Assert.assertNotNull(res);
        Assert.assertEquals(7, res.size());
    }
    
    /**
     * Test de la méthode findByLogin cas nominal
     * @throws TechniqueException erreur technique
     */
    @Test
    public void testFindByLoginNominal() throws TechniqueException {
        // Appelle de la méthode à tester
        UserEntity user = manager.findByLogin("didjo972");
        
        // Assertions
        Assert.assertNotNull(user);
    }
}
