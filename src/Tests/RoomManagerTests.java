package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;

import Managers.AccountManager;
import Managers.HotelManager;
import Managers.RoomManager;
import Models.Account;
import Models.Hotel;
import Models.Room;

public class RoomManagerTests {
	// user
	private Account user1;
	private AccountManager acM;
	// hotel
	private Hotel hotel1;
	private Hotel hotel2;
	private HotelManager htM;
	//
	private Date date1;
	private Date date2;
	private Date date3;
	private Date date4;
	private Date date5;
	private Date date6;
	private Date date7;
	private Date date8;
	//
	private Room room1;
	private Room room2;
	private Room room3;
	private Room room4;
	private Room room5;
	private Room room6;
	private RoomManager roomM;

	@Before
	public void Create() {
		//Account
		acM = AccountManager.getInstance();
		acM.createAccount("iuri", "jikidze", "ijiki16@freeuni.edu.ge", "ijiki16", "1234iuri", "1998-12-30");
		user1 = acM.getAccount("ijiki16@freeuni.edu.ge");
		//Hotel
		htM = HotelManager.getInstance();
		int user1Id = Integer.parseInt(user1.getId());
		int htId1 = htM.addHotel("Wyaltubo", 3, "", "Holet Wyaltubo 24/7", "599909990", user1Id);
		int htId2 = htM.addHotel("Tbilisi", 4, "", "Holet Tbilisi 24/7", "597777777", user1Id);

		hotel1 = htM.getHotel(htId1);
		hotel2 = htM.getHotel(htId2);
		htM.addLocation(hotel1.getId(), "Wyaltubo", "safichxia 6");
		htM.addLocation(hotel2.getId(), "Tbilisi", "agmasheneblis 15");
		//Date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		date1 = new Date();
		try {
			date2 = dateFormat.parse("1999-12-10");
			date3 = dateFormat.parse("2015-03-14");
			date4 = dateFormat.parse("2007-07-24");	
			date5 = dateFormat.parse("2009-01-08");	
			date6 = dateFormat.parse("2011-10-01");	
			date7 = dateFormat.parse("2011-10-10");	
			date8 = dateFormat.parse("2019-06-10");	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Room
		roomM = RoomManager.getInstance();
		int rm1Id = roomM.addRoom(date4, date3, 150, "nelazviadi.png", htId2, 2, false, false, false, false);
		room1 = roomM.getRoom(rm1Id);
	}

	@Test
	public void test1() {
		List<Room> ht1Rms = roomM.getRooms(hotel1.getId());
		List<Room> ht2Rms = roomM.getRooms(hotel2.getId());
		assertEquals(0, ht1Rms.size());
		assertEquals(1, ht2Rms.size());
		//add
		assertEquals(-1, roomM.addRoom(date3, date4, 150, "nelazviadi.png", hotel1.getId(), 2, false, false, false, false));
		assertEquals(-1, roomM.addRoom(date4, date3, -10, "nelazviadi.png", hotel1.getId(), 2, false, false, false, false));
		assertEquals(-1, roomM.addRoom(date4, date3, 150, "nelazviadi.png", -1, 2, false, false, false, false));
		assertEquals(-1, roomM.addRoom(date4, date3, 150, "nelazviadi.png", 0, 2, false, false, false, false));
		//get
		assertNull(roomM.getRoom(222222222)); //big int
		//upadate
		assertEquals(-1, roomM.addRoom(date4, date3, 150, "nelazviadi.png", hotel1.getId(), -1, false, false, false, false));
		assertFalse(roomM.updateRoom(-1, date4, date3, 150, "nelazviadi.png", hotel1.getId(), 2, false, false, false, false));
		assertFalse(roomM.updateRoom(room1.getRoomId(), date3, date4, 150, "nelazviadi.png", hotel1.getId(), 2, false, false, false, false));
		assertFalse(roomM.updateRoom(room1.getRoomId(), date4, date3, -10, "nelazviadi.png", hotel1.getId(), 2, false, false, false, false));
		assertFalse(roomM.updateRoom(room1.getRoomId(), date4, date3, 150, "nelazviadi.png", -1, 2, false, false, false, false));
		assertFalse(roomM.updateRoom(room1.getRoomId(), date4, date3, 150, "nelazviadi.png", 0, 2, false, false, false, false));
		assertFalse(roomM.updateRoom(room1.getRoomId(), date4, date3, 150, "nelazviadi.png", hotel1.getId(), -1, false, false, false, false));
		assertTrue(roomM.updateRoom(room1.getRoomId(), date4, date3, 150, "nelazviadi.png", hotel1.getId(), 10, true, true, false, false));
		//book1
		assertFalse(roomM.bookRoom(-1, Integer.parseInt(user1.getId()), date6, date7));
		assertFalse(roomM.bookRoom(room1.getRoomId(), -1, date6, date7));
		assertFalse(roomM.bookRoom(room1.getRoomId(), Integer.parseInt(user1.getId()), date7, date6));

		assertTrue(roomM.bookRoom(room1.getRoomId(), Integer.parseInt(user1.getId()), date6, date7));
		assertTrue(roomM.bookRoom(room1.getRoomId(), Integer.parseInt(user1.getId()), date4, date5));
		assertFalse(roomM.bookRoom(room1.getRoomId(), Integer.parseInt(user1.getId()), date5, date6));
		assertFalse(roomM.bookRoom(room1.getRoomId(), Integer.parseInt(user1.getId()), date5, date6));
		//unbook1
		assertFalse(roomM.unbookRoom(-1, Integer.parseInt(user1.getId()), date6, date7));
		assertFalse(roomM.unbookRoom(room1.getRoomId(), -1, date6, date7));
		assertFalse(roomM.unbookRoom(room1.getRoomId(), Integer.parseInt(user1.getId()), date7, date6));
		
		assertTrue(roomM.unbookRoom(room1.getRoomId(), Integer.parseInt(user1.getId()), date6, date7));
		assertTrue(roomM.unbookRoom(room1.getRoomId(), Integer.parseInt(user1.getId()), date4, date5));
		//book2
		assertFalse(roomM.bookRoom(room1.getRoomId(), Integer.parseInt(user1.getId()), date5, date8));
		assertFalse(roomM.bookRoom(room1.getRoomId(), Integer.parseInt(user1.getId()), date2, date5));
		assertTrue(roomM.bookRoom(room1.getRoomId(), Integer.parseInt(user1.getId()), date5, date7));
		assertFalse(roomM.bookRoom(room1.getRoomId(), Integer.parseInt(user1.getId()), date6, date4));
		//unbook2
		assertTrue(roomM.unbookRoom(room1.getRoomId(), Integer.parseInt(user1.getId()), date5, date7));
		//delete
		assertFalse(roomM.deleteRoom(-1));
		assertTrue(roomM.deleteRoom(room1.getRoomId()));
		htM.deleteHotel(hotel1.getId());
		htM.deleteHotel(hotel2.getId());
	}
}
