package services;

import javax.servlet.http.HttpServletRequest;

public class CheckCredentialsService implements ServiceInterface {

	public boolean execute(HttpServletRequest request) {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		return (login == "admin" && password == "admin");
		
	}
	
	
}
