package fr.stl.filter;

import java.io.IOException;
import java.security.Key;

import javax.annotation.Priority;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import fr.stl.AbstractServlet;
import fr.stl.Constantes;
import fr.stl.util.KeyGenerator;
import fr.stl.util.SimpleKeyGenerator;
import io.jsonwebtoken.Jwts;

/**
 * Filtre les requêtes pour l'authentification
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements Filter {

    /** Le logger */
    private Logger logger = Logger.getLogger(getClass());

    /** La clé de génération */
    private KeyGenerator keyGenerator = new SimpleKeyGenerator();

    @Override
    public void init(FilterConfig filter) throws ServletException {
        // RAS
    }

    @Override
    public void doFilter(ServletRequest servReq, ServletResponse servRep, FilterChain chain)
                    throws IOException, ServletException {
        System.out.println("Filter on......");
        HttpServletRequest request = (HttpServletRequest) servReq;
        HttpServletResponse response = (HttpServletResponse) servRep;
        HttpSession session = request.getSession();
        try {
            // Vérification de la session
            String tokenFromSession = (String) session.getAttribute("Authorization");
            System.out.println("Token connected: " + tokenFromSession);
            // Get the HTTP Authorization header from the request
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader == "" || authorizationHeader.isEmpty()) {
                throw new Exception();
            }

            // Extract the token from the HTTP Authorization header
            String token = authorizationHeader.substring("Bearer".length()).trim();

            // Validate the token
            Key key = keyGenerator.generateKey();
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            logger.info("#### valid token : " + token);
            if (!token.equals(tokenFromSession)) {
                throw new Exception();
            }
            logger.info("#### valid session");

            request.setAttribute(Constantes.USER_AUTH, AbstractServlet.parseTokenToUser(token));
            chain.doFilter(request, response);
        } catch (Exception e) {
            if (request.getRequestURI().equalsIgnoreCase(Constantes.PATH_AUTH + "/login")) {
                chain.doFilter(request, response);
            } else if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                return;
            } else {
                logger.fatal("#### invalid token");
                response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
            }
        }
    }

    @Override
    public void destroy() {
        // RAS
    }

}
