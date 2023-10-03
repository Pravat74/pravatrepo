
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>

<h1 style="color: blue; text-align: center">Actor registration form
	page</h1>
<frm:form action="actor_add" modelAttribute="actor" method="POST">
	<table bgcolor=cyan align="center" border="1">
		<tr>
			<td>Actor name ::</td>
			<td><frm:input path="actorName" /></td>
		</tr>
		<tr>
			<td>Category ::</td>
			<td><frm:input path="category" /></td>
		</tr>
		<tr>
			<td>mobile No ::</td>
			<td><frm:input path="mobileNo" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="register"></td>
		</tr>

	</table>
</frm:form>