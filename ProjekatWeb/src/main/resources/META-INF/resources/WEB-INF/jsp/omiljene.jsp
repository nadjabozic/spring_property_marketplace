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
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
}

table {
    width: 80%;
    border-collapse: collapse;
    margin-top: 20px;
}

table, th, td {
    border: 1px solid #ddd;
}

th, td {
    padding: 8px;
    text-align: center;
}

th {
    background-color: #f2f2f2;
}

img {
    max-width: 100%;
    height: auto;
}

.c-message {
    color: #4caf50;
    margin-top: 10px;
}

a {
    text-decoration: none;
    color: #3498db;
    display: block;
    margin-top: 20px;
}

a:hover {
    text-decoration: underline;
}

</style>
<title>Omiljene</title>
</head>
<body>

	<c:if test="${empty omiljene }">
		Nemate omiljene nekretnine!
	</c:if>
	<c:if test="${not empty omiljene }">
		<table border="1">
			<tr>
				<th>Slika</th>
				<th>Tip</th>
				<th>Kvadratura</th>
				<th>Broj spavacih soba</th>
				<th>Broj kupatila</th>
				<th>Adresa</th>
				<th>Link oglasa</th>
				<th>Obriši iz omiljenih</th>
			</tr>
			<c:forEach items="${omiljene }" var="n">
				<tr>
					<td><img src="<c:url value='/uploads/${n.slikas[0].url}'/>" width="300" height="200" alt="slika_nekretnine"></td>
					<td>${n.tipnekretnine.naziv }</td>
					<td>${n.kvadratura }</td>
					<td>${n.brojSpavacihSoba}</td>
					<td>${n.brojKupatila }</td>
					<td>${n.adresa }</td>
					<td><a href="/Projekat/controllerP/oglas?id=${n.idNekretnina }">Link</a></td>
					<td><a href="/Projekat/kupac/obrisiOmiljenu?id=${n.idNekretnina }">Obriši</a>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${not empty uspesnoObrisana }">
			${uspesnoObrisana }
		</c:if>
	</c:if>
	<br>
	<a href="/Projekat/controllerP/glavna">Povratak na početnu stranicu</a>

</body>
</html>