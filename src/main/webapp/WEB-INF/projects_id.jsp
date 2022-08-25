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
	<div class="container d-flex justify-content-between align-items-center pt-4">
		<h2>Project Details</h2>
		<a href="/projects">Back to Dashboard</a>
	</div>
	<table class="table table-hover">
	  <tr>
	    <td>Project</td>
	    <td>${project.title}</td>
	  </tr>
	  <tr>
	    <td>Description</td>
	    <td>${project.description}</td>
	  </tr>
	  <tr>
	    <td>Due Date</td>
	    <td><fmt:formatDate pattern = "M/d/yyyy" value = "${project.dueDate}" /></td>
	  </tr>
	</table>
	<a href="/projects/${project.id}/tasks">See tasks!</a>
</div>
</body>
</html>