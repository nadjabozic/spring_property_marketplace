<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
body {
    font-family: Arial, sans-serif;
    background-color: #ccffff;
    margin: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

p {
    font-size: 1.5em;
    color: #007bff;
}

form {
    margin-bottom: 20px;
}

label {
    display: block;
    margin-top: 10px;
}

form margin-bottom: 20px;
}

label {
    display: block;
    margin-top: 10px;
}

form select, form input[type="date"], form input[type="submit"] {
    margin-bottom: 10px;
    padding: 5px;
    width: 150px;
}

form input[type="submit"] {
    background-color: #007bff;
    color: #fff;
    cursor: pointer;
}

.alert {
    margin-top: 10px;
    padding: 10px;
    background-color: #dc3545;
    color: #fff;
    border-radius: 5px;
}

a {
    text-decoration: none;
    color: #007bff;
}

a:hover {
    text-decoration: underline;
}

</style>
<title>Izvestaj obilazaka</title>
</head>
<body>

	<p>Tip nekretnine</p>
	<form action="/Projekat/admin/getIzvestaj3" method="get">
		<input type="date" name="datum"
				value='<fmt:formatDate
								pattern="yyyy-MM-dd" value="${today}"/>' />
		<select name="selectovani">
			<c:forEach items="${tipovi}" var="t">
				<option value="${t.naziv}">${t.naziv}</option>
			</c:forEach>
		</select> <input type="submit" value="Kreiraj izveštaj">
	</form>


	<br>

	<c:if test="${not empty greskaIzvestaj3}">
		<div class="alert alert-danger">${greskaIzvestaj3}</div>
	</c:if>


	<br>
    <a href="/Projekat/controllerP/glavna">Povratak na glavnu stranicu</a>

</body>
</html>