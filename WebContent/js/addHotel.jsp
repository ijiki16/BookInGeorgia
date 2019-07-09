<%@page import="Managers.AccountManager"%>
<%@page import="Managers.HotelManager"%>
<%@page import="Models.Account"%>
<%  Integer id = Integer.parseInt(AccountManager.getInstance().getAccount((String)request.getSession().getAttribute("user")).getId());
	HotelManager hm = HotelManager.getInstance();
	Integer hotel_id = hm.addHotel(request.getParameter("name"), 
			Integer.parseInt(request.getParameter("stars")), 
			request.getParameter("image"), request.getParameter("status"), request.getParameter("number"), id);
	boolean wifi = request.getParameter("wifi").equals("true") ? true : false;
	boolean parking = request.getParameter("parking").equals("true") ? true : false;
	boolean beach = request.getParameter("beach").equals("true") ? true : false;
	boolean forest = request.getParameter("forest").equals("true") ? true : false;
	hm.addLocation(hotel_id, request.getParameter("city"), request.getParameter("street"));
	hm.addFacilities(hotel_id, request.getParameter("facility"), wifi, parking, beach, forest);
	request.getSession().setAttribute("id", hotel_id);
%>