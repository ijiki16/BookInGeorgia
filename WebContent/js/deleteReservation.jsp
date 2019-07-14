<%@page import="Managers.RoomManager"%>
<%Integer hotel_id = Integer.parseInt((String)request.getParameter("room_id"));
boolean res = RoomManager.getInstance().deleteRoom(hotel_id);%>
<%=res %>