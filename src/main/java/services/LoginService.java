package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginService implements ServiceInterface {
	
	public boolean execute(HttpServletRequest request) {
		if (checkCredentials(request)) {
			setSession(request);
			
			return true;
		}
		
		return false;
	}
	
	private boolean checkCredentials(HttpServletRequest request) {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		if (login == null || password == null) {
			return false;
		}
		
		return (login.equals("admin") && password.equals("admin"));
	}
	
	private void setSession(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		
		session.setAttribute("authenticated", true);
		session.setAttribute("login", request.getParameter("login"));
	}
}
