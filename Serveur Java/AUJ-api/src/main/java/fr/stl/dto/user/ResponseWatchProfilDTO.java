package fr.stl.dto.user;

import java.io.Serializable;

import fr.stl.dto.UserDTO;
import fr.stl.dto.tretour.TRetour;

/**
 * Classe de réponse de watchProfil
 */
public class ResponseWatchProfilDTO implements Serializable {
    
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /** L'utilisateur récupérer avec l'id de la requête */
    private UserDTO user;
    
    /** Les information de retour */
    private TRetour tRetour;

    /**
     * @return the user
     */
    public UserDTO getUser() {
        return user;
    }
    
    /**
     * @param user the user to set
     */
    public void setUser(UserDTO user) {
        this.user = user;
    }
    
    /**
     * @return the tRetour
     */
    public TRetour gettRetour() {
        return tRetour;
    }

    /**
     * @param tRetour the tRetour to set
     */
    public void settRetour(TRetour tRetour) {
        this.tRetour = tRetour;
    }
    
}
