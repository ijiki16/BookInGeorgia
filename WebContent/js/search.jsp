<%@page import="Managers.AccountManager"%>
<%@page import="Managers.HotelManager"%>
<%@page import="Models.Account"%>
<%@page import="java.util.*"%>
<% HotelManager hm = HotelManager.getInstance();
   List<Integer> l = hm.getSearchedHotels(request.getParameter("option").equals("Select") ? null : request.getParameter("option"), request.getParameter("hotel_name"));
   request.getSession().setAttribute("searched", l);
%>