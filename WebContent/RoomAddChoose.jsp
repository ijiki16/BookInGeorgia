<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Choose action</title>
<link rel="stylesheet" href="css/CreatePost.css">
<script src="https://kit.fontawesome.com/13f325d0c5.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<script src="js/home.js" type="text/javascript"></script>
<script src="js/addRooms.js" type="text/javascript"></script>
</head>
<body>
	<jsp:include page="Header.jsp" />
	
	<form action="home.jsp" class="inline">
	<button onclick="home.jsp" class="myButton">Home</button>
	</form>
	<form action="addRooms.jsp" class="inline">
	<button type="submit"class="myButton">Add Room</button>
	</form>
</body>
</html>