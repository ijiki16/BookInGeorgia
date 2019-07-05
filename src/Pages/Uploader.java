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
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			System.out.println("debilo");
			out.println("No File To Upload");
			return;
		}
		
		FileItemFactory itemFactory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(itemFactory);
	    System.out.println("debilo2");
	    try {
	    	List<FileItem> items = upload.parseRequest(request);
	    	System.out.println("debilo3");
	    	for(FileItem item: items) {
	    		System.out.println("debilo5");
	    		String fileType = item.getContentType();
	    		if(!fileType.equals("image/png")) {
	    			System.out.println("debilo4");
	    			out.println("only png files!!!");
	    			continue;
	    		}
	    		System.out.println("debilo7");
	    		//
	    		File uploadDir = new File("C:\\Users\\iurik\\Downloads");
	    		if(!uploadDir.exists()) uploadDir.mkdir();
	    		
	    		
	    		System.out.println("debilo8");
	    		//File fl = File.createTempFile("img", ".png", uploadDir);
	    		File fl = new File(uploadDir.getAbsolutePath()+File.separator+"debilo"+".png");
	    		System.out.println("debilo9");
	    		item.write(fl);
	    		System.out.println("debilo10");
	    		out.println("File has Uploaded");
	    	}
	    	System.out.println("debilo6");
	    }catch (Exception e) {
	    	System.out.println("debilo1000000000000");
	    	e.printStackTrace();
			// TODO: handle exception
		}
//		
		
		//doGet(request, response);
	}

}
