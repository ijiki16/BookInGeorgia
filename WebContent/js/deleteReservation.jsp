<%@page import="Managers.RoomManager"%>
<%RoomManager.getInstance().unbookRoom(Integer.parseInt(request.getParameter("reserved_id")));
request.removeAttribute("reserved_id");%>