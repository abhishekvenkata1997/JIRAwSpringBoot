<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task activity </title>
</head>
<body>
	
	
	<c:choose>
    <c:when test="${requestScope.val==true}">
    	<a href="../viewtask/${requestScope.id}.htm">View Task</a>
		<br/><br/>
    	&nbsp; &nbsp;<h3>Activity</h3>
    	<br/><br/>
    	<c:forEach items="${requestScope.activities}" var="activity" varStatus="status">
     	<tr><td>Change: ${activity.activity_text} <br/><i> By: ${requestScope.UserEmail[status.index]}</i></td></tr> <br/>
     	<tr><td><b>Time: ${activity.update_time }</b></td></tr>	<br/><br/>
		</c:forEach>
    	<br/><br/>
    	<br/><br/>
	</c:when>    
    <c:otherwise>
        <br/>
        <br/>
        <h3>This page is not visible to you because you are not logged in yet</h3>
        <br/> <a href="../loginas.htm">Click here</a> to login as a User
    </c:otherwise>
</c:choose>

</body>
</html>