<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Result</title>
</head>
<body>
	<p>hello are you working</p>
	<p>ID ${dog.getId()}</p>
	<p>Name ${dog.getName()}</p>
	<p>weight ${dog.getWeight()}</p>
	<p>temperature ${dog.getTemperature()}</p>
	<p>heartbeat ${dog.getHeartbeat()}</p>
	<p>lat ${dog.getLat()}</p>
	<p>long ${dog.getLon()}</p>
	
	
	
    <a href="/controller">Submit another message</a>
	<a href="/getdogs">See entire list of dogs</a>
</body>
</html>