<link rel="stylesheet" href="css/RoomsCss.css">
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
   		From <input type="date" placeholder="yyyy/mm/dd">
   		To <input type="date" placeholder="yyyy/mm/dd"> <br>
   		<button type="submit"> Book </button>
   </td>
</tr>