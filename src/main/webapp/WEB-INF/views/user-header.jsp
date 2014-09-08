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
<title>Witaj na platformie e-learningowej</title>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript">
 $(function() {
		/* For zebra striping */
        $("table tr:nth-child(odd)").addClass("odd-row");
		/* For cell text alignment */
		$("table td:first-child, table th:first-child").addClass("first");
		/* For removing the last border */
		$("table td:last-child, table th:last-child").addClass("last");
});
</script>

</head>
<body>

<table> 
<tr>
	<td rowspan="2">
		<%@include file="logo.jsp" %>
	</td>
	<td colspan="4">
		<div align="center">
		<h1>Witaj <sec:authentication property="principal.username"/></h1>
		</div>
	</td>

</tr>
<tr>
	<td>
		<spring:url value="/home/edit" var="edit"/>
		<a href="${edit}">Edycja danych</a>
	</td>
	<td>
		<spring:url value="/home/alltrainings" var="alltrainings"/>
		<a href="${alltrainings}">Wszystkie treningi</a>
	</td>
	<td>
		<spring:url value="/home/mytrainings" var="mytrainings"/>
		<a href="${mytrainings}">Moje treningi</a>
	</td>
	<td>
		<spring:url value="/j_spring_security_logout" var="logout"/>
		<a href="${logout}">Wyloguj</a>  
	</td>
</tr>
</table>