package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ResetOrderService implements ServiceInterface {
	
	/**
	 * user's session
	 */
	HttpSession session;
	
	@Override
	public boolean execute(HttpServletRequest request) {
		session = SessionService.getSession(request);
		
		return resetOrder();
	}
	
	/**
	 * Reset user's current order
	 */
	public boolean resetOrder() {
		session.removeAttribute("orders");

		return session.getAttribute("orders") == null;
	}

}
