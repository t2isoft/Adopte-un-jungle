package fr.stl.services;

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
     * @param server son serveur
     * @throws FonctionnelleException erreur fonctionnelle
     * @throws TechniqueException erreur technique
     */
    void creerCompteUtilisateur(String email, String password, String username, String nomInvocateur,
                    String role, String server) throws FonctionnelleException, TechniqueException;
    
}
