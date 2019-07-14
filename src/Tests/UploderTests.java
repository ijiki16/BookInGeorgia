package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import Managers.AccountManager;
import Managers.HotelManager;
import Pages.Session;
import Pages.Uploader;

public class UploderTests extends Mockito {
	@Test
    public void correctInput() throws Exception {
		AccountManager acM = AccountManager.getInstance();
		acM.createAccount("iu", "jiki", "ijiki@gmail.com", "ijiki", "1234iuri", "2019-03-03");
		Integer acID = Integer.parseInt(acM.getAccount("ijiki@gmail.com").getId());
		HotelManager htM = HotelManager.getInstance();
		Integer htlID1 = htM.addHotel("", 5, "", "", "6", acID);
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response  = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
        when(session.getAttribute("hotel")).thenReturn("true");
        when(request.getParameter("hotelID")).thenReturn(htlID1+"");
        when(request.getContentType()).thenReturn("multipart/form-data; boundary=someBoundary");
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        Uploader server = new Uploader();
        server.doPost(request, response);
        
        
        //assertEquals(expected, actual);
//        assertEquals(session.getAttribute("user"), "dkhos17");
//        assertTrue(stringWriter.toString().equals("dkhos17"));
        
	}

	@Test
    public void correctInput2() throws Exception {
		
	}
	
	@Test
    public void incorrectInput() throws Exception {
		
	}

}
