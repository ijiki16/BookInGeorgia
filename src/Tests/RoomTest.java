package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import Models.Room;

public class RoomTest {
	// date
	private Date date1;
	private Date date2;
	private Date date3;
	private Date date4;
	// rooms
	private Room room1;
	private Room room2;
	private Room room3;
	private Room room4;
	private Room room5;
	private Room room6;
	private Room room7;

	@Before
	public void Create() {
		date1 = new Date();
		date2 = new Date(1998, 12, 30);
		date3 = new Date(2008, 8, 8);
		date4 = new Date(2018, 12, 8);
		// without ID
		room1 = new Room();
		room2 = new Room(1, date1, date1, 1, 2, false, false, false, false);
		room3 = new Room(1, date1, date1, 1, 2, false, false, false, false);
		room4 = new Room(2, date1, date1, 1, 6, true, false, false, false);
		room5 = new Room(3, date1, date2, 1, 3, true, false, true, false);
		room6 = new Room(4, date2, date3, 2, 5, true, true, false, true);
		room7 = new Room(5, date1, date3, 1, 4, true, true, true, true);

	}

	@Test
	public void testGetter() {
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
		assertEquals(1, room2.getRoomId());
		assertEquals(1, room2.getHottelId());
		assertEquals(2, room2.getNumberOfBeds());
		assertEquals(date1, room2.getStartDate());
		assertEquals(date1, room2.getEndDate());
		assertFalse(room2.isWifi());
		assertFalse(room2.isTv());
		assertFalse(room2.isHotWater());
		assertFalse(room2.isAirConditioning());
		// room3
		assertEquals(1, room3.getRoomId());
		assertEquals(1, room3.getHottelId());
		assertEquals(2, room3.getNumberOfBeds());
		assertEquals(date1, room3.getStartDate());
		assertEquals(date1, room3.getEndDate());
		assertFalse(room3.isWifi());
		assertFalse(room3.isTv());
		assertFalse(room3.isHotWater());
		assertFalse(room3.isAirConditioning());
		// room4
		assertEquals(2, room4.getRoomId());
		assertEquals(1, room4.getHottelId());
		assertEquals(6, room4.getNumberOfBeds());
		assertEquals(date1, room4.getStartDate());
		assertEquals(date1, room4.getEndDate());
		assertTrue(room4.isWifi());
		assertFalse(room4.isTv());
		assertFalse(room4.isHotWater());
		assertFalse(room4.isAirConditioning());
		// room5
		assertEquals(3, room5.getRoomId());
		assertEquals(1, room5.getHottelId());
		assertEquals(3, room5.getNumberOfBeds());
		assertEquals(date1, room5.getStartDate());
		assertEquals(date2, room5.getEndDate());
		assertTrue(room5.isWifi());
		assertFalse(room5.isTv());
		assertTrue(room5.isHotWater());
		assertFalse(room5.isAirConditioning());
		// room5
		assertEquals(4, room6.getRoomId());
		assertEquals(2, room6.getHottelId());
		assertEquals(5, room6.getNumberOfBeds());
		assertEquals(date2, room6.getStartDate());
		assertEquals(date3, room6.getEndDate());
		assertTrue(room6.isWifi());
		assertTrue(room6.isTv());
		assertFalse(room6.isHotWater());
		assertTrue(room6.isAirConditioning());
		// room7
		assertEquals(5, room7.getRoomId());
		assertEquals(1, room7.getHottelId());
		assertEquals(4, room7.getNumberOfBeds());
		assertEquals(date1, room7.getStartDate());
		assertEquals(date3, room7.getEndDate());
		assertTrue(room7.isWifi());
		assertTrue(room7.isTv());
		assertTrue(room7.isHotWater());
		assertTrue(room7.isAirConditioning());
	}

	@Test
	public void testEquals() {
		assertTrue(room1.equals(room1));
		assertTrue(room2.equals(room3));
		assertTrue(room3.equals(room2));
		// room1
		assertFalse(room1.equals(room2));
		assertFalse(room1.equals(room3));
		assertFalse(room1.equals(room4));
		assertFalse(room1.equals(room5));
		assertFalse(room1.equals(room6));
		assertFalse(room1.equals(room7));
		// room2
		assertFalse(room2.equals(room4));
		assertFalse(room2.equals(room5));
		assertFalse(room2.equals(room6));
		assertFalse(room2.equals(room7));
		// room3
		assertFalse(room3.equals(room4));
		assertFalse(room3.equals(room5));
		assertFalse(room3.equals(room6));
		assertFalse(room3.equals(room7));
		// room4
		assertFalse(room4.equals(room5));
		assertFalse(room4.equals(room6));
		assertFalse(room4.equals(room7));
		// room5
		assertFalse(room5.equals(room6));
		assertFalse(room5.equals(room7));
		// room6
		assertFalse(room6.equals(room7));
		assertFalse(room7.equals(room6));
		//
		assertFalse(room6.equals(new Object()));
		assertFalse(room2.equals(new Room(1, date1, date1, 2, 2, false, false, false, false)));
		assertFalse(room2.equals(new Room(1, date2, date1, 1, 2, false, false, false, false)));
		assertFalse(room2.equals(new Room(1, date1, date2, 1, 2, false, false, false, false)));
		assertFalse(room2.equals(new Room(1, date1, date1, 1, 3, false, false, false, false)));
		assertFalse(room2.equals(new Room(1, date1, date1, 1, 2, true, false, false, false)));
		assertFalse(room2.equals(new Room(1, date1, date1, 1, 2, false, true, false, false)));
		assertFalse(room2.equals(new Room(1, date1, date1, 1, 2, false, false, true, false)));
		assertFalse(room2.equals(new Room(1, date1, date1, 1, 2, false, false, false, true)));
	}
	
	@Test
	public void setValues() {
		room7.setRoomId(1);
		assertEquals(1, room7.getRoomId());
		room7.setStartDate(date1);
		assertEquals(date1, room7.getStartDate());
		room7.setEndDate(date4);
		assertEquals(date4, room7.getEndDate());
		room7.setEndDate(null);
		assertEquals(null, room7.getEndDate());
		room7.setEndDate(date1);
		assertEquals(date1, room7.getEndDate());
		room7.setHottelId(1);
		assertEquals(1, room7.getHottelId());
		room7.setNumberOfBeds(2);
		assertEquals(2, room7.getNumberOfBeds());
		room7.setWifi(false);
		assertFalse(room7.isWifi());
		room7.setTv(false);
		assertFalse(room7.isTv());
		room7.setHotWater(false);
		assertFalse(room7.isHotWater());
		room7.setAirConditioning(false);
		assertFalse(room7.isAirConditioning());
		
		assertEquals(room2, room7);
		assertTrue(room2.equals(room7));
		assertTrue(room7.equals(room2));
		assertEquals(room2.toString(), room3.toString());
		assertEquals(room3.toString(), room7.toString());
		assertEquals(room2.toString(), room7.toString());
	}
}
