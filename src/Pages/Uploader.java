package Pages;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Managers.AccountManager;
import Managers.HotelManager;
import Managers.RoomManager;
import Models.Hotel;
import Models.Room;

/**
 * Servlet implementation class Uploader
 */
@WebServlet("/Uploader")
public class Uploader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Uploader() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ServletFileUpload.isMultipartContent(request))
			return;
		
		FileItemFactory itemFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(itemFactory);
		try {
			List<FileItem> items = upload.parseRequest(request);
			File uploadDir = new File(this.getServletContext().getRealPath(File.separator) + "\\images");
			if (!uploadDir.exists())
				uploadDir.mkdir();
			String file = uploadDir.getAbsolutePath() + File.separator;
			Integer id;
			if (request.getParameter("hotel") != null) {
				id = (Integer) request.getSession().getAttribute("hotelId");
				file += "hotel" + id + ".jpg";
			} else {
				id = (Integer) request.getSession().getAttribute("roomId");
				file += "room" + id + ".jpg";
			}
			File fl = new File(file);
			
			if(items.get(0).getName().length() != 0)items.get(0).write(fl);
			if (request.getParameter("hotel") != null) {
				HotelManager hm = HotelManager.getInstance();
				Hotel h = hm.getHotel(id);
				hm.updateHotel(id, h.getName(), h.getRating(), items.get(0).getName().length() == 0 ? "images/defhotel.jpg" : ("images/hotel" + id + ".jpg"), h.getStatus(),
						h.getNumber(), Integer.parseInt(AccountManager.getInstance()
								.getAccount((String) request.getSession().getAttribute("user")).getId()));
				request.getSession().removeAttribute("id");
				request.getSession().removeAttribute("hotel");
				request.removeAttribute("hotel");
				request.getRequestDispatcher("addRooms.jsp").forward(request, response);
			} else {
				RoomManager rmM = RoomManager.getInstance();		
				Room rm = rmM.getRoom(id);
				Integer rmId = rm.getRoomId();
				Integer htId = rm.getHottelId();
				boolean wifi = rm.isWifi();
				boolean tv = rm.isTv();
				boolean hotWater = rm.isHotWater();
				boolean airCo = rm.isAirConditioning();
				Integer numBeds = rm.getNumberOfBeds();
				Integer rPrice =  rm.getPricePerDay();
				
				Date sDate = rm.getStartDate();
				Date eDate = rm.getEndDate();
				
				rmM.updateRoom(rmId, sDate, eDate, rPrice, items.get(0).getName().length() == 0 ? "images/defroom.jpg" : ("images/room" + id + ".jpg"), htId, numBeds, wifi, tv,
						hotWater, airCo);
				request.getRequestDispatcher("RoomAddChoose.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.getRequestDispatcher("home.jsp").forward(request, response);
			e.printStackTrace();
		}
		
	}

}
