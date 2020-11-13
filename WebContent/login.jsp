<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/login.css" media="screen" />
</head>
<body>
	<div class="wrap">
		<div id="content">
			<div id="main">
				<div class="full_w">
					<form action="action" method="post">
						<table width="300px" height="150px">
							<tr>
								<td><label for="pass">Login:</label></td>
								<td><input type="text" name="login"/></td>
							</tr>
							<tr>
								<td><label for="pass">Mot de passe:</label></td>
								<td><input type="password" name="pwd"/></td>
							</tr>
							<tr>
								<td colspan="2">
									<input type="hidden" name="op" value="login" />
									<input type="submit" class="ok" id="loginButton" value="Authentification"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="footer">&raquo; <a href="http://www.matcheurosoft.ma">MATCHEUROSOFT</a> | Gestion Damandes Achat</div>
			</div>
		</div>
	</div>
</body>
</html>