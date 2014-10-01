<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="admin-header.jsp" %>

<!-- TRAININGS LIST -->
<table cellpadding="5" cellspacing="0">
<thead>
	<tr><td colspan="6"><div align="center"><h3>Wszystkie treningi</h3></div></td></tr>
	<tr>
		<td><strong>Nazwa</strong></td>
		<td><strong>Opis</strong></td>
		<td><strong>Cena</strong></td>
		<td><strong>Online</strong></td>		
		<td colspan="2"><strong>Polecania</strong></td>
	</tr>
</thead>

<tbody>
	<c:forEach var="training" items="${trainingsList}">
		<tr>
			<td>${training.name}</td>
			<td>${training.description}</td>
			<td>${training.cost}</td>			
			<td>${training.online}</td>
			<td><a href="<spring:url value="/admin/training/edit/${training.id}" />">Edytuj</a></td>
			<td><a href="<spring:url value="/admin/training/remove/${training.id}" />" onclick="return confirm ('Usuwam trening ${training.name}! Czy jestes pewien?');">Usun</a></td>
		</tr>
	</c:forEach>
</tbody>

<tfoot>
	<tr>
	<spring:url value="/admin/add/training/" var="addTraining"/>
		<td colspan="6"><a href="${addTraining}">Dodaj trening</a></td>
	</tr>
</tfoot>

</table>


<%@include file="admin-footer.jsp" %>
