<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored ="false" %>

<%@include file="user-header.jsp" %>

<!-- ALL USER TRAININGS -->
<table cellpadding="5" cellspacing="0">
<thead>
	<tr><td colspan="5"><div align="center"><h3>Moje treningi</h3></div></td></tr>
	<tr>
		<td><strong>Name</strong></td>
		<td><strong>Description</strong></td>
		<td><strong>Cost</strong></td>
		<td><strong>Online</strong></td>		
		<td colspan="2"><strong>Commands</strong></td>
	</tr>
</thead>

<tbody>
	<c:forEach var="userTraining" items="${userTrainings}">
		<tr>
			<td>${userTraining.name}</td>
			<td>${userTraining.description}</td>
			<td>${userTraining.cost}</td>			
			<td>${userTraining.online}</td>
			<spring:url value="/home/training/quit/" var="quit"/>
			<td><a href="${quit}${userTraining.id}">Quit</a></td>
		</tr>
	</c:forEach>
</tbody>

<tfoot>
	<tr>
	
	</tr>
</tfoot>

</table>

<%@include file="user-footer.jsp" %>