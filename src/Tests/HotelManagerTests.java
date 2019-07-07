package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;

import Managers.AccountManager;
import Managers.HotelManager;
import Models.Facilities;
import Models.Hotel;
import Models.Location;

public class HotelManagerTests {
	
	private HotelManager HM;
	private AccountManager AM;
	private Integer id1, id2;
	
	@Before
	public void setUp() {
		AM = AccountManager.getInstance();
		HM = HotelManager.getInstance();
		AM.createAccount("devi", "khos", "devidevuka@mail.ru", "dkhos", "0406", "1999-06-04");
		id1 = Integer.parseInt(AM.getAccount("devidevuka@mail.ru").getId());
		AM.createAccount("devi", "khose", "dkhos17@freeuni.edu.ge", "dkhos17", "0406", "1999-06-04");
		id2 = Integer.parseInt(AM.getAccount("dkhos17@freeuni.edu.ge").getId());
	}
	
	@Test
	@Order(1)
	public void testHotel() {
		List<Hotel> hotels = HM.getHotels(id1);
		assertEquals(hotels.size(), 0);
		HM.addHotel("Tiflisi", 5, "none", "old tbilisi", "+995 ...", id1);
		hotels = HM.getHotels(id1);
		Hotel h = hotels.get(0);
		
		assertEquals(h.getName(), "Tiflisi");
		assertEquals((int)h.getRating(), 5);
		assertEquals(h.getAccountId(), id1);
		assertEquals(h.getRooms().size(), 0);
		assertEquals(h.getFacilities(), null);
		assertEquals(h.getLocation(), null);
		
		HM.updateHotel(h.getId(), h.getName(), 4, "newimg", h.getStatus(), h.getNumber(), id1);
		h = HM.getHotel(h.getId());
		
		assertEquals(h.getName(), "Tiflisi");
		assertEquals(h.getImage(), "newimg");
		assertEquals((int)h.getRating(), 4);
		assertEquals(h.getAccountId(), id1);
		
		HM.deleteHotel(h.getId());
		assertEquals(HM.getHotel(h.getId()), null);
		
		HM.addHotel("Tiflisi", 5, "none", "old tbilisi", "+995 ...", id2);
		HM.addHotel("Tiflisi blue", 3, "none", "old tbilisi", "+995 ...", id2);
		hotels = HM.getHotels(id2);
		Hotel h1 = hotels.get(0);
		Hotel h2 = hotels.get(1);
		
		assertEquals(h1.getName(), "Tiflisi");
		assertEquals((int)h1.getRating(), 5);
		assertEquals(h1.getAccountId(), id2);
		assertEquals(h1.getRooms().size(), 0);
		assertEquals(h1.getFacilities(), null);
		assertEquals(h1.getLocation(), null);
		assertEquals(h2.getName(), "Tiflisi blue");
		assertEquals((int)h2.getRating(), 3);
		assertEquals(h2.getAccountId(), id2);
		assertEquals(h2.getRooms().size(), 0);
		assertEquals(h2.getFacilities(), null);
		assertEquals(h2.getLocation(), null);
		
		HM.updateHotel(h1.getId(), h2.getName(), h2.getRating(), h2.getStatus(), h.getStatus(), h2.getNumber(), h2.getAccountId());
		HM.updateHotel(h2.getId(), h1.getName(), h1.getRating(), h1.getStatus(), h1.getStatus(), h1.getNumber(), h1.getAccountId());
		h1 = HM.getHotel(h1.getId());
		h2 = HM.getHotel(h2.getId());
		
		assertEquals(h2.getName(), "Tiflisi");
		assertEquals((int)h2.getRating(), 5);
		assertEquals(h2.getAccountId(), id2);
		assertEquals(h2.getRooms().size(), 0);
		assertEquals(h2.getFacilities(), null);
		assertEquals(h2.getLocation(), null);
		assertEquals(h1.getName(), "Tiflisi blue");
		assertEquals((int)h1.getRating(), 3);
		assertEquals(h1.getAccountId(), id2);
		assertEquals(h1.getRooms().size(), 0);
		assertEquals(h1.getFacilities(), null);
		assertEquals(h1.getLocation(), null);

		HM.deleteHotel(h1.getId());
		HM.deleteHotel(h2.getId());
		assertEquals(HM.getHotel(h1.getId()), null);
		assertEquals(HM.getHotel(h2.getId()), null);
	}
	
	@Test
	@Order(2)
	public void testFacility() {
		HM.addHotel("Radison", 5, "iveria", "reworked", "+995 ...", id1);
		List<Hotel> hotels = HM.getHotels(id1);
		Hotel h = hotels.get(0);
		HM.addFacilities(h.getId(), "swimmingpool on top", true, true, false, false);
		h = HM.getHotel(h.getId());
		Facilities facil = h.getFacilities();
		assertEquals(facil.getFacility(), "swimmingpool on top");
		assertTrue(facil.getWiFi());
		assertTrue(facil.getParking());
		assertFalse(facil.getBeachfront());
		assertFalse(facil.getWoodfront());
		
		HM.updateFacilities(h.getId(), "new status", true, false, true, true);
		h = HM.getHotel(h.getId());
		facil = h.getFacilities();
		
		assertEquals(facil.getFacility(), "new status");
		assertTrue(facil.getWiFi());
		assertFalse(facil.getParking());
		assertTrue(facil.getBeachfront());
		assertTrue(facil.getWoodfront());
		
		HM.deleteFacilites(h.getId());
		h = HM.getHotel(h.getId());
		assertEquals(h.getFacilities(), null);
		HM.deleteHotel(h.getId());
	}
	
	@Test
	@Order(3)
	public void testLocation() {
		HM.addHotel("Radison blue", 5, "blue iveria", "reworked", "+995 ...", id1);
		List<Hotel> hotels = HM.getHotels(id1);
		Hotel h = hotels.get(0);
		HM.addLocation(h.getId(), "tbilisi", "rustaveli str.");
		h = HM.getHotel(h.getId());
		Location l = h.getLocation();
		assertEquals(l.getCity(), "tbilisi");
		assertEquals(l.getAddress(), "rustaveli str.");
		
		HM.updateLocation(h.getId(), "batumi", "d.agmashenebeli 76");
		h = HM.getHotel(h.getId());
		l = h.getLocation();
		assertEquals(l.getCity(), "batumi");
		assertEquals(l.getAddress(), "d.agmashenebeli 76");	
		
		HM.deleteLocation(h.getId());
		h = HM.getHotel(h.getId());
		assertEquals(h.getLocation(), null);
		HM.deleteHotel(h.getId());
	}
	
	@Test
	@Order(4)
	public void testRoom() {
		
	}
	
	@Test
	@Order(4)
	public void testSelectMethods() {
		HM.addHotel("Radison", 5, "iveria", "reworked", "+995 ...", id1);
		HM.addHotel("Tiflisi", 4, "none", "old tbilisi", "+995 ...", id2);
		HM.addHotel("Tiflisi TM", 3, "none", "tbilisi", "+995 ...", id2);
		
		List<Hotel> hotels = HM.getHotels(id1);
		HM.addLocation(hotels.get(0).getId(), "batumi", "d.agmashenebeli 76");
		HM.addFacilities(hotels.get(0).getId(), "swimmingpool on top", false, false, false, false);
		hotels = HM.getHotels(id2);
		HM.addLocation(hotels.get(0).getId(), "tbilisi", "sanapiro 77");
		HM.addLocation(hotels.get(1).getId(), "tbilisi", "sanapiro 74");
		HM.addFacilities(hotels.get(0).getId(), "swimmingpool on top", true, true, true, true);
		HM.addFacilities(hotels.get(1).getId(), "swimmingpool on top", true, true, true, true);
		
		List<String> locations = HM.getAllLocations();
		assertEquals(locations.size(), 2);
		assertTrue(locations.contains("tbilisi"));
		assertTrue(locations.contains("batumi"));
		
		boolean ratings[] = {false, false, true, true, true};
		List<Integer> filter = HM.getFilteredHotels(ratings, false, false, false, false);
		assertEquals(filter.size(), 3);
		ratings[2] = false;
		filter = HM.getFilteredHotels(ratings, false, true, false, false);
		assertEquals(filter.size(), 1);
		
		List<Integer> search = HM.getSearchedHotels(null, null);
		assertEquals(search.size(), 3);
		search = HM.getSearchedHotels("tbilisi", null);
		assertEquals(search.size(), 2);
		search = HM.getSearchedHotels("tbilisi", "Tiflisi");
		assertEquals(search.size(), 1);
		search = HM.getSearchedHotels(null, "Tiflisi TM");
		assertEquals(search.size(), 1);

		HM.deleteHotel(HM.getHotels(id1).get(0).getId());
		HM.deleteHotel(HM.getHotels(id2).get(0).getId());
		HM.deleteHotel(HM.getHotels(id2).get(0).getId());
	}
	
}
