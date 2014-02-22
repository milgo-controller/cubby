<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<spring:url value="/resources/css/style.css" var="stylecss"/>    
<link href="${stylecss}" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>

<div align="center">
<h1>Welcome <sec:authentication property="principal.username"/></h1>(<a href='<c:url value="j_spring_security_logout" />' >Logout</a>)
</div>

<!-- TRAININGS LIST -->
<table cellpadding="5" cellspacing="0">
<thead>
	<tr><td colspan="6"><div align="center"><h3>Available trainings</h3></div></td></tr>
	<tr>
		<td><strong>Name</strong></td>
		<td><strong>Description</strong></td>
		<td><strong>Cost</strong></td>
		<td><strong>Online</strong></td>		
		<td colspan="2"><strong>Commands</strong></td>
	</tr>
</thead>

<tbody>
	<c:forEach var="training" items="${trainingsList}">
		<tr>
			<td>${training.name}</td>
			<td>${training.description}</td>
			<td>${training.cost}</td>			
			<td>${training.online}</td>
			<td><a href="home/training/join/${training.id}">Join</a></td>
			<td>Quit</td>
		</tr>
	</c:forEach>
</tbody>

<tfoot>
	<tr>
	
	</tr>
</tfoot>

</table>

</body>
</html>