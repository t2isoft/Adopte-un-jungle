package fr.stl.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.stl.AbstractServlet;
import fr.stl.Constantes;
import fr.stl.dto.ContactDTO;
import fr.stl.dto.UserDTO;
import fr.stl.dto.contact.ResponseMyContactDTO;
import fr.stl.dto.login.ResponseLoginDTO;
import fr.stl.dto.tretour.TRetour;
import fr.stl.entity.ContactEntity;
import fr.stl.exceptions.TechniqueException;
import fr.stl.services.AUJServiceFunctionnalFactory;
import fr.stl.util.ConverterToDto;

/**
 * Servlet de gestion des contacts
 */
public class ContactServlet extends HttpServlet {

    /** Le logger */
    private final Logger LOG = Logger.getLogger(getClass());

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println(req.getRequestURI());
            switch (req.getRequestURI()) {
                case Constantes.PATH_CONTACT + "/mycontact":
                    myContact(resp, req);
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
            AbstractServlet.sendAsJson(resp, tRetour);
            resp.setStatus(500);
        }
    }

    /**
     * Renvoie la liste des contacts
     * @param resp la réponse
     * @param req la requête
     * @throws TechniqueException erreur technique
     */
    private void myContact(HttpServletResponse resp, HttpServletRequest req) throws TechniqueException {
        // Récupération de l'utilisateur
        UserDTO userDto = (UserDTO) req.getAttribute(Constantes.USER_AUTH);

        // Récupération des contacts
        Long idUser = Long.parseLong(userDto.getId());
        List<ContactEntity> listeContact = AUJServiceFunctionnalFactory.getInstance().getContactService()
                        .findContactForUser(idUser);
        List<ContactDTO> listContactDto = ConverterToDto.convertToListContactDTO(listeContact, idUser);

        // Les information de retour
        TRetour tRetour = new TRetour();
        tRetour.setCodeRetour("0000000000");
        tRetour.setMessageRetour("OK");

        // La réponse
        ResponseMyContactDTO response = new ResponseMyContactDTO();
        response.settRetour(tRetour);
        response.setListContact(listContactDto);

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
        super.init();
    }

}
