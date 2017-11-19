package fr.stl.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter permettant d'ajouter des paramètres au HEADER
 */
public class CORSFilter implements Filter {
    
    /** List de domains origin pour les  CORS */
    private List<String> origins = Arrays.asList("http://localhost:8080","https://adopte-un-jungle.herokuapp.com");

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filtering on...........................................................");
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		String origin = request.getHeader("Origin");
        if (origins.contains(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        }
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "*");
		response.setHeader("Access-Control-Allow-Headers", "content-type, Authorization, async, Cookie");
		response.setHeader("Access-Control-Expose-Headers", "Authorization, X-Requested-With, async, Cookie");
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

}