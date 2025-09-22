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
    background-color: #f8d7da;
    color: #721c24;
    text-align: center;
    padding: 20px;
    margin: 0;
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

h1 {
    font-size: 24px;
    margin-bottom: 20px;
}

a {
    text-decoration: none;
    color: #007bff;
}

a:hover {
    text-decoration: underline;
}

</style>
<title>Access denied</title>
</head>
<body>

	<h1>Zabranjen vam je pristup ovoj stranici</h1>

	<a href="/Projekat/controllerP/goBack">Povratak na početnu stranicu</a>

</body>
</html>