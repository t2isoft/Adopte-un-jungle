package fr.stl.dto.post;

import java.io.Serializable;
import java.util.List;

import fr.stl.dto.PostDTO;
import fr.stl.dto.tretour.TRetour;

/**
 * Classe de response NewsFeedDTO
 */
public class ResponseNewsFeedDTO implements Serializable{

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** Les information de retour */
    private TRetour tRetour;
    
    /** Les posts à afficher */
    private List<PostDTO> posts;

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

    /**
     * @return the newsfeed posts
     */
    public List<PostDTO> getPosts() {
        return posts;
    }

    /**
     * 
     * @param posts the posts to set
     */
    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }
    
}
