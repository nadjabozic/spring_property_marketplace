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
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
}

h1 {
    color: #333;
}

h2 {
    color: #333;
    font-size: 24px;
    margin-bottom: 20px;
    text-align: center;
}

a {
    text-decoration: none;
    color: #3498db;
    display: block;
    margin-bottom: 10px;
}

a:hover {
    text-decoration: underline;
}

table {
	background-color: #f2f2f2;
    width: 60%;
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

.slicice {
	vertical-align: middle;
    margin-right: 5px;
}

.alert {
    color: #a94442;
    background-color: #f2dede;
    border: 1px solid #ebccd1;
    border-radius: 4px;
    padding: 10px;
    margin-top: 20px;
}

.page-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.page-header a {
    display: inline-block;
    padding: 10px 15px;
    margin: 0 5px;
    text-decoration: none;
    color: #fff;
    background-color: #007bff;
    border: 1px solid #007bff;
    border-radius: 5px;
    transition: background-color 0.3s ease, color 0.3s ease;
}

.page-header a:hover {
    background-color: #0056b3;
    color: #fff;
}

.page-header a.logout {
    background-color: #dc3545;
    border-color: #dc3545;
}

.page-header a.logout:hover {
    background-color: #bd2130;
    border-color: #bd2130;
}

</style>
<title>Prodaja</title>
</head>
<body>

	<h1>Dobrodošli<sec:authorize access="hasRole('ADMIN')">
 		<sec:authentication property="principal.username"/></sec:authorize>
 	</h1>
 	
 	<div class="page-header">
		<sec:authorize access="hasRole('ADMIN')">
			<a href="/Projekat/admin/izvestaj1">Generiši izveštaj omiljenih
				nekretnina izabranog grada</a>
			<a href="/Projekat/admin/izvestaj2">Generiši izveštaj za cene
				nekretnina po izabranom tipu</a>
				<a href="/Projekat/admin/izvestaj3">Generiši izveštaj obilazaka</a>
		</sec:authorize>

		<sec:authorize access="hasRole('PRODAVAC')">
			<a href="/Projekat/prodavac/getNekretnine">Vaše nekretnine</a>
			<a href="/Projekat/prodavac/prelazUnos">Unos nove nekretnine</a>
		</sec:authorize>

		<sec:authorize access="hasRole('KUPAC')">
			<a href="/Projekat/kupac/getOmiljene">Omiljene</a>
			<a href="/Projekat/kupac/getObilasci">Zakazani obilasci</a>
		</sec:authorize>

		<sec:authorize access="isAuthenticated()">
			<a class="logout" href="/Projekat/contrLogin/logout">Log out</a>
		</sec:authorize>
		<sec:authorize access="isAnonymous()">
			<a href="/Projekat/contrLogin/logout">Početna</a>
		</sec:authorize>
	</div>

	
	<c:if test="${empty nekretnine }">
		<div class="alert">
			Nema okačenih oglasa!
		</div>
	</c:if>
	<c:if test="${!empty nekretnine }">
		<h2>Oglasi:</h2>
		<div class="page-header">
			<a href="/Projekat/controllerP/filteri">Pretraga po filterima</a>
		</div>

		<h4>Svi oglasi</h4>
		<table border="1" width="70%" height="70%">
			<%-- <tr>
				<th>Slika</th>
				<th>Tip</th>
				<th>Kvadratura</th>
				<th>Broj spavacih soba</th>
				<th>Broj kupatila</th>
				<th>Adresa</th>
				<th>Link oglasa</th>
			</tr>--%>
			<c:forEach items="${nekretnine }" var="n">
				<tr>
					<c:forEach var="slika" items="${n.slikas}" varStatus="loopStatus">
						<c:if test="${loopStatus.index eq 0}">
							<td colspan="8"><img src="<c:url value='/uploads/${slika.url}'/>"
								width="100%" height="100%" alt="slika_nekretnine"></td>
						</c:if>
					</c:forEach>
				</tr>
				<tr>
					<td>${n.grad.naziv }</td>
					<td>${n.tipnekretnine.naziv }</td>
					<td>$<fmt:formatNumber value="${n.cena }" pattern="#,###" /></td>
					<td>${n.kvadratura } m²</td>
					<td class="slicice"><img class="slicice" src="<c:url value='/Slicice/OIP.jpg'/>"width="20%" height="20%" alt="br_spavacih"> ${n.brojSpavacihSoba}</td>
					<td class="slicice"><img class="slicice" src="<c:url value='/Slicice/kupatilo.jpg'/>"width="20%" height="20%" alt="br_kuatila">${n.brojKupatila }</td>
					<td>Adresa: ${n.adresa }</td>
					<td><a href="/Projekat/controllerP/oglas?id=${n.idNekretnina }">Link</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>