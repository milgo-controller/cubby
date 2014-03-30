<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
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
<div align="center">

<h1>
Admin panel
(<sec:authentication property="principal.username"/>) </h1>(<a href="<c:url value="j_spring_security_logout" />" >Logout</a>)
</div>

<table cellpadding="5" cellspacing="0">
<thead>
	<tr><td colspan="7"><div align="center"><h3>Users list</h3></div></td></tr>
	<tr>
		<td><strong>Login</strong></td>
		<td><strong>First name</strong></td>
		<td><strong>Last name</strong></td>
		<td><strong>Email</strong></td>
		<td><strong>Role</strong></td>
		<td colspan="2"><strong>Commands</strong></td>
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
			<td><a href="<spring:url value="admin/edit/user/${user.login}" />">Edit</a></td>
			<td><a href="admin/remove/user/${user.login}" onclick="return confirm ('Removing user ${user.login}! Are you sure?');">Remove</a></td>
		</tr>
	</c:forEach>
</tbody>

<tfoot>
	<tr>
		<!-- <td><a href="admin/add/user/">Add user</a></td> -->
	</tr>
</tfoot>

</table>

<!-- TRAININGS LIST -->
<table cellpadding="5" cellspacing="0">
<thead>
	<tr><td colspan="6"><div align="center"><h3>Trainings list</h3></div></td></tr>
	<tr>
		<td><strong>Name</strong></td>
		<td><strong>Descriptin</strong></td>
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
			<td><a href="<spring:url value="admin/training/edit/${training.id}" />">Edit</a></td>
			<td><a href="admin/training/remove/${training.id}" onclick="return confirm ('Removing training ${training.name}! Are you sure?');">Remove</a></td>
		</tr>
	</c:forEach>
</tbody>

<tfoot>
	<tr>
		<td colspan="6"><a href="admin/add/training/">Add training</a></td>
	</tr>
</tfoot>

</table>

</body>
</html>