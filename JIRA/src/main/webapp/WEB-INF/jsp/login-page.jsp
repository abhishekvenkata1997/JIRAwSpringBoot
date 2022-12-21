<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login to jira</h1>
        
    <form:form modelAttribute="user">
        Email: <form:input path="email_id" /><br/><form:errors path="email_id"/><br/>
        Password: <form:input path="password"/><br/><form:errors path="password"/><br/>
        <form:button name="Submit" type="submit" value="Login"/>
    </form:form>
        
    </body>
</html>
