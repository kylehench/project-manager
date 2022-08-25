<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login and Registration</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container d-flex pt-4">
		<div class="container p-3">
			<h2>Register</h2>
			<form:form action="/register" method="post" modelAttribute="newUser">
				<form:label path="firstName">First Name:</form:label>
				<form:errors class="text-danger" path="firstName"/>
				<form:input type="text" path="firstName" class="form-control mb-2" />
				<form:label path="lastName">Last Name:</form:label>
				<form:errors class="text-danger" path="lastName"/>
				<form:input type="text" path="lastName" class="form-control mb-2" />
				<form:label path="email">Email:</form:label>
				<form:errors class="text-danger" path="email"/>
				<form:input type="text" path="email" class="form-control mb-2" />
				<form:label path="password">Password:</form:label>
				<form:errors class="text-danger" path="password"/>
				<form:input type="password" path="password" class="form-control mb-2" />
				<form:label path="passwordConfirm">Confirm Password:</form:label>
				<form:errors class="text-danger" path="passwordConfirm"/>
				<form:input type="password" path="passwordConfirm" class="form-control mb-2" />
				<input type="submit" value="Register" class="btn btn-primary" />
			</form:form>
		</div>
		<div class="container p-3">
			<h2>Login</h2>
			<form:form action="/login" method="post" modelAttribute="newLogin">
				<form:label path="email">Email:</form:label>
				<form:errors class="text-danger" path="email"/>
				<form:input type="text" path="email" class="form-control mb-2" />
				<form:label path="password">Password:</form:label>
				<form:errors class="text-danger" path="password"/>
				<form:input type="password" path="password" class="form-control mb-2" />
				<input type="submit" value="Login" class="btn btn-success" />
			</form:form>
		</div>
	</div>
</body>
</html>