<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Describe Rooms Below</title>
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
	<img src="images/addroom.png" style="position: absolute; top: 227px; left: 220px;">
	<i class="fas fa-lightbulb bulb" style="color:black"></i>
	<div class="post-facility">
		<h1 id="addInfo">Add Room Info</h1>

		<input type="checkbox" id="tv" name="tv"> 
		<label for="tv">TV</label><br>
		
		<input type="checkbox" id="wifi" name="wifi"> 
		<label for="wifi">WI-Fi</label><br>
		
		<input type="checkbox" id="hotWater" name="hotWater"> 
		<label for="hotWater">Hot Water</label><br>
		
		<input type="checkbox" id="airCo" name="airCo">
		<label for="airCo">Air Conditioning</label><br>

		<div class="text-box">
			<i class="fas fa-bed"></i> <input type="number" id=numBeds value="0"
				min="0">
		</div>
		<%! SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");%>
		<%!Date dt = new Date();%>
		<%! String nw = ft.format(dt); %>
		<div class="text-box">
			<i class="fas fa-calendar-day"></i> <label>Reservation start
				date</label> <input type="date" id="sDate" value=<%=nw%> min=<%=nw%>>
		</div>

		<div class="text-box">
			<i class="fas fa-calendar-day"></i> <label>Reservation end
				date</label> <input type="date" id="eDate" value=<%=nw%> min=<%=nw%>>
		</div>

		<div class="text-box">
			<i class="fas fa-dollar-sign"></i> <input type="number" id="rPrice"
				value="0" min="0" required="required">
		</div>



		<form action="Uploader" method="post" enctype="multipart/form-data">
			<div class="image-upload">
				<div class="file-select">
					<div class="file-select-button" id="fileName">Choose File</div>
					<div class="file-select-name" id="noFile">No file chosen...</div>
					<input type="file" name="chooseFile2" id="image">
				</div>
			</div>
			<button id="next2" class="myButton" style="margin-top: 5px">Next</button>
		</form>
		<input type="hidden" id="hotel_id" value="<%=request.getParameter("hotel_id")%>">
		<button id="save2" class="myButton" style="margin-top: 5px">Upload</button>
	</div>

</body>
</html>