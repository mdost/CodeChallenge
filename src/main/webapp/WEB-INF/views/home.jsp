<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
 
 <style>
 .navbar{
 	background-color: transparent;
 	background: transparent;
 	border-color: transparent;
 }
 </style>
</head>
<body>
	<div style="background-color: #003D00;">
	<nav class="navbar navbar-inverse navbar-fixed-top" id="nav">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">
<!-- 				<img src="DVI_Logo_footer.png" class="img-rounded" width="70px" height="25px">
 -->				</a>
			</div>
			<div>
				<ul class="nav navbar-nav">
				<li class="active"><a href="/controller">Home</a></li>
				<li><a href="createDog.html">Register Dog</a></li>
				<li><a href="dogs">List of Dogs</a></li>
				<li><a href="map.html">Maps</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<div class="container-fluid" style="padding: 0;">
		<div class="jumbotron" style="width: 100%; height: 100%; background: url('resources/images/dogs.jpg'); background-repeat: no-repeat; background-position: center;  background-size: 100% auto; background-color:transparent;">
			<div class="container">
			 	<div class="row">
			 		<div class="col-md-8 col-md-offset-2">
						<h1 style="color: white; text-align: center; padding-top: 50px;">Hippity Hop</h1>
						<p style="color: white; text-align: center;">The first indoor dog park in Calgary</p>
						<a href="#create" class="glyphicon glyphicon-menu-down" style="font-size: 5em; color: white; display: block; text-align: center;" ></a>
					</div>
				</div>
			</div>

		</div>
		
	</div>
	
	<div class="container-fluid">
		<div id="create" class="jumbotron" style="padding: 0; width: 100%; height: 100%; background-color: transparent;">
			<div class="row">
				<div class="col-md-8 col-md-offset-2" style="text-align: center; display: block;">
					<div style="margin-top: 150px; color: white;">
					<h2>About</h2>
					<p>Owners can leave their dogs at Hippity Hop, where dogs will be fed, washed, and groommed. In addition to this dogs can play with various toys available at Hippity Hop. </p>
					<br><p>We offer a safe, reliable and fun environment for dogs to </p>
					<button class="btn-success" onclick="location.href='createDog.html'">Register your dog</button>
					</div>
				</div>
			</div>
		</div>
	
	</div>
	
	<div class="container-fluid">
		<div class="jumbotron" style="padding: 0; width: 100%; height: 100%; background-color: transparent;">
			<div class="row">
				<div class="col-md-8 col-md-offset-2" style="text-align: center; display: block;">
					<div style="margin-top: 130px; color: white;">
					<h3>Track your dog</h3>
					<p>We offer a online dog tracking, where owners can see where there dog is at all times. Each dog is given a tracking sensor that contains a unique ID. This sensor records the dogs heartbeat, temperature, and position (lat and long). This gives customers a peace of mind that they can leave there pooches and grab some coffee upstairs as they can track their location at all times on their cellphone, computer, or tablets.</p>
					</div>
				</div>
			</div>
		</div>
		<footer style="color: white; margin-top: 40px; height:100%; width:100%; positon:absolute; text-align:center;">
		<span class="glyphicon glyphicon-copyright-mark"></span> copyright Hippity Hop Inc. | <a href="#">Financials</a> | <a href="#">Legal Statement</a> | <a href="#">Developers</a> | <a href="#">Media</a>
		</footer>
	</div>
<%-- <form:form action="controller/dog" method="post">
	<p>Name: <input type="text" name="name" /></p>
    <p>heartbeat: <input type="text" name="heartbeat" /></p>
    <p>weight: <input type="text" name="weight" /></p>
    <p>temperature: <input type="text" name="temperature" /></p>
    <p>lat: <input type="text" name="lat" /></p>
    <p>long: <input type="text" name="lon" /></p>

    <input type="submit" value="Enter" />
</form:form> --%>

<%-- <form:form action="dogs" method="post">
	<p>Id: <input type="text" name="id" id="id"/></p>
 <input type="submit" value="Enter" />
</form:form>
<button>hello</button>
 --%>
<!-- <a href="getDogs">See entire list of dogs</a>
 --><%-- <P>  The time on the server is ${serverTime}. </P>
 --%>
 
<%--  <P>  The time on the server is ${getDog}. </P>
 --%>
 	
 </div>
</body>
</html>