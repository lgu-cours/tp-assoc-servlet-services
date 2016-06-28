package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.Article;
import bean.Commande;
import bean.ListeArticles;

public class OrderService implements ServiceInterface {
	
	/**
	 * user's session
	 */
	HttpSession session;

	@Override
	public boolean execute(HttpServletRequest request) {
		session = SessionService.getSession(request);
		
		if (request.getParameter("code") == null) return false;
		
		/**
		 * Return if order creation is
		 * a success.
		 */
		return createOrder(request);
	}
	
	/**
	 * Store new order inside user's session
	 * @param request
	 */
	private boolean createOrder(HttpServletRequest request) {
		Commande orders = null;
		
		if (session.getAttribute("orders") == null) {
			orders = new Commande();
			session.setAttribute("orders", orders);
		}
		
		// get orders by reference
		orders = (Commande) session.getAttribute("orders");
		
		// check if article exists
		Article article = ListeArticles.chercherArticle(ListeArticles.getList(), request.getParameter("code"));
		if (article == null) return false;

		if (articleAlreadyOrdered(orders, article)) return false;
		
		return orders.commanderArticle(article);
	}
	
	/**
	 * Check if the specified article is not already ordered
	 * 
	 * @param orders
	 * @param a
	 * @return
	 */
	private boolean articleAlreadyOrdered(Commande orders, Article a) {
		Article article = ListeArticles.chercherArticle(orders.getListe(), a.getCode());
		
		return article != null;
	}
}
