<tr>
   <td><img src="<%=request.getAttribute("img") %>"></td>
   <td><%=request.getAttribute("bed") %></td>
   <td class="facil">
   		<div> <i class="fa fa-<%= (boolean)request.getAttribute("wifi") == true ? "check-circle" : "ban"%>"></i><h6>Wi-Fi</h6></div>
		<div> <i class="fa fa-<%= (boolean)request.getAttribute("tv") == true ? "check-circle" : "ban"%>" ></i><h6>TV</h6></div>
		<div> <i class="fa fa-<%= (boolean)request.getAttribute("hot-water") == true ? "check-circle" : "ban"%>"></i><h6>Hot water</h6></div>
		<div> <i class="fa fa-<%= (boolean)request.getAttribute("air-cond") == true ? "check-circle" : "ban"%>"></i><h6>Air condition</h6></div>
   </td>
   <td>
   		reserveeed
   </td>
   <td>
   		<%=request.getAttribute("sd") %>  <%=request.getAttribute("ed") %>
   </td>
   <td>
   		From <input type="text" placeholder="yyyy/mm/dd"> <br>
   		To <input type="text" placeholder="yyyy/mm/dd"> <br>
   		<button type="submit"> Book </button>
   </td>
</tr>