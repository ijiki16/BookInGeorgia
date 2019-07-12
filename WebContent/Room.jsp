<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<link rel="stylesheet" href="css/RoomsCss.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js" type="text/javascript"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js" type="text/javascript"></script>
<script src="js/room.js" type="text/javascript"></script>
<tr>
   <td><img src="<%=request.getAttribute("img") %>"></td>
   <td><%=request.getAttribute("bed") %> beds</td>
   <td class="facile">
   		<div> <i class="fa fa-<%= (boolean)request.getAttribute("wifi") == true ? "check-circle" : "ban"%>"></i><h6>Wi-Fi</h6></div>
		<div> <i class="fa fa-<%= (boolean)request.getAttribute("tv") == true ? "check-circle" : "ban"%>" ></i><h6>TV</h6></div>
		<div> <i class="fa fa-<%= (boolean)request.getAttribute("hot-water") == true ? "check-circle" : "ban"%>"></i><h6>Hot water</h6></div>
		<div> <i class="fa fa-<%= (boolean)request.getAttribute("air-cond") == true ? "check-circle" : "ban"%>"></i><h6>Air condition</h6></div>
   </td>
   <td>
   		reserveeed
   </td>
   <td>
   		From <%=request.getAttribute("sd") %> to <%=request.getAttribute("ed") %>
   </td>
   <td>
   		From <input type="date" placeholder="yyyy/mm/dd" required="required" id="sd">
   		To <input type="date" placeholder="yyyy/mm/dd" required="required" id="ed">
   		<button type="submit" id="button"></button>
   </td>
</tr>