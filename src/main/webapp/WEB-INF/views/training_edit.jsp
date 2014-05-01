<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<spring:url value="/resources/css/style.css" var="stylecss"/>    
<link href="${stylecss}" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">

function toggleVisible (chb, obj1, obj2) 
{
    var chbox = document.getElementById(chb);
    var a = document.getElementsByName(obj1);
	var b = document.getElementsByName(obj2);
	
    if(chbox.checked)
    {	
    	for(var i=0;i<a.length;i++)a[i].style.display = "table-row";
    	for(var i=0;i<b.length;i++)b[i].style.display = "none";
    }
    else
    {
    	for(var i=0;i<a.length;i++)a[i].style.display = "none";
    	for(var i=0;i<b.length;i++)b[i].style.display = "table-row";
    }
}

</script>

</head>
<style>
.error {
	color: #ff0000;
}
</style>
<body onload="toggleVisible('training_online', 'show_online', 'show_offline')">

<div align="center"><h3>Training details</h3></div>

<sf:form method="POST" modelAttribute="training" >
	<fieldset>
		<table>
			<tr>
				<th><label for="training_name">Name</label></th>
				<td><sf:input path="name" id="training_name" size="25"/></td>
				<td><sf:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<th><label for="training_desc">Description</label></th>
				<td><sf:textarea path="description" id="training_desc" size="15"/></td>
				<td><sf:errors path="description" cssClass="error" /></td>
			</tr>
			<tr>
				<th><label for="training_cost">Cost</label></th>
				<td><sf:input path="cost" id="training_cost" size="15"/></td>
				<td><sf:errors path="cost" cssClass="error" /></td>
			</tr>
			<tr>
				<th><label for="training_online">Online</label></th>				
				<td><sf:checkbox path="online" id="training_online" value="1" onclick="toggleVisible('training_online', 'show_online', 'show_offline')"/></td>
				<td><sf:errors path="online" cssClass="error" /></td>
			</tr>
			<tr name="show_online" style="display:none;">
				<th><label for="treining_url">Url</label></th>
				<td><sf:input path="url" id="treining_url" size="25"/></td>
				<td><sf:errors path="url" cssClass="error" /></td>
			</tr>		
			<tr name="show_offline" style="display:table-row;">
				<th><label for="training_startdate">Start date</label></th>
				<td><sf:input path="startDate" id="training_startdate" size="15"/></td>
				<td><sf:errors path="startDate" cssClass="error" /></td>
			</tr>
			<tr name="show_offline" style="display:table-row;">
				<th><label for="training_place">Place</label></th>
				<td><sf:input path="place" id="training_place" size="15"/></td>
				<td><sf:errors path="place" cssClass="error" /></td>
			</tr>
			<tr>
				<th></th>
				<td><input name="commit" type="submit" value="Submit"/></td>
			</tr>
		</table>		
	</fieldset>
</sf:form>
</body>
</html>