package fr.stl.dto;

import java.io.Serializable;

/**
 * Classe contenant les posts à envoyer
 */
public class PostDTO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** Le message du post */
    private String message;

    /** La date */
    private String date;

    /** L'ID */
    private String id;
    
    /** Le posteur du post*/
    private UserDTO poster;

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

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

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
     * 
     * @return the poster
     */
    public UserDTO getPoster() {
        return poster;
    }

    /**
     * 
     * @param poster the userDTO to set
     */
    public void setPoster(UserDTO poster) {
        this.poster = poster;
    }

}
