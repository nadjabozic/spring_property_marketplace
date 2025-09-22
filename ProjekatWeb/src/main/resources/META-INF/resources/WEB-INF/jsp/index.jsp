<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prodaja nekretnina</title>
<style type="text/css">
body {
	font-family: Arial, sans-serif;
	background-color: #ccffff;
	margin: 0;
	padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
}

h1 {
	color: #333;
	margin-bottom: 20px;
}

h4 {
	color: #666;
}

a {
	display: block;
	margin-bottom: 10px;
	padding: 8px 12px;
	text-decoration: none;
	background-color: #3498db;
	color: #fff;
	border-radius: 4px;
	transition: background-color 0.3s ease;
}

a:hover {
	background-color: #2980b9;
}

html, body {
	height: 100%;
	display: flex;
	align-items: center;
	justify-content: center;
}

body>* {
	margin-bottom: 20px;
}
</style>
</head>
<body>

	<h1>Dobrodošli!</h1> <br>
	
	<h4>Izaberite tip korisnika:</h4>
	
	<a href="/Projekat/contrLogin/login">Korisnik</a><br>
	<a href="/Projekat/contrLogin/gost">Gost</a><br>

</body>
</html>