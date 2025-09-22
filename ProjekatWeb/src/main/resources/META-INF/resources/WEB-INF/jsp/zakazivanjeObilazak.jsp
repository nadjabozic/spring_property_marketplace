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

form {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 20px;
}

form select,
form input[type="date"],
form input[type="submit"] {
    margin-bottom: 10px;
    padding: 5px;
    width: 200px;
}

form input[type="submit"] {
    background-color: #007bff;
    color: #fff;
    cursor: pointer;
}

.c-if-message {
    color: #28a745;
    font-weight: bold;
    margin-top: 10px;
}

.error {
	color: #dc3545;
    font-weight: bold;
    margin-top: 10px;
}

a {
    text-decoration: none;
    color: #007bff;
    margin-top: 20px;
}

a:hover {
    text-decoration: underline;
}
</style>
<title>Zakazivanje obilaska</title>
</head>
<body>
	
	<c:if test="${empty nekretnineK }">
		<p class="error">Nemate nijednu nekretninu!</p>
	</c:if>
	<c:if test="${not empty nekretnineK }">
		<form:form action="/Projekat/prodavac/zakazi" method="post"
			modelAttribute="obilazakDTO">
			<form:select path="nekretnina">
				<c:forEach items="${nekretnineK }" var="n">
					<option value="${n.idNekretnina}">${n.tipnekretnine.naziv }
						${n.grad.naziv } ${n.adresa }</option>
				</c:forEach>
			</form:select>
			<br>
			<form:input type="date" path="datum"
				value='<fmt:formatDate
								pattern="yyyy-MM-dd" value="${today}"/>' />
			<input type="submit" value="Sačuvaj">
		</form:form>
		<br>
		<%--<c:if test="${not empty message }">
			<p class="c-if-message">${message }</p>
		</c:if>
		<br>--%>
		<c:if test="${not empty messageZakazivanje }">
			<p class="error">${messageZakazivanje }</p>
		</c:if>
		<br>
		<c:if test="${not empty messageUspeh }">
			<p class="c-if-message">${messageUspeh }</p>
		</c:if>
		<br>
	</c:if>

	<a href="/Projekat/controllerP/glavna">Povratak na glavnu stranicu</a>

</body>
</html>