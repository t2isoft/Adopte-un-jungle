package fr.stl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La classe entity Post
 */
@Entity
@Table(name = "auj_post")
public class PostEntity {

    /** L'id */
    private Long id;
    
    /** L'utilisateur qui a posté */
    private UserEntity poster;

    /** La date du post */
    private Date postDate;

    /** Le contenu du post */
    private String content;

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
     * @return the poster
     */
    @ManyToOne
    @JoinColumn(name="auj_user_ID")
    public UserEntity getPoster() {
        return poster;
    }

    /**
     * @param poster the poster to set
     */
    public void setPoster(UserEntity poster) {
        this.poster = poster;
    }

    /**
     * @return the postDate
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "POSTDATE", nullable = false, columnDefinition="DATETIME")
    public Date getPostDate() {
        return postDate;
    }

    /**
     * @param postDate the postDate to set
     */
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    /**
     * @return the content
     */
    @Column(name = "CONTENT", length = 255, nullable = false)
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

}
