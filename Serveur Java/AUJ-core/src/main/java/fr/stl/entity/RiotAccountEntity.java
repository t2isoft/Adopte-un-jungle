package fr.stl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * La classe entity Riot Account
 */
@Entity
@Table(name = "auj_riotaccount")
public class RiotAccountEntity {

    /** L'id */
    private Long id;

    /** Le pseudo en jeu */
    private String pseudo;

    /** Le rôle */
    private String role;

    /** Le serveur */
    private String serveur;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true, length = 11)
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the pseudo
     */
    @Column(name = "PSEUDO", length = 255, nullable = false)
    public String getPseudo() {
        return pseudo;
    }

    /**
     * @param pseudo the pseudo to set
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * @return the role
     */
    @Column(name = "ROLE", length = 255, nullable = false)
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
     * @return the serveur
     */
    @Column(name = "SERVER", length = 255, nullable = false)
    public String getServeur() {
        return serveur;
    }

    /**
     * @param serveur the serveur to set
     */
    public void setServeur(String serveur) {
        this.serveur = serveur;
    }

}
