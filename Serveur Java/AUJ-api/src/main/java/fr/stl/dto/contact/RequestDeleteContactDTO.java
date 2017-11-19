package fr.stl.dto.contact;

import java.io.Serializable;

/**
 * Classe de requête de DeleteContact
 */
public class RequestDeleteContactDTO implements Serializable {
    
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /** id de l'utilisateur cible*/
    private String id;

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

}
