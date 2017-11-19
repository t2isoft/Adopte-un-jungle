package fr.stl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.stl.Environment;

/**
 * Servlet de test
 */
public class TestServlet extends HttpServlet {
    
    /** Le logger */
    private final Logger LOG = Logger.getLogger(getClass());

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("Hello World!");
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
