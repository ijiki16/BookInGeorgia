package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import Managers.AccountManager;
import Managers.HotelManager;
import Managers.RoomManager;
import Models.Reservation;
import Models.Room;
import Pages.Book;
import Pages.addRoom;

public class BookTests extends Mockito {

	@Test
	public void correctInput() throws Exception {
		AccountManager acM = AccountManager.getInstance();
		acM.createAccount("iu", "jiki", "ijiki@gmail.com", "ijiki", "1234iuri", "2019-03-03");
		Integer acID = Integer.parseInt(acM.getAccount("ijiki@gmail.com").getId());
		HotelManager htM = HotelManager.getInstance();
		Integer htlID1 = htM.addHotel("", 5, "", "", "6", acID);
		RoomManager rmM = RoomManager.getInstance();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date date1 = dateFormat.parse("1999-12-10");
		Date date2 = dateFormat.parse("2007-07-23");
		Date date3 = dateFormat.parse("2009-01-07");
//		Date date4 = dateFormat.parse("2011-10-01");	
//		Date date5 = dateFormat.parse("2011-10-10");
//		Date date6 = dateFormat.parse("2015-03-14");
		Date date7 = dateFormat.parse("2019-06-10");

		Integer rmID = rmM.addRoom(date1, date7, 150, "nelazviadi.png", htlID1, 2, false, false, false, false);

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		when(session.getAttribute("user")).thenReturn("ijiki@gmail.com");
		when(request.getSession()).thenReturn(session);
//		
		when(request.getParameter("roomid")).thenReturn("" + rmID);

		when(request.getParameter("syear")).thenReturn("2007");
		when(request.getParameter("smonth")).thenReturn("07");
		when(request.getParameter("sday")).thenReturn("24");
		when(request.getParameter("eyear")).thenReturn("2009");
		when(request.getParameter("emonth")).thenReturn("01");
		when(request.getParameter("eday")).thenReturn("08");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		Book server = new Book();
		server.doPost(request, response);

		assertTrue(stringWriter.toString().equals("success"));

		List<Reservation> res = acM.getReservations(acID);
		assertEquals(1, res.size());
		Reservation res1 = res.get(0);

		assertEquals(rmID, res1.getRoomId());
		assertEquals(acID, res1.getAccountId());

		assertEquals(date2.getYear(), res1.getFrom().getYear());
		assertEquals(date2.getMonth(), res1.getFrom().getMonth());
		assertEquals(date2.getDay(), res1.getFrom().getDay());

		assertEquals(date3.getYear(), res1.getTo().getYear());
		assertEquals(date3.getMonth(), res1.getTo().getMonth());
		assertEquals(date3.getDay(), res1.getTo().getDay());
		// assertTrue(date3.equals(res1.getTo()));

		assertTrue(rmM.unbookRoom(res1.getId()));
		htM.deleteHotel(htlID1);
		acM.deleteAccount("ijiki@gmail.com", "1234iuri");

	}

	@Test
	public void incorrectInput1() throws Exception {
		AccountManager acM = AccountManager.getInstance();
		acM.createAccount("iu", "jiki", "ijiki@gmail.com", "ijiki", "1234iuri", "2019-03-03");
		Integer acID = Integer.parseInt(acM.getAccount("ijiki@gmail.com").getId());




		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		when(session.getAttribute("user")).thenReturn("ijiki@gmail.com");
		when(request.getSession()).thenReturn(session);
//		
		when(request.getParameter("roomid")).thenReturn("-1");

		when(request.getParameter("syear")).thenReturn("2007");
		when(request.getParameter("smonth")).thenReturn("07");
		when(request.getParameter("sday")).thenReturn("24");
		when(request.getParameter("eyear")).thenReturn("2009");
		when(request.getParameter("emonth")).thenReturn("01");
		when(request.getParameter("eday")).thenReturn("08");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		Book server = new Book();
		server.doPost(request, response);

		assertTrue(stringWriter.toString().equals("fail"));

	}

}
