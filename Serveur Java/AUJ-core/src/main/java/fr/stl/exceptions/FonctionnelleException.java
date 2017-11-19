package fr.stl.exceptions;

/**
 * Description de la classe : Exception fonctionnelle, elle "herite" de l'exception générique ApplicationException.
 */
public class FonctionnelleException extends ApplicationException {

    /**
     * Numéro d'identification de la version de la classe.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur de la classe. : ApplicationException()
     */
    public FonctionnelleException() {
        super();
    }

    /**
     * Constructeur qui prend un message
     * @param message le message
     */
    public FonctionnelleException(final String message) {
        super(message);
    }

}
