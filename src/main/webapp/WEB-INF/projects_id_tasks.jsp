<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Details</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-between align-items-center mt-4">
		<h2>Project: ${project.title}</h2>
		<a href="/projects">Back to Dashboard</a>
	</div>
	<p>Project Lead: ${project.lead.firstName}</p>
	<form:form action="/projects/${project.id}/tasks/create" method="post" modelAttribute="newTask" class="mb-4">
		<form:label path="description">Add a task ticket for this team:</form:label>
		<form:errors path="description" class="text-danger"/>
		<form:textarea path="description" rows="3" class="form-control mb-2"></form:textarea>
		<input type="submit" value="Submit" class="btn btn-primary" />
	</form:form>
	<c:forEach var="task" items="${project.tasks}">
	    <p>
	    	<c:set var="timeZone" value="GMT-7"/>
	    	<b>Added by ${task.user.firstName} at <fmt:formatDate pattern="h:mm a MMM d" value="${task.createdAt}" timeZone="${timeZone}" />:</b>
	    	<br>${task.description}
	    </p>
	</c:forEach>
</div>
</body>
</html>