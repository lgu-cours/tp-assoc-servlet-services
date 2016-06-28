package services;

import javax.servlet.http.HttpServletRequest;

import bean.ListeArticles;

public class ArticleService implements ServiceInterface {
	
	public boolean execute(HttpServletRequest request) {
		setArticles(request);
		
		return (request.getAttribute("articles") != null);
	}
	
	private void setArticles(HttpServletRequest request) {
		request.setAttribute("articles", ListeArticles.getList());
	}
}
