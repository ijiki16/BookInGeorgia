 <%@page import="Managers.RoomManager"%>
<%@page import="Models.Facilities"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
    <%@page import="DataBases.HotelsDB"%>
    <%@page import="Managers.HotelManager"%>
    <%@page import="Models.Hotel"%>
    <%@page import="Models.Room"%>
    <%@page import="Models.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<link rel="stylesheet" href="css/RoomsCss.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js" type="text/javascript"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js" type="text/javascript"></script>
<script src="js/room.js" type="text/javascript"></script>
</head>
<body>
<tr>
   <td><img id="image" src="<%=request.getAttribute("img") %>"></td>
   <td><%=request.getAttribute("bed") %> beds</td>
   <td class="facile">
   		<div> <i class="fa fa-<%= (boolean)request.getAttribute("wifi") == true ? "check-circle" : "ban"%>"></i><h6>Wi-Fi</h6></div>
		<div> <i class="fa fa-<%= (boolean)request.getAttribute("tv") == true ? "check-circle" : "ban"%>" ></i><h6>TV</h6></div>
		<div> <i class="fa fa-<%= (boolean)request.getAttribute("hot-water") == true ? "check-circle" : "ban"%>"></i><h6>Hot water</h6></div>
		<div> <i class="fa fa-<%= (boolean)request.getAttribute("air-cond") == true ? "check-circle" : "ban"%>"></i><h6>Air condition</h6></div>
   </td>
   <td>
   		<% List<List<Date>> l = RoomManager.getInstance().getRoomReservations((Integer)request.getAttribute("id"));
   			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
   			for(int i = 0; i < l.size(); i++){%>
   				<h6>From: <%= sd.format((Date)l.get(i).get(0)) %> To: <%= sd.format(l.get(i).get(1)) %></h6>
   		<%}
   		  if(l.size() == 0){%>
   		  	<h6>Not Reserved Yet</h6>
   		<%} %>
   </td>
   <td>
   		From: <%=request.getAttribute("sd") %> to: <%= request.getAttribute("ed") %>
   </td>
   <td>
   		<input id="roomid" type="hidden" value="<%=request.getAttribute("id")%>">
   		<h5>Price: <i class="fas fa-dollar-sign"></i> <%=request.getAttribute("price") %></h5>
   		From: <input type="date" placeholder="yyyy/mm/dd" required="required" id="sd<%=request.getAttribute("id")%>">
   		To: <input type="date" placeholder="yyyy/mm/dd" required="required" id="ed<%=request.getAttribute("id")%>">
   		<button class="button" type="submit" id="<%=request.getAttribute("id")%>" value="<%=request.getAttribute("id")%>" <% if(request.getSession().getAttribute("user") == null) out.print("style=\"display: none\""); else out.print(""); %>></button>
   </td>
</tr>
</body>
</html>

