package fr.stl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.stl.AbstractServlet;
import fr.stl.Constantes;
import fr.stl.dto.UserDTO;
import fr.stl.dto.login.ResponseLoginDTO;
import fr.stl.dto.tretour.TRetour;

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
            AbstractServlet.sendAsJson(resp, tRetour);
            resp.setStatus(500);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        try {
            System.out.println(req.getRequestURL());
            switch (req.getRequestURI()) {
                case Constantes.PATH_USER + "/updateaccount":
                    // TODO Gestion de la maj de compte
                    System.out.println("Tentative de maj d'un compte");
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
            AbstractServlet.sendAsJson(resp, tRetour);
            resp.setStatus(500);
        }
    }

    /**
     * Renvoie les informations du compte
     * @param resp la réponse
     * @param req la requête
     * @throws IOException erreur IO
     */
    private void myAccount(HttpServletResponse resp, HttpServletRequest req) {
        // Récupération de l'utilisateur
        UserDTO userDto = (UserDTO) req.getAttribute(Constantes.USER_AUTH);
        AbstractServlet.sendAsJson(resp, userDto);
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
