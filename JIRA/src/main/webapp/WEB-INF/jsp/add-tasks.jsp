<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add tasks</title>
    </head>
    <body>
    	
    	
    <c:choose>
    	<c:when test="${requestScope.val==true}">
    		<h1>Add tasks to JIRA </h1> &nbsp; &nbsp; &nbsp; &nbsp; <a href="showcalendar.htm">Cancel</a>
        
    <form:form modelAttribute="task">
    
    	 <br/>
        Summary: <form:input path="summary" /><br/><form:errors path="summary"/><br/>
        Issue Type: <form:input path="issue_type" /><br/><form:errors path="issue_type"/><br/>
        Priority: <form:input path="priority" /><br/><form:errors path="priority"/><br/>
        Description: <form:input path="description" /><br/><form:errors path="description"/><br/>
        Assigned To: <form:select path="assigned_to">
                            <c:forEach var="user" items="${users}">
                                <form:option value="${user.first_name}"/>
                            </c:forEach>
                      </form:select>
  
        State:  
        <form:select path="state">		
            	<option value="OPEN">Open</option>
            	<option value="WAITING FOR INPUTS">Waiting for Inputs</option>
            	<option value="READY TO START">Ready to Start</option>      
            	<option value="EXECUTION">Execution</option>   	
            	<option value="READY FOR TECH REVIEW">Ready For Tech Review</option>            	         	      	
            	<option value="DEPLOY">Deploy</option>
            	<option value="CLOSED">Closed</option>            	
		</form:select><br/><br/>
        Due Date: <input type="date" name = "due" min="2022-12-16" max = "2023-12-16" required><br/>
        
	
        
        <form:button name="Submit" type="submit">Add Task</form:button>
    </form:form>
    	</c:when>
        <c:otherwise>
               <br/>
        		<br/>
       			 <h3>This page is not visible to you because you are not logged in yet, and you cannot add tasks yet</h3>
       			 <br/> <a href="loginas.htm">Click here</a> to login as a User
        </c:otherwise>
        </c:choose>
    </body>	
</html>
