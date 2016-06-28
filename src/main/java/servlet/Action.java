package servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.HttpRequestHandlerException;
import exceptions.ServiceException;

import javax.servlet.annotation.WebInitParam;

import services.SessionService;
import utils.Forwarder;
import utils.HttpRequestHandler;

/**
 * Servlet configuration
 */
@WebServlet(
		loadOnStartup=1,
		urlPatterns = "/action/*",
		initParams = {
				@WebInitParam(name = "template", value = "Template.jsp"),
		}
)
public class Action extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Logger log = Logger.getAnonymousLogger();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Action() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	/**
	 * Process GET and POST requests
	 */
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpRequestHandler httpRequestHandler = HttpRequestHandler.getInstance();
		
		try {
			httpRequestHandler.setRequestAttribute(request, getServletConfig(), "page");
		} catch (HttpRequestHandlerException httpException) {
			response.sendError(404, "Page not found.'");
			return;
		} catch (ServiceException serviceException) {
			// write some logs to monitor our app... in real world
			log.warning(serviceException.getMessage());
		}
		
		if (SessionService.isAuth(request)) {
			Forwarder.forward(request, response,  "/WEB-INF/jsp/" + getInitParameter("template"));
		} else {
			Forwarder.forward(request, response,  "/WEB-INF/jsp/Login.jsp");
		}
	}
}
