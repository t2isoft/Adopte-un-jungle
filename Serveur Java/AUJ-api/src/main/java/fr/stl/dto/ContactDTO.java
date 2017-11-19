package fr.stl.dto;

import java.io.Serializable;

/**
 * Classe de requête contact
 */
public class ContactDTO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** Nom Invocateur */
    private String nomInvocateur;

    /** Status Amitié */
    private String status;

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
     * @return the nomInvocateur
     */
    public String getNomInvocateur() {
        return nomInvocateur;
    }

    /**
     * @param nomInvocateur the nomInvocateur to set
     */
    public void setNomInvocateur(String nomInvocateur) {
        this.nomInvocateur = nomInvocateur;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
