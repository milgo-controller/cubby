<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url value="/resources/css/style.css" var="stylecss"/>    
<link href="${stylecss}" rel="stylesheet" type="text/css"/>
<title>Platforma e-learningowa</title>
</title>

<table> 
<tr>
	<td rowspan="2">
		<%@include file="logo.jsp" %>
	</td>
	<td colspan="2">
		<div align="center">
		<h1>Platforma e-learningowa</h1>
		</div>
	</td>
</tr>
<tr>
<td><a href="register">Rejestracja</a></td>
<td><a href="login">Logowanie</a></td>
</tr>
</table>

<!-- TRAININGS LIST -->
<table cellpadding="5" cellspacing="0">
<thead>
	<tr><td colspan="6"><div align="center"><h3>Nasza oferta</h3></div></td></tr>
	<tr>
		<td><strong>Nazwa</strong></td>
		<td><strong>Opis</strong></td>
		<td><strong>Cena</strong></td>		
		<td><strong>Data</strong></td>
		<td><strong>Miejsce</strong></td>
	</tr>
</thead>

<tbody>
	<c:forEach var="training" items="${trainingsList}">
		<tr>
			<td>${training.name}</td>
			<td>${training.description}</td>
			<td>${training.cost}</td>						
			<td>${training.startDate}</td>
			<td><c:choose>
					<c:when test="${training.online == 1}">Online</c:when>
					<c:when test="${training.online == 0}">${training.place}</c:when>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
</tbody>

</table>

</head>
<body>

</body>
</html>