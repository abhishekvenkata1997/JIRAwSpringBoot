<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Set User</title>
</head>
    <body>
        <h1>Start a User session</h1>
        
    <form:form modelAttribute="user">
        Email: <form:input path="email_id" /><br/><form:errors path="email_id"/>&nbsp; &nbsp; ${requestScope.error}<br/>
        
        Password: <form:input path="password"/><br/><form:errors path="password"/><br/>
        <form:button name="Submit" type="submit">Start Session</form:button>
    </form:form>
        
    </body>
</html>