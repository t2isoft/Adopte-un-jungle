package fr.stl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.HttpHeaders;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

import fr.stl.AbstractServlet;
import fr.stl.Constantes;
import fr.stl.Environment;
import fr.stl.dto.UserDTO;
import fr.stl.dto.login.RequestLoginDTO;
import fr.stl.dto.login.ResponseLoginDTO;
import fr.stl.dto.logout.ResponseLogoutDTO;
import fr.stl.dto.signin.AccountDTO;
import fr.stl.dto.tretour.TRetour;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.AuthentificationException;
import fr.stl.exceptions.FonctionnelleException;
import fr.stl.exceptions.TechniqueException;
import fr.stl.services.AUJServiceFunctionnalFactory;
import fr.stl.util.RegexValidator;

/**
 * Servlet d'authentification
 */
public class AuthentificationServlet extends HttpServlet {
    
    /** Le logger */
    private final Logger LOG = Logger.getLogger(getClass());

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** La classe des gestion du json */
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            System.out.println(req.getRequestURL());
            switch (req.getRequestURI()) {
                case Constantes.PATH_AUTH + "/login":
                    login(req, resp);
                    break;
                case Constantes.PATH_AUTH + "/logout":
                    logout(req, resp);
                    break;
                default:
                    resp.setStatus(400);
                    break;
            }
        } catch (AuthentificationException e) {
            LOG.error(e);
            // Le retour
            TRetour tRetour = new TRetour();
            tRetour.setCodeRetour("1000000001");
            tRetour.setMessageRetour(e.getMessage());

            // La réponse
            ResponseLoginDTO response = new ResponseLoginDTO();
            response.settRetour(tRetour);
            AbstractServlet.sendAsJson(resp, response);
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        try {
            switch (req.getRequestURI()) {
                case Constantes.PATH_AUTH + "/signin":
                    // Gestion de l'inscription
                    System.out.println("Tentative d'inscription");
                    signIn(resp, req);
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
     * Création d'un compte utilisateur
     * @param resp la réponse
     * @param req la requête
     * @throws FonctionnelleException erreur fonctionnelle
     * @throws TechniqueException erreur technique
     */
    private void signIn(HttpServletResponse resp, HttpServletRequest req)
                    throws FonctionnelleException, TechniqueException {
        // Récupération des information du compte
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
        AccountDTO account = gson.fromJson(json, AccountDTO.class);

        // Vérification des données
        if (account == null) {
            throw new FonctionnelleException("Erreur, les données sont manquantes");
        }
        if (StringUtils.isBlank(account.getEmail())) {
            throw new FonctionnelleException("Erreur, l'email est manquant.");
        }
        RegexValidator regex = new RegexValidator(RegexValidator.EMAIL_PATTERN);
        if (!regex.validateValue(account.getEmail())) {
            throw new FonctionnelleException("Erreur, l'email n'est pas conforme.");
        }
        if (StringUtils.isBlank(account.getPassword())) {
            throw new FonctionnelleException("Erreur, le mot de passe est manquant.");
        }
        regex = new RegexValidator(RegexValidator.PASSWORD_PATTERN);
        if (!regex.validateValue(account.getPassword())) {
            throw new FonctionnelleException("Erreur, le mot de passe n'est pas conforme");
        }
        if (StringUtils.isBlank(account.getUsername())) {
            throw new FonctionnelleException("Erreur, votre nom d'utilisateur est incorrecte.");
        }
        if (StringUtils.isBlank(account.getNomInvocateur())) {
            throw new FonctionnelleException("Erreur, le nom d'invocateur est incorrecte.");
        }
        if (StringUtils.isBlank(account.getRole())) {
            throw new FonctionnelleException("Erreur, votre rôle est manquant.");
        }

        // Création de l'utilisateur
        AUJServiceFunctionnalFactory.getInstance().getUserService().creerCompteUtilisateur(account.getEmail(),
                        account.getPassword(), account.getUsername(), account.getNomInvocateur(), account.getRole());

        // Le retour
        TRetour tRetour = new TRetour();
        tRetour.setCodeRetour("0000000000");
        tRetour.setMessageRetour("OK");

        // La réponse
        ResponseLogoutDTO response = new ResponseLogoutDTO();
        response.settRetour(tRetour);
        AbstractServlet.sendAsJson(resp, response);
    }

    /**
     * Le connexion
     * @param req la requête
     * @param resp la reponse
     * @throws IOException erreur IO
     * @throws AuthentificationException erreur Authentification
     * @throws TechniqueException erreur technique
     * @throws FonctionnelleException erreur fonctionnelle
     */
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, AuthentificationException, TechniqueException, FonctionnelleException {
        // Vérification que l'utilisateur n'es pas déjà connecté
        UserDTO userDto = (UserDTO) req.getAttribute(Constantes.USER_AUTH);
        if (userDto != null) {
            resp.setStatus(200);
            return;
        }
        
        // Récupération du json
        String json = AbstractServlet.getJson(req);

        // Parser le Json en objet Java
        RequestLoginDTO requestLoginDto = gson.fromJson(json, RequestLoginDTO.class);
        if (requestLoginDto == null) {
            throw new AuthentificationException("Erreur, les identifiants sont incorrectes.");
        }

        // Authentifie l'utilisateur
        UserEntity userEntity = AUJServiceFunctionnalFactory.getInstance().getUserService().authenticate(requestLoginDto.getLogin(),
                        requestLoginDto.getPassword());

        // Délivre un token pour l'utilisateur
        String token = AbstractServlet.issueToken(requestLoginDto.getLogin(), req.getRequestURI());

        // Retourne un token dans la réponse ainsi que les informations du compte
        resp.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        resp.setStatus(200);
        userDto = new UserDTO();
        userDto.setNomInvocateur(userEntity.getRiotAccount().getPseudo());
        userDto.setLogin(userEntity.getUsername());
        userDto.setId(String.valueOf(userEntity.getId()));
        HttpSession session = req.getSession();
        session.setAttribute("Authorization", token);
        resp.addHeader(HttpHeaders.COOKIE, session.getId());

        // Les information de retour
        TRetour tRetour = new TRetour();
        tRetour.setCodeRetour("0000000000");
        tRetour.setMessageRetour("OK");

        // La réponse
        ResponseLoginDTO response = new ResponseLoginDTO();
        response.settRetour(tRetour);
        response.setUserDto(userDto);

        AbstractServlet.sendAsJson(resp, response);
    }

    /**
     * Invalidation de la session utilisateur
     * @param req la requête
     * @param resp la réponse
     * @throws IOException exception IO
     * @throws ServletException erreur de servlet
     */
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Récupération de l'utilisateur
        UserDTO userAuthenticated = (UserDTO) req.getAttribute(Constantes.USER_AUTH);
        
        // Déconnexion
        HttpSession session = req.getSession();
        session.removeAttribute("Authorization");
        session.invalidate();
        req.removeAttribute(Constantes.USER_AUTH);
        
        LOG.info("Déconnexion de l'utilisateur " + userAuthenticated.getLogin());

        // Le retour
        TRetour tRetour = new TRetour();
        tRetour.setCodeRetour("0000000000");
        tRetour.setMessageRetour("OK");

        // La réponse
        ResponseLogoutDTO response = new ResponseLogoutDTO();
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
