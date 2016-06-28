<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
           
<h1>Votre commande</h1>
<c:if test="${not empty orders}">
<table>
	<thead>
		<tr>
			<th>Code</th>
			<th>Nom</th>
			<th>Prix</th>
			<th>Stock</th>
		</tr>
	</thead>
	<c:forEach var="article" begin="0" items="${orders.getListe()}">
		<tr>
			<td>${article.code}</td>
			<td>${article.nom}</td>
			<td>${article.prix}</td>
			<td>${article.stock}</td>
		</tr>
	</c:forEach>
</table>

	<p><a href="${pageContext.request.contextPath}/action/postresetorder">Annuler ma commande</a></p>
</c:if>
