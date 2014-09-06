<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url value="/resources/css/style.css" var="stylecss"/>    
<link href="${stylecss}" rel="stylesheet" type="text/css"/>
<title>
<sec:authorize ifAnyGranted="ROLE_ADMIN">Admin page</sec:authorize>
</title>
<style>
.error {
	color: #ff0000;
}
</style>

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

<table width="50%"> 
<tr>
	<td rowspan="2">
		<%@include file="logo.jsp" %>
	</td>
	<td colspan="3">
		<div align="center"><h1>Panel administratora <sec:authentication property="principal.username"/></h1></div>
	</td>

<tr>
	<td><spring:url value="/admin/allusers" var="allusers"/><a href="${allusers}">Lista klientów</a></td>
	<td><spring:url value="/admin/alltrainings" var="alltrainings"/><a href="${alltrainings}">Wszystkie treningi</a></td>
	<td><spring:url value="/j_spring_security_logout" var="logout"/>  <a href="${logout}">Wyloguj</a></td>
</tr>
</table>
