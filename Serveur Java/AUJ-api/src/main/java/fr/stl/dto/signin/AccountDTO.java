package fr.stl.dto.signin;

import java.io.Serializable;

/**
 * Classe contenant les informations du compte
 */
public class AccountDTO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** L'email */
    private String email;

    /** Le nomInvocateur */
    private String nomInvocateur;

    /** L'username */
    private String username;

    /** Le password */
    private String password;

    /** Rank */
    private String rank;

    /** Rôle */
    private String role;

    /** Serveur */
    private String server;

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

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

    /**
     * @return the rank
     */
    public String getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(String server) {
        this.server = server;
    }

}
