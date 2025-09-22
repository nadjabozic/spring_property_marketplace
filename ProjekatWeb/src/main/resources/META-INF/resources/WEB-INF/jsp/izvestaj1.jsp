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
<title>Izvestaj</title>
</head>
<body>

	<p>Grad:</p>
	<form action="/Projekat/admin/getIzvestaj" method="get">
		<select name="selectovani">
			<c:forEach items="${gradovi}" var="g">
				<option value="${g.naziv}">${g.naziv}</option>
			</c:forEach>
		</select> <input type="submit" value="Kreiraj izveštaj">
	</form>


	<br>

	<c:if test="${not empty greskaIzvestaj1}">
		<div class="alert alert-danger">${greskaIzvestaj1}</div>
	</c:if>


	<br>
    <a href="/Projekat/controllerP/glavna">Povratak na glavnu stranicu</a>

</body>
</html>