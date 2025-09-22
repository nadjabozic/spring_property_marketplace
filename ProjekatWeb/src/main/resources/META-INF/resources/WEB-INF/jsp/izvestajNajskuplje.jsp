<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
body {
    font-family: Arial, sans-serif;
    background-color: #ccffff;
    margin: 0;
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

p {
    font-size: 18px;
    color: #333;
}

form {
    margin-top: 10px;
}

input[type="number"],
select {
    padding: 8px;
    margin-right: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

input[type="submit"] {
    background-color: #4caf50;
    color: white;
    padding: 8px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #45a049;
}

.alert {
    background-color: #f2dede;
    color: #a94442;
    padding: 10px;
    border: 1px solid #ebccd1;
    border-radius: 4px;
    margin-top: 10px;
}

a {
    text-decoration: none;
    color: #3498db;
    margin-top: 20px;
}

a:hover {
    text-decoration: underline;
}
</style>
<title>Izveštaj o cenama po tipu</title>
</head>
<body>

	<p>Tip nekretnine</p>
	<form action="/Projekat/admin/getIzvestaj2" method="get">
		<input type="number" name="cena" placeholder="Cena" value="cena">
		<select name="selectovani">
			<c:forEach items="${tipovi}" var="t">
				<option value="${t.naziv}">${t.naziv}</option>
			</c:forEach>
		</select> <input type="submit" value="Kreiraj izveštaj">
	</form>


	<br>

	<c:if test="${not empty greskaIzvestaj2}">
		<div class="alert alert-danger">${greskaIzvestaj2}</div>
	</c:if>


	<br>
    <a href="/Projekat/controllerP/glavna">Povratak na glavnu stranicu</a>

</body>
</html>