package fr.stl.dao;

import org.hibernate.Session;

import fr.stl.dao.impl.ContactDAOImpl;
import fr.stl.dao.impl.PostDAOImpl;
import fr.stl.dao.impl.RiotAccountDAOImpl;
import fr.stl.dao.impl.UserDAOImpl;
import fr.stl.generics.AbstractDAOFactory;
import fr.stl.generics.GenericHibernateDAO;
import fr.stl.util.HibernateUtil;

/**
 * Factory des DAOs
 */
public class DAOFactory extends AbstractDAOFactory {
    
    /**
     * Le singleton.
     */
    private static final DAOFactory INSTANCE = new DAOFactory();
    
    /**
     * @return le singleton
     */
    public static DAOFactory getInstance() {
        return INSTANCE;
    }
    
    /**
     * Instancies en fonction de la classe de DAO
     * @param daoClass la classe de DAO
     * @return une DAO Générique
     */
    @SuppressWarnings("rawtypes")
    protected GenericHibernateDAO instantiateDAO(Class daoClass) {
        try {
            GenericHibernateDAO dao = (GenericHibernateDAO)daoClass.newInstance();
            dao.setSession(getCurrentSession());
            return dao;
        } catch (Exception ex) {
            throw new RuntimeException("Ne peut pas instancier DAO: " + daoClass, ex);
        }
    }

    /**
     * @return la session
     */
    protected Session getCurrentSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    @Override
    public UserDAO getUserDAO() {
        return (UserDAO)instantiateDAO(UserDAOImpl.class);
    }

    @Override
    public RiotAccountDAO getRiotAccountDAO() {
        return (RiotAccountDAO)instantiateDAO(RiotAccountDAOImpl.class);
    }
    
    @Override
    public ContactDAO getContactDAO() {
        return (ContactDAO)instantiateDAO(ContactDAOImpl.class);
    }

    @Override
    public PostDAO getPostDAO() {
        return (PostDAO)instantiateDAO(PostDAOImpl.class);
    }
 
}
