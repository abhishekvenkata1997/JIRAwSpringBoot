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
		
	<form:form modelAttribute="task">
    
    	 <br/>
        Summary: <form:input path="summary" placeholder = "${task.summary}" /><br/><form:errors path="summary"/><br/>
        Issue Type: <form:input path="issue_type" placeholder = "${task.issue_type}" /><br/><form:errors path="issue_type"/><br/>
        Priority: <form:input path="priority" placeholder="${task.priority }" /><br/><form:errors path ="priority"/><br/>
        Description: <form:input path="description" placeholder="${task.description }"/><br/><form:errors path="description"/><br/>
        Assigned To: <form:select path="assigned_to">
                            <c:forEach var="user" items="${users}">
                                <form:option value="${user.first_name}"/>
                            </c:forEach>
                      </form:select>
        State: 
		<form:select path="state" placeholder = "${task.state}">		
            	<option value="OPEN">Open</option>
            	<option value="WAITING FOR INPUTS">Waiting for Inputs</option>
            	<option value="READY TO START">Ready to Start</option>      
            	<option value="EXECUTION">Execution</option>   
            	<option value="READY FOR TECH REVIEW">Ready For Tech Review</option>            	         	      	
            	<option value="DEPLOY">Deploy</option>
            	<option value="CLOSED">Closed</option>            	
		</form:select>
		<br/><form:errors path="state"/><br/>
        <form:button name="Submit" type="submit" value="Login">Update Task</form:button>
    </form:form>
		
    	
</body>
</html>