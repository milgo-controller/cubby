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
	<tr><td colspan="6"><div align="center"><h3>Moje treningi</h3></div></td></tr>
	<tr>
		<td><strong>Nazwa</strong></td>
		<td><strong>Opis</strong></td>
		<td><strong>Cena</strong></td>
		<td><strong>Data</strong></td>
		<td><strong>Miejsce/Link</strong></td>	
		<td colspan="2"><strong>Operacje</strong></td>
	</tr>
</thead>

<tbody>
	<c:forEach var="userTraining" items="${trainings}">
		<tr>
			<td>${userTraining.training.name}</td>
			<td>${userTraining.training.description}</td>
			<td>${userTraining.training.cost}</td>			
			<td>${userTraining.training.startDate}</td>
			<td>
				<c:choose>
					<c:when test="${userTraining.training.online == 1}">
						<c:choose>
							<c:when test="${userTraining.active == 1}">${userTraining.training.url}</c:when>
							<c:when test="${userTraining.active == 0}">Nieaktywny</c:when>
						</c:choose>
					</c:when>
					<c:when test="${userTraining.training.online == 0}">${userTraining.training.place}</c:when>
				</c:choose>
			</td>
			<spring:url value="/home/training/quit/" var="quit"/>
			<td><a href="${quit}${userTraining.training.id}">Zrezygnuj</a></td>
		</tr>
	</c:forEach>
</tbody>

<tfoot>
	<tr>
	
	</tr>
</tfoot>

</table>

<%@include file="user-footer.jsp" %>