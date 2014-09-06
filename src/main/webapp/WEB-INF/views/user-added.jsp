<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored ="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rejestracja</title>
<spring:url value="/resources/css/style.css" var="stylecss"/>    
<link href="${stylecss}" rel="stylesheet" type="text/css"/>
</head>
<body>
<div align="center">

<a href="index"><%@include file="logo.jsp" %></a>

<H1>Rejestracja zakonczona sukcesem!</H1> 

</div>
</body>
</html>