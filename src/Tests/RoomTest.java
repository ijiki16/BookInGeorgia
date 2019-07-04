package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Models.Room;



public class RoomTest {
	
	private Room room1;
	private Room room2;
	private Room room3;
	private Room room4;
	private Room room5;
	private Room room6;
	
	
	
	@Before
	public void Create() {
		room1 = new Room();
		
	}
	
	@Test
	public void testGetter1() {
		assertEquals(-1, room1.getRoomId());
		assertEquals(-1, room1.getHottelId());
		assertEquals(0, room1.getNumberOfBeds());
		//assertEquals(null, room1.getHottelId());
	}
}
