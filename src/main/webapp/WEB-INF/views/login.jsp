<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored ="false" %> 
<html>
<head>
<spring:url value="/resources/css/style.css" var="stylecss"/>    
<link href="${stylecss}" rel="stylesheet" type="text/css"/>
<title>Logowanie</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>

</head>
<body onload='document.f.j_username.focus();'>
<div align="center">

	<a href="index"><%@include file="logo.jsp" %></a>
 
	<c:if test="${not empty error}">
		<div class="errorblock">
			Logowanie nie powieodlo sie, sprobuj ponownie!<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	
 	<c:url value='j_spring_security_check' var="loginUrl"/>
	<form name='f' action="${loginUrl}"
		method='POST'>
 
		<table>
			<tr>
				<td><h3>Login:</h3></td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td><h3>Haslo:</h3></td>
				<td><input type='password' name='j_password' />
				</td>
			</tr>
			<tr>
				<td>
					<a href="register/">Zarejestruj mnie</a>
				</td>
				<td><p align="right"><input name="submit" type="submit"
					value="Zaloguj" /></p>
				</td>				
			</tr>
		</table>
 
	</form>
	</div>
</body>
</html>