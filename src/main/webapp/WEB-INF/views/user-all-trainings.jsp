<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored ="false" %>

<%@include file="user-header.jsp" %>

<!-- ALL TRAININGS THAT USER CAN JOIN -->
<table cellpadding="5" cellspacing="0">
<thead>
	<tr><td colspan="5"><div align="center"><h3>Wszystkie trainingi</h3></div></td></tr>
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
			<spring:url value="/home/training/join/" var="join"/>
			<td><a href="${join}${training.id}">Join</a></td>
		</tr>
	</c:forEach>
</tbody>

<tfoot>
	<tr>
	
	</tr>
</tfoot>

</table>

<%@include file="user-footer.jsp" %>

