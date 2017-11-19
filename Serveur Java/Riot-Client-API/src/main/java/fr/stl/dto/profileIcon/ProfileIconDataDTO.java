package fr.stl.dto.profileIcon;

import java.util.Map;

/**
 * Classe ProfileIconDataDTO lié à l'API RIOT
 */
public class ProfileIconDataDTO {

    //TODO: no description available
    
    private Map<String,ProfileIconDetailsDTO> data;
    
    private String version;
    
    private String type;

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

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
