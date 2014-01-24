<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %> 
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

<sf:form method="POST" modelAttribute="user" >
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
				<th><label for="user_confirm_pass">Confirm Password</label></th>
				<td><sf:password path="confirmPassword" id="user_confirm_pass" size="15" showPassword="true"/></td>
				<td><sf:errors path="confirmPassword" cssClass="error" /></td>
			</tr>
			<tr>
				<th><label for="user_email">Email</label></th>
				<td><sf:input path="email" id="user_email" size="15"/></td>
				<td><sf:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<th><label for="user_first_name">First name</label></th>
				<td><sf:input path="firstName" id="user_first_name" size="15"/></td>
				<td><sf:errors path="firstName" cssClass="error" /></td>
			</tr>
			<tr>
				<th><label for="user_last_name">Last name</label></th>
				<td><sf:input path="lastName" id="user_last_name" size="15"/></td>
				<td><sf:errors path="lastName" cssClass="error" /></td>
			</tr>
			<tr>
				<th><label for="user_birth_date">Birth date (dd/MM/yy)</label></th>
				<td><sf:input path="birthDate" id="user_birth_date" size="15"/></td>
				<td><sf:errors path="birthDate" cssClass="error" /></td>
			</tr>
			<tr>
				<th><label for="user_street">Street</label></th>
				<td><sf:input path="address.street" id="user_street" size="15"/></td>
				<td><sf:errors path="address.street" cssClass="error" /></td>
			</tr>
			<tr>
				<th><label for="user_zipcode">Zip code</label></th>
				<td><sf:input path="address.zipCode" id="user_zipcode" size="15"/></td>
				<td><sf:errors path="address.zipCode" cssClass="error" /></td>
			</tr>
			<tr>
				<th><label for="user_city">City</label></th>
				<td><sf:input path="address.city" id="user_city" size="15"/></td>
				<td><sf:errors path="address.city" cssClass="error" /></td>
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