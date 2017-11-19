package fr.stl.exceptions;

/**
 * Description de la classe : Exception générique de l'application, elle est la "parente" des autres exceptions.
 */
public class ApplicationException extends Exception {

    /**
     * Numéro d'identification de la version de la classe.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Message de l'exception.
     */
    private final String msg;

    /**
     * Constructeur de la classe. : ApplicationException()
     */
    public ApplicationException() {
        super();
        msg = null;
    }

    /**
     * Constructeur de la classe avec un message. : ApplicationException()
     * @param pMsg Message de l'exception
     */
    public ApplicationException(final String pMsg) {
        super(pMsg);
        this.msg = pMsg;
    }

    /**
     * Constructeur de la classe avec un message et une exception source. : ApplicationException()
     * @param pMsg Message de l'exeption
     * @param ex Exception source
     */
    public ApplicationException(final String pMsg, final Throwable ex) {
        super(pMsg, ex);
        this.msg = pMsg;
    }

    /**
     * Lecture de message de l'exception.
     * @return Message de l'exception
     */
    @Override
    public final String getMessage() {
        return this.msg;
    }
}
