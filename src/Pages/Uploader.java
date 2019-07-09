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
	private int hotelid = 1;
	private int roomid = 1;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Uploader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) return;
		
		FileItemFactory itemFactory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(itemFactory);
	    try {
	    	List<FileItem> items = upload.parseRequest(request);
	    	System.out.println(items.size());
	    	
	    		String fileType = items.get(0).getContentType();
	    		if(!fileType.equals("image/png")) {
	    			return;
	    		}
	    		File uploadDir = new File("C:\\Users\\alexp\\Desktop\\BookInGeorgia\\WebContent\\images");
	    		if(!uploadDir.exists()) uploadDir.mkdir();
	    		//File fl = File.createTempFile("img", ".png", uploadDir);
	    		File fl = new File(uploadDir.getAbsolutePath()+File.separator + request.getParameter("hotel") == null ? "hotel" + hotelid : "room" + roomid + ".png");
	    		if(request.getParameter("hotel") == null) roomid++; else hotelid++;
	    		items.get(0).write(fl);
	    		System.out.println("aitvirtaaa");
	    		if(request.getAttribute("hotel_id") != null) {
	    			int id = (Integer)request.getAttribute("hotel_id");
	    			HotelManager hm = HotelManager.getInstance();
	    			Hotel h = hm.getHotel(id);
	    			hm.updateHotel(id, h.getName(), h.getRating(), "images/hotel" + (hotelid - 1) , h.getStatus(), h.getNumber(), 
	    					Integer.parseInt(AccountManager.getInstance().getAccount((String)request.getSession().getAttribute("user")).getId()));
	    		} else {
	    			
	    		}
	    	
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
	    request.getRequestDispatcher("addRooms.jsp").forward(request, response);
	}

}
