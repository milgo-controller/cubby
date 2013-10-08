<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<style>
.error {
	color: #ff0000;
}
</style>
<body>

<h2>Register</h2>

<sf:form method="POST" modelAttribute="userDetails" >
	<fieldset>
		<table>
			<tr>
				<th><label for="user_login">Login</label></th>
				<td><sf:input path="login" id="user_login" size="15"/></td>
				<td><sf:errors path="login" cssClass="error" /></td>
			</tr>
			<tr>
				<th><label for="user_pass">Password</label></th>
				<td><sf:password path="password" id="user_pass" size="15" showPassword="true"/></td>
				<td><sf:errors path="password" cssClass="error" /></td>
			</tr>
			<tr>
				<th></th>
				<td><input name="commit" type="submit" value="Create Account"/></td>
			</tr>
		</table>
	</fieldset>
</sf:form>

</body>
</html>