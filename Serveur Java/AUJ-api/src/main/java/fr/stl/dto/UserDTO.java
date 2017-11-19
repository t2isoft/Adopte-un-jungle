package fr.stl.dto;

import java.io.Serializable;

/**
 * Classe contenant les informations à envoyer
 */
public class UserDTO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** Le nom d'invocateur */
    private String nomInvocateur;

    /** Le login */
    private String login;

    /** L'ID */
    private String id;

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
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
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

    // TODO Ajouter Icone Invocateur
    // TODO Ajouter La liste des publications

}
