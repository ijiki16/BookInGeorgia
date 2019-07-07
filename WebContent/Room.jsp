<tr>
   <td><%=request.getAttribute("bed") %></td>
   <td class="facil"><div> <i class="fa fa-check-circle"></i><h6><%=request.getAttribute("wifi") %></h6></div>
		<div> <i class="fa fa-ban" ></i><h6><%=request.getAttribute("tv") %></h6></div>
		<div> <i class="fa fa-check-circle"></i><h6><%=request.getAttribute("hot-water") %></h6></div>
		<div> <i class="fa fa-ban"></i><h6><%=request.getAttribute("air-cond") %></h6></div>
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