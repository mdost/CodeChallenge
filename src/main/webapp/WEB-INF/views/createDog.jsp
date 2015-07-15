<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Register Dog</title>
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 
 <style>
.fieldset {
  border: 1px solid #ccc;
  padding: 30px;
}
 
 </style>
 
 
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" id="nav">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">
				</a>
			</div>
			<div>
				<ul class="nav navbar-nav">
				<li><a href="/">Home</a></li>
				<li class="active"><a href="createDog.html">Register Dog</a></li>
				<li><a href="dogs">List of Dogs</a></li>
				<li><a href="map">Maps</a></li>
				<li><a href="algorithm.html">Algorithm</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<div class="container" style="margin-top: 100px;">
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<c:if test="${not empty message}">
				<div class="alert alert-success" id="msgAlert">
					<a href="#" class="close" onclick="$('#msgAlert').hide()">&times;</a>
					<strong>Success: </strong>${message}
				</div></c:if>
				<fieldset class="fieldset">
				<legend style="display: block; text-align: center;">Please enter the following information</legend>
				<div class="form-group row-fluid">
				<form:form action="dogCreated" method="post">
					<label for="usr">Name:</label>
					<input type="text" class="form-control" name="name"><br>
					<label for="usr">Weight (lb):</label>
					<input type="text" class="form-control" name="weight"><br>
					<label for="usr">Heartbeat (b/min):</label>
					<input type="text" class="form-control" name="heartbeat"><br>
					<label for="usr">Temperature (C):</label>
					<input type="text" class="form-control" name="temperature"><br>
					<label for="usr">Lat:</label>
					<input type="text" class="form-control" name="lat"><br>
					<label for="usr">Long:</label>
					<input type="text" class="form-control" name="lon"><br>
					
					<div style="text-align: center; display: block;"><input type="submit" class="btn-success" value="Enter" /></div>
				</form:form>
				</div>
				</fieldset>
			</div>
		
		</div>
	
	
		<footer style="padding-bottom: 20px; margin-top: 40px; height:100%; width:100%; positon:absolute; text-align:center;">
			<span class="glyphicon glyphicon-copyright-mark"></span> copyright Hippity Hop Inc. | <a href="#">Financials</a> | <a href="#">Legal Statement</a> | <a href="#">Developers</a> | <a href="#">Media</a>
		</footer>
	</div>

</body>
</html>