<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Admin page</title>
</head>
<body>
	<h1>Welcome, "${admin.getName()}"</h1>
	<form method="POST" action="controller">
		<input type="hidden" name="command" value="crashedList">
		<input type="submit" value="Show crashed cars">
	</form>
	<div>
		<c:forEach items="${rentedCars}" var="rentedCar">
		<div>
			<form method="POST" action="controller">
				<input type="hidden" name="command" value="unrent">
				<input type="hidden" name="carId" value="${rentedCar.getId()}">
				<p>${rentedCar}</p>
				<input type="submit" value="Unrent">
			</form>
			<form method="POST" action="controller">
				<input type="hidden" name="command" value="crashed">
				<input type="hidden" name="crashedCarId" value="${rentedCar.getId()}">
				<input type="submit" value="Mark crashed">
			</form>
		</div>
		</c:forEach>
	</div>
</body>
</html>