package fr.stl.dao.impl;

import java.util.List;

import org.junit.Assert;

import fr.stl.AbstractTest;
import fr.stl.entity.PostEntity;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.TechniqueException;
import fr.stl.manager.PostManager;
import fr.stl.manager.UserManager;

/**
 * Classe de test de la DAO Post
 */
public class PostDAOTest extends AbstractTest{
    
    /** Manager de Post */
    PostManager postManager = new PostManager();
    
    /** Manager de User */
    UserManager userManager = new UserManager();
    
    /**
     * Test de findAll cas nominal
     * @throws TechniqueException erreur technique
     */
    public void testFindAllNominal() throws TechniqueException{
        // Appelle de la méthode à tester
        List<PostEntity> res = postManager.findAll();
        
        // Assertions
        Assert.assertNotNull(res);
        Assert.assertTrue(res.size() > 0);
    }
    
    /**
     * Test de findPostByUser cas nominal
     * @throws TechniqueException
     */
    public void testNormalCaseNewsFeed() throws TechniqueException {
        // Récupère le contact qui correspond à mon profil
        UserEntity me = userManager.findByLogin("thibo");
        
        // Assertions
        Assert.assertNotNull(me);
        
        // Récupère les posts visibles sur le mur
        List<PostEntity> myNewsFeed = postManager.findPostByUser(me);
        
        Assert.assertNotNull(myNewsFeed);
        Assert.assertTrue(myNewsFeed.size() > 0);
    }
    
}
