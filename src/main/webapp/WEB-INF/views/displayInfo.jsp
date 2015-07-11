<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Info</title>
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
 	
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" id="nav">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">
<!-- 				<img src="DVI_Logo_footer.png" class="img-rounded" width="70px" height="25px">
 -->				</a>
			</div>
			<div>
				<ul class="nav navbar-nav">
				<li><a href="/controller">Home</a></li>
				<li><a href="createDog.html">Register Dog</a></li>
				<li class="active"><a href="dogs">List of Dogs</a></li>
				<li><a href="map.html">Maps</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<div class="container" style="margin-top: 100px;">
		<fieldset style="text-align: center;">
		<legend><strong>List of all dogs</strong></legend></fieldset>
		<table class="table table-striped">
			<thead>
				<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Heartbeat (b/min)</th>
				<th>Weight (lb)</th>
				<th>Temperature (C)</th>
				<th>Lat</th>
				<th>Long</th>
				<th>Edit/Delete</th>
				</tr>
			</thead>
			<c:forEach items="${listOfDogs}" var="listDog">
				<tr>
					<td><c:out value="${listDog.getId()}"/></td>
					<td><c:out value="${listDog.getName()}"/></td>
					<td><c:out value="${listDog.getHeartbeat()}"/></td>
					<td><c:out value="${listDog.getWeight()}"/></td>
					<td><c:out value="${listDog.getTemperature()}"/></td>
					<td><c:out value="${listDog.getLat()}"/></td>
					<td><c:out value="${listDog.getLong()}"/></td>
					<td><button class="btn-info">Edit</button><button class="btn-danger" style="margin-left: 5px;">Delete</button></td>
				</tr>
			</c:forEach>
			
		</table>
		<footer style="color: white; margin-top: 40px; height:100%; width:100%; positon:absolute; text-align:center;">
			<span class="glyphicon glyphicon-copyright-mark"></span> copyright Hippity Hop Inc. | <a href="#">Financials</a> | <a href="#">Legal Statement</a> | <a href="#">Developers</a> | <a href="#">Media</a>
		</footer>
	</div>

</body>
</html>