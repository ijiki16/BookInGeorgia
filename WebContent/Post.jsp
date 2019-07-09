<div class="hotel">
		<figure class="image"><a href="hotelpage.jsp?id=<%=request.getAttribute("hotel_id")%>"> <img src="<%=request.getAttribute("img") %>"> </a> </figure>
			<div class="hotel-info">
				<div class="name">
					<h5><a href="hotelpage.jsp?id=<%=request.getAttribute("hotel_id")%>"> <%=request.getAttribute("name")%></a></h5>
					<% Integer st = (Integer) request.getAttribute("rating"); %>
					<%for(int i = 0; i < st; i++) {%>
						<span class="fa fa-star checked"></span>
					<%}
					for(int i = st; i < 5; i++){ %>
						<span class="fa fa-star"></span>
					<%} %>
				</div>
				<div class="facil">
					<div> <i class="fa fa-<%= (boolean)request.getAttribute("wifi") == true ? "check-circle" : "ban"%>"></i><h6>Wi-Fi</h6></div>
					<div> <i class="fa fa-<%= (boolean)request.getAttribute("parking") == true ? "check-circle" : "ban"%>" ></i><h6>Parking</h6></div>
					<div> <i class="fa fa-<%= (boolean)request.getAttribute("beachfront") == true ? "check-circle" : "ban"%>"></i><h6>Near Beach</h6></div>
					<div> <i class="fa fa-<%= (boolean)request.getAttribute("woodfront") == true ? "check-circle" : "ban"%>"></i><h6>Near Forest</h6></div>
				</div>
				<div class="text">
					<p><a href="hotelpage.jsp?id=<%=request.getAttribute("hotel_id")%>"> <%=request.getAttribute("status") %></a></p>
				</div>
			</div>
					
			<div class="price">
				<div class="per">Price per day</div>
				<div class="from-to"> <span><i class="fas fa-dollar-sign">50</i> - <i class="fas fa-dollar-sign">100</i></span></div>
			</div> 
</div>			
