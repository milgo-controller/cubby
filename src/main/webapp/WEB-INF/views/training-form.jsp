<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
    
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

window.onload = toggleVisible('training_online', 'show_online', 'show_offline');

</script>
    
<sf:form method="POST" modelAttribute="training" >
	<fieldset>
		<table>
			<thead>
				<tr><td colspan="6"><div align="center"><h3>Trening</h3></div></td></tr>
			</thead>
			<tr>
				<th><label for="training_name">Nazwa</label></th>
				<td><sf:input path="name" id="training_name" size="25"/></td>
				<td><sf:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<th><label for="training_desc">Opis</label></th>
				<td><sf:textarea path="description" id="training_desc" size="15"/></td>
				<td><sf:errors path="description" cssClass="error" /></td>
			</tr>
			<tr>
				<th><label for="training_cost">Cena</label></th>
				<td><sf:input path="cost" id="training_cost" size="15"/></td>
				<td><sf:errors path="cost" cssClass="error" /></td>
			</tr>
			<tr>
				<th><label for="training_online">Online</label></th>				
				<td><sf:checkbox path="online" id="training_online" value="1" onclick="toggleVisible('training_online', 'show_online', 'show_offline')"/></td>
				<td><sf:errors path="online" cssClass="error" /></td>
			</tr>
			<tr name="show_online" style="display:none;">
				<th><label for="treining_url">Link</label></th>
				<td><sf:input path="url" id="treining_url" size="25"/></td>
				<td><sf:errors path="url" cssClass="error" /></td>
			</tr>		
			<tr name="show_offline" style="display:table-row;">
				<th><label for="training_startdate">Data</label></th>
				<td><sf:input path="startDate" id="training_startdate" size="15"/></td>
				<td><sf:errors path="startDate" cssClass="error" /></td>
			</tr>
			<tr name="show_offline" style="display:table-row;">
				<th><label for="training_place">Miejsce</label></th>
				<td><sf:input path="place" id="training_place" size="15"/></td>
				<td><sf:errors path="place" cssClass="error" /></td>
			</tr>
			<tr>
				<th></th>
				<td><input name="commit" type="submit" value="Akceptuj"/></td>
			</tr>
		</table>		
	</fieldset>
</sf:form>    