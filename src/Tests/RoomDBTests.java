package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import Managers.AccountManager;
import Managers.HotelManager;
import Models.Account;
import Models.Hotel;
import Models.Room;

public class RoomDBTests {
	// user
	private Account user1;
	private AccountManager acM;
	// hotel
	private Hotel hotl1;
	private Hotel hotl2;
	private HotelManager htM;
	//
	private Date date1;
	private Date date2;
	private Date date3;
	private Date date4;
	private Room room1;
	private Room room2;
	private Room room3;
	private Room room4;
	private Room room5;
	private Room room6;

	@Before
	public void Create() {
		acM = AccountManager.getInstance();
		acM.createAccount("iuri", "jikidze", "ijiki16@freeuni.edu.ge", "ijiki16", "1234iuri", "1998-12-30");

		date1 = new Date();
		date2 = new Date();
		date3 = new Date(2008, 8, 8);
		date4 = new Date(2018, 82, 8);
		room1 = new Room();
		room2 = new Room();
	}

	@Test
	public void testGetter1() {
		// room1
		assertEquals(-1, room1.getRoomId());
		assertEquals(-1, room1.getHottelId());
		assertEquals(0, room1.getNumberOfBeds());
		assertEquals(null, room1.getStartDate());
		assertEquals(null, room1.getEndDate());
		assertFalse(room1.isWifi());
		assertFalse(room1.isTv());
		assertFalse(room1.isHotWater());
		assertFalse(room1.isAirConditioning());
		// room2
		assertEquals(-1, room2.getRoomId());
		assertEquals(-1, room2.getHottelId());
		assertEquals(0, room2.getNumberOfBeds());
		assertEquals(null, room2.getStartDate());
		assertEquals(null, room2.getEndDate());
		assertFalse(room2.isWifi());
		assertFalse(room2.isTv());
		assertFalse(room2.isHotWater());
		assertFalse(room2.isAirConditioning());
	}
}
