package fr.stl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fr.stl.dto.UserDTO;
import fr.stl.entity.UserEntity;
import fr.stl.exceptions.TechniqueException;
import fr.stl.manager.UserManager;
import fr.stl.util.KeyGenerator;
import fr.stl.util.SimpleKeyGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Classe contenant les méthodes utilisés dans les Servlets
 */
public class AbstractServlet {

    /** Le GSON */
    private static Gson gson = new Gson();

    /** Génération d'un key */
    private static KeyGenerator keyGenerator = new SimpleKeyGenerator();

    /**
     * Envoie du Json dans la réponse
     * @param response la réponse
     * @param obj l'objet à envoyer
     * @throws IOException exception IO
     */
    public static void sendAsJson(HttpServletResponse response, Object obj) {
        response.setContentType("application/json");

        String res = gson.toJson(obj);
        
        try {
            PrintWriter out;
            out = response.getWriter();

            out.print(res);
            out.flush();
        } catch (IOException e) {
            response.setStatus(500);
        }
    }

    /**
     * Récupère le json d'une requête
     * @param request la requête
     * @return un String
     * @throws IOException exception IO
     */
    public static String getJson(HttpServletRequest request) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        String payload = buffer.toString();

        return payload;
    }

    /**
     * Créer une session et donc un token à l'utilisateur
     * @param login le login
     * @param absolutePath le path absolue
     * @return un token
     */
    public static String issueToken(String login, String absolutePath) {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder().setSubject(login).setIssuer(absolutePath).setIssuedAt(new Date())
                        .setExpiration(toDate(LocalDateTime.now().plusDays(1L)))
                        .signWith(SignatureAlgorithm.HS512, key).compact();
        return jwtToken;
    }

    /**
     * Retourne la date du jour
     * @param localDateTime le LocalDateTime
     * @return une date
     */
    private static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Retourne l'Utilisateur à partir du token
     * @param token le token
     * @return un UserDTO
     * @throws TechniqueException erreur technique
     */
    public static UserDTO parseTokenToUser(String token) throws TechniqueException {
        Key key = keyGenerator.generateKey();
        try {
            Claims body = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            
            // Recherche du User dans la base
            UserManager userManager = new UserManager();
            UserEntity userEntity = userManager.findByLogin(body.getSubject());
            UserDTO u = new UserDTO();
            u.setLogin(userEntity.getUsername());
            u.setNomInvocateur(userEntity.getRiotAccount().getPseudo());
            u.setId(String.valueOf(userEntity.getId()));
            return u;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

}
