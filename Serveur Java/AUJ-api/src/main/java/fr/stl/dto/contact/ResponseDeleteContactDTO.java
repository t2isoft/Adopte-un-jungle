package fr.stl.dto.contact;

import java.io.Serializable;

import fr.stl.dto.tretour.TRetour;

/**
 * Classe de réponse de DeleteContact
 */
public class ResponseDeleteContactDTO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** Les information de retour */
    private TRetour tRetour;
    
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
