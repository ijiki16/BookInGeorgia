package Tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import Managers.AccountManager;
import Managers.HotelManager;
import Managers.RoomManager;
import Models.Account;
import Models.Room;

class EditRoomTests extends Mockito {

	@Test
	@Order (1)
	void testNoUpdate() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response  = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		assertTrue(AccountManager.getInstance().createAccount("devi", "khos", "dkhos17", "dkhos","0406", "1999-04-06"));
		Account acc = AccountManager.getInstance().getAccount("dkhos17");
		Integer account_id = Integer.parseInt(acc.getId()); 
		Integer hotel_id = HotelManager.getInstance().addHotel("Radison", 5, "iveria", "reworked", "+995 ...", account_id);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = dateFormat.parse("1998-12-30");
		java.util.Date date2 = dateFormat.parse("1998-12-31");
		
		RoomManager.getInstance().addRoom(date1, date2, 50, "none", hotel_id, 2, false, false, false, false);
		Room r = RoomManager.getInstance().getRooms(hotel_id).get(0);
		
		when(request.getParameter("hotel_id")).thenReturn(hotel_id.toString());
		when(request.getParameter("room_id")).thenReturn(r.getRoomId().toString());
		when(request.getParameter("wifi")).thenReturn("false");
		when(request.getParameter("tv")).thenReturn("false");
		when(request.getParameter("hotWater")).thenReturn("false");
		when(request.getParameter("airCo")).thenReturn("false");
		when(request.getParameter("numBeds")).thenReturn("2");
		when(request.getParameter("rPrice")).thenReturn("50");
		when(request.getParameter("sDate")).thenReturn("1998-12-30");
		when(request.getParameter("eDate")).thenReturn("1998-12-31");
		
		verify(request).removeAttribute("room_id");
		
		Room ur = RoomManager.getInstance().getRooms(hotel_id).get(0);
		assertFalse(ur.isWifi());
		assertFalse(ur.isTv());
		assertFalse(ur.isHotWater());
		assertFalse(ur.isAirConditioning());
		assertTrue(HotelManager.getInstance().deleteHotel(hotel_id));
		assertTrue(AccountManager.getInstance().deleteAccount("dkhos17", "0406"));
	}
	
	@Test
	@Order (2)
	void testUpdate() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response  = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		assertTrue(AccountManager.getInstance().createAccount("devi", "khos", "dkhos17", "dkhos","0406", "1999-04-06"));
		Account acc = AccountManager.getInstance().getAccount("dkhos17");
		Integer account_id = Integer.parseInt(acc.getId()); 
		Integer hotel_id = HotelManager.getInstance().addHotel("Radison", 5, "iveria", "reworked", "+995 ...", account_id);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = dateFormat.parse("1998-12-30");
		java.util.Date date2 = dateFormat.parse("1998-12-31");
		
		RoomManager.getInstance().addRoom(date1, date2, 50, "none", hotel_id, 2, true, true, true, true);
		Room r = RoomManager.getInstance().getRooms(hotel_id).get(0);
		
		when(request.getParameter("hotel_id")).thenReturn(hotel_id.toString());
		when(request.getParameter("room_id")).thenReturn(r.getRoomId().toString());
		when(request.getParameter("wifi")).thenReturn("false");
		when(request.getParameter("tv")).thenReturn("false");
		when(request.getParameter("hotWater")).thenReturn("false");
		when(request.getParameter("airCo")).thenReturn("false");
		when(request.getParameter("numBeds")).thenReturn("2");
		when(request.getParameter("rPrice")).thenReturn("50");
		when(request.getParameter("sDate")).thenReturn("1998-12-30");
		when(request.getParameter("eDate")).thenReturn("1998-12-31");
		
		verify(request).removeAttribute("room_id");
		
		Room ur = RoomManager.getInstance().getRooms(hotel_id).get(0);
		assertFalse(ur.isWifi());
		assertFalse(ur.isTv());
		assertFalse(ur.isHotWater());
		assertFalse(ur.isAirConditioning());
		assertTrue(HotelManager.getInstance().deleteHotel(hotel_id));
		assertTrue(AccountManager.getInstance().deleteAccount("dkhos17", "0406"));
	}

}
