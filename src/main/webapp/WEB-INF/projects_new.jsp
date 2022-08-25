<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Project</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container d-flex pt-4">
		<div class="container p-3">
			<h2>Register</h2>
			<form:form action="/projects/create" method="post" modelAttribute="newProject">
				<form:label path="title">Project Title:</form:label>
				<form:errors class="text-danger" path="title"/>
				<form:input type="text" path="title" class="form-control mb-2" />
				
				<form:label path="description">Description:</form:label>
				<form:errors class="text-danger" path="description"/>
				<form:textarea path="description" cols="20" rows="3" class="form-control mb-2"></form:textarea>
				
				<form:label path="dueDate" class="mb-2">Due Date:</form:label>
				<form:errors class="text-danger" path="dueDate"/>
				<form:input type="date" path="dueDate" class="form-control mb-2"/>
				
				<input type="submit" value="Register" class="btn btn-primary" />
				<a href="/projects" class="btn btn-secondary">Cancel</a>
			</form:form>
		</div>
	</div>
</body>
</html>