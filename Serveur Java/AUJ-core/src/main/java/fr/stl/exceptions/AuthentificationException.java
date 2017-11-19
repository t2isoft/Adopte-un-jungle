package fr.stl.exceptions;

/**
 * Description de la classe : Exception fonctionnelle de type authentification, elle "herite" de l'exception générique ApplicationException.
 */
public class AuthentificationException extends ApplicationException {

    /**
     * Numéro d'identification de la version de la classe.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur de la classe. : ApplicationException()
     */
    public AuthentificationException() {
        super();
    }

    /**
     * Constructeur qui prend un message
     * @param message le message
     */
    public AuthentificationException(final String message) {
        super(message);
    }

}
