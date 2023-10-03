
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1 style="color: red; text-align: center">Actor Information Report</h1>


<c:choose>
	<c:when test="${!empty actorsInfo }">
		<table border="1" align="center" bgcolor="yellow">
			<tr bgcolor="green">
				<th>ActorId</th>
				<th>Actor Name</th>
				<th>Category</th>
				<th>mobileNo</th>
				<th>Operations</th>
			</tr>
			<c:forEach var="artist" items="${actorsInfo}">
				<tr>
					<td>${artist.actorid}</td>
					<td>${artist.actorName}</td>
					<td>${artist.category}</td>
					<td>${artist.mobileNo}</td>
					<td><a href="actor_edit?aid=${artist.actorid }"><b>edit</b><img
							src="images/edit1.jpg" width="30px" height="30px"></a> <a
						href="actor_delete?aid=${artist.actorid }" onclick="return confirm('do you wants to delete record?')"><b>delete</b><img
							src="images/delete1.jpg" width="30px" height="30px"></a></td>
				</tr>
			</c:forEach>
		</table>

		<center>
			<a href="actor_add"><b>register_Actor</b><img src="images/add1.jpg"
				width="40px" height="40px"></a>
		</center>
		<br>
		<br>
		<center>
			<a href="./">Home<img src="images/home1.jpg" width="40px"
				height="40px"></a>
		</center>
		<h1 style="color: green; text-align: center">${resultMsg }</h1>
	</c:when>
	<c:otherwise>
		<h1 style="color: red; text-align: center">Actors not found</h1>
	</c:otherwise>
</c:choose>