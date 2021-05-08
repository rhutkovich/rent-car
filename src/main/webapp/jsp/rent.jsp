<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- Internalization -->
		<fmt:setLocale value="ru" />
		<fmt:setBundle basename="ConfirmPageContent" var="client_lang" />
		<fmt:setBundle basename="MainContent" var="lang" />
		<!-- --------------- -->
		<title><fmt:message key="title" bundle="${client_lang}" /></title>
		<link rel="stylesheet" type="text/css" href="jsp/css/rent_style.css">
	</head>
	<body>
		<div class="header">
			<h1 align="center"><fmt:message key="title" bundle="${client_lang}" /></h1>
		</div>
		<div class="keeper">
			<form method="post" action="controller">
				<div class="car-info">
					<input type="hidden" name="command" value="confirm">
					<h3><fmt:message key="infoClient" bundle="${client_lang}" />:</h3>
					Name: "${user.getName()}"<br/>
					Passport: "${user.getPassportNumber()}"<br/>
					Date: "${user.getPassportDate()}"<br/>
					Depart: "${user.getPassportDepart() }"<br/>
					<select name="useTerm">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
						<option>6</option>
						<option>7</option>
						<option>8</option>
						<option>9</option>
						<option>10</option>
						<option>11</option>
						<option>12</option>
						<option>13</option>
						<option>14</option>
						<option>15</option>
						<option>16</option>
						<option>17</option>
						<option>18</option>
						<option>19</option>
						<option>20</option>
						<option>21</option>
						<option>22</option>
						<option>23</option>
						<option>24</option>
						<option>25</option>
					</select>
					<input type="submit" value="confirm">
				</div>
				<div class="client-info">
					<h3>Car info:</h3>
						<ul style="list-style: none;">
							<li><fmt:message key="manufacturer" bundle="${lang}" />: <c:out value="${rentedCar.getManufacturer()}" /></li>
							<li><fmt:message key="model" bundle="${lang}" />: <c:out value="${rentedCar.getModel()}" /></li>
							<li><fmt:message key="class" bundle="${lang}" />: <c:out value="${rentedCar.getCarClass()}" /></li>
							<li>
								<fmt:message key="color" bundle="${lang}" />:
								<c:set var="col" value="${fn:split(rentedCar.getColor(),'_')}" />
								<fmt:message key="${col[0]}" bundle="${lang}" /> 
								<fmt:message key="${col[1]}" bundle="${lang}" />
							</li>
							<li>
								<fmt:message key="carcase" bundle="${lang}" />: 
								<fmt:message key="${rentedCar.getCarcase()}" bundle="${lang}"/>
							</li>
							<li><fmt:message key="issue" bundle="${lang}" />: <c:out value="${rentedCar.getIssueDate()}" /></li>
							<li>
								<fmt:message key="trans" bundle="${lang}" />: 
								<fmt:message key="${rentedCar.getTransmission()}" bundle="${lang}" />
							</li>
							<li><fmt:message key="engine.vol" bundle="${lang}" />: <c:out value="${rentedCar.getEngineVol()}" /> </li>
							<li><fmt:message key="fuel" bundle="${lang}" />: <c:out value="${rentedCar.getFuelFlow()}" /></li>
							<li>
								<fmt:message key="engine.type" bundle="${lang}" />: 
								<fmt:message key="${rentedCar.getEngineType()}" bundle="${lang}" />
							</li>
							<li>
								<fmt:message key="cost" bundle="${lang}" />: 
								<c:out value="${rentedCar.getCost()}" />$
								<input type="hidden" name="carCost" value="${rentedCar.getCost()}">
							</li>
						</ul>
				</div>
			</form>	
		</div>
	</body>
</html>