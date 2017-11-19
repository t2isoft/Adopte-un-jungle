package fr.stl.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fr.stl.dao.UserDAO;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.TechniqueException;
import fr.stl.generics.GenericHibernateDAO;

/**
 * Implémentation de la DAO User
 */
public class UserDAOImpl extends GenericHibernateDAO<UserEntity, Long> implements UserDAO {

    /** Le logger */
     private Logger logger = Logger.getLogger(getClass());

    @Override
    public UserEntity findByLogin(String login) throws TechniqueException {
        Transaction tx = null;
        UserEntity user = null;
        try {
            tx = getSession().beginTransaction();
            Criteria crit = getSession().createCriteria(getPersistentClass());
            crit.add(Restrictions.eq("username", login));
            user = (UserEntity) crit.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (Exception e1) {
                logger.error("Couldn’t roll back transaction", e1);
            }
            throw new TechniqueException(e);
        }
        return user;
    }

}
