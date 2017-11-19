package fr.stl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * La classe entity Contact
 */
@Entity
@Table(name = "auj_in_contact")
public class ContactEntity {

    /** L'id */
    private Long id;

    /** Utilisateur 1 */
    private UserEntity utilisateur1;

    /** Utilisateur 2 */
    private UserEntity utilisateur2;

    /** Le status du contact */
    private int status;

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
     * @return the utilisateur1
     */
    @ManyToOne
    @JoinColumn(name = "auj_user1_ID")
    public UserEntity getUtilisateur1() {
        return utilisateur1;
    }

    /**
     * @param utilisateur1 the utilisateur1 to set
     */
    public void setUtilisateur1(UserEntity utilisateur1) {
        this.utilisateur1 = utilisateur1;
    }

    /**
     * @return the utilisateur2
     */
    @ManyToOne
    @JoinColumn(name = "auj_user2_ID")
    public UserEntity getUtilisateur2() {
        return utilisateur2;
    }

    /**
     * @param utilisateur2 the utilisateur2 to set
     */
    public void setUtilisateur2(UserEntity utilisateur2) {
        this.utilisateur2 = utilisateur2;
    }

    /**
     * @return the status
     */
    @Column(name = "STATUS", length = 11, nullable = false)
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

}
