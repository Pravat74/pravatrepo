
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>

<h1 style="color: blue; text-align: center">Actor Information
	updation form page</h1>
<frm:form action="actor_edit" modelAttribute="artist" method="POST">
	<table bgcolor="pink" align="center">
		<tr>
			<td>Actor Id ::</td>
			<td><frm:input path="actorid" readonly="true" />
		</tr>
		<tr>
			<td>Actor Name ::</td>
			<td><frm:input path="actorName" />
		</tr>
		<tr>
			<td>Category ::</td>
			<td><frm:input path="category" />
		</tr>
		<tr>
			<td>mobile no ::</td>
			<td><frm:input path="mobileNo" />
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="update Actor"></td>
		</tr>
	</table>
</frm:form>