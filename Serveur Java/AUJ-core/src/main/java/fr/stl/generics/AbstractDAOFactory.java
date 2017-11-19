package fr.stl.generics;

import fr.stl.dao.ContactDAO;
import fr.stl.dao.DAOFactory;
import fr.stl.dao.RiotAccountDAO;
import fr.stl.dao.UserDAO;

/**
 *Creates a standalone DAOFactory that returns unmanaged DAO
 * beans for use in any environment Hibernate has been configured
 * for. Uses HibernateUtil/SessionFactory and Hibernate context
 * propagation (CurrentSessionContext), thread-bound or transaction-bound,
 * and transaction scoped.
 */
public abstract class AbstractDAOFactory {
    
    /**
     * Creates a standalone DAOFactory that returns unmanaged DAO
     * beans for use in any environment Hibernate has been configured
     * for. Uses HibernateUtil/SessionFactory and Hibernate context
     * propagation (CurrentSessionContext), thread-bound or transaction-bound,
     * and transaction scoped.
     */
    public static final Class<DAOFactory> HIBERNATE = DAOFactory.class;
 
    /**
     * Factory method for instantiation of concrete factories.
     * @param factory la classe factory
     * @return une instance de DAOFactory
     */
    public static AbstractDAOFactory instance(Class<?> factory) {
        try {
            return (AbstractDAOFactory)factory.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("N'a pas pu créé la DAOFactory: " + factory);
        }
    }
 
    /**
     * Retourne un objet de haut niveau de type DAO
     * @return un UserDAO
     */
    public abstract UserDAO getUserDAO();

    /**
     * Retourne un objet de haut niveau de type DAO
     * @return un RiotAccountDAO
     */
    public abstract RiotAccountDAO getRiotAccountDAO();

    /**
     * Retourne un objet de haut niveau de type DAO
     * @return un ContactDAO
     */
    public abstract ContactDAO getContactDAO();
}
