package fr.stl.dto.post;

import java.io.Serializable;

/**
 * Classe contenant la requête de nouveau post
 */
public class RequestNewPostDTO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** Le message du post */
    private String message;

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
