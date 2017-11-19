package fr.stl.dto.user;

import java.io.Serializable;

/**
 * Classe de requête de watchProfil
 */
public class RequestWatchProfilDTO implements Serializable {
    
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /** L'id de l'utilisateur cible*/
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
