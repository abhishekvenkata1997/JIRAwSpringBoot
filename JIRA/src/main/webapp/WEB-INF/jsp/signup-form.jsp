<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<head>
    <meta charset="ISO-8859-1">
    <title>Sign Up</title>
</head>
<body>
    <div class="container text-center">
        <div>
            <h1>User Registration - Sign Up</h1>
        </div>
        <form:form modelAttribute="user" method="post">
        <div class="m-3">
            <div class="form-group row">
                <label class="col-4 col-form-label">E-mail: </label>
                <div class="col-8">
 					<form:input path="email_id" size="30" /><font color="red"><form:errors path="email_id"/></font>
 					${requestScope.error }
                </div>
            </div>
             
            <div class="form-group row">
                <label class="col-4 col-form-label">Password: </label>
                <div class="col-8">
                    <form:input path="password" size="30"/><font color="red"><form:errors path="password"/></font>
                </div>
            </div>
            
            <div class="form-group row">
                <label class="col-4 col-form-label">First Name: </label>
                <div class="col-8">
                    <form:input path="first_name" size="30"/><font color="red"><form:errors path="first_name"/></font>
                </div>
            </div>
             
            <div class="form-group row">
                <label class="col-4 col-form-label">Last Name: </label>
                <div class="col-8">
                    <form:input path="last_name" size="30"/><font color="red"><form:errors path="last_name"/></font>
                </div>
            </div>
            <div>
                <form:button type="submit" class="btn btn-primary">Sign Up</form:button>
            </div>
        </div>
        </form:form>
    </div>
</body>
</html>