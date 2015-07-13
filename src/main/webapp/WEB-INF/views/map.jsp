<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Maps</title>
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
 	<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>  

<script>
	/* $.ajax({
		type: "GET",
		url: "dogs",
		contentType: "applicaton/json",
		success: function(){
			System.out.println("success")
		},
		error: function(response){
			System.out.println()
		}
		
	}); */
	
	function loadMap() {
		var dogs = new Array();

		<c:forEach items="${listOfDogs}" var="item">
		    var dog = new Object();
	
		    dog.name = '${item.getName()}';
		    dog.id = '${item.getId()}';
		    dog.lat = '${item.getLat()}';
		    dog.lon = '${item.getLong()}';
		    dog.weight = '${item.getWeight()}';
		    dog.temperature = '${item.getTemperature()}';
		    dog.heartbeat = '${item.getHeartbeat()}';
		    
		    dogs.push(dog);
		</c:forEach>
		
		var infowindow = new google.maps.InfoWindow();
		
		var myOptions = {
			 zoom: 18,
			 mapTypeId: google.maps.MapTypeId.HYBRID
		};
		var map = new google.maps.Map(document.getElementById("map_container"),myOptions);
		
		dogs.forEach(function(arrayItem){
	    	var latlng = new google.maps.LatLng(arrayItem.lat, arrayItem.lon);
	    
	    	var marker = new google.maps.Marker({
	      		position: latlng, 
	      		map: map, 
	      		title: arrayItem.name,
	      		animation: google.maps.Animation.BOUNCE
	    	}); 
	    	map.setCenter(marker.getPosition());
	    	
	    	var contentString="Name: "+arrayItem.name+"\nHeartbeat: "+arrayItem.heartbeat+"\nTemperature: "+arrayItem.temperature+"\nWeight: "+arrayItem.weight;
	    	
	    	 google.maps.event.addListener(marker, 'click', function() {
	    		infowindow.close();
	    		infowindow.setContent(contentString);
	    	    infowindow.open(map,marker);
	    	  }); 
	    	
		});
		
	 }

	
</script>

<style>
#map_container{	
	width: 1150px;
	height: 500px;
}

</style>

</head>
<body onload="loadMap()">
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
				<li><a href="dogs">List of Dogs</a></li>
				<li class="active"><a href="map.html">Maps</a></li>
				<li><a href="algorithm.html">Algorithm</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<div class="container" style="margin-top: 100px;">
		<h3 style="display: block; text-align: center;">Maps</h3>
		
		<div class="row">
<!-- 			<div class="col-md-4"><p>The map below shows where the location of all dogs</p></div>
 -->			<div class="col-md-8">
				<p>The map below shows where the location of all dogs</p>
				<div id="map_container"></div>
			</div>
		</div>
		<footer style="margin-top: 40px; height:100%; width:100%; positon:absolute; text-align:center;">
			<span class="glyphicon glyphicon-copyright-mark"></span> copyright Hippity Hop Inc. | <a href="#">Financials</a> | <a href="#">Legal Statement</a> | <a href="#">Developers</a> | <a href="#">Media</a>
		</footer>
	</div>

</body>
</html>