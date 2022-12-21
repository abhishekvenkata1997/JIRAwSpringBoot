<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task new values:</title>
</head>
<body>
	Task Updated Successfully!
	${requestScope.task.task_id }
	${requestScope.task.summary }
	${requestScope.task.issue_type }
	${requestScope.task.description }
	${requestScope.task.priority }
	<a href="../viewtask/${requestScope.task2_id}.htm">View task</a>
</body>
</html>
