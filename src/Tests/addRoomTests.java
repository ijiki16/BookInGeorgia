package Tests;

import static org.junit.Assert.assertEquals;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import Managers.AccountManager;
import Managers.HotelManager;
import Managers.RoomManager;
import Models.Room;
import Pages.addRoom;

public class addRoomTests extends Mockito {
	@Test
	public void correctInput() throws Exception {
		AccountManager acM = AccountManager.getInstance();
		acM.createAccount("iu", "jiki", "ijiki@gmail.com", "ijiki", "1234iuri", "2019-03-03");
		Integer acID = Integer.parseInt(acM.getAccount("ijiki@gmail.com").getId());
		HotelManager htM = HotelManager.getInstance();
		Integer htlID1 = htM.addHotel("", 5, "", "", "6", acID);
		RoomManager rmM = RoomManager.getInstance();

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		when(request.getSession()).thenReturn(session);
		
		when(request.getParameter("hotel_id")).thenReturn(""+htlID1);
		
		when(request.getParameter("wifi2")).thenReturn("false");
		when(request.getParameter("tv2")).thenReturn("true");
		when(request.getParameter("hotWater2")).thenReturn("false");
		when(request.getParameter("airCo2")).thenReturn("true");
		
		when(request.getParameter("numBeds2")).thenReturn("4");
		when(request.getParameter("rPrice2")).thenReturn("120");
		
		when(request.getParameter("sDate2")).thenReturn("2018-07-20");
		when(request.getParameter("eDate2")).thenReturn("2018-08-09");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		addRoom server = new addRoom();
		server.doPost(request, response);

		List<Room> rms = rmM.getRooms(htlID1);
		Room crR = rms.get(0);
		
		assertEquals(htlID1, crR.getHottelId());
		assertEquals(false, crR.isWifi());
		assertEquals(true, crR.isTv());
		assertEquals(false, crR.isHotWater());
		assertEquals(true, crR.isAirConditioning());
		
		assertEquals(4, crR.getNumberOfBeds());
		assertEquals(120, crR.getPricePerDay());
		
		//assertEquals(, crR.getStartDate());
		//assertEquals(, crR.getStartDate());
		
		rmM.deleteRoom(crR.getRoomId());
		htM.deleteHotel(htlID1);
		acM.deleteAccount("ijiki@gmail.com", "1234iuri");

	}

	@Test
	public void correctInput2() throws Exception {
		AccountManager acM = AccountManager.getInstance();
		acM.createAccount("iu", "jiki", "ijiki@gmail.com", "ijiki", "1234iuri", "2019-03-03");
		Integer acID = Integer.parseInt(acM.getAccount("ijiki@gmail.com").getId());
		HotelManager htM = HotelManager.getInstance();
		Integer htlID1 = htM.addHotel("", 5, "", "", "6", acID);
		RoomManager rmM = RoomManager.getInstance();

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		when(session.getAttribute("hotelId")).thenReturn(htlID1);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("hotel_id")).thenReturn("null");
		
		when(request.getParameter("wifi2")).thenReturn("false");
		when(request.getParameter("tv2")).thenReturn("true");
		when(request.getParameter("hotWater2")).thenReturn("false");
		when(request.getParameter("airCo2")).thenReturn("true");
		
		when(request.getParameter("numBeds2")).thenReturn("4");
		when(request.getParameter("rPrice2")).thenReturn("120");
		
		when(request.getParameter("sDate2")).thenReturn("2018-07-20");
		when(request.getParameter("eDate2")).thenReturn("2018-08-09");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		addRoom server = new addRoom();
		server.doPost(request, response);

		List<Room> rms = rmM.getRooms(htlID1);
		Room crR = rms.get(0);
		String rmId = (String) request.getSession().getAttribute("roomId");
		
		assertEquals(htlID1, crR.getHottelId());
		assertEquals(false, crR.isWifi());
		assertEquals(true, crR.isTv());
		assertEquals(false, crR.isHotWater());
		assertEquals(true, crR.isAirConditioning());
		
		assertEquals(4, crR.getNumberOfBeds());
		assertEquals(120, crR.getPricePerDay());
		
		
		rmM.deleteRoom(crR.getRoomId());
		htM.deleteHotel(htlID1);
		acM.deleteAccount("ijiki@gmail.com", "1234iuri");
	}

	@Test
	public void incorrectInput1() throws Exception {
		AccountManager acM = AccountManager.getInstance();
		acM.createAccount("iu", "jiki", "ijiki@gmail.com", "ijiki", "1234iuri", "2019-03-03");
		Integer acID = Integer.parseInt(acM.getAccount("ijiki@gmail.com").getId());
		HotelManager htM = HotelManager.getInstance();
		Integer htlID1 = htM.addHotel("", 5, "", "", "6", acID);
		RoomManager rmM = RoomManager.getInstance();

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		when(request.getSession()).thenReturn(session);
		
		when(request.getParameter("hotel_id")).thenReturn(""+htlID1);
		
		when(request.getParameter("wifi2")).thenReturn("false");
		when(request.getParameter("tv2")).thenReturn("true");
		when(request.getParameter("hotWater2")).thenReturn("false");
		when(request.getParameter("airCo2")).thenReturn("true");
		
		when(request.getParameter("numBeds2")).thenReturn("4");
		when(request.getParameter("rPrice2")).thenReturn("120");
		
		when(request.getParameter("sDate2")).thenReturn("2019-07-20");
		when(request.getParameter("eDate2")).thenReturn("2018-08-09");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		addRoom server = new addRoom();
		server.doPost(request, response);

		List<Room> rms = rmM.getRooms(htlID1);
		assertEquals(0, rms.size());
		
		//String rmId = (String) request.getSession().getAttribute("roomId");

		//verify(mock)
		//assertEquals("-1", rmId);

		htM.deleteHotel(htlID1);
		acM.deleteAccount("ijiki@gmail.com", "1234iuri");
	}
	
	@Test
	public void incorrectInput2() throws Exception {
		AccountManager acM = AccountManager.getInstance();
		acM.createAccount("iu", "jiki", "ijiki@gmail.com", "ijiki", "1234iuri", "2019-03-03");
		Integer acID = Integer.parseInt(acM.getAccount("ijiki@gmail.com").getId());
		HotelManager htM = HotelManager.getInstance();
		Integer htlID1 = htM.addHotel("", 5, "", "", "6", acID);
		RoomManager rmM = RoomManager.getInstance();

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		when(request.getSession()).thenReturn(session);
		
		when(request.getParameter("hotel_id")).thenReturn(""+htlID1);
		
		when(request.getParameter("wifi2")).thenReturn("false");
		when(request.getParameter("tv2")).thenReturn("true");
		when(request.getParameter("hotWater2")).thenReturn("false");
		when(request.getParameter("airCo2")).thenReturn("true");
		
		when(request.getParameter("numBeds2")).thenReturn("4");
		when(request.getParameter("rPrice2")).thenReturn("120");
		
		when(request.getParameter("sDate2")).thenReturn("bla bla");
		when(request.getParameter("eDate2")).thenReturn("2018-08-09");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		addRoom server = new addRoom();
		server.doPost(request, response);

		List<Room> rms = rmM.getRooms(htlID1);
		assertEquals(0, rms.size());
		
		
		htM.deleteHotel(htlID1);
		acM.deleteAccount("ijiki@gmail.com", "1234iuri");
		//String rmId = (String) request.getSession().getAttribute("roomId");

		//verify(mock)
		//assertEquals("-1", rmId);
	}
}
