package fr.stl.generics;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.stl.exceptions.TechniqueException;

/**
 * Classe abstraite généric des Objets de type DAO
 * @param <T> la Classe
 * @param <ID> l'ID en base
 */
public abstract class GenericHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

    /** Le logger */
    private Logger logger = Logger.getLogger(getClass());

    /**
     * La classe persistente
     */
    private Class<T> persistentClass;

    /**
     * La session
     */
    private Session session;

    /**
     * Constructeur
     */
    @SuppressWarnings("unchecked")
    public GenericHibernateDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    /**
     * @param s la Session
     */
    public void setSession(Session s) {
        this.session = s;
    }

    /**
     * Retourne la Session
     * @return la Session
     */
    protected Session getSession() {
        if (session == null)
            throw new IllegalStateException("Session has not been set on DAO before usage");
        return session;
    }

    /**
     * Retourne la classe persistente
     * @return la Classe <T>
     */
    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(ID id) throws TechniqueException {
        Transaction tx = null;
        T entity = null;
        try {
            tx = getSession().beginTransaction();
            entity = (T) getSession().get(getPersistentClass(), id);
            tx.commit();
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (Exception e1) {
                logger.error("Couldn't roll back transaction", e1);
            }
            throw new TechniqueException(e);
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() throws TechniqueException {
        Transaction tx = null;
        List<T> listUser = null;
        try {
            tx = getSession().beginTransaction();
            Criteria crit = getSession().createCriteria(getPersistentClass());
            listUser = crit.list();
            tx.commit();
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (Exception e1) {
                logger.error("Couldn't roll back transaction", e1);
            }
            throw new TechniqueException(e);
        }
        return listUser;
    }

    @Override
    public T persiste(T entity) throws TechniqueException {
        Transaction tx = null;
        try {
            tx = getSession().beginTransaction();
            getSession().saveOrUpdate(entity);
            tx.commit();
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (Exception e1) {
                logger.error("Couldn't roll back transaction", e1);
            }
            throw new TechniqueException(e);
        }
        return entity;
    }

    // /**
    // * Classe abstraite
    // * @return
    // */
    // public abstract T beforePersite();

    /**
     * Flush
     */
    // public void flush() {
    // getSession().flush();
    // }

    /**
     * Clear
     */
    // public void clear() {
    // getSession().clear();
    // }

}
