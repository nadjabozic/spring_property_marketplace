<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
    text-align: center;
}

h2 {
    color: #333;
}

form {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin-top: 20px;
}

label {
    display: block;
    margin-top: 10px;
}

input, select, textarea {
    width: 100%;
    padding: 8px;
    margin: 5px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
    border-radius: 4px;
}

button {
    background-color: #4caf50;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

button:hover {
    background-color: #45a049;
}

.c-if-message {
    margin-top: 20px;
    color: #007bff;
}
</style>
<title>Unos nekretnine</title>
</head>
<body>

    <h2>Unos nekretnine</h2>

    <form:form method="post" enctype="multipart/form-data" action="/Projekat/prodavac/sacuvajNekretninu" modelAttribute="nekretninaDTO">

        <label for="kvadratura">Kvadratura:</label>
        <form:input path="kvadratura" required="true" />
        <br>

        <label for="brojSpavacihSoba">Broj spavaćih soba:</label>
        <form:input path="brojSpavacihSoba" required="true" />
        <br>

        <label for="brojKupatila">Broj kupatila:</label>
        <form:input path="brojKupatila" required="true" />
        <br>

        <label for="cena">Cena:</label>
        <form:input path="cena" required="true" />
        <br>

        <label for="opis">Opis:</label>
        <form:textarea path="opis" />
        <br>

        <label for="adresa">Adresa:</label>
        <form:input path="adresa" required="true" />
        <br>

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
        
        <label for="slike">Slike:</label>
    	<input type="file" id="slike" name="slike" multiple>
		<br>
		
        <button type="submit">Sačuvaj</button>
    </form:form>
    <br>
    <c:if test="${not empty message }">
    	<p class="c-if-message">${message }</p>
    </c:if> <br>
    <a href="/Projekat/controllerP/glavna">Povratak na glavnu stranicu</a>

</body>
</html>
