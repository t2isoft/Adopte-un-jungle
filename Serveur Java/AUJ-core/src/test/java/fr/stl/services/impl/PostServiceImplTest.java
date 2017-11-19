package fr.stl.services.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.stl.AbstractTest;
import fr.stl.dao.DAOFactory;
import fr.stl.entity.PostEntity;
import fr.stl.exceptions.FonctionnelleException;
import fr.stl.exceptions.TechniqueException;
import fr.stl.services.AUJServiceFunctionnalFactory;

/**
 * Classe de test du Service PostServiceImpl
 */
public class PostServiceImplTest extends AbstractTest {

    /**
     * Test de nouveauPost cas nominal
     * @throws TechniqueException erreur technique
     */
    @Test
    public void testNouveauPostNominal() throws TechniqueException {
        // Paramètres d'entrées
        Long idUser = 1L;

        // Appelle de la méthode à tester
        AUJServiceFunctionnalFactory.getInstance().getPostService().nouveauPost("Voici mon nouveau post", idUser);
    }

    /**
     * Test de tous les posts cas nominal
     * @throws TechniqueException erreur technique
     */
    @Test
    public void testTousMesPostNominal() throws TechniqueException {
        // Paramètres d'entrées
        // Long idUser = 1L;

        // Appelle de la méthode à tester
        List<PostEntity> mesPosts = AUJServiceFunctionnalFactory.getInstance().getPostService().tousLesPosts(1L);

        Assert.assertNotNull(mesPosts);
        Assert.assertTrue(!mesPosts.isEmpty());
        // Assert.assertTrue(mesPosts.size() == 3);
    }

    /**
     * Test de updatePostUser cas nominal
     * @throws FonctionnelleException erreur fonctionnelle
     * @throws TechniqueException erreur technique
     */
    @Test
    public void updatePostUserNominal() throws TechniqueException, FonctionnelleException {
        // Paramètres d'entrées
        Long idUser = 1L;
        Long idPost = 2L;
        String messageUpdated = "Mon message est mis à jour";

        // Appelle de la méthode à tester
        AUJServiceFunctionnalFactory.getInstance().getPostService().updatePostUser(idUser, idPost, messageUpdated);

        // Récupération du message mis à jour
        PostEntity post = DAOFactory.getInstance().getPostDAO().findById(idPost);

        // Assertions
        Assert.assertNotNull(post);
        Assert.assertEquals(messageUpdated, post.getContent());
    }

}
