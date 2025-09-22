<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registracija</title>
<style type="text/css">
body {
    font-family: Arial, sans-serif;
    background-color: #ccffff;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 200vh;
}

.tabela-autentifikacija {
    width: 100%;
    height: 190vh;
    background-color: #fff;
    padding: 15px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.tabela-autentifikacija td {
    padding: 10px;
}

.tabela-autentifikacija .link-container {
    margin-top: 10px;
}

.link-container a {
    display: block;
    margin-top: 5px;
}

form input[type="text"],
form input[type="password"],
form input[type="submit"],
form select {
    width: calc(100% - 20px);
    padding: 8px;
    margin: 5px 0 15px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
    border-radius: 4px;
}

form input[type="submit"] {
    background-color: #4caf50;
    color: white;
    cursor: pointer;
}

form input[type="submit"]:hover {
    background-color: #45a049;
}

.error {
    border: 1px solid #ff0000; 
    background-color: #ffe6e6; 
    padding: 10px;
    margin-bottom: 10px;
}

.error p {
    color: #ff0000;
    margin: 0;
}

.inputC {
    position: relative;
}

.inputC .error {
    position: absolute;
    top: 100%;
    left: 0;
    margin-top: 5px;
    margin-bottom: 5px;
    z-index: 1;
}

.inputC input,
.inputC select {
    width: calc(100% - 20px);
    padding: 8px;
    margin: 5px 0 15px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
    border-radius: 4px;
    margin-bottom: 25px;
}

.inputC .error {
    position: absolute;
    top: 100%;
    left: 0;
    margin-top: 5px;
}

</style>
</head>
<body>
	<form:form modelAttribute="korisnik" action="/Projekat/contrLogin/sacuvajKorisnika" method="post">
		<table class="tabela-autentifikacija">
			<tr>
				<td>Ime:</td>
				<td>
					<form:input path="ime" />
					<div class="inputC"><form:errors path="ime" cssClass="error" /></div>
				</td>
			</tr>
			<tr>
				<td>Prezime:</td>
				<td>
					<form:input path="prezime" />
					<div class="inputC"><form:errors path="prezime" cssClass="error" /></div>
				</td>
			</tr>
			<tr>
				<td>Email:</td>
				<td>
					<form:input path="email" />
					<div class="inputC"><form:errors path="email" cssClass="error" /></div>
				</td>
			</tr>
			<tr>
				<td>Korisničko ime:</td>
				<td>
					<form:input path="username" />
					<div class="inputC"><form:errors path="username" cssClass="error" /></div>
				</td>
			</tr>
			<tr>
				<td>Sifra:</td>
				<td>
					<form:password path="password" />
					<div class="inputC"><form:errors path="password" cssClass="error" /></div>
				</td>
			</tr>
			<tr>
				<td>Uloga:</td>
				<td>
					<form:select path="uloga" items="${uloge}"
						itemValue="idUloga" itemLabel="naziv" />
					<div class="inputC"><form:errors path="uloga" cssClass="error" /></div>	
				</td>
			</tr>
			<tr>
				<td />
				<td><input type="submit" value="Sacuvaj">
			</tr>
			<tr class="link-container">
    			<td colspan="2">
        			<a href="/Projekat/contrLogin/login">Vrati se na login stranicu</a>
    			</td>
			</tr>
		</table>
	</form:form>
	<c:if test="${not empty errors}">
		<div class="error">
			<c:forEach items="${errors.allErrors}" var="error">
				<p>${error.defaultMessage}</p>
			</c:forEach>
		</div>
	</c:if>

</body>
</html>