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
}

.nema-oglase-poruka {
    font-weight: bold;
    color: #ff6347; 
    margin-bottom: 20px;
}

.oglasi-tabela {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

.oglasi-tabela th, .oglasi-tabela td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: center;
}

.oglasi-tabela th {
    background-color: #f2f2f2; 
}

.oglasi-tabela a {
    text-decoration: none;
    color: #3498db; 
}

.oglasi-tabela a:hover {
    text-decoration: underline;
}

.uspeh-poruka {
    color: #4caf50; 
    font-weight: bold;
    margin-top: 10px;
}

.povratak-link {
    display: block;
    margin-top: 20px;
    text-decoration: none;
    color: #3498db;
}
</style>
<title>Oglasi</title>
</head>
<body>

	<c:if test="${empty nekretnineK }">
		 <p class="nema-oglase-poruka">Niste okačili nijedan oglas!</p>
	</c:if>

	<c:if test="${!empty nekretnineK }">
		<table class="oglasi-tabela" border="1">
			<tr>
				<th>Slika</th>
				<th>Cena</th>
				<th>Tip</th>
				<th>Kvadratura</th>
				<th>Broj spavacih soba</th>
				<th>Broj kupatila</th>
				<th>Adresa</th>
				<th>Link oglasa</th>
				<th>Uklanjanje oglasa</th>
			</tr>
			<c:forEach items="${nekretnineK }" var="n">
				<tr>
					<td><c:forEach var="slika" items="${n.slikas}" varStatus="loopStatus">
						<c:if test="${loopStatus.index eq 0}">
							<img src="<c:url value='/uploads/${slika.url}'/>"
								width="300px" height="200px" alt="slika_nekretnine">
						</c:if>
					</c:forEach>
					</td>
					<td>$<fmt:formatNumber value="${n.cena }" pattern="#,###" /></td>
					<td>${n.tipnekretnine.naziv }</td>
					<td>${n.kvadratura }m²</td>
					<td>${n.brojSpavacihSoba}</td>
					<td>${n.brojKupatila }</td>
					<td>${n.adresa }</td>
					<td><a href="/Projekat/prodavac/oglas?id=${n.idNekretnina }">Link</a></td>
					<td><a
						href="/Projekat/prodavac/ukloniOglas?id=${n.idNekretnina }">Ukloni
							oglas</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${not empty message }">
		 <p class="uspeh-poruka">${message }</p>
	</c:if>
	<br>
	<a class="povratak-link" href="/Projekat/controllerP/goBack">Povratak na početnu stranicu</a>

</body>
</html>