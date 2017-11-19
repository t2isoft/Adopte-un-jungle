package fr.stl.util;

import fr.stl.dto.UserDTO;
import fr.stl.entity.UserEntity;

/**
 * Classe permettant de convertir un DTO en une classe entitée
 */
public class ConverterToEntity {

    /**
     * Conversion d'un UserDTO vers un UserEntity
     * @param userDTO le UserDTO
     * @return un UserEntiy
     */
    public static UserEntity convertToUserEntity(UserDTO userDTO) {
        UserEntity newUser = new UserEntity();
        newUser.setId(Long.parseLong(userDTO.getId()));
        newUser.setUsername(userDTO.getNomInvocateur());
        newUser.setEmail(userDTO.getMail());
        // newUser.setAdditionalInformation(userDTO.getAdditionalInformation());
        return newUser;
    }

}
