package fr.stl.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fr.stl.dao.ContactDAO;
import fr.stl.entity.ContactEntity;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.TechniqueException;
import fr.stl.generics.GenericHibernateDAO;

/**
 * Implémentation de la DAO Contact
 */
public class ContactDAOImpl extends GenericHibernateDAO<ContactEntity, Long> implements ContactDAO {

    /** Le logger */
    private Logger logger = Logger.getLogger(getClass());

    @Override
    public ContactEntity findCoupleOfContact(UserEntity user1, UserEntity user2) throws TechniqueException {
        Transaction tx = null;
        ContactEntity contact = null;
        try {
            tx = getSession().beginTransaction();
            Criteria crit = getSession().createCriteria(getPersistentClass());
            crit.add(Restrictions.eq("me", user1)).add(Restrictions.eq("myContact", user2));
            contact = (ContactEntity) crit.uniqueResult();
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
        return contact;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ContactEntity> findContactByUser(UserEntity userEntity) throws TechniqueException {
        Transaction tx = null;
        List<ContactEntity> listContact = null;
        try {
            tx = getSession().beginTransaction();
            Criteria crit = getSession().createCriteria(getPersistentClass());
            crit.add(Restrictions.or(Restrictions.eq("me", userEntity),
                            Restrictions.eq("myContact", userEntity)));
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

    @SuppressWarnings("unchecked")
    @Override
    public List<ContactEntity> findContactByCoupleOfUser(UserEntity userEntity1, UserEntity userEntity2) throws TechniqueException {
        Transaction tx = null;
        List<ContactEntity> listContact = null;
        try {
            tx = getSession().beginTransaction();
            Criteria crit = getSession().createCriteria(getPersistentClass());
            crit.add(Restrictions.or(Restrictions.and(Restrictions.eq("me", userEntity1),Restrictions.eq("myContact", userEntity2),
                            Restrictions.and(Restrictions.eq("me", userEntity2),Restrictions.eq("myContact", userEntity2)))));
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
