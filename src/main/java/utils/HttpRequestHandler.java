package utils;

import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import exceptions.HttpRequestHandlerException;
import exceptions.ServiceException;
import services.ArticleService;
import services.DefaultService;
import services.LoginService;
import services.LogoutService;
import services.OrderService;
import services.ResetOrderService;
import services.ServiceInterface;

public class HttpRequestHandler {
	
	/**
	 * Router instance
	 */
	private static HttpRequestHandler instance;
	
	/**
	 * List of available routes with their respective
	 * services.
	 */
	private HashMap<String, ServiceInterface> authorized_routes;
	
	/**
	 * Private Route constructor to have singleton pattern
	 * Initialize authorized routes with their service
	 */
	private HttpRequestHandler() {
		authorized_routes = new HashMap<>();

		authorized_routes.put("login", new DefaultService());
		authorized_routes.put("postlogin", new LoginService());
		authorized_routes.put("postlogout", new LogoutService());
		authorized_routes.put("accueil", new DefaultService());
		authorized_routes.put("article", new ArticleService());
		authorized_routes.put("order", new OrderService());
		authorized_routes.put("postresetorder", new ResetOrderService());
	}
	
	/**
	 * Router singleton
	 * @return instance
	 */
	public static HttpRequestHandler getInstance() {
		if (instance == null) {
			instance = new HttpRequestHandler();
		}
		
		return instance;
	}
	
	/**
	 * Set request attribute.
	 * If route is unauthorized an exception is thrown.
	 * 
	 * @param request
	 * @param config
	 * @param attribute
	 */
	public void setRequestAttribute(
			HttpServletRequest request,
			ServletConfig config,
			String attribute) throws HttpRequestHandlerException, ServiceException {
		
		request.setAttribute("page", "Accueil");
		
		if (request.getPathInfo() == null || request.getPathInfo().equals("/")) return;
			
		if (authorized_routes.containsKey(getResourceLowerCase(request))) {
			if (!getResourceLowerCase(request).equals("postlogin")
					&& !getResourceLowerCase(request).equals("postlogout")
					&& !getResourceLowerCase(request).equals("postresetorder")) {
				request.setAttribute("page", getTemplateResource(request));
			}
			
			ServiceInterface service = authorized_routes.get(getResourceLowerCase(request));

			if (!service.execute(request)) throw new ServiceException(Error.SERVICE_NOT_EXECUTED + getResourceLowerCase(request));
			
			return;
		}
		
		throw new HttpRequestHandlerException(Error.UNAUTHORIZED_ROUTE);
	}
	
	/**
	 * Route resource formatting to get template name
	 * 
	 * @param request
	 * @return template's name
	 */
	private String getTemplateResource(HttpServletRequest request) {
		String route =  getResourceLowerCase(request);
		
		return route.substring(0, 1).toUpperCase() + route.substring(1);
	}
	
	/**
	 * Route resource to lower case
	 * 
	 * @param request
	 * @return resource
	 */
	private String getResourceLowerCase(HttpServletRequest request) {
		return request.getPathInfo().substring(1).toLowerCase();
	}
}