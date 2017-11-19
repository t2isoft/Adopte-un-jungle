package fr.stl.dto.contact;

import java.io.Serializable;

/**
 * Classe de requête pour les contacts
 */
public class RequestContactDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /** Nom d'utilisateur */
    private String username;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
