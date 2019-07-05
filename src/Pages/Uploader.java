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
		PrintWriter out = response.getWriter();
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			out.println("No File To Upload");
			return;
		}
		
		FileItemFactory itemFactory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(itemFactory);
	    
	    try {
	    	List<FileItem> items = upload.parseRequest(request);
	    	for(FileItem item: items) {
	    		String fileType = item.getContentType();
	    		
	    		if(!fileType.equals("image/png")) {
	    			out.println("only png files!!!");
	    			continue;
	    		}
	    		
	    		File uploadDir = new File("C:\\Users\\iurik\\Downloads");
	    		if(!uploadDir.exists()) uploadDir.mkdir();
	    		
	    		//File fl = File.createTempFile("img", ".png", uploadDir);
	    		File fl = new File(uploadDir.getAbsolutePath()+File.separator+"debilo"+".png");
	    		item.write(fl);
	    		out.println("File has Uploaded");
	    	}
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
	}

}
