<div class="hotel">
		<figure class="image"><a href="view.jsp?id=<%=request.getAttribute("hotel_id")%>"> <img src="<%=request.getAttribute("img") %>"> </a> </figure>
			<div class="hotel-info">
				<div class="name">
					<h5><a href="view.jsp?id=<%=request.getAttribute("hotel_id")%>"> <%=request.getAttribute("name")%></a></h5>
					<span class="fa fa-star checked"></span>
					<span class="fa fa-star checked"></span>
					<span class="fa fa-star checked"></span>
					<span class="fa fa-star checked"></span>
					<span class="fa fa-star"></span>
				</div>
				<div class="facil">
					<div> <i class="fa fa-check-circle"></i><h6><%=request.getAttribute("wifi") %></h6></div>
					<div> <i class="fa fa-ban" ></i><h6><%=request.getAttribute("parking") %></h6></div>
					<div> <i class="fa fa-check-circle"></i><h6><%=request.getAttribute("beachfront") %></h6></div>
					<div> <i class="fa fa-ban"></i><h6><%=request.getAttribute("woodfront") %></h6></div>
				</div>
				<div class="text">
					<p><a href="view.jsp?id=<%=request.getAttribute("hotel_id")%>"> <%=request.getAttribute("status") %></a></p>
				</div>
			</div>
					
			<div class="price">
				<div class="per">Price per day</div>
				<div class="from-to"> <span><i class="fas fa-dollar-sign">50</i> - <i class="fas fa-dollar-sign">100</i></span></div>
			</div> 
</div>			
