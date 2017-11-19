package fr.stl.util;

import java.util.ArrayList;
import java.util.List;

import fr.stl.dto.ContactDTO;
import fr.stl.entity.ContactEntity;

/**
 * Classe de conversion des objets en DTO
 */
public class ConverterToDto {

    /**
     * Convertis une liste de ContactEntity en liste de ContactDTO
     * @param listContactEntity une liste de ContactEntity
     * @param idUser le User correspondant
     * @return une liste de ContactDTO
     */
    public static List<ContactDTO> convertToListContactDTO(final List<ContactEntity> listContactEntity, Long idUser) {
        List<ContactDTO> listContactDto = new ArrayList<>();
        for (ContactEntity contactEntity : listContactEntity) {
            ContactDTO contactDto = new ContactDTO();
            
            // Remplir le DTO
            if (contactEntity.getUtilisateur1().getId() != idUser) {
                contactDto.setNomInvocateur(contactEntity.getUtilisateur1().getRiotAccount().getPseudo());
                contactDto.setId(String.valueOf(contactEntity.getUtilisateur2().getId()));
            } 
            if (contactEntity.getUtilisateur2().getId() != idUser) {
                contactDto.setNomInvocateur(contactEntity.getUtilisateur2().getRiotAccount().getPseudo());
                contactDto.setId(String.valueOf(contactEntity.getUtilisateur1().getId()));
            }
            if (contactEntity.getStatus() == 1) {
                contactDto.setStatus("ACTIF");
            }
            if (contactEntity.getStatus() == 0) {
                contactDto.setStatus("ATTENTE");
            }

            listContactDto.add(contactDto);
        }
        return listContactDto;
    }

}
