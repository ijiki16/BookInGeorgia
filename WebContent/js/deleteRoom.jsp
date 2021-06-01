<%@page import="Managers.RoomManager"%>
<%boolean res = RoomManager.getInstance().deleteRoom(Integer.parseInt(request.getParameter("room_id")));
request.removeAttribute("room_id");%>
<%=res %>