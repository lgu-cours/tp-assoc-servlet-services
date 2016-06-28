package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutService implements ServiceInterface {

	public boolean execute(HttpServletRequest request) {

		return destroySession(request);
	}
	
	private boolean destroySession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			session.invalidate();
		}
		
		return request.getSession(false) == null;
	}
	
}
