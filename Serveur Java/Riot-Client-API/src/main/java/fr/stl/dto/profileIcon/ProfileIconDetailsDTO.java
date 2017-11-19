package fr.stl.dto.profileIcon;

/**
 * Classe ProfileIconDetailsDTO lié à l'API RIOT
 */
public class ProfileIconDetailsDTO {

    //TODO: no description available
    
    private ImageDTO image;
    
    private long id;

    /**
     * @return the image
     */
    public ImageDTO getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(ImageDTO image) {
        this.image = image;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
}
