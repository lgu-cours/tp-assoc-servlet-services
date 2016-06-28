package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.Auth;

public class SessionService {
	
	/**
	 * Get the current session.
	 * The session is created if doesn't exist.
	 * 
	 * @param request
	 * @return the current session
	 */
	public static HttpSession getSession(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		
		return session;
	}
	
	/**
	 * Set the current auth in the session
	 * 
	 * @param request
	 * @param auth
	 */
	public static void setAuth(HttpServletRequest request, Auth auth) {
		HttpSession session = getSession(request);
		
		session.setAttribute("authenticated", true);
		session.setAttribute("auth", auth);
	}
	
	/**
	 * Set current user as a guest
	 * 
	 * @param request
	 */
	public static void setGuest(HttpServletRequest request) {
		HttpSession session = getSession(request);
		session.setAttribute("authenticated", false);
		session.removeAttribute("auth");
	}
	
	/**
	 * Check if current session is set
	 * @param request
	 * @return
	 */
	public static boolean isAuth(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		
		if (session == null) {
			return false;
		}
		
		if (session.getAttribute("authenticated") == null) {
			return false;
		} else {
			return (boolean)session.getAttribute("authenticated");
		}
		
		
	}
}
