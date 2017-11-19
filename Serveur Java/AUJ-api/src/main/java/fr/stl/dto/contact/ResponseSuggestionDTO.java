package fr.stl.dto.contact;

import java.io.Serializable;
import java.util.List;

import fr.stl.dto.UserDTO;
import fr.stl.dto.tretour.TRetour;

/**
 * Classe de response Suggestion
 */
public class ResponseSuggestionDTO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** Les information de retour */
    private TRetour tRetour;

    /** List des suggestions */
    private List<UserDTO> listSuggestion;

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

    /**
     * @return the listSuggestion
     */
    public List<UserDTO> getListSuggestion() {
        return listSuggestion;
    }

    /**
     * @param listSuggestion the listSuggestion to set
     */
    public void setListSuggestion(List<UserDTO> listSuggestion) {
        this.listSuggestion = listSuggestion;
    }

}
