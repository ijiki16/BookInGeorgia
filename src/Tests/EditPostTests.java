package Tests;

import Managers.AccountManager;
import Managers.HotelManager;
import Models.Hotel;
import Pages.EditPost;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;

@TestMethodOrder(OrderAnnotation.class)
public class EditPostTests extends Mockito{
	
	private AccountManager am;
	private HotelManager hm;
	private Integer hotelId;
	
	@Before
	public void fillDB() {
		am = AccountManager.getInstance();
		hm = HotelManager.getInstance();
		
		am.createAccount("nika", "basiashvili", "nbasi16", "nika977", "123456", "1997-12-19");
		hotelId = hm.addHotel("myhotel", 4, "myimage", "hotel", "12165464", Integer.parseInt(am.getAccount("nbasi16").getId()));
		hm.addLocation(hotelId, "Rustavi", "Chavchavadze12");
		
	}
	
	@Test
	public void test1() throws IOException, ServletException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(am.getAccount("nbasi16").getEmail());
		when(request.getParameter("hotel_id")).thenReturn(Integer.toString(hotelId));
		when(request.getParameter("name")).thenReturn("");
		when(request.getParameter("rating")).thenReturn("");
		when(request.getParameter("status")).thenReturn("");
		when(request.getParameter("number")).thenReturn("");
		when(request.getParameter("city")).thenReturn("");
		when(request.getParameter("address")).thenReturn("");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
		
        new EditPost().doGet(request, response);
        
        Hotel hotel = hm.getHotel(hotelId);
        assertTrue(hotel.getName().equals("myhotel"));
        assertTrue(hotel.getRating().equals(4));
        assertTrue(hotel.getStatus().equals("hotel"));
        assertTrue(hotel.getNumber().equals("12165464"));
        assertTrue(hotel.getLocation().getCity().equals("Rustavi"));
        assertTrue(hotel.getLocation().getAddress().equals("Chavchavadze12"));
        writer.flush();
	}
	
	@Test
	public void test2() throws IOException, ServletException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(am.getAccount("nbasi16").getEmail());
		when(request.getParameter("hotel_id")).thenReturn(Integer.toString(hotelId));
		when(request.getParameter("name")).thenReturn("axalihoteli");
		when(request.getParameter("rating")).thenReturn("");
		when(request.getParameter("status")).thenReturn("");
		when(request.getParameter("number")).thenReturn("");
		when(request.getParameter("city")).thenReturn("");
		when(request.getParameter("address")).thenReturn("");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
		
        new EditPost().doPost(request, response);
        
        Hotel hotel = hm.getHotel(hotelId);
        assertTrue(hotel.getName().equals("axalihoteli"));
        assertTrue(hotel.getRating().equals(4));
        assertTrue(hotel.getStatus().equals("hotel"));
        assertTrue(hotel.getNumber().equals("12165464"));
        assertTrue(hotel.getLocation().getCity().equals("Rustavi"));
        assertTrue(hotel.getLocation().getAddress().equals("Chavchavadze12"));
        writer.flush();
	}
	
	@Test
	public void test3() throws IOException, ServletException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(am.getAccount("nbasi16").getEmail());
		when(request.getParameter("hotel_id")).thenReturn(Integer.toString(hotelId));
		when(request.getParameter("name")).thenReturn("");
		when(request.getParameter("rating")).thenReturn("5");
		when(request.getParameter("status")).thenReturn("");
		when(request.getParameter("number")).thenReturn("");
		when(request.getParameter("city")).thenReturn("");
		when(request.getParameter("address")).thenReturn("");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
		
        new EditPost().doPost(request, response);
        
        Hotel hotel = hm.getHotel(hotelId);
        assertTrue(hotel.getName().equals("myhotel"));
        assertTrue(hotel.getRating().equals(5));
        assertTrue(hotel.getStatus().equals("hotel"));
        assertTrue(hotel.getNumber().equals("12165464"));
        assertTrue(hotel.getLocation().getCity().equals("Rustavi"));
        assertTrue(hotel.getLocation().getAddress().equals("Chavchavadze12"));
        writer.flush();
	}
	
	@Test
	public void test4() throws IOException, ServletException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(am.getAccount("nbasi16").getEmail());
		when(request.getParameter("hotel_id")).thenReturn(Integer.toString(hotelId));
		when(request.getParameter("name")).thenReturn("");
		when(request.getParameter("rating")).thenReturn("");
		when(request.getParameter("status")).thenReturn("motel");
		when(request.getParameter("number")).thenReturn("");
		when(request.getParameter("city")).thenReturn("");
		when(request.getParameter("address")).thenReturn("");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
		
        new EditPost().doPost(request, response);
        
        Hotel hotel = hm.getHotel(hotelId);
        assertTrue(hotel.getName().equals("myhotel"));
        assertTrue(hotel.getRating().equals(4));
        assertTrue(hotel.getStatus().equals("motel"));
        assertTrue(hotel.getNumber().equals("12165464"));
        assertTrue(hotel.getLocation().getCity().equals("Rustavi"));
        assertTrue(hotel.getLocation().getAddress().equals("Chavchavadze12"));
        writer.flush();
	}
	
	@Test
	public void test5() throws IOException, ServletException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(am.getAccount("nbasi16").getEmail());
		when(request.getParameter("hotel_id")).thenReturn(Integer.toString(hotelId));
		when(request.getParameter("name")).thenReturn("axalihoteli");
		when(request.getParameter("rating")).thenReturn("");
		when(request.getParameter("status")).thenReturn("");
		when(request.getParameter("number")).thenReturn("427349827424");
		when(request.getParameter("city")).thenReturn("");
		when(request.getParameter("address")).thenReturn("");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
		
        new EditPost().doPost(request, response);
        
        Hotel hotel = hm.getHotel(hotelId);
        assertTrue(hotel.getName().equals("axalihoteli"));
        assertTrue(hotel.getRating().equals(4));
        assertTrue(hotel.getStatus().equals("hotel"));
        assertTrue(hotel.getNumber().equals("427349827424"));
        assertTrue(hotel.getLocation().getCity().equals("Rustavi"));
        assertTrue(hotel.getLocation().getAddress().equals("Chavchavadze12"));
        writer.flush();
	}
	
	@Test
	public void test6() throws IOException, ServletException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(am.getAccount("nbasi16").getEmail());
		when(request.getParameter("hotel_id")).thenReturn(Integer.toString(hotelId));
		when(request.getParameter("name")).thenReturn("axalihoteli");
		when(request.getParameter("rating")).thenReturn("");
		when(request.getParameter("status")).thenReturn("");
		when(request.getParameter("number")).thenReturn("427349827424");
		when(request.getParameter("city")).thenReturn("Tbilisi");
		when(request.getParameter("address")).thenReturn("");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
		
        new EditPost().doPost(request, response);
        
        Hotel hotel = hm.getHotel(hotelId);
        assertTrue(hotel.getName().equals("axalihoteli"));
        assertTrue(hotel.getRating().equals(4));
        assertTrue(hotel.getStatus().equals("hotel"));
        assertTrue(hotel.getNumber().equals("427349827424"));
        assertTrue(hotel.getLocation().getCity().equals("Tbilisi"));
        assertTrue(hotel.getLocation().getAddress().equals("Chavchavadze12"));
        writer.flush();
	}
	
	@Test
	public void test7() throws IOException, ServletException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(am.getAccount("nbasi16").getEmail());
		when(request.getParameter("hotel_id")).thenReturn(Integer.toString(hotelId));
		when(request.getParameter("name")).thenReturn("axalihoteli");
		when(request.getParameter("rating")).thenReturn("");
		when(request.getParameter("status")).thenReturn("");
		when(request.getParameter("number")).thenReturn("427349827424");
		when(request.getParameter("city")).thenReturn("");
		when(request.getParameter("address")).thenReturn("Rustaveli14");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
		
        new EditPost().doPost(request, response);
        
        Hotel hotel = hm.getHotel(hotelId);
        assertTrue(hotel.getName().equals("axalihoteli"));
        assertTrue(hotel.getRating().equals(4));
        assertTrue(hotel.getStatus().equals("hotel"));
        assertTrue(hotel.getNumber().equals("427349827424"));
        assertTrue(hotel.getLocation().getCity().equals("Rustavi"));
        assertTrue(hotel.getLocation().getAddress().equals("Rustaveli14"));
        writer.flush();
	}
	
	@After
	public void clearDB() {
		
		hm.deleteLocation(hotelId);
		hm.deleteHotel(hotelId);
		am.deleteAccount("nbasi16", "123456");
		
	}

}
