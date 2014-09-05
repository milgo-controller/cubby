<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored ="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cubby registration</title>
</head>
<body>
<div align="center">
<H1>Registration success!</H1>
<spring:url value="/login" var="login" /> 
(<a href="<spring:url value="/login" />" >Login</a>)
(<a href="<spring:url value="/register" />" >Register</a>)
</div>
</body>
</html>