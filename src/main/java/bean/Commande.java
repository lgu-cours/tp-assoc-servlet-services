package bean;

import java.util.LinkedList;

public class Commande {

	LinkedList listCommande = new LinkedList();
	
	//LinkedList listCatalogue = null ;
	
	
//	public Commande(LinkedList listCatalogue) {
//		super();
//		//this.listCatalogue = listCatalogue;
//	}

	public boolean commanderArticle(Article a )
	{
		System.out.println("Article commande : " + a.getCode() + " : " + a.getNom() );
		return listCommande.add(a);
	}

	public int getNbArticles()
	{
		return listCommande.size();
	}
	
	public LinkedList getListe()
	{
		return listCommande ;
	}
}
