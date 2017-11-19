package fr.stl.dto.login;

import java.io.Serializable;

import fr.stl.dto.UserDTO;
import fr.stl.dto.tretour.TRetour;

/**
 * Classe de response Login
 */
public class ResponseLoginDTO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** L'utilisateur */
    private UserDTO userDto;

    /** Les information de retour */
    private TRetour tRetour;

    /**
     * @return the userDto
     */
    public UserDTO getUserDto() {
        return userDto;
    }

    /**
     * @param userDto the userDto to set
     */
    public void setUserDto(UserDTO userDto) {
        this.userDto = userDto;
    }

    /**
     * @return the tRetour
     */
    public TRetour gettRetour() {
        return tRetour;
    }

    /**
     * @param tRetour the tRetour to set
     */
    public void settRetour(TRetour tRetour) {
        this.tRetour = tRetour;
    }

}
