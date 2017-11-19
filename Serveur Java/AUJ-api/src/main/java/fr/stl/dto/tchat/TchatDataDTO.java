package fr.stl.dto.tchat;

/**
 * La classe de tchat
 */
public class TchatDataDTO {

    /** Le nom de l'émetteur */
    private String nickname;

    /** Le message */
    private String message;
    
    /**
     * Constructor
     * @param nickname le nom de l'émetteur
     * @param message le message
     */
    public TchatDataDTO(String nickname, String message) {
        this.nickname = nickname;
        this.message = message;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
