package fr.stl.dto;

import java.io.Serializable;

/**
 * Classe contenant les informations à envoyer
 */
public class UserDTO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** Le nom d'invocateur */
    private String nomInvocateur;

    /** Le login */
    private String login;

    /** Le mail */
    private String mail;

    /** Le poste */
    private String poste;

    /** Etre en ami */
    private String statusAmitie;

    /** Les informations additionnal */
    // private String additionalInformation;

    // FIXME Utilité de donner toutes ses informations à l'utilisateur ?
    // private SummonerDTO summoner;

    /** L'ID */
    private String id;

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the user mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the user additional Information
     */
    // public String getAdditionalInformation() {
    // return additionalInformation;
    // }

    /**
     * @param additionalInformation the additionalInformation to set
     */
    // public void setAdditionalInformation(String additionalInformation) {
    // this.additionalInformation = additionalInformation;
    // }

    /**
     * @return the summoner
     */
    // public SummonerDTO getSummoner() {
    // return summoner;
    // }

    /**
     * @param summoner the summonerDTO to set
     */
    // public void setSummoner(SummonerDTO summoner) {
    // this.summoner = summoner;
    // }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nomInvocateur
     */
    public String getNomInvocateur() {
        return nomInvocateur;
    }

    /**
     * @param nomInvocateur the nomInvocateur to set
     */
    public void setNomInvocateur(String nomInvocateur) {
        this.nomInvocateur = nomInvocateur;
    }

    /**
     * @return the poste
     */
    public String getPoste() {
        return poste;
    }

    /**
     * @param poste the poste to set
     */
    public void setPoste(String poste) {
        this.poste = poste;
    }

    /**
     * @return the statusAmitie
     */
    public String getStatusAmitie() {
        return statusAmitie;
    }

    /**
     * @param statusAmitie the statusAmitie to set
     */
    public void setStatusAmitie(String statusAmitie) {
        this.statusAmitie = statusAmitie;
    }

    // TODO Ajouter Icone Invocateur
    // TODO Ajouter La liste des publications

}
