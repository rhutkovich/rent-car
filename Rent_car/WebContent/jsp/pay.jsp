<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="css/pay_style.css">
		<title>Payment</title>
	</head>

	<body>
		<div class="header">
			<h1 align="center">Payment</h1>
		</div>
		<div class="keeper">
		<form name="payment" method="POST" action="controller" class="payform">
			<input type="hidden" name="command" value="pay">
			<table>
				<tr>
					<td align="center" colspan="2"><h3>Your info:</h3></td>
				</tr>
				<tr>
					<td><label for="name">Full name:</label></td>
					<td><input type="text" id="name" value="${user.getName()}" class="textbox"></td>
				</tr>
				<tr>
					<td><label for="passport-id">Passport number:</label></td>
					<td><input type="text" id="passport-id" value="${user.getPassportNumber()}" class="textbox"></td>
				</tr>
				<tr>
					<td><label for="passport-date">Passport date:</label></td>
					<td><input type="text" id="passport-date" value="${user.getPassportDate()}" class="textbox"></td>
				</tr>
				<tr>
					<td><label for="passport-depart">Passport departament:</label></td>
					<td><input type="text" id="passport-depart" value="${user.getPassportDepart()}" class="textbox"></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><h3>Card info:</h3></td>
				</tr>
				<tr>
					<!--<td align="center"><h3>Card info:</h3></td>-->
					<td><label for="card-number">Card number:</label></td>
					<td><input type="text" id="card-number" value="4213123XXXXX900" class="textbox"></td>
				</tr>
				<tr>
					<td><label for="valit-time">Valid until:</label></td>
					<td>
						<select name="month">
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
						</select>
						<select name="year">
							<option>2014</option>
							<option>2015</option>
							<option>2016</option>
							<option>2017</option>
							<option>2018</option>
							<option>2019</option>
							<option>2020</option>
							<option>2021</option>
							<option>2022</option>
							<option>more</option>
						</select>
					</td>
				</tr>
			</table>
			<p>Rented for "${useTerm}" days</p>
			<p>Total sum: "${totalSum}" $</p>
			<input type="hidden" name="useTerm" value="${useTerm}">
			<input type="submit" value="Pay">
			<input type="submit" value="Cancel" formaction="javascript:history.back()">
		</form>
	</div>
	</body>
</html>