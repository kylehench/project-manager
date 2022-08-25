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
<title>Dashboard</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3">
	<div class="d-flex justify-content-between">
		<div class="me-4">
			<h2>Welcome, ${sessionScope.firstName}</h2>
			<h4>All Projects</h4>
		</div>
		<div class="d-flex flex-column align-items-end">
			<a href="/logout">Logout</a>
			<a href="/projects/new">New Project</a>
		</div>
	</div>
	<table class="table table-hover">
	    <tr>
	        <th>Project</th>
	        <th>Team Lead</th>
	        <th>Due Date</th>
	        <th>Actions</th>
	    </tr>
        <c:forEach var="project" items="${projectsNotIn}">
			    <tr>
			        <td><a href="/projects/${project.id}">${project.title}</a></td>
			        <td>${project.lead.firstName} ${project.lead.lastName}</td>
			        <td><fmt:formatDate pattern = "MMM dd" value = "${project.dueDate}" /></td>
			        <td><a href="/projects/join/${project.id}">Join team</a></td>
			    </tr>
		</c:forEach>
	</table>
	<h4 class="mt-5">Your Projects</h4>
	<table class="table table-hover">
	    <tr>
	        <th>Project</th>
	        <th>Team Lead</th>
	        <th>Due Date</th>
	        <th>Actions</th>
	    </tr>
        <c:forEach var="project" items="${projectsIn}">
			    <tr>
			        <td><a href="/projects/${project.id}">${project.title}</a></td>
			        <td>${project.lead.firstName} ${project.lead.lastName}</td>
			        <td><fmt:formatDate pattern = "MMM dd" value = "${project.dueDate}" /></td>
			        <td>
					    <c:choose>
						    <c:when test="${project.lead.id==sessionScope.userId}">
						    	<a href="/projects/edit/${project.id}">Edit</a>
						    </c:when>
						    <c:otherwise>
						    	<a href="/projects/leave/${project.id}">Leave team</a>
						    </c:otherwise>
						</c:choose>
			        </td>
			    </tr>
		</c:forEach>
	</table>
</div>
</body>
</html>