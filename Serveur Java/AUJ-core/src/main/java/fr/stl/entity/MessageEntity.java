package fr.stl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * La classe entity Message
 */
@Entity
@Table(name = "auj_message", uniqueConstraints = {@UniqueConstraint(columnNames = {"AUJ_MESSAGE_ID" }) })
public class MessageEntity {

    /** L'id */
    private long id;

    /** L'emetteur */
    // FIXME On récupère un UserEntity
    private UserEntity sender;

    /** Le récepteur */
    // FIXME On récupère un UserEntity
    private UserEntity receiver;

    /** Le contenu du message */
    private String content;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUJ_MESSAGE_ID", nullable = false, unique = true, length = 11)
    public long getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the sender
     */
    @ManyToOne
    @JoinColumn(name = "AUJ_USER_ID")
    public UserEntity getSender() {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    /**
     * @return the receiver
     */
    @ManyToOne
    @JoinColumn(name = "AUJ_USER_ID")
    public UserEntity getReceiver() {
        return receiver;
    }

    /**
     * @param receiver the receiver to set
     */
    public void setReceiver(UserEntity receiver) {
        this.receiver = receiver;
    }

    /**
     * @return the content
     */
    @Column(name = "AUJ_MESSAGE_content", length = 200, nullable = false)
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
