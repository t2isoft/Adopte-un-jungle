package fr.stl.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import fr.stl.dto.ContactDTO;
import fr.stl.dto.PostDTO;
import fr.stl.dto.UserDTO;
import fr.stl.entity.ContactEntity;
import fr.stl.entity.PostEntity;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.TechniqueException;
import fr.stl.services.AUJServiceFunctionnalFactory;

/**
 * Classe de conversion des objets en DTO
 */
public class ConverterToDto {

    /** Le logger */
    private final static Logger LOG = Logger.getLogger("ConverterToDto");

    /**
     * Convertis une liste de ContactEntity en liste de ContactDTO
     * @param listContactEntity une liste de ContactEntity
     * @param idUser le User correspondant
     * @return une liste de ContactDTO
     */
    public static List<ContactDTO> convertToListContactDTO(final List<ContactEntity> listContactEntity, Long idUser) {
        List<ContactDTO> listContactDto = new ArrayList<>();
        boolean addContact;
        for (ContactEntity contactEntity : listContactEntity) {
            ContactDTO contactDto = new ContactDTO();

            // Remplir le DTO
            if (contactEntity.getMe().getId() == idUser) {
                contactDto.setNomInvocateur(contactEntity.getMyContact().getRiotAccount().getPseudo());
                contactDto.setId(String.valueOf(contactEntity.getMyContact().getId()));
            }
            if (contactEntity.getMyContact().getId() == idUser) {
                contactDto.setNomInvocateur(contactEntity.getMe().getRiotAccount().getPseudo());
                contactDto.setId(String.valueOf(contactEntity.getMe().getId()));
            }
            if (contactEntity.getStatus() == 1) {
                contactDto.setStatus("ACTIF");
            }
            if (contactEntity.getStatus() == 0) {
                contactDto.setStatus("ATTENTE");
            }

            addContact = true;
            for (ContactDTO contactDTO : listContactDto) {
                if (contactDTO.getId().equalsIgnoreCase(contactDto.getId())) {
                    addContact = false;
                }
            }
            if (addContact) {
                listContactDto.add(contactDto);
            }
        }
        return listContactDto;
    }

    /**
     * Convertis une liste de PostEntity en liste de PostDTO
     * @param listPostEntity une liste de PostEntity
     * @return une liste de postDTO
     */
    public static List<PostDTO> convertToListPostDTO(final List<PostEntity> listPostEntity) {
        List<PostDTO> listPostDto = new ArrayList<>();
        for (PostEntity postEntity : listPostEntity) {
            PostDTO postDTO = new PostDTO();

            UserDTO userDTO = new UserDTO();
            userDTO.setId(String.valueOf(postEntity.getPoster().getId()));
            userDTO.setLogin(postEntity.getPoster().getUsername());
            userDTO.setNomInvocateur(postEntity.getPoster().getRiotAccount().getPseudo());
            userDTO.setMail(postEntity.getPoster().getEmail());

            postDTO.setPoster(userDTO);
            postDTO.setId(String.valueOf(postEntity.getId()));
            postDTO.setDate(String.valueOf(postEntity.getPostDate()));
            postDTO.setMessage(postEntity.getContent());

            // Remplir le DTO
            listPostDto.add(postDTO);
        }

        return listPostDto;
    }

    /**
     * Convertis une liste de UserEntity en liste de UserDTO
     * @param listSuggestion une liste de UserEntity
     * @return une liste de userDTO
     */
    public static List<UserDTO> convertToListUserDTO(List<UserEntity> listSuggestion) {
        List<UserDTO> listUserDto = new ArrayList<>();
        for (UserEntity user : listSuggestion) {
            UserDTO userDto = new UserDTO();
            userDto.setNomInvocateur(user.getRiotAccount().getPseudo());
            userDto.setLogin(user.getUsername());
            userDto.setId(String.valueOf(user.getId()));

            // Remplir le DTO
            listUserDto.add(userDto);
        }
        return listUserDto;
    }

    /**
     * Convertis l'UserEntity en UserDTO pour la visualisation d'un profil
     * @param userEntity le UserEntity
     * @return le UserDTO
     */
    public static UserDTO convertUserToMyProfil(UserEntity userEntity) {
        UserDTO userDto = new UserDTO();
        userDto.setId(userEntity.getId().toString());
        userDto.setNomInvocateur(userEntity.getRiotAccount().getPseudo());
        userDto.setPoste(userEntity.getRiotAccount().getRole());
        userDto.setMail(userEntity.getEmail());
        userDto.setLogin(userEntity.getUsername());
        return userDto;
    }

    /**
     * Convertis l'UserEntity en UserDTO pour la visualisation de son compte
     * @param userEntity le UserEntity
     * @param userConnected le User connecté
     * @return le UserDTO
     */
    public static UserDTO convertUserToWatchProfil(UserEntity userEntity, UserEntity userConnected) {
        UserDTO userDto = new UserDTO();
        userDto.setId(userEntity.getId().toString());
        userDto.setNomInvocateur(userEntity.getRiotAccount().getPseudo());
        userDto.setPoste(userEntity.getRiotAccount().getRole());
        List<ContactEntity> listContactUserConnected;
        try {
            listContactUserConnected = AUJServiceFunctionnalFactory.getInstance().getContactService()
                            .findContactForUser(userConnected.getId());
            for (ContactEntity contact : listContactUserConnected) {
                if (contact.getMyContact().getId().equals(userEntity.getId())
                                || contact.getMe().getId().equals(userEntity.getId())) {
                    if (contact.getStatus() == 1) {
                        userDto.setStatusAmitie("ACTIF");
                        break;
                    }
                    if (contact.getStatus() == 0) {
                        userDto.setStatusAmitie("ATTENTE");
                        break;
                    }
                }
            }
        } catch (TechniqueException e) {
            LOG.error(e);
        }
        return userDto;
    }

}
