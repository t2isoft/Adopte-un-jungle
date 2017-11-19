package fr.stl.util;

import java.security.MessageDigest;
import java.util.Base64;

import fr.stl.exceptions.TechniqueException;

/**
 * Classe de gestion de mots de passe
 */
public class PasswordUtils {

    /**
     * Retourne un mot de passe crypté
     * @param plainTextPassword mot de passe
     * @return mot de passe crypté
     * @throws TechniqueException erreur technique
     */
    public static String cryptSha1(String plainTextPassword) throws TechniqueException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plainTextPassword.getBytes("UTF-8"));
            byte[] passwordDigest = md.digest();
            return new String(Base64.getEncoder().encode(passwordDigest));
        } catch (Exception e) {
            throw new TechniqueException("Exception encoding password", e);
        }
    }
    
    /**
     * Vérifie si l'inputpassword est égale au password
     * @param password le mot de passe
     * @param inputpassword le mot de passe à vérifier
     * @return un boolean
     * @throws TechniqueException erreur technique
     */
    public static boolean decodeSha1(String password, String inputpassword) throws TechniqueException {
        return cryptSha1(inputpassword).equals(password) ? true : false;
    }
}
