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
    private UserEntity me;

    /** Utilisateur 2 */
    private UserEntity myContact;

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
     * @return me
     */
    @ManyToOne
    @JoinColumn(name = "auj_user1_ID")
    public UserEntity getMe() {
        return me;
    }

    /**
     * @param me me to set
     */
    public void setMe(UserEntity me) {
        this.me = me;
    }

    /**
     * @return the myContact
     */
    @ManyToOne
    @JoinColumn(name = "auj_user2_ID")
    public UserEntity getMyContact() {
        return myContact;
    }

    /**
     * @param myContact myContact to set
     */
    public void setMyContact(UserEntity myContact) {
        this.myContact = myContact;
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
