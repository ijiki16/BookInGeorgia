<%@page import="java.util.*"%>
<%@page import="Managers.AccountManager"%>
<%@page import="Managers.HotelManager"%>
<%@page import="Models.Account"%>
<%  HotelManager.getInstance().addComment(Integer.parseInt(request.getParameter("id")), (String)request.getSession().getAttribute("user"), (String)request.getParameter("text"));
%>