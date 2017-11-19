package fr.stl.servlet;

import java.io.IOException;
import java.util.List;

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
import fr.stl.dto.ContactDTO;
import fr.stl.dto.UserDTO;
import fr.stl.dto.contact.RequestAddContactDTO;
import fr.stl.dto.contact.ResponseAddContactDTO;
import fr.stl.dto.contact.ResponseMyContactDTO;
import fr.stl.dto.contact.ResponseSuggestionDTO;
import fr.stl.dto.login.ResponseLoginDTO;
import fr.stl.dto.tretour.TRetour;
import fr.stl.entity.ContactEntity;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.FonctionnelleException;
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
                case Constantes.PATH_CONTACT + "/suggestions":
                    suggestionContact(resp, req);
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
            tRetour.setMessageRetour("Erreur, veuillez contacter un administrateur de l'API.");
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
            System.out.println(req.getRequestURI());
            switch (req.getRequestURI()) {
                case Constantes.PATH_CONTACT + "/addContact":
                    addContact(resp, req);
                    break;
                case Constantes.PATH_CONTACT + "/acceptContact":
                    acceptContact(resp, req);
                    break;
                case Constantes.PATH_CONTACT + "/deleteContact":
                    deleteContact(resp, req);
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
            tRetour.setMessageRetour("Erreur, veuillez contacter un administrateur de l'API.");
            ResponseLoginDTO response = new ResponseLoginDTO();

            // La réponse
            response.settRetour(tRetour);
            AbstractServlet.sendAsJson(resp, response);
            resp.setStatus(500);
        }
    }

    /**
     * demande d'etre en contact avec le contact reçu
     * @param resp la réponse
     * @param req la requête
     * @throws TechniqueException erreur technique
     * @throws FonctionnelleException erreur fonctionnelle
     */
    private void addContact(HttpServletResponse resp, HttpServletRequest req)
                    throws TechniqueException, FonctionnelleException {
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
        RequestAddContactDTO addContactDto = gson.fromJson(json, RequestAddContactDTO.class);

        if (addContactDto == null)
            throw new FonctionnelleException("Erreur, données manquantes.");

        if (addContactDto.getId() == null)
            throw new FonctionnelleException("Erreur, aucun utilisateur à modifier.");

        // Récupération des contacts
        Long idUser = Long.parseLong(userDto.getId());
        Long idContact = Long.parseLong(addContactDto.getId());

        AUJServiceFunctionnalFactory.getInstance().getContactService().ajouterUnContact(idUser, idContact);

        // Les information de retour
        TRetour tRetour = new TRetour();
        tRetour.setCodeRetour("0000000000");
        tRetour.setMessageRetour("OK");

        // La réponse
        ResponseAddContactDTO response = new ResponseAddContactDTO();
        response.settRetour(tRetour);

        AbstractServlet.sendAsJson(resp, response);
    }

    /**
     * Accepte d'etre en contact avec le contact reçu
     * @param resp la réponse
     * @param req la requête
     * @throws TechniqueException erreur technique
     * @throws FonctionnelleException
     */
    private void acceptContact(HttpServletResponse resp, HttpServletRequest req)
                    throws TechniqueException, FonctionnelleException {
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
        RequestAddContactDTO addContactDto = gson.fromJson(json, RequestAddContactDTO.class);

        if (addContactDto == null)
            throw new FonctionnelleException("Erreur, données manquantes.");

        if (addContactDto.getId() == null)
            throw new FonctionnelleException("Erreur, aucun utilisateur à modifier.");

        // Récupération des contacts
        Long idUser = Long.parseLong(userDto.getId());
        Long idContact = Long.parseLong(addContactDto.getId());

        AUJServiceFunctionnalFactory.getInstance().getContactService().accepterUnContact(idUser, idContact);

        // Les information de retour
        TRetour tRetour = new TRetour();
        tRetour.setCodeRetour("0000000000");
        tRetour.setMessageRetour("OK");

        // La réponse
        ResponseAddContactDTO response = new ResponseAddContactDTO();
        response.settRetour(tRetour);

        AbstractServlet.sendAsJson(resp, response);

    }

    /**
     * supprime le lien de contact avec le contact reçu
     * @param resp la réponse
     * @param req la requête
     * @throws TechniqueException erreur technique
     * @throws FonctionnelleException
     */
    private void deleteContact(HttpServletResponse resp, HttpServletRequest req)
                    throws TechniqueException, FonctionnelleException {
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
        RequestAddContactDTO addContactDto = gson.fromJson(json, RequestAddContactDTO.class);

        if (addContactDto == null)
            throw new FonctionnelleException("Erreur, données manquantes.");

        if (addContactDto.getId() == null)
            throw new FonctionnelleException("Erreur, aucun utilisateur à modifier.");

        // Récupération des contacts
        Long idUser = Long.parseLong(userDto.getId());
        Long idContact = Long.parseLong(addContactDto.getId());

        AUJServiceFunctionnalFactory.getInstance().getContactService().supprimerUnContact(idUser, idContact);

        // Les information de retour
        TRetour tRetour = new TRetour();
        tRetour.setCodeRetour("0000000000");
        tRetour.setMessageRetour("OK");

        // La réponse
        ResponseAddContactDTO response = new ResponseAddContactDTO();
        response.settRetour(tRetour);

        AbstractServlet.sendAsJson(resp, response);
    }

    /**
     * Renvoie la liste des suggestions de contact
     * @param resp la réponse
     * @param req la requête
     * @throws TechniqueException erreur technique
     */
    private void suggestionContact(HttpServletResponse resp, HttpServletRequest req) throws TechniqueException {
        // Récupération de l'utilisateur
        UserDTO userDto = (UserDTO) req.getAttribute(Constantes.USER_AUTH);
        if (userDto == null) {
            throw new TechniqueException("Erreur, impossible de récupérer les informations de l'utilisateur connecté.");
        }

        // Récupération des suggestions de contact
        List<UserEntity> listSuggestion = AUJServiceFunctionnalFactory.getInstance().getUserService()
                        .getSuggestion(Long.parseLong(userDto.getId()));

        // Conversion de la liste de Suggestion
        List<UserDTO> listUserDto = ConverterToDto.convertToListUserDTO(listSuggestion);

        // Les information de retour
        TRetour tRetour = new TRetour();
        tRetour.setCodeRetour("0000000000");
        tRetour.setMessageRetour("OK");

        // La réponse
        ResponseSuggestionDTO response = new ResponseSuggestionDTO();
        response.settRetour(tRetour);
        response.setListSuggestion(listUserDto);

        AbstractServlet.sendAsJson(resp, response);
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
        if (userDto == null) {
            throw new TechniqueException("Erreur, impossible de récupérer les informations de l'utilisateur connecté.");
        }

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
        Environment.initServlet(getServletContext());
        super.init();
    }

}
