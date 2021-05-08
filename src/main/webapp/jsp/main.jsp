<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<!-- Internalization -->
	<fmt:setLocale value="ru" />
	<fmt:setBundle basename="MainContent" var="lang" />
	<!-- --------------- -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="jsp/css/main_style.css">
	<title><fmt:message key="title" bundle="${lang}" /></title>
</head>

<body>
	<div align="center" class="content"> 
		<h1><fmt:message key="title" bundle="${lang}" />:</h1>
		<!-- Блоки с доступными машинами -->
		<c:forEach items="${freeCars}" var="car">
			<div class="item">
					<form name="car-info" method="POST" action="controller">
						<input type="hidden" name="command" value="rent">
						<input type="hidden" name="rentedId" value="${car.getId()}">
						<ul style="list-style: none;">
							<li><fmt:message key="manufacturer" bundle="${lang}" />: <c:out value="${car.getManufacturer()}" /></li>
							<li><fmt:message key="model" bundle="${lang}" />: <c:out value="${car.getModel()}" /></li>
							<li><fmt:message key="class" bundle="${lang}" />: <c:out value="${car.getCarClass()}" /></li>
							<li>
								<fmt:message key="color" bundle="${lang}" />:
								<!-- В цвет представлен в виде ЦВЕТ_ТИП
									Например: BLACK_M - черный матовый
									Для локализации разбивается на BLACK и M
									и используются как ключи в ресурс бандлах
								 --> 
								<c:set var="col" value="${fn:split(car.getColor(),'_')}" />
								<fmt:message key="${col[0]}" bundle="${lang}" /> 
								<fmt:message key="${col[1]}" bundle="${lang}" />
							</li>
							<li>
								<fmt:message key="carcase" bundle="${lang}" />: 
								<fmt:message key="${car.getCarcase()}" bundle="${lang}"/>
							</li>
							<li><fmt:message key="issue" bundle="${lang}" />: <c:out value="${car.getIssueDate()}" /></li>
							<li>
								<fmt:message key="trans" bundle="${lang}" />: 
								<fmt:message key="${car.getTransmission()}" bundle="${lang}" />
							</li>
							<li><fmt:message key="engine.vol" bundle="${lang}" />: <c:out value="${car.getEngineVol()}" /> </li>
							<li><fmt:message key="fuel" bundle="${lang}" />: <c:out value="${car.getFuelFlow()}" /></li>
							<li>
								<fmt:message key="engine.type" bundle="${lang}" />: 
								<fmt:message key="${car.getEngineType()}" bundle="${lang}" />
							</li>
							<li><fmt:message key="cost" bundle="${lang}" />: <c:out value="${car.getCost()}" />$</li>
							<fmt:message key="button_logo" bundle="${lang}" var="but"/>
							<li><input type="submit" class="button" value="${but}"></li>
						</ul>
					</form>
					<img id="image" alt="${car.getId()}" src="jsp/img/cars/${car.getId()}.jpg" width="380px" height="250px">	
			</div>
		</c:forEach>
	</div>
	<div class="welcome_box">
			<fmt:message key="welcome_logo" bundle="${lang}" />
			<br>
			${user.getName()}
			<br/>
			<font size="2">
				<a href="controller?command=logout">
					<fmt:message key="logout_logo" bundle="${lang}" />
				</a>
			</font>
	</div>
	<div class="options">
			<form method="post">
			<input type="hidden" name="command" value="options">
			<fmt:message key="any" bundle="${lang }" var="anyValue" />
			<select name="manufacturer">
				<option value="any">${anyValue}</option>
				<c:forEach items="${options.getManufacturers()}" var="manuf">
					<option ${manuf.equals(options.getSelectedManufacturer())? "selected" : ""} 
							value="${manuf}">${manuf}</option>
				</c:forEach>
			</select>
			<select name="model">
				<option value="any">${anyValue}</option>
				<c:forEach items="${options.getModels()}" var="model">
					<option ${model.equals(options.getSelectedModel())? "selected" : ""} value="${model}">${model}</option>
				</c:forEach>
			</select>
			<select name="color">
				<option value="any">${anyValue}</option>
				<c:forEach items="${options.getColors()}" var="colour">
					<option ${colour.equals(options.getSelectedColor())? "selected" : ""} value="${colour}">
					<!-- TODO!! -->
					<c:set var="col" value="${fn:split(colour,'_')}" />
						<fmt:message key="${col[0]}" bundle="${lang}" /> 
						<fmt:message key="${col[1]}" bundle="${lang}" />
					</option>
				</c:forEach>
			</select>
			<select name="date-from">
				<option value="any">${anyValue}</option>
				<c:forEach items="${options.getDates()}" var="date">
					<option ${date.equals(options.getSelectedDateFrom())? "selected" : ""} value = "${date}">${date}</option>
				</c:forEach>
			</select>
			<select name="date-to">
				<option value="any">${anyValue}</option>
				<c:forEach items="${options.getDates()}" var="date">
					<option ${date.equals(options.getSelectedDateTo())? "selected" : ""} value = "${date}">${date}</option>
				</c:forEach>
			</select>
			<select name="cost-from">
				<option value="any">${anyValue}</option>
				<c:forEach items="${options.getCosts()}" var="cost">
					<option ${options.getSelectedCostFrom().equals(cost.toString())? "selected" : ""} value ="${cost}">${cost}</option>
				</c:forEach>
			</select>
			<select name="cost-to">
				<option value="any">${anyValue}</option>
				<c:forEach items="${options.getCosts()}" var="cost">
					<option ${options.getSelectedCostTo().equals(cost.toString())? "selected" : ""} value ="${cost}">${cost}</option>
				</c:forEach>
			</select>
			<fmt:message key="perform" bundle="${lang}" var="perf" />
			<input type="submit" value="${perf}">
		</form>
	</div>
</body>
</html>