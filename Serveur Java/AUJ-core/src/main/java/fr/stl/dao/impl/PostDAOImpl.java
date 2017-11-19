package fr.stl.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fr.stl.dao.PostDAO;
import fr.stl.entity.PostEntity;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.TechniqueException;
import fr.stl.generics.GenericHibernateDAO;

/**
 * Implémentation de la DAO Post
 */
public class PostDAOImpl extends GenericHibernateDAO<PostEntity, Long> implements PostDAO {
    
    /** Le logger */
    private Logger logger = Logger.getLogger(getClass());

    @SuppressWarnings("unchecked")
    @Override
    public List<PostEntity> findPostByUser(UserEntity user) throws TechniqueException {
        Transaction tx = null;
        List<PostEntity> listPost = null;
        
        try {

            tx = getSession().beginTransaction();
            Criteria crit = getSession().createCriteria(getPersistentClass());
            crit.add(Restrictions.eq("poster", user));

            listPost = crit.list();
            tx.commit();
        
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (Exception e1) {
                logger.error("Couldn’t roll back transaction", e1);
            }
            throw new TechniqueException(e);
        }
        
        return listPost;
    }
    
    
    
}
