<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show tasks assigned to me</title>
</head>
<body>
		<br/>
    	Project tasks:
    	<br/>
    	<c:forEach items="${requestScope.all_tasks}" var="task" varStatus="status">
        <Table>
        <tr><td>By: ${requestScope.all_users[status.index]} </td></tr>
    	<tr><td>Summary: ${task.summary}</td></tr> 
    	<tr><td>Issue Type: ${task.issue_type}</td></tr> 
    	<tr><td> State: ${task.state} </td></tr>
    	<tr><td>Summary: <b><a href="viewtask/${task.task_id}.htm"> ${task.summary}</a></b></td></tr> <br/>
    	<tr><td>Assigned to: ${task.assigned_to} </td></tr> 
    	<tr><td>Priority: ${task.priority}       </td></tr> 
    	<tr><td>Description: ${task.description}       </td></tr> 
    	</Table> <br/> 
		</c:forEach>

</body>
</html>