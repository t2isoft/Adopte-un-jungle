package fr.stl.dao.impl;

import java.util.List;

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
public class UserDAOImpl extends GenericHibernateDAO<UserEntity, Long>implements UserDAO {

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
                if (tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception e1) {
                logger.error("Couldn’t roll back transaction", e1);
            }
            throw new TechniqueException(e);
        }
        return user;
    }

    @Override
    public UserEntity findByLoginOrByEmail(String username, String email) throws TechniqueException {
        Transaction tx = null;
        UserEntity user = null;
        try {
            tx = getSession().beginTransaction();
            Criteria crit = getSession().createCriteria(getPersistentClass());
            crit.add(Restrictions.or(Restrictions.eq("username", username), Restrictions.eq("email", email)));
            user = (UserEntity) crit.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            try {
                if (tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception e1) {
                logger.error("Couldn’t roll back transaction", e1);
            }
            throw new TechniqueException(e);
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserEntity> listContactWithoutSameRoleAndWithoutContactWithUser(UserEntity userEntity)
                    throws TechniqueException {
        Transaction tx = null;
        List<UserEntity> listContact = null;
        try {
            tx = getSession().beginTransaction();
            Criteria crit = getSession().createCriteria(getPersistentClass());
            crit.createAlias("riotAccount", "riotAccount");
            // FIXME Ajouter une Restrictions sur le fait de ne pas être ami avec le nouveau User
            // ou ne pas être en attente d'amitié
            crit.add(Restrictions.and(Restrictions.not(Restrictions.eq("id", userEntity.getId())), Restrictions
                            .not(Restrictions.eq("riotAccount.role", userEntity.getRiotAccount().getRole()))));
            listContact = crit.list();
            tx.commit();
        } catch (Exception e) {
            try {
                if (tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception e1) {
                logger.error("Couldn’t roll back transaction", e1);
            }
            throw new TechniqueException(e);
        }
        return listContact;
    }

}
