<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<table>
	<thead>
		<tr>
			<th>Code</th>
			<th>Nom</th>
			<th>Prix</th>
			<th>Stock</th>
			<th></th>
		</tr>
	</thead>
	<c:forEach var="article" begin="0" items="${articles}">
		<tr>
			<td>${article.code}</td>
			<td>${article.nom}</td>
			<td>${article.prix}</td>
			<td>${article.stock}</td>
			<td><a href="${pageContext.request.contextPath}/action/order?code=${article.code}">Commander</a></td>
		</tr>
	</c:forEach>
</table>
