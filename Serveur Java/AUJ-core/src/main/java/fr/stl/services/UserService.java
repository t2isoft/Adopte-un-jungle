package fr.stl.services;

import java.util.List;

import fr.stl.entity.UserEntity;
import fr.stl.exceptions.AuthentificationException;
import fr.stl.exceptions.FonctionnelleException;
import fr.stl.exceptions.TechniqueException;

/**
 * Interface des méthodes de type UserService
 */
public interface UserService {

    /**
     * Vérifie le login et le mot de passe passé en paramètre
     * @param login le login
     * @param password le password
     * @return un UserEntity
     * @throws TechniqueException erreur technique
     * @throws AuthentificationException erreur authentification
     */
    UserEntity authenticate(final String login, final String password) throws TechniqueException, AuthentificationException;

    /**
     * Créer un utilisateur à partir de ses informations
     * @param email son email
     * @param password son mot de passe
     * @param username son nom d'utilisateur
     * @param nomInvocateur son nom d'invocateur
     * @param rank son rang
     * @param role son rôle
     * @throws FonctionnelleException erreur fonctionnelle
     * @throws TechniqueException erreur technique
     */
    void creerCompteUtilisateur(String email, String password, String username, String nomInvocateur,
                    String role) throws FonctionnelleException, TechniqueException;

    /**
     * Récupères un liste de suggestion
     * @param idUser id du User
     * @return une liste de User
     * @throws TechniqueException 
     */
    List<UserEntity> getSuggestion(long idUser) throws TechniqueException;
    
    /**
     * Mise à jour du compte avec les nouvelles informations de compte
     * @param userEntity1 l'utilisateur qui va être modifié
     * @param userEntity2 l'entité utilisateur contenant le nouvelles informations de compte
     * @return true if update and false if not updated
     * @throws FonctionnelleException erreur fonctionnelle
     * @throws TechniqueException erreur technique
     */
    boolean miseAJourCompteUtilisateur(UserEntity userEntity1, UserEntity userEntity2) throws FonctionnelleException, TechniqueException;

    /**
     * Mise à jour du mot de passe de l'utilisateur ayant pour identifiant id
     * @param id de l'utilisateur qui souhaite modifier son mot de passe
     * @param password le nouveau mot de passe
     * @throws TechniqueException  erreur technique
     * @throws FonctionnelleException 
     */
    void miseAJourMotDePasse(long id, String password) throws TechniqueException, FonctionnelleException;

    /**
     * Récupère l'utilisateur qui possède l'id en paramètre
     * @param id id de l'utilisateur recherché
     * @return la UserEntity de l'utilisateur avec l'identifiant id
     * @throws TechniqueException erreur technique
     */
    UserEntity getProfil(long id) throws TechniqueException ;
    
}
