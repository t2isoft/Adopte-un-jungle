package fr.stl.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * La classe entity User
 */
@Entity
@Table(name = "auj_user")
public class UserEntity {

    /** L'id */
    private Long id;

    /** L'id Riot */
    private RiotAccountEntity riotAccount;

    /** Le nom d'utilisateur */
    private String username;

    /** Le password */
    private String password;

    /** L'email */
    private String email;

    /** Les informations additionnelles */
    private String additionalInformation;

    /** Les posts de l'utilisateur */
    private Set<PostEntity> posts;

    /** Les contacts de l'utilisateur */
    private Set<ContactEntity> contacts;

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
     * @return the riotAccount
     */
    @ManyToOne
    @JoinColumn(name = "auj_riotaccount_ID")
    public RiotAccountEntity getRiotAccount() {
        return riotAccount;
    }

    /**
     * @param riotAccount the riotAccount to set
     */
    public void setRiotAccount(RiotAccountEntity riotAccount) {
        this.riotAccount = riotAccount;
    }

    /**
     * @return the username
     */
    @Column(name = "USERNAME", unique = true, length = 255, nullable = false)
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
    @Column(name = "PASSWORD", length = 255, nullable = false)
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
     * @return the email
     */
    @Column(name = "EMAIL", unique = true, length = 255, nullable = false)
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
     * @return the additionalInformation
     */
    @Column(name = "ADDITIONALINFORMATION", length = 255)
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * @param additionalInformation the additionalInformation to set
     */
    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    /**
     * Get the user posts
     * @return list of post entity
     */
    @OneToMany(targetEntity = PostEntity.class, mappedBy = "poster", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<PostEntity> getPosts() {
        return posts;
    }

    /**
     * @param posts the contact list to set
     */
    public void setPosts(Set<PostEntity> posts) {
        this.posts = posts;
    }

    /**
     * Get the user posts
     * @return list of contact entity
     */
    @OneToMany(targetEntity = ContactEntity.class, mappedBy = "myContact", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<ContactEntity> getContacts() {
        return contacts;
    }

    /**
     * @param contacts the contact list to set
     */
    public void setContacts(Set<ContactEntity> contacts) {
        this.contacts = contacts;
    }

}
