<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show All Tasks</title>
</head>
<body>
		<br/><br/>
		
		<% boolean val = (boolean) request.getAttribute("home");
		if(val)
		{
		%>
		<a href="showmytasks.htm">Created By Me</a> &nbsp; &nbsp; &nbsp; &nbsp;
    	<c:forEach items="${requestScope.full_users}" var ="user" varStatus="status">
    		<a href= "showcalendar/${user.first_name}+${user.user_id}.htm">${user.first_name}.${user.last_name}</a>
    		&nbsp; &nbsp; &nbsp; &nbsp;
    	</c:forEach>
    	<br/><br/>
    	<a href = "add-tasks.htm">Add new tasks</a> &nbsp; &nbsp; &nbsp;
    	<a href = "logout.htm">Logout</a>
    	<%} 
		else{ %> 
    	    	<c:forEach items="${requestScope.full_users}" var ="user" varStatus="status">
    		<a href= "../showcalendar.htm">${user.first_name}.${user.last_name}</a>
    		&nbsp; &nbsp; &nbsp; &nbsp;
    		
    	</c:forEach>
    	<br/><br/>
    	<a href = "../add-tasks.htm">Add new tasks</a> &nbsp; &nbsp; &nbsp;
    	<a href = "../logout.htm">Logout</a>
    	<%}%>
		<br/><br/>
    	Project tasks:
    	<br/>
    	<c:forEach items = "${requestScope.user_tasks}" var = "user_task" varStatus="status">
    	<b>	&nbsp; ${user_task.key.first_name} &nbsp; ${user_task.key.last_name} &nbsp; </b>
    		    	<br/>
    		    	
    	
    		<c:forEach items="${user_task.value}" var="task" varStatus="status">
        	<Table>
    		<tr><td>Summary: ${task.summary}</td></tr> 
    		<tr><td>Issue Type: ${task.issue_type}</td></tr> 
			<c:if test = "${task.state == 'OPEN'}">
    			<tr style="background-color:#FFFFE0"><td> State: ${task.state} </td></tr>
    		</c:if>
    		<c:if test = "${task.state == 'WAITING FOR INPUTS'}">
    			<tr style="background-color:#ecca00"><td> State: ${task.state} </td></tr>
    		</c:if>
    		<c:if test = "${task.state == 'READY TO START'}">
    			<tr style="background-color:#ec9b00"><td> State: ${task.state} </td></tr>
    		</c:if>
    		<c:if test = "${task.state == 'EXECUTION'}">
    			<tr style="background-color:#ec5300"><td> State: ${task.state} </td></tr>
    		</c:if>
    		<c:if test = "${task.state == 'READY FOR TECH REVIEW'}">
    			<tr style="background-color:#FD9C0A"><td> State: ${task.state} </td></tr>
    		</c:if>
    		<c:if test = "${task.state == 'DEPLOY'}">
    			<tr style="background-color:#ec0000"><td> State: ${task.state} </td></tr>
    		</c:if>
    		<c:if test = "${task.state == 'CLOSED'}">
    			<tr style="background-color:#8B0000"><td> State: ${task.state} </td></tr>
    		</c:if>
    		<% 
    			boolean val2 = (boolean) request.getAttribute("home");
    			if(val2)
    			{
    		%>
    		<tr><td>Summary: <b><a href="viewtask/${task.task_id}.htm"> ${task.summary}</a></b></td></tr> 
    		<% }
    		else
    		{%>
    		<tr><td>Summary: <b><a href="../viewtask/${task.task_id}.htm"> ${task.summary}</a></b></td></tr> 
    		<%} %>
    		
    		<tr><td>Assigned to: ${task.assigned_to} </td></tr> 
    		<tr><td>Priority: ${task.priority}       </td></tr> 
    		<tr><td>Description: ${task.description}       </td></tr> 
    		
    		<c:set var="date1" value="2022-12-14" />
			<c:set var="date2" value="2022-01-01" />
    		<c:set var="currentDate" value="${requestScope.date}" />
    		<c:set var="specifiedDate" value="${task.due_date}" />
    		<c:set var="diff" value="${currentDate.compareTo(specifiedDate)}"/>
     	     <tr ><td style="">Due Date: ${task.due_date } </td></tr>
    		</Table> <br/> 
    		</c:forEach>
    		<br/>
    	</c:forEach>
    	
</body>
</html>
