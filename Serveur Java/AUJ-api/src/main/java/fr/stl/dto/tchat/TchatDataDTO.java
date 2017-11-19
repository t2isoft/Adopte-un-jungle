package fr.stl.dto.tchat;

/**
 * La classe de tchat
 */
public class TchatDataDTO {

    /** Le nom de l'émetteur */
    private String nickName;

    /** Le message */
    private String message;
    
    /**
     * Constructor
     * @param nickName le nom de l'émetteur
     * @param message le message
     */
    public TchatDataDTO(String nickName, String message) {
        this.nickName = nickName;
        this.message = message;
    }

    /**
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
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
