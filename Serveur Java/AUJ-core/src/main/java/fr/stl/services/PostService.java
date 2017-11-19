package fr.stl.services;

import java.util.List;

import fr.stl.entity.PostEntity;
import fr.stl.exceptions.FonctionnelleException;
import fr.stl.exceptions.TechniqueException;

/**
 * Interface des méthodes de type PostService
 */
public interface PostService {

    /**
     * Créer un nouveau post lié à un User
     * @param message le message
     * @param idUser l'id du User
     * @throws TechniqueException erreur technique
     */
    void nouveauPost(String message, long idUser) throws TechniqueException;

    /**
     * Retourne les posts de l'utilisateur et ceux de ses contacts
     * @param idUser l'id du User
     * @return les posts
     * @throws TechniqueException erreur technique
     */
    List<PostEntity> tousLesPosts(long idUser) throws TechniqueException;

    /**
     * Retourne les posts de l'utilisateur
     * @param idUser l'id du User
     * @return les posts
     * @throws TechniqueException
     */
    List<PostEntity> mesPosts(long idUser) throws TechniqueException;

    /**
     * Met à jour le message de la publication d'un User
     * @param idUser l'id du User
     * @param idPost l'id de la publication
     * @param messageUpdated le message à modifier
     * @throws TechniqueException erreur technique
     * @throws FonctionnelleException erreur fonctionnelle
     */
    void updatePostUser(long idUser, long idPost, String messageUpdated)
                    throws TechniqueException, FonctionnelleException;

}
