package fr.stl.exceptions;

/**
 * Description de la classe : Exception technique, elle hérite de l'exception générique ApplicationException().
 */
public class TechniqueException extends ApplicationException {

    /**
     * Numéro d'identification de la version de la classe.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur de la classe. : ApplicationException()
     */
    public TechniqueException() {
        super();
    }

    /**
     * Constructeur de la classe avec un message.
     * @param message Message l'exception
     */
    public TechniqueException(final String message) {
        super(message);
    }

    /**
     * Constructeur avec un message et une exception
     * @param message le message de l'exception
     * @param ex Exception source
     */
    public TechniqueException(final String message, final Throwable ex) {
        super(message, ex);
    }

    /**
     * Constructeur avec un message et une exception
     * @param ex Exception source
     */
    public TechniqueException(final Throwable ex) {
        super(ex.getMessage(), ex);
    }
}
