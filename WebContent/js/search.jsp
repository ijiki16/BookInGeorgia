<%@page import="Managers.AccountManager"%>
<%@page import="Managers.HotelManager"%>
<%@page import="Models.Account"%>
<%@page import="java.util.*"%>
<% HotelManager hm = HotelManager.getInstance();
   List<Integer> l = hm.getSearchedHotels(request.getParameter("city").equals("Select") ? null : request.getParameter("city"), request.getParameter("hotel_name").equals("") ? null : request.getParameter("hotel_name"));
   request.getSession().setAttribute("searched", l);
%>