<!DOCTYPE html>
<html>
	<head>
		<title>Title of the document</title>
	</head>

	<body>
		<form action="${pageContext.request.contextPath}/action/postlogin" method="POST">
			<label>Login</label>
			<input type="text" name="login" />
			<label>Password</label>
			<input type="password" name="password" />
			
			<input type="submit" value="Ok" />
		</form>
		
		<p>Pas encore enregistr�? <a href="">Cr�ez votre compte</a>
	</body>
</html>
