package Pages;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import Models.Hotel;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
			// File fl = File.createTempFile("img", ".png", uploadDir);
			String file = uploadDir.getAbsolutePath() + File.separator;
			Integer id = (Integer) request.getSession().getAttribute("id");
			if (request.getParameter("hotel") != null) {
				file += "hotel" + id + ".jpg";
			} else {
				file += "room" + id + ".jpg";
			}
			File fl = new File(file);
			System.out.println(fl.getName());
			items.get(0).write(fl);
			if (request.getParameter("hotel") != null) {
				HotelManager hm = HotelManager.getInstance();
				Hotel h = hm.getHotel(id);
				// System.out.println("unda gaeketebina");
				hm.updateHotel(id, h.getName(), h.getRating(), "images/hotel" + id + ".jpg", h.getStatus(),
						h.getNumber(), Integer.parseInt(AccountManager.getInstance()
								.getAccount((String) request.getSession().getAttribute("user")).getId()));
				request.getSession().removeAttribute("id");
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("addRooms.jsp").forward(request, response);
	}

}
