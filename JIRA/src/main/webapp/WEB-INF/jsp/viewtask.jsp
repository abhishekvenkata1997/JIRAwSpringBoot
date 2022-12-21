<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
		
		<br/><br/>
		 
	<c:choose>
    <c:when test="${requestScope.val==true}">
        <a href="../updatetask/${task.task_id}.htm">Edit task</a>
		&nbsp; &nbsp; 
		
		<a href="../showcalendar.htm">Show all tasks</a> &nbsp; &nbsp;
		<a href="../taskactivity/${task.task_id}.htm"> Task Activity</a>
		<br/> <br/>
	    <Table border="1"  	>
    	<tr><td>Summary: ${task.summary}</td></tr> 
    	<tr><td>Issue Type: ${task.issue_type}</td></tr>
    	<tr><td>Assigned to: ${task.assigned_to} </td></tr> 
    	<tr><td>State: ${task.state}         </td></tr> 
    	<tr><td>Priority: ${task.priority}       </td></tr> 
    	<tr><td>Description: ${task.description}       </td></tr>
    	<tr><td>Task Id: ${task.task_id}         </td></tr> 
    	<tr><td> Creation Date: ${task.create_date } </td></tr>
    	<tr><td>Due date: ${task.due_date }</td></tr>
    	</Table>
    	
    	 <br/>
         Update Assignment:
         <form:form modelAttribute="user">
         <form:select path="email_id">
              <c:forEach var="user" items="${users}">
                <form:option value="${user.email_id}"/>
              </c:forEach>
         </form:select>
         <form:button name="Update" type="submit"> Update </form:button><br/>
         </form:form>
    	
    	<br/><br/>
    	Comments:
    	<br/><br/>
    	<c:forEach items="${requestScope.comments}" var="comment" varStatus="status">
     	<tr><td>${comment.column_text}  By: ${requestScope.UserEmail[status.index]}</td></tr> <br/>	
     	<tr><td> </td></tr>		
    	<tr><td></td></tr>
		</c:forEach>
    	<br/><br/>
    	<br/><br/>
    	
    	<form:form modelAttribute="comment">	
    	<form:input path="column_text" placeholder=""/><br/><form:errors path="column_text"/><br/><br/>
    	<form:button name="Comment" type="submit"> Add Comment </form:button><br/>
    	</form:form>
    </c:when>    
    <c:otherwise>
        <br/>
        <br/>
        <h3>This page is not visible to you because you are not logged in yet, and you cannot edit tasks yet</h3>
        <br/> <a href="../loginas.htm">Click here</a> to login as a User
    </c:otherwise>
</c:choose>
    	
</body>
</html>