package fr.stl.manager;

import java.util.List;

import fr.stl.dao.DAOFactory;
import fr.stl.entity.PostEntity;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.TechniqueException;

/**
 * Classe de management de la transaction
 */
public class PostManager {
    
    /**
     * FindAll
     * @return une liste de ContactEntity
     * @throws TechniqueException erreur technique
     */
    public List<PostEntity> findAll() throws TechniqueException {
        return DAOFactory.getInstance().getPostDAO().findAll();
    }
    
    /**
     * Persiste
     * @param postEntity un PostEntity
     * @return un PostEntity
     * @throws TechniqueException erreur technique
     */
    public PostEntity persiste(PostEntity postEntity) throws TechniqueException {
        return DAOFactory.getInstance().getPostDAO().persiste(postEntity);
    }
    
    /**
     * FindPostByUser
     * @param user le user concerné
     * @return la liste des post de l'utilisateur
     * @throws TechniqueException erreur technique
     */
    public List<PostEntity> findPostByUser(UserEntity user) throws TechniqueException {
        return DAOFactory.getInstance().getPostDAO().findPostByUser(user);
    }

    /**
     * FindById
     * @param idPost l'id du post
     * @return un PostEntity
     * @throws TechniqueException erreur technique
     */
    public PostEntity findPostById(long idPost) throws TechniqueException {
        return DAOFactory.getInstance().getPostDAO().findById(idPost);
    }
    
}
