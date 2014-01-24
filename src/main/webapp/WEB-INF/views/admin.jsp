<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored ="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin page</title>
</head>
<body>
<h1>Admin panel</h1>(<a href="<c:url value="j_spring_security_logout" />" > Logout</a>)
<h3>Users list</h3>

<table>
<thead bgcolor="gold">
	<tr>
		<td>Login</td>
		<td>First name</td>
		<td>Last name</td>
		<td>Email</td>
		<td colspan="2">Commands</td>
	</tr>
</thead>

<tbody bgcolor="aliceblue">
	<c:forEach var="user" items="${usersList}">
		<tr>
			<td>${user.login}</td>
			<td>${user.firstName}</td>
			<td>${user.lastName}</td>			
			<td>${user.email}</td>
			<td><a href="">Edit</a></td>
			<td><a href="">Remove</a></td>
		</tr>
	</c:forEach>
</tbody>

<tfoot>
	<tr>
		<td><a href="">Add user</a></td>
	</tr>
</tfoot>

</table>



</body>
</html>