package fr.stl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

import fr.stl.AbstractServlet;
import fr.stl.Constantes;
import fr.stl.Environment;
import fr.stl.dto.UserDTO;
import fr.stl.dto.login.ResponseLoginDTO;
import fr.stl.dto.tretour.TRetour;
import fr.stl.dto.user.RequestUpdateAccountDTO;
import fr.stl.dto.user.RequestUpdatePasswordDTO;
import fr.stl.dto.user.RequestWatchProfilDTO;
import fr.stl.dto.user.ResponseMyAccountDTO;
import fr.stl.dto.user.ResponseUpdateAccountDTO;
import fr.stl.dto.user.ResponseUpdatePasswordDTO;
import fr.stl.dto.user.ResponseWatchProfilDTO;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.FonctionnelleException;
import fr.stl.exceptions.TechniqueException;
import fr.stl.services.AUJServiceFunctionnalFactory;
import fr.stl.util.ConverterToDto;
import fr.stl.util.ConverterToEntity;

/**
 * Servlet de gestion utilisateur
 */
public class UserServlet extends HttpServlet {

    /** Le logger */
    private final Logger LOG = Logger.getLogger(getClass());

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            System.out.println(req.getRequestURL());
            switch (req.getRequestURI()) {
                case Constantes.PATH_USER + "/myaccount":
                    myAccount(resp, req);
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
            tRetour.setMessageRetour("Erreur, veuillez contacter un administrateur de l'API.");
            ResponseLoginDTO response = new ResponseLoginDTO();

            // La réponse
            response.settRetour(tRetour);
            AbstractServlet.sendAsJson(resp, response);
            resp.setStatus(500);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        try {
            System.out.println(req.getRequestURL());
            switch (req.getRequestURI()) {
                case Constantes.PATH_USER + "/updateAccount":
                    updateAccount(resp, req);
                    break;
                case Constantes.PATH_USER + "/updatePassword":
                    updatePassword(resp, req);
                    break;
                case Constantes.PATH_USER + "/resetPassword":
                    resetPassword(resp, req);
                    break;
                default:
                    resp.setStatus(400);
                    break;
            }
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
        } catch (TechniqueException e) {
            LOG.error(e);
            // Le retour
            TRetour tRetour = new TRetour();
            tRetour.setCodeRetour("2000000001");
            tRetour.setMessageRetour(e.getMessage());

            // La réponse
            ResponseLoginDTO response = new ResponseLoginDTO();
            response.settRetour(tRetour);
            AbstractServlet.sendAsJson(resp, tRetour);
        } catch (Exception e) {
            LOG.error(e);
            // Le retour
            TRetour tRetour = new TRetour();
            tRetour.setCodeRetour("2000000000");
            tRetour.setMessageRetour("Erreur, veuillez contacter un administrateur de l'API.");
            ResponseLoginDTO response = new ResponseLoginDTO();

            // La réponse
            response.settRetour(tRetour);
            AbstractServlet.sendAsJson(resp, response);
            resp.setStatus(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println(req.getRequestURL());
            switch (req.getRequestURI()) {
                case Constantes.PATH_USER + "/watchProfil":
                    watchProfil(resp, req);
                    break;
                default:
                    resp.setStatus(400);
                    break;
            }
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
        } catch (TechniqueException e) {
            LOG.error(e);
            // Le retour
            TRetour tRetour = new TRetour();
            tRetour.setCodeRetour("2000000001");
            tRetour.setMessageRetour(e.getMessage());

            // La réponse
            ResponseLoginDTO response = new ResponseLoginDTO();
            response.settRetour(tRetour);
            AbstractServlet.sendAsJson(resp, tRetour);
        } catch (Exception e) {
            LOG.error(e);
            // Le retour
            TRetour tRetour = new TRetour();
            tRetour.setCodeRetour("2000000000");
            tRetour.setMessageRetour("Erreur, veuillez contacter un administrateur de l'API.");
            ResponseLoginDTO response = new ResponseLoginDTO();

            // La réponse
            response.settRetour(tRetour);
            AbstractServlet.sendAsJson(resp, response);
            resp.setStatus(500);
        }
    }

    /**
     * Renvoie les informations du compte
     * @param resp la réponse
     * @param req la requête
     * @throws TechniqueException erreur technique
     * @throws IOException erreur IO
     */
    private void myAccount(HttpServletResponse resp, HttpServletRequest req) throws TechniqueException {
        // Récupération de l'utilisateur
        UserDTO userDto = (UserDTO) req.getAttribute(Constantes.USER_AUTH);

        if (userDto == null) {
            throw new TechniqueException("Erreur, impossible de récupérer les informations de l'utilisateur connecté.");
        }

        // Ajout des informations complémentaires
        UserEntity userEntity = AUJServiceFunctionnalFactory.getInstance().getUserService()
                        .getProfil(Long.parseLong(userDto.getId()));

        // Converter
        userDto = ConverterToDto.convertUserToMyProfil(userEntity);

        // Les informations de retour
        TRetour tRetour = new TRetour();
        tRetour.setCodeRetour("0000000000");
        tRetour.setMessageRetour("OK");

        // La réponse
        ResponseMyAccountDTO response = new ResponseMyAccountDTO();
        response.settRetour(tRetour);
        response.setUserDto(userDto);
        AbstractServlet.sendAsJson(resp, response);
    }

    /**
     * Mise à jour des informations de compte
     * @param resp la réponse
     * @param req la requête
     * @throws TechniqueException erreur technique
     * @throws FonctionnelleException erreur fonctionnelle
     */
    private void updateAccount(HttpServletResponse resp, HttpServletRequest req)
                    throws TechniqueException, FonctionnelleException {
        // Récupération de l'utilisateur
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
        RequestUpdateAccountDTO updatedAccountDto = gson.fromJson(json, RequestUpdateAccountDTO.class);

        if (updatedAccountDto == null)
            throw new FonctionnelleException("Erreur, données manquantes.");

        if (updatedAccountDto.getUserDTO() == null)
            throw new FonctionnelleException("Erreur, aucun utilisateur à modifier.");

        // Conversion du UserDTO en un UserEntity
        UserEntity userEntity = ConverterToEntity.convertToUserEntity(userDto);
        UserEntity updatedUserEntity = ConverterToEntity.convertToUserEntity(updatedAccountDto.getUserDTO());

        AUJServiceFunctionnalFactory.getInstance().getUserService().miseAJourCompteUtilisateur(userEntity,
                        updatedUserEntity);

        // Les information de retour
        TRetour tRetour = new TRetour();
        tRetour.setCodeRetour("0000000000");
        tRetour.setMessageRetour("OK");

        // La réponse
        ResponseUpdateAccountDTO response = new ResponseUpdateAccountDTO();
        response.settRetour(tRetour);

        AbstractServlet.sendAsJson(resp, response);
    }

    /**
     * Mise à jour du mot de passe
     * @param resp la réponse
     * @param req la requête
     * @throws TechniqueException erreur technique
     * @throws FonctionnelleException erreur fonctionnelle
     */
    private void updatePassword(HttpServletResponse resp, HttpServletRequest req)
                    throws TechniqueException, FonctionnelleException {
        // Récupération de l'utilisateur
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
        RequestUpdatePasswordDTO updatedAccountDto = gson.fromJson(json, RequestUpdatePasswordDTO.class);

        long userId = Long.parseLong(userDto.getId());

        AUJServiceFunctionnalFactory.getInstance().getUserService().miseAJourMotDePasse(userId,
                        updatedAccountDto.getPassword());

        // Les information de retour
        TRetour tRetour = new TRetour();
        tRetour.setCodeRetour("0000000000");
        tRetour.setMessageRetour("OK");

        // La réponse
        ResponseUpdatePasswordDTO response = new ResponseUpdatePasswordDTO();
        response.settRetour(tRetour);

        AbstractServlet.sendAsJson(resp, response);
    }

    /**
     * Réinitialisation mot de passe
     * @param resp la réponse
     * @param req la requête
     */
    private void resetPassword(HttpServletResponse resp, HttpServletRequest req) {
        /**
         * 
         */
    }

    /**
     * Regarde le profil
     * @param resp la réponse
     * @param req la requête
     * @throws FonctionnelleException erreur fonctionnelle
     * @throws TechniqueException erreur technique
     */
    private void watchProfil(HttpServletResponse resp, HttpServletRequest req)
                    throws FonctionnelleException, TechniqueException {
        // Récupération de l'utilisateur
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
        RequestWatchProfilDTO watchProfilDto = gson.fromJson(json, RequestWatchProfilDTO.class);

        long userId = Long.parseLong(watchProfilDto.getId());

        UserEntity userConnected = AUJServiceFunctionnalFactory.getInstance().getUserService().getProfil(Long.parseLong(userDto.getId()));
        
        UserEntity profilToWatch = AUJServiceFunctionnalFactory.getInstance().getUserService().getProfil(userId);
        UserDTO profilToWatchDto = ConverterToDto.convertUserToWatchProfil(profilToWatch, userConnected);

        ResponseWatchProfilDTO response = new ResponseWatchProfilDTO();

        // Les information de retour
        TRetour tRetour = new TRetour();
        tRetour.setCodeRetour("0000000000");
        tRetour.setMessageRetour("OK");

        response.setUser(profilToWatchDto);
        response.settRetour(tRetour);

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
