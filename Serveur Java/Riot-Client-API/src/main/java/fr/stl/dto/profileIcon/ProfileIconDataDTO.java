package fr.stl.dto.profileIcon;

import java.util.Map;

/**
 * Classe ProfileIconDataDTO lié à l'API RIOT
 */
public class ProfileIconDataDTO {

    //TODO: no description available
    
    private Map<String,ProfileIconDetailsDTO> data;

    /**
     * @return the data
     */
    public Map<String, ProfileIconDetailsDTO> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Map<String, ProfileIconDetailsDTO> data) {
        this.data = data;
    }
    
}
