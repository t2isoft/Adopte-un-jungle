package fr.stl.dto.contact;

import java.io.Serializable;
import java.util.List;

import fr.stl.dto.ContactDTO;
import fr.stl.dto.tretour.TRetour;

/**
 * Classe de response MyContact
 */
public class ResponseMyContactDTO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** Les information de retour */
    private TRetour tRetour;

    /** List des contactes */
    private List<ContactDTO> listContact;

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
     * @return the listContact
     */
    public List<ContactDTO> getListContact() {
        return listContact;
    }

    /**
     * @param listContact the listContact to set
     */
    public void setListContact(List<ContactDTO> listContact) {
        this.listContact = listContact;
    }

}
