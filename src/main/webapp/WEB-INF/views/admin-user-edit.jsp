<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@include file="admin-header.jsp" %>

<div align="center">

<%@include file="user-form.jsp" %>

<sec:authorize ifAnyGranted="ROLE_ADMIN, ROLE_MODERATOR">

	<!-- ALL TRAININGS THAT USER CAN JOIN -->
	<table cellpadding="5" cellspacing="0">
		<thead>
			<tr><td colspan="7"><div align="center"><h3>Treningi klienta: ${user.login}</h3></div></td></tr>
			<tr>
				<td><strong>Nazwa</strong></td>
				<td><strong>Opis</strong></td>
				<td><strong>Cena</strong></td>
				<td><strong>Online</strong></td>		
				<td colspan="2"><strong>Operacje</strong></td>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="userTraining" items="${userTrainings}">
				<tr>
					<td>${userTraining.training.name}</td>
					<td>${userTraining.training.description}</td>
					<td>${userTraining.training.cost}</td>			
					<td>${userTraining.training.online}</td>
					<spring:url value="/admin/edit/user" var="editUrl"/>
					<c:if test="${userTraining.active == 1}">
						<td><a href="${editUrl}/${user.login}/training/deactivate/${userTraining.training.id}">Deactivate</a></td>
					</c:if>
					<c:if test="${userTraining.active == 0}">
						<td><a href="${editUrl}/${user.login}/training/activate/${userTraining.training.id}">Activate</a></td>
					</c:if>
					<td><a href="${editUrl}/${user.login}/training/delete/${userTraining.training.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
		
		<tfoot>
			<tr>
			
			</tr>
		</tfoot>
	
	</table>

</sec:authorize>

</div>

<br />
<br />

<%@include file="admin-footer.jsp" %>