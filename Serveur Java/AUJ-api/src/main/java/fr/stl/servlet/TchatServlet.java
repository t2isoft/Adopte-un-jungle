package fr.stl.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import fr.stl.Environment;
import fr.stl.dto.tchat.TchatDataDTO;

/**
 * Servlet de tchat
 */
public class TchatServlet extends HttpServlet {

    /** Le logger */
    private final Logger LOG = Logger.getLogger(getClass());

    /** Ensemble des requétes asynchrones en attente de réponse. */
    private Queue<AsyncContext> contexts = new ConcurrentLinkedQueue<AsyncContext>();

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickname = req.getParameter("nickname");
        String message = req.getParameter("message");
        fireMessage(new TchatDataDTO(nickname, message));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.isAsyncSupported()) {
             req.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
             AsyncContext context = req.startAsync(req, resp);
             context.setTimeout(10 * 60 * 1000);
             contexts.add(context);
        } else {
            log("Les requêtes asynchrones ne sont pas supportées, dommage!");
        }
    }

    /**
     * Envoi les message aux clients en attente.
     * @param tchatData les datas du tchat
     * @throws IOException exception IO
     */
    private void fireMessage(TchatDataDTO tchatData) throws IOException {
        Gson gson = new Gson();
        while (!contexts.isEmpty()) {
            AsyncContext context = contexts.poll();
            ServletResponse response = context.getResponse();
            assert response != null;
            response.setContentType("text/json");
            Writer out = response.getWriter();
            String json = gson.toJson(tchatData);
            out.write(json);
            out.flush();
            context.complete();
        }
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
