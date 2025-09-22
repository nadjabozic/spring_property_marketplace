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
    margin: 0;
    padding: 20px;
    align-items: center;
}

img {
    max-width: 300px;
    height: 200px;
    margin-bottom: 10px;
    transition: transform 0.3s ease-in-out;
}

.slicice {
	width: 20px;
	height: 20px;
	vertical-align: middle;
    margin-right: 5px;
}

img:hover {
    transform: scale(1.1);
}

a {
    text-decoration: none;
    color: #3498db;
    margin-top: 10px;
    display: block;
}

a:hover {
    text-decoration: underline;
}

.secured-content {
    margin-top: 10px;
}

p {
    font-size: 16px;
    line-height: 1.6;
    color: #333;
}

span {
    font-weight: bold;
    color: #007bff;
}

br {
    margin-bottom: 10px;
}

h1 {
    color: #343a40;
    margin-bottom: 20px;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

td {
	text-align: center;
}

.property-info td {
    padding: 8px;
    border-bottom: 1px solid #ddd;
}

.property-info span {
    font-weight: bold;
    color: #28a745;
}

.property-info .slicice {
    margin-right: 5px;
}

.opis-container {
    margin-top: 20px;
}

.opis-container span {
    display: block;
    margin-top: 10px;
    font-style: italic;
    color: #555;
}

.opis-container span::before {
    content: '\2014 \0020';
    color: #007bff;
    font-weight: bold;
}

.contact-info {
    margin-top: 20px;
    font-weight: bold;
    color: #007bff;
}

</style>
<title>Oglas</title>
</head>
<body>

	<h1>Oglas nekretnine</h1>
	
	
	<c:forEach items="${nekretnina.slikas}" var="s">
        <img src="<c:url value='/uploads/${s.url}'/>" width="300" height="200" alt="slika_nekretnine">
    </c:forEach> <br>
	<table class="property-info" border="1">
		<tr>
			<td>Cena: <span>$<fmt:formatNumber value="${nekretnina.cena }" pattern="#,###" /></span></td>
			<td>Tip nekretnine:
			<span>${nekretnina.tipnekretnine.naziv}</span></td>
			<td colspan="3">Mesto: <span>${nekretnina.grad.naziv}</span></td>
		</tr>
		<tr>
			<td>Kvadratura: <span>${nekretnina.kvadratura} m²</span></td>
			<td><img class="slicice" src="<c:url value='/Slicice/OIP.jpg'/>"
				width="30px" height="20px" alt="br_spavacih">
			<span>${nekretnina.brojSpavacihSoba}
					spavaćih soba</span></td>
			<td><img class="slicice"
				src="<c:url value='/Slicice/kupatilo.jpg'/>" width="20px"
				height="10px" alt="br_kuatila">
			<span>${nekretnina.brojKupatila}
					kupatila</span></td>
		</tr>
		<tr>
			<td colspan="5">Adresa: <span>${nekretnina.adresa}</span></td>
		</tr>
	</table>
	<br><br>
	<div class="opis-container">
		<p>O nekretnini:</p>
		<span>${nekretnina.opis}</span>
	</div>

	<p class="contact-info">
		Kontakt: <span>${nekretnina.prodavac.email}</span> <br>
	</p>
	
	<sec:authorize access="hasRole('KUPAC')">
		<a href="/Projekat/kupac/dodajOmiljene?id=${nekretnina.idNekretnina }">
			Dodaj u omiljene
		</a>
		<br>
		<c:if test="${not empty uspesnoDodato }">
        	${uspesnoDodato }
    	</c:if>

    	<form action="<c:url value='/kupac/zakazi' />" method="post">
            <input type="hidden" name="nekretnina" value="${nekretnina.idNekretnina}"/>
            <label for="datum">Izaberi datum:</label>
            <input type="date" name="datum"/>
            <input type="submit" value="Zakaži obilazak"/>
        </form>

        <c:if test="${not empty messageZakazivanje}">
            <p class="error">${messageZakazivanje}</p>
        </c:if>
        <c:if test="${not empty messageUspeh}">
            <p class="uspeh">${messageUspeh}</p>
        </c:if>
	</sec:authorize>
	
	<br>
    <a href="/Projekat/controllerP/glavna">Povratak na početnu stranicu</a>

</body>
</html>