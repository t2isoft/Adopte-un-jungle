package fr.stl.dao;

import java.util.List;

import fr.stl.entity.PostEntity;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.TechniqueException;
import fr.stl.generics.GenericDAO;

/**
 * Interface PostDAO
 */
public interface PostDAO extends GenericDAO<PostEntity, Long> {

    /**
     * Find posts posted by the user in parameter
     * @param user the concerned user
     * @return posts of the user
     * @throws TechniqueException 
     */
    public List<PostEntity> findPostByUser(UserEntity user) throws TechniqueException;
    
}
