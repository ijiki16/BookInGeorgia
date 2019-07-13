package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mockito;

import Managers.AccountManager;
import Managers.HotelManager;
import Pages.Filter;
import Pages.Login;
import Pages.Register;

public class FilterTests extends Mockito{
	
	private AccountManager am;
	private HotelManager hm;
	private Integer id;
	
	@Before
	public void fillDatabase() {
		
		am = AccountManager.getInstance();
		hm = HotelManager.getInstance();
		
		am.createAccount("nika", "basiashvili", "nbasi16", "nika97", "123456", "1997-12-19");
		
		id = hm.addHotel("myHotel", 3, "myImage", "hotel", "456141285", Integer.parseInt(am.getAccount("nbasi16").getId()));
		
		hm.addFacilities(id, "fac", true, true, false, false);
		
	}
	
	@Test
	public void test1() throws ServletException, IOException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        
        when(request.getAttribute("s1")).thenReturn("false");
        when(request.getAttribute("s2")).thenReturn("false");
        when(request.getAttribute("s3")).thenReturn("true");
        when(request.getAttribute("s4")).thenReturn("false");
        when(request.getAttribute("s5")).thenReturn("false");
        
        when(request.getAttribute("wifi")).thenReturn(true);
        when(request.getAttribute("parking")).thenReturn(true);
        when(request.getAttribute("beach")).thenReturn(false);
        when(request.getAttribute("forest")).thenReturn(false);
        when(request.getSession()).thenReturn(session);
        
        new Filter().doPost(request, response);
        
        verify(request, atLeast(1)).getParameter("filter");
	}
	
	@After
	public void clearDatabase() {
		
		hm.deleteFacilites(id);
		hm.deleteHotel(id);
		am.deleteAccount("nbasi16", "123456");
		
	}
	
}
