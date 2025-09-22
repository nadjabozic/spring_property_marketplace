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

h4 {
    color: #007bff;
}

form {
    margin-bottom: 20px;
}

label {
    display: block;
    margin-top: 10px;
}

form select, form input[type="number"], form input[type="submit"] {
    margin-bottom: 10px;
    padding: 5px;
    width: 150px;
}

form input[type="submit"] {
    background-color: #007bff;
    color: #fff;
    cursor: pointer;
}

h3 {
    color: #007bff;
}

table {
    border-collapse: collapse;
    width: 70%;
    height: 70%;
    margin-top: 20px;
}

table, th, td {
    border: 1px solid #ddd;
}

th, td {
    padding: 10px;
    text-align: center;
}

.slicice img {
    width: 20%;
    height: 20%;
    vertical-align: middle;
}

a {
    text-decoration: none;
    color: #007bff;
}

a:hover {
    text-decoration: underline;
}

.no-results-found {
    color: #dc3545;
    font-weight: bold;
    margin-top: 10px;
}
</style>
<title>Pretraga</title>
</head>
<body>

	<h4>Filteri pretrage</h4>
	<form:form action="/Projekat/controllerP/pretraga" method="post"
		modelAttribute="searchForm">
		<label for="tip">Tip nekretnine:</label>
		<form:select path="tip">
			<c:forEach items="${tipovi }" var="t">
				<option value="${t.naziv}">${t.naziv }</option>
			</c:forEach>
		</form:select>
		<br>
		<label for="grad">Grad:</label>
		<form:select path="grad">
			<c:forEach items="${gradovi }" var="g">
				<option value="${g.naziv}">${g.naziv }</option>
			</c:forEach>
		</form:select>
		<br>
		<label for="minPrice">Najmanja cena:</label>
		<input type="number" id="minPrice" name="minPrice">
		<label for="maxPrice">Najveća cena:</label>
		<input type="number" id="maxPrice" name="maxPrice">
		<label for="minSize">Najmanja kvadratura:</label>
		<input type="number" id="minSize" name="minSize">
		<label for="maxSize">Najveća kvadratura:</label>
		<input type="number" id="maxSize" name="maxSize">
		<label for="minBedrooms">Najmanji broj spavaćih soba:</label>
		<input type="number" id="minBedrooms" name="minBedrooms">
		<label for="minBathrooms">Najmanji broj kupatila:</label>
		<input type="number" id="minBathrooms" name="minBathrooms"> <br>
		<input type="submit" value="Search">
	</form:form>
	<br>
	<c:if test="${not empty porukaRezultati }">
		<span class="no-results-found">${porukaRezultati }</span>
	</c:if> <br>
	<c:if test="${not empty searchResults}">
		<h3>Rezultati pretrage</h3>
		<table border="1" width="70%" height="70%">
			<c:forEach items="${searchResults }" var="sr">
				<tr>
					<c:forEach var="slika" items="${sr.slikas}" varStatus="loopStatus">
						<c:if test="${loopStatus.index eq 0}">
							<td colspan="8"><img
								src="<c:url value='/uploads/${slika.url}'/>" width="100%"
								height="100%" alt="slika_nekretnine"></td>
						</c:if>
					</c:forEach>
				</tr>
				<tr>
					<td>${sr.grad.naziv }</td>
					<td>${sr.tipnekretnine.naziv }</td>
					<td>$<fmt:formatNumber value="${sr.cena }" pattern="#,###" /></td>
					<td>${sr.kvadratura }m²</td>
					<td class="slicice"><img class="slicice"
						src="<c:url value='/Slicice/OIP.jpg'/>" width="20%" height="20%"
						alt="br_spavacih"> ${sr.brojSpavacihSoba}</td>
					<td class="slicice"><img class="slicice"
						src="<c:url value='/Slicice/kupatilo.jpg'/>" width="20%"
						height="20%" alt="br_kuatila">${sr.brojKupatila }</td>
					<td>Adresa: ${sr.adresa }</td>
					<td><a
						href="/Projekat/controllerP/oglas?id=${sr.idNekretnina }">Link</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<br>
	<a href="/Projekat/controllerP/glavna">Povratak na glavnu stranicu</a>

</body>
</html>