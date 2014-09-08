<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="admin-header.jsp" %>

<table cellpadding="5" cellspacing="0">
<thead>
	<tr><td colspan="7"><div align="center"><h3>Lista klientów</h3></div></td></tr>
	<tr>
		<td><strong>Login</strong></td>
		<td><strong>Imie</strong></td>
		<td><strong>Nazwisko</strong></td>
		<td><strong>Email</strong></td>
		<td><strong>Rola</strong></td>
		<td colspan="2"><strong>Polecenia</strong></td>
	</tr>
</thead>

<tbody>
	<c:forEach var="user" items="${usersList}">
		<tr>
			<td>${user.login}</td>
			<td>${user.firstName}</td>
			<td>${user.lastName}</td>			
			<td>${user.email}</td>
			<td>${user.roleName}</td>
			<td><a href="<spring:url value="/admin/edit/user/${user.login}" />">Edytuj</a></td>
			<td><a href="/admin/remove/user/${user.login}" onclick="return confirm ('Removing user ${user.login}! Czy jestes pewien?');">Usun</a></td>
		</tr>
	</c:forEach>
</tbody>

<tfoot>
	<tr>
		<td colspan="7">&nbsp</td>
	</tr>
</tfoot>

</table>

<%@include file="admin-footer.jsp" %>
