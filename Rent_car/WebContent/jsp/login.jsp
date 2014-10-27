<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<!-- Internalization -->
	<fmt:setLocale value="${param.language}" scope="session"/>
	<fmt:setBundle basename="com.epam.rentcar.internalization.login.LoginContent" var="lang"/>
	<!-- --------------- -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><fmt:message key="title" bundle="${lang}" /></title>
	<link rel="stylesheet" type="text/css" href="jsp/css/style.css">
</head>
<body>
	<form id="login" name="loginForm" method="POST" action="controller">
		<input type="hidden" name="command" value="login" />
		<p>
			<fmt:message key="login" bundle="${lang}" />
			<br/>
			<input type="text" name="login" class="textbox"/>
			<br/>
			<fmt:message key="password" bundle="${lang}"/>
			<br/>
			<input type="password" name="password" class="textbox"/>
			<br/>
			<br/>
			<fmt:message key="enter" bundle="${lang}" var="enter"/>
			<fmt:message key="register" bundle="${lang}" var="register" />
			<input type="submit" class="button" value="${enter}"/>
			<input type="submit" class="button" value="${register}"/>
			<br/>
			<!-- TODO: -->
			<fmt:message key="${errorLoginPassMessage}" bundle="${lang}" />
			${wrongAction}
			${nullPage}
			<a href="?language=en">English</a>
			<a href="?language=ru">Russian</a>
		</p>
	</form>
	<div id="car"></div>	
</body>
</html>