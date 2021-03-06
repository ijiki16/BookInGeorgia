<%@page import="Managers.AccountManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*"%>
    <%@page import="DataBases.HotelsDB"%>
    <%@page import="Managers.HotelManager"%>
    <%@page import="Models.Hotel"%>
    <%@page import="Models.Account"%>
    <%@page import="Models.Location"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookInGeorgia</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/flaticon.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/Post.css">
<script src="https://kit.fontawesome.com/13f325d0c5.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
</head>
<body>
	<div class="homepage"></div>
		<jsp:include page="Header.jsp"/>
		<section class="main">
			<section class="search-sec">
				<div class="container">
					<form action="#" method="post" novalidate="novalidate">
						<div class="row">
							<div class="col-lg-12">
								<div class="row">
									<div class="col-lg-3 col-md-3 col-sm-12 p-0">
										<select class="form-control search-slt"
											id="exampleFormControlSelect1">
											<option>Select</option>
											<%HotelManager hm = HotelManager.getInstance();
											List<String> locations = hm.getAllLocations();
												for(String loc : locations){%>
													<option><%=loc %></option>
												<%}%>
										</select>
									</div>
									
									<div class="col-lg-5 col-md-5 col-sm-12 p-0">
										<input type="text" class="form-control search-slt"
											placeholder="Enter Hotel Name" id="hotel-name">
									</div>
									
									<div class="col-lg-4 col-md-4 col-sm-12 p-0">
										<button type="button" class="btn btn-danger wrn-btn" id="search">Search</button>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</section>
		</section>
		<% boolean wifi = request.getSession().getAttribute("wifi") == null ? false : request.getSession().getAttribute("wifi").equals("true");
		boolean beach = request.getSession().getAttribute("beach") == null ? false : request.getSession().getAttribute("beach").equals("true");
		boolean wood = request.getSession().getAttribute("wood") == null ? false : request.getSession().getAttribute("wood").equals("true");
		boolean parking = request.getSession().getAttribute("parking") == null ? false : request.getSession().getAttribute("parking").equals("true");

		String ar = (String)request.getSession().getAttribute("arr");
		
		boolean[] arr = new boolean[5];
		for(int i = 0; i < 5; i++){
			if(ar == null) arr[i] = false; 
			else arr[i] = (ar.charAt(i) == '1') ? true : false;
		}%>
		<div class="filter">
			<div class="filter-wrap">
				<form action="#" method="get">
					<h2>Filter by</h2>
					<div class="check">
						<h5>Star rating</h5>
						<input type="checkbox" id="5st" <%if(arr[4]){%> checked <%}%>>
						<span class="checkmark"> 5 stars </span> <br>
						<input type="checkbox" id="4st" <%if(arr[3]){%> checked <%}%>>
						<span class="checkmark"> 4 stars </span> <br>
						<input type="checkbox" id="3st" <%if(arr[2]){%> checked <%}%>>
						<span class="checkmark"> 3 stars </span> <br>
						<input type="checkbox" id="2st" <%if(arr[1]){%> checked <%}%>>
						<span class="checkmark"> 2 stars </span> <br>
						<input type="checkbox" id="1st" <%if(arr[0]){%> checked <%}%>>
						<span class="checkmark"> 1 stars </span> <br>
					</div>
					<div class="check">
						<h5>Facilities</h5>
						<input type="checkbox" id="wi-fi" <%if(wifi){%> checked <%}%>>
						<span class="checkmark"> Wi-Fi </span> <br>
						<input type="checkbox" id="parking" <%if(parking){%> checked <%}%>>
						<span class="checkmark"> Parking </span> <br>
						<input type="checkbox" id="beach" <%if(beach){%> checked <%}%>>
						<span class="checkmark"> Beachfront </span> <br>
						<input type="checkbox" id="forest" <%if(wood){%> checked <%}%>>
						<span class="checkmark"> Near forest </span> <br>
					</div>
					<input class="filter-btn" type="button" name="" value="Filter"> 
				</form>
			</div>
		</div>
		
		<div class="hotels">
		<%  String city = (String)request.getSession().getAttribute("city");
			String hotel_name = (String)request.getSession().getAttribute("hotel_name");
			
			List<Integer> IDs = hm.getSearchedHotels(city, hotel_name);
			IDs = hm.intersectLists(IDs, hm.getFilteredHotels(arr, beach, wood, wifi, parking));
			
			for(Integer hotel_id : IDs){
				if(hm.getHotel(hotel_id) == null) continue;
				Hotel hotel = hm.getHotel(hotel_id);
				request.setAttribute("hotel_id", hotel_id);%>
				<jsp:include page="Post.jsp"/>
			<%}%>
		</div>
		
		<%if(request.getSession().getAttribute("1st") == null) {
			request.getSession().setAttribute("1st", "visited");%>
			<div class="moto"> 
				<i class="fas fa-times-circle" id="close-moto"></i> <br>
				<a href="https://www.facebook.com/SpendYourSummerInGeo/"> #Spend Your Summer in Georgia </a>
			</div>
		<%}%>
</body>
</html>