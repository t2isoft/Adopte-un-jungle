package fr.stl.dto.user;

import java.io.Serializable;

import fr.stl.dto.UserDTO;

/**
 * Classe de requête updateAccount
 */
public class RequestUpdateAccountDTO implements Serializable{

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /** Le UserDTO */
    private UserDTO userDTO;

    /**
     * @return the userDTO
     */
    public UserDTO getUserDTO() {
        return userDTO;
    }

    
    /**
     * @param userDTO the userDTO to set
     */
    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
    
}
