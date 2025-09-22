<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style type="text/css">
body {
    font-family: Arial, sans-serif;
    background-color: #ccffff;
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
}

h1 {
    color: #333;
}

.form {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.tabela-autentifikacija {
    width: 100%;
}

.tabela-autentifikacija td {
    padding: 10px;
}

input[type="text"],
input[type="password"] {
    width: 100%;
    padding: 8px;
    margin: 5px 0 15px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
    border-radius: 4px;
}

input[type="submit"] {
    background-color: #4caf50;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #45a049;
}

a {
    text-decoration: none;
    color: #3498db;
}

a:hover {
    text-decoration: underline;
}

.alert_container {
    margin-top: 20px;
}

.alert {
    background-color: #f2dede;
    color: #a94442;
    padding: 10px;
    border: 1px solid #ebccd1;
    border-radius: 4px;
}
</style>
</head>
<body>
	
	<h1>Login</h1>
    <c:url var="loginUrl" value="/contrLogin/login" />
	<form action="${loginUrl}" method="post">
	<div class="form">
		<table class="tabela-autentifikacija">
			<tr>
				<td>Korisnicko ime:</td>
				<td><input type="text" name="username"
					placeholder="Unesite korisnicko ime" required></td>
			</tr>
			<tr>
				<td>Sifra:</td>
				<td><input type="password" name="password"
					placeholder="Unesite sifru" required></td>
			</tr>
			<tr>
				<td><input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /></td>
				<td><input type="submit" value="Prijava"></td>
			</tr>
		</table><br/><br/>
		  Nemate nalog? <a href="/Projekat/contrLogin/registracija">Registrujte se</a>
	</div>
	</form>
	<div class="alert_container">
		<c:if test="${loginError}">
            <div class="alert alert-danger">
                <p>Pogresni podaci.</p>
            </div>
        </c:if>

	</div>

</body>
</html>