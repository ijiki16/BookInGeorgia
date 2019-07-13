<%@page import="Managers.HotelManager"%>
<%Integer hotel_id = Integer.parseInt((String)request.getParameter("hotel_id"));
		boolean bool = HotelManager.getInstance().deleteHotel(hotel_id);%>
<%=bool %>