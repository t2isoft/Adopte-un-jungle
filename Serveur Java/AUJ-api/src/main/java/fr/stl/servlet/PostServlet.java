package fr.stl.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

import com.google.gson.Gson;

import fr.stl.AbstractServlet;
import fr.stl.Constantes;
import fr.stl.Environment;
import fr.stl.dto.PostDTO;
import fr.stl.dto.UserDTO;
import fr.stl.dto.login.ResponseLoginDTO;
import fr.stl.dto.post.RequestNewPostDTO;
import fr.stl.dto.post.RequestUpdatePostDTO;
import fr.stl.dto.post.ResponseMyPostDTO;
import fr.stl.dto.post.ResponseNewPostDTO;
import fr.stl.dto.post.ResponseNewsFeedDTO;
import fr.stl.dto.post.ResponseUpdatePostDTO;
import fr.stl.dto.tretour.TRetour;
import fr.stl.entity.PostEntity;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.FonctionnelleException;
import fr.stl.exceptions.TechniqueException;
import fr.stl.services.AUJServiceFunctionnalFactory;
import fr.stl.util.ConverterToDto;

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
        } catch (TechniqueException e) {
            LOG.error(e);
            // Le retour
            TRetour tRetour = new TRetour();
            tRetour.setCodeRetour("2000000001");
            tRetour.setMessageRetour(e.getMessage());

            // La réponse
            ResponseLoginDTO response = new ResponseLoginDTO();
            response.settRetour(tRetour);
            AbstractServlet.sendAsJson(resp, response);
        } catch (Exception e) {
            LOG.error(e);
            // Le retour
            TRetour tRetour = new TRetour();
            tRetour.setCodeRetour("2000000000");
            tRetour.setMessageRetour("Erreur: veuillez contacter un administrateur de l'API.");
            ResponseLoginDTO response = new ResponseLoginDTO();

            // La réponse
            response.settRetour(tRetour);
            AbstractServlet.sendAsJson(resp, response);
            resp.setStatus(500);
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println(req.getRequestURL());
            switch (req.getRequestURI()) {
                case Constantes.PATH_POST + "/newPost":
                    newPost(resp, req);
                    break;
                case Constantes.PATH_POST + "/updatePost":
                    updatePost(resp, req);
                    break;
                default:
                    resp.setStatus(400);
                    break;
            }
        } catch (TechniqueException e) {
            LOG.error(e);
            // Le retour
            TRetour tRetour = new TRetour();
            tRetour.setCodeRetour("2000000001");
            tRetour.setMessageRetour(e.getMessage());

            // La réponse
            ResponseLoginDTO response = new ResponseLoginDTO();
            response.settRetour(tRetour);
            AbstractServlet.sendAsJson(resp, response);
        } catch (FonctionnelleException e) {
            LOG.error(e);
            // Le retour
            TRetour tRetour = new TRetour();
            tRetour.setCodeRetour("1000000000");
            tRetour.setMessageRetour(e.getMessage());
            ResponseLoginDTO response = new ResponseLoginDTO();

            // La réponse
            response.settRetour(tRetour);
            AbstractServlet.sendAsJson(resp, response);
            resp.setStatus(500);
        } catch (Exception e) {
            LOG.error(e);
            // Le retour
            TRetour tRetour = new TRetour();
            tRetour.setCodeRetour("2000000000");
            tRetour.setMessageRetour("Erreur: veuillez contacter un administrateur de l'API.");
            ResponseLoginDTO response = new ResponseLoginDTO();

            // La réponse
            response.settRetour(tRetour);
            AbstractServlet.sendAsJson(resp, response);
            resp.setStatus(500);
        }
    }

    /**
     * Update un post
     * @param resp la réponse
     * @param req la requête
     * @throws TechniqueException erreur technique
     * @throws FonctionnelleException erreur fonctionnelle
     */
    private void updatePost(HttpServletResponse resp, HttpServletRequest req)
                    throws TechniqueException, FonctionnelleException {
        // TODO Update Post
        // Récupération de l'utilisateur connecté
        UserDTO userDto = (UserDTO) req.getAttribute(Constantes.USER_AUTH);
        if (userDto == null) {
            throw new TechniqueException("Erreur, impossible de récupérer les informations de l'utilisateur connecté.");
        }

        // Récupération des information du post
        String json = null;
        try {
            json = AbstractServlet.getJson(req);
        } catch (IOException e) {
            LOG.warn(e);
        }

        if (StringUtils.isBlank(json)) {
            throw new FonctionnelleException("Erreur, les données sont manquantes");
        }

        // Parsing du json
        Gson gson = new Gson();
        RequestUpdatePostDTO updatePostDto = gson.fromJson(json, RequestUpdatePostDTO.class);

        // Vérification des données
        if (updatePostDto == null) {
            throw new FonctionnelleException("Erreur, données manquantes.");
        }
        if (StringUtils.isBlank(updatePostDto.getId())) {
            throw new FonctionnelleException("Erreur, la publication n'existe pas.");
        }

        // Récupération de l'id de la publication
        Long idPost = 0L;
        try {
            idPost = Long.parseLong(updatePostDto.getId());
        } catch (NumberFormatException e) {
            throw new TechniqueException("Erreur, l'information de la publication n'est pas au bon format.");
        }
        if (idPost == 0) {
            throw new TechniqueException("Erreur, l'information de la publication n'est pas au bon format.");
        }

        // Récupération du message à mettre à jour
        if (StringUtils.isBlank(updatePostDto.getMessageUpdated())) {
            throw new FonctionnelleException("Erreur, le message mis à jour est vide.");
        }

        // Mise à jour de la publication
        AUJServiceFunctionnalFactory.getInstance().getPostService().updatePostUser(Long.parseLong(userDto.getId()),
                        idPost, updatePostDto.getMessageUpdated());

        // Les information de retour
        TRetour tRetour = new TRetour();
        tRetour.setCodeRetour("0000000000");
        tRetour.setMessageRetour("OK");

        // La réponse
        ResponseUpdatePostDTO response = new ResponseUpdatePostDTO();
        response.settRetour(tRetour);

        AbstractServlet.sendAsJson(resp, response);
    }

    /**
     * Créé un nouveau post
     * @param resp la réponse
     * @param req la requête
     * @throws FonctionnelleException erreur fonctionnelle
     * @throws TechniqueException erreur technique, l'utilisateur n'a pas été bindé
     */
    private void newPost(HttpServletResponse resp, HttpServletRequest req)
                    throws FonctionnelleException, TechniqueException {
        // Récupération de l'utilisateur connecté
        UserDTO userDto = (UserDTO) req.getAttribute(Constantes.USER_AUTH);
        if (userDto == null) {
            throw new TechniqueException("Erreur, impossible de récupérer les informations de l'utilisateur connecté.");
        }

        // Récupération des information du post
        String json = null;
        try {
            json = AbstractServlet.getJson(req);
        } catch (IOException e) {
            LOG.warn(e);
        }

        if (StringUtils.isBlank(json)) {
            throw new FonctionnelleException("Erreur, les données sont manquantes");
        }

        // Parsing du json
        Gson gson = new Gson();
        RequestNewPostDTO newPostDto = gson.fromJson(json, RequestNewPostDTO.class);

        // Vérification des données
        if (newPostDto == null) {
            throw new FonctionnelleException("Erreur, données manquantes.");
        }
        if (StringUtils.isBlank(newPostDto.getMessage())) {
            throw new FonctionnelleException("Erreur, la publication est vide.");
        }

        // Création du post
        AUJServiceFunctionnalFactory.getInstance().getPostService().nouveauPost(newPostDto.getMessage(),
                        Long.parseLong(userDto.getId()));

        // Les information de retour
        TRetour tRetour = new TRetour();
        tRetour.setCodeRetour("0000000000");
        tRetour.setMessageRetour("OK");

        // La réponse
        ResponseNewPostDTO response = new ResponseNewPostDTO();
        response.settRetour(tRetour);

        AbstractServlet.sendAsJson(resp, response);
    }

    /**
     * Renvoie le fil d'actualité de l'utilisateur
     * @param resp la réponse
     * @param req la requête
     * @throws TechniqueException
     */
    private void myNewsFeed(HttpServletResponse resp, HttpServletRequest req) throws TechniqueException {

        // Récupération de l'utilisateur connecté
        UserDTO userDto = (UserDTO) req.getAttribute(Constantes.USER_AUTH);
        if (userDto == null) {
            throw new TechniqueException("Erreur, impossible de récupérer les informations de l'utilisateur connecté.");
        }

        Hibernate.initialize(UserEntity.class);

        // Création du post
        List<PostEntity> result = AUJServiceFunctionnalFactory.getInstance().getPostService()
                        .tousLesPosts(Long.parseLong(userDto.getId()));

        // Conversion des entité de post en DTO
        List<PostDTO> resultDTO = ConverterToDto.convertToListPostDTO(result);

        // Les information de retour
        TRetour tRetour = new TRetour();
        tRetour.setCodeRetour("0000000000");
        tRetour.setMessageRetour("OK");

        // La réponse
        ResponseNewsFeedDTO response = new ResponseNewsFeedDTO();
        response.settRetour(tRetour);
        response.setPosts(resultDTO);

        AbstractServlet.sendAsJson(resp, response);
    }

    /**
     * Renvoie la liste des postes de l'utilisateur
     * @param resp la réponse
     * @param req la requête
     * @throws TechniqueException
     */
    private void myPost(HttpServletResponse resp, HttpServletRequest req) throws TechniqueException {
        // Récupération de l'utilisateur connecté
        UserDTO userDto = (UserDTO) req.getAttribute(Constantes.USER_AUTH);
        if (userDto == null) {
            throw new TechniqueException("Erreur, impossible de récupérer les informations de l'utilisateur connecté.");
        }

        // Création du post
        List<PostEntity> resultSet = AUJServiceFunctionnalFactory.getInstance().getPostService()
                        .mesPosts(Long.parseLong(userDto.getId()));
        List<PostEntity> result = new ArrayList<>();
        result.addAll(resultSet);

        // Conversion des entité de post en DTO
        List<PostDTO> resultDTO = ConverterToDto.convertToListPostDTO(result);

        // Les information de retour
        TRetour tRetour = new TRetour();
        tRetour.setCodeRetour("0000000000");
        tRetour.setMessageRetour("OK");

        // La réponse
        ResponseMyPostDTO response = new ResponseMyPostDTO();
        response.settRetour(tRetour);
        response.setPosts(resultDTO);

        AbstractServlet.sendAsJson(resp, response);
    }

    @Override
    public void destroy() {
        LOG.info("Servlet " + this.getServletName() + " has stopped");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        LOG.info("Servlet " + this.getServletName() + " has started");
        Environment.initServlet(getServletContext());
        super.init();
    }

}
