package fr.stl.dto.post;

import java.io.Serializable;

/**
 * Classe contenant la requête d'update de post
 */
public class RequestUpdatePostDTO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** L'id du post à update */
    private String id;

    /** Le message, du post, mis à jour */
    private String messageUpdated;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the messageUpdated
     */
    public String getMessageUpdated() {
        return messageUpdated;
    }

    /**
     * @param messageUpdated the messageUpdated to set
     */
    public void setMessageUpdated(String messageUpdated) {
        this.messageUpdated = messageUpdated;
    }

}
