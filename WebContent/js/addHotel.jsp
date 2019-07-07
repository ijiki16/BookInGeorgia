<%@page import="Managers.AccountManager"%>
<%@page import="Managers.HotelManager"%>
<%@page import="Models.Account"%>
<%  int id = Integer.parseInt(AccountManager.getInstance().getAccount((String)request.getSession().getAttribute("user")).getId());
	HotelManager hm = HotelManager.getInstance();
	int hotel_id = hm.getLastHotelId();
	hm.addHotel(request.getParameter("name"),  Integer.parseInt(request.getParameter("stars")), request.getParameter("image"), request.getParameter("status"), request.getParameter("number"), id);
	hm.addLocation(hotel_id, request.getParameter("city"), request.getParameter("street"));
	request.setAttribute("hotel_id", hotel_id);
	request.getRequestDispatcher("../addRooms.jsp").forward(request, response);
%>