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
}

.nema-obilazaka-poruka {
    color: #dc3545; 
    font-weight: bold;
    margin-top: 20px;
}

.obilasci-tabela {
    border-collapse: collapse;
    width: 70%;
    margin-top: 20px;
}

.obilasci-tabela, .obilasci-tabela th, .obilasci-tabela td {
    border: 1px solid #ddd;
}

.obilasci-tabela th, .obilasci-tabela td {
    padding: 10px;
    text-align: center;
}

.uspeh-poruka {
    color: #28a745;
    font-weight: bold;
    margin-top: 20px;
}

.povratak-link {
    text-decoration: none;
    color: #007bff;
    display: block;
    margin-top: 20px;
}

.povratak-link:hover {
    text-decoration: underline;
}

</style>
<title>Obilasci</title>
</head>
<body>

	<c:if test="${empty obilasci }">
		 <p class="nema-obilazaka-poruka">Niste zakazali nijedan obilazak!</p>
	</c:if>

	<c:if test="${!empty obilasci }">
		<table class="obilasci-tabela" border="1">
			<tr>
				<th>Datum</th>
				<th>Otkaži obilazak</th>
			</tr>
			<c:forEach items="${obilasci }" var="o">
				<tr>
					<td>${o.datum }</td>
					<td><a href="/Projekat/prodavac/ukloniObilazak?idO=${o.idObilazak }">Otkaži</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${not empty messageObilazak }">
		 <p class="uspeh-poruka">${messageObilazak }</p>
	</c:if>
	<br>
	<a class="povratak-link" href="/Projekat/controllerP/goBack">Povratak na početnu stranicu</a>

</body>
</html>