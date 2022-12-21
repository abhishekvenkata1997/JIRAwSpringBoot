<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User home</title>
</head>
<body>
	
	 <h5><a href="loginas.htm">Change Session</a></h5>
     <h5 inline="true"><a href="add-tasks.htm">Add Tasks</a></h3>
	 <h5><a href="showcalendar.htm">Show Calendar</h5></a>
     <br/><br/>
     <h4>${requestScope.login_user.user_id } <br/></h4>
     <h4>${requestScope.login_user.email_id }<br/></h4>
     <h4>${requestScope.login_user.first_name }<br/></h4>
     <h4>${requestScope.login_user.last_name }<br/></h4>

</body>
</html>