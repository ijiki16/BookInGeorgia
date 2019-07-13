<%@page import="Managers.RoomManager"%>
<%RoomManager.getInstance().unbookRoom(Integer.parseInt(request.getParameter("resId")));%>