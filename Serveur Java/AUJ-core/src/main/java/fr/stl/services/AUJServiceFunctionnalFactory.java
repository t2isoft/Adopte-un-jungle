package fr.stl.services;

import fr.stl.services.impl.ContactServiceImpl;
import fr.stl.services.impl.UserServiceImpl;

/**
 * Factory des services fonctionnels
 */
public class AUJServiceFunctionnalFactory {

    /**
     * Le singleton.
     */
    private static final AUJServiceFunctionnalFactory INSTANCE = new AUJServiceFunctionnalFactory();

    /**
     * Constructeur privé pour singleton.
     */
    private AUJServiceFunctionnalFactory() {
        super();
    }
    
    /**
     * @return le singleton
     */
    public static AUJServiceFunctionnalFactory getInstance() {
        return INSTANCE;
    }
    
    /**
     * Méthode qui créee et renvoie un objet de type UserService.
     * @return un service exposant les méthodes de haut niveau
     */
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    /**
     * Méthode qui créee et renvoie un objet de type ContactService.
     * @return un service exposant les méthodes de haut niveau
     */
    public ContactService getContactService() {
        return new ContactServiceImpl();
    }
    
}
