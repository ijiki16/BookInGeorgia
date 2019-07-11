<%@page import="Managers.HotelManager"%>
<%@page import="Models.Hotel"%>
<%@page import="Models.Facilities"%>
<div class="hotel">
		<%Integer hotel_id = Integer.parseInt((String)request.getAttribute("hotel_id"));
		Hotel hotel = HotelManager.getInstance().getHotel(hotel_id);
		Facilities facil = hotel.getFacilities();
		if(facil == null) facil = new Facilities(-1,"",false,false,false,false);
		Integer min_price = HotelManager.getInstance().getMinPrice(hotel_id);
		Integer max_price = HotelManager.getInstance().getMaxPrice(hotel_id);
		%>
		<figure class="image"><a href="hotelpage.jsp?id=<%=request.getAttribute("hotel_id")%>"> <img id="img" src="<%=hotel.getImage()%>"> </a> </figure>
			<div class="hotel-info">
				<div class="name">
					<h5><a href="hotelpage.jsp?id=<%=request.getAttribute("hotel_id")%>"> <%=hotel.getName()%></a></h5>
					<%for(int i = 0; i < hotel.getRating(); i++) {%>
						<span class="fa fa-star checked"></span>
					<%}
					for(int i = hotel.getRating(); i < 5; i++){ %>
						<span class="fa fa-star"></span>
					<%} %>
				</div>
				<div class="facil">
					<div> <i class="fa fa-<%= facil.getWiFi() == true ? "check-circle" : "ban"%>"></i><h6>Wi-Fi</h6></div>
					<div> <i class="fa fa-<%= facil.getParking() == true ? "check-circle" : "ban"%>" ></i><h6>Parking</h6></div>
					<div> <i class="fa fa-<%= facil.getBeachfront() == true ? "check-circle" : "ban"%>"></i><h6>Near Beach</h6></div>
					<div> <i class="fa fa-<%= facil.getWoodfront() == true ? "check-circle" : "ban"%>"></i><h6>Near Forest</h6></div>
				</div>
				<div class="text">
					<p><a href="hotelpage.jsp?id=<%=request.getAttribute("hotel_id")%>"> <%=hotel.getStatus()%></a></p>
				</div>
			</div>
					
			<div class="price">
				<div class="per">Price per day</div>
				<div class="from-to"> <span><i class="fas fa-dollar-sign">50</i> - <i class="fas fa-dollar-sign">100</i></span></div>
			</div> 
</div>			
