package fr.stl.servlet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.stl.AbstractServlet;
import fr.stl.Constantes;
import fr.stl.dto.login.ResponseLoginDTO;
import fr.stl.dto.post.PostDTO;
import fr.stl.dto.tretour.TRetour;

/**
 * Servlet de gestion des posts
 */
public class PostServlet extends HttpServlet {
    
    /** Le logger */
    private final Logger LOG = Logger.getLogger(getClass());

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            System.out.println(req.getRequestURL());
            switch (req.getRequestURI()) {
                case Constantes.PATH_POST + "/myposts":
                    myPost(resp, req);
                    break;
                case Constantes.PATH_POST + "/newsFeed":
                    myNewsFeed(resp, req);
                    break;
                default:
                    resp.setStatus(400);
                    break;
            }
        } catch (Exception e) {
            LOG.error(e);
            // Le retour
            TRetour tRetour = new TRetour();
            tRetour.setCodeRetour("2000000000");
            tRetour.setMessageRetour("Erreur: " + e.getMessage() + ". Veuillez contacter un administrateur de l'API.");
            ResponseLoginDTO response = new ResponseLoginDTO();
            
            // La réponse
            response.settRetour(tRetour);
            AbstractServlet.sendAsJson(resp, tRetour);
            resp.setStatus(500);
        }
    }
    
    /**
     * Renvoie le fil d'actualité de l'utilisateur
     * @param resp la réponse
     * @param req la requête
     */
    private void myNewsFeed(HttpServletResponse resp, HttpServletRequest req) {
        // TODO Récupération du fil d'actualité
        List<PostDTO> listPost = new ArrayList<PostDTO>();
        PostDTO post = new PostDTO();
        post.setDate(new Date().toString());
        post.setMessage("Mid or Feed !");

        PostDTO post1 = new PostDTO();
        post1.setDate(new Date().toString());
        post1.setMessage("Cyka Blyat !");

        PostDTO post2 = new PostDTO();
        post2.setDate(new Date().toString());
        post2.setMessage("Noob Team, reported !");

        listPost.add(post);
        listPost.add(post1);
        listPost.add(post2);
        
        // Envoie..
        AbstractServlet.sendAsJson(resp, listPost);
    }

    /**
     * Renvoie la liste des postes de l'utilisateur
     * @param resp la réponse
     * @param req la requête
     */
    private void myPost(HttpServletResponse resp, HttpServletRequest req) {
        // TODO Récupération des posts
        List<PostDTO> listPost = new ArrayList<PostDTO>();
        PostDTO post = new PostDTO();
        post.setDate(new Date().toString());
        post.setMessage("Bienvenue sur Adopte un Jungle ! Tu veux être mon ami ?");

        PostDTO post1 = new PostDTO();
        post1.setDate(new Date().toString());
        post1.setMessage("Je cherche une team de 4, je main ADC ou Support !");

        PostDTO post2 = new PostDTO();
        post2.setDate(new Date().toString());
        post2.setMessage("Seek a team to have fun !");

        listPost.add(post);
        listPost.add(post1);
        listPost.add(post2);

        // Envoie..
        AbstractServlet.sendAsJson(resp, listPost);
    }
    
    @Override
    public void destroy() {
        LOG.info("Servlet " + this.getServletName() + " has stopped");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        LOG.info("Servlet " + this.getServletName() + " has started");
        super.init();
    }

}
