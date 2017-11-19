package fr.stl.dto.user;

import java.io.Serializable;

/**
 * Classe de requête update password
 */
public class RequestUpdatePasswordDTO implements Serializable {
    
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /** Le nouveau mot de passe */
    private String password;

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
