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
    display: flex;
    flex-direction: column;
    align-items: center;
}

.error {
    color: #dc3545;
    font-weight: bold;
    margin-bottom: 20px;
}

form {
    margin-bottom: 20px;
}

select {
    padding: 8px;
}

input[type="submit"] {
    padding: 10px;
    background-color: #007bff;
    color: #fff;
    cursor: pointer;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

table, th, td {
    border: 1px solid #ddd;
}

th, td {
    padding: 10px;
    text-align: center;
}

.uspeh-poruka {
    color: #28a745;
    font-weight: bold;
    margin-top: 10px;
}

.povratak-link {
    text-decoration: none;
    color: #007bff;
    margin-top: 20px;
}

.povratak-link:hover {
    text-decoration: underline;
}

</style>
<title>Zakazani obilasci</title>
</head>
<body>

	<c:if test="${empty obilasci}">
        <p class="error">Nemate zakazanih obilazaka!</p>
    </c:if>

    <c:if test="${not empty obilasci}">
        <table>
            <tr>
                <th>Datum</th>
                <th>Nekretnina</th>
                <th>Adresa</th>
                <th>Otkaži</th>
            </tr>
            <c:forEach items="${obilasci}" var="o">
                <tr>
                    <td><fmt:formatDate value="${o.datum}" pattern="dd.MM.yyyy"/></td>
                    <td>${o.nekretnina.tipnekretnine.naziv}</td>
                    <td>${o.nekretnina.grad.naziv} ${o.nekretnina.adresa}</td>
                    <td>
                        <a href="<c:url value='/kupac/ukloniObilazak?idO=${o.idObilazak}'/>">Otkaži</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${not empty messageObilazak}">
        <p class="uspeh-poruka">${messageObilazak}</p>
    </c:if>

    <a href="/Projekat/controllerP/goBack">Povratak na početnu stranicu</a>

</body>
</html>