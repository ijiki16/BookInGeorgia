package Tests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;

import Managers.AccountManager;
import Managers.HotelManager;
import Managers.RoomManager;
import Models.Facilities;
import Models.Hotel;
import Models.Location;
import Models.Room;

public class HotelManagerTests {
	
	private HotelManager HM;
	private RoomManager RM;
	private AccountManager AM;
	private Integer id1, id2;
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		AM = AccountManager.getInstance();
		RM = RoomManager.getInstance();
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
		Integer hotel_id = HM.addHotel("Tiflisi", 5, "none", "old tbilisi", "+995 ...", id1);
		Hotel h = HM.getHotel(hotel_id);
		
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

		assertTrue(HM.deleteHotel(h1.getId()));
		assertTrue(HM.deleteHotel(h2.getId()));
		assertEquals(HM.getHotel(h1.getId()), null);
		assertEquals(HM.getHotel(h2.getId()), null);
		
		AM.deleteAccount("devidevuka@mail.ru", "0406");
		AM.deleteAccount("dkhos17@freeuni.edu.ge", "0406");
	}
	
	@Test
	@Order(2)
	public void testFacility() {
		Integer hotel_id = HM.addHotel("Radison", 5, "iveria", "reworked", "+995 ...", id1);
		Hotel h = HM.getHotel(hotel_id);
		HM.addFacilities(h.getId(), "swimmingpool on top", true, true, false, false);
		h = HM.getHotel(h.getId());
		Facilities facil = h.getFacilities();
		assertEquals(facil.getFacility(), "swimmingpool on top");
		assertTrue(facil.getWiFi());
		assertTrue(facil.getParking());
		assertFalse(facil.getBeachfront());
		assertFalse(facil.getWoodfront());
		assertEquals(hotel_id, facil.getHotelId());
		
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
		assertTrue(HM.deleteHotel(h.getId()));
		
		AM.deleteAccount("devidevuka@mail.ru", "0406");
		AM.deleteAccount("dkhos17@freeuni.edu.ge", "0406");
	}
	
	@Test
	@Order(3)
	public void testLocation() {
		Integer hotel_id = HM.addHotel("Radison blue", 5, "blue iveria", "reworked", "+995 ...", id1);
		Hotel h = HM.getHotel(hotel_id);
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
		assertTrue(HM.deleteHotel(h.getId()));
		
		AM.deleteAccount("devidevuka@mail.ru", "0406");
		AM.deleteAccount("dkhos17@freeuni.edu.ge", "0406");
	}
	
	@Test
	@Order(4)
	public void testSelectMethods() throws ParseException {
		Integer hotel_id1 = HM.addHotel("Radison", 5, "iveria", "reworked", "+995 ...", id1);
		Integer hotel_id2 = HM.addHotel("Tiflisi", 4, "none", "old tbilisi", "+995 ...", id2);
		Integer hotel_id3 = HM.addHotel("Tiflisi TM", 3, "none", "tbilisi", "+995 ...", id2);
		 
		List<Hotel> hotels = HM.getHotels(id1);
		assertEquals(hotel_id1, hotels.get(0).getId());
		HM.addLocation(hotel_id1, "batumi", "d.agmashenebeli 76");
		HM.addFacilities(hotel_id1, "swimmingpool on top", false, false, false, false);
		hotels = HM.getHotels(id2);
		HM.addLocation(hotels.get(0).getId(), "tbilisi", "sanapiro 77");
		HM.addLocation(hotels.get(1).getId(), "tbilisi", "sanapiro 74");
		assertEquals(HM.getHotel(hotel_id1).getLocation().getHotelId(), hotel_id1);
		assertEquals(hotel_id2, hotels.get(0).getId());
		assertEquals(hotel_id3, hotels.get(1).getId());
		HM.addFacilities(hotels.get(0).getId(), "swimmingpool on top", true, true, true, true);
		HM.addFacilities(hotels.get(1).getId(), "swimmingpool on top", true, true, true, true);
		
		List<String> locations = HM.getAllLocations();
		assertEquals(locations.size(), 2);
		assertTrue(locations.contains("tbilisi"));
		assertTrue(locations.contains("batumi"));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = dateFormat.parse("1998-12-30");
		java.util.Date date2 = dateFormat.parse("1998-12-31");
		
		Integer zr = 0;
		Integer f = 50;
		Integer oneH = 100;
		
		assertEquals(zr, HM.getMinPrice(hotel_id1));
		assertEquals(zr, HM.getMaxPrice(hotel_id1));
		
		RM.addRoom(date1, date2, 50, "none", hotel_id1, 2, false, false, false, false);
		RM.addRoom(date1, date2, 100, "none", hotel_id1, 3, true, false, true, false);

		Integer min_price = HM.getMinPrice(hotel_id1);
		Integer max_price = HM.getMaxPrice(hotel_id1);

		assertEquals(f, min_price);
		assertEquals(oneH, max_price);

		boolean ratings[] = {false, false, true, true, true};
		List<Integer> filter = HM.getFilteredHotels(ratings, false, false, false, false);
		assertEquals(filter.size(), 3);
		filter = HM.getFilteredHotels(ratings, true, true, true, true);
		assertEquals(filter.size(), 2);
		ratings[2] = false;
		filter = HM.getFilteredHotels(ratings, false, true, false, false);
		assertEquals(filter.size(), 1); 
		filter = HM.getFilteredHotels(ratings, false, false, false, false);
		assertEquals(filter.size(), 2);
		ratings[3] = false; ratings[4] = false;
		filter = HM.getFilteredHotels(ratings, false, false, false, false);
		assertEquals(filter.size(), 3);
		filter = HM.getFilteredHotels(ratings, true, false, false, false);
		assertEquals(filter.size(), 2);
		
		List<Integer> search = HM.getSearchedHotels(null, null);
		assertEquals(search.size(), 3);
		search = HM.getSearchedHotels("tbilisi", null);
		assertEquals(search.size(), 2);
		search = HM.getSearchedHotels("tbilisi", "Tiflisi");
		assertEquals(search.size(), 1);
		search = HM.getSearchedHotels(null, "Tiflisi TM");
		assertEquals(search.size(), 1);
		
		
		List<Integer> sorted = HM.sortByRating(true);
		assertEquals(Arrays.asList(hotel_id3,hotel_id2,hotel_id1), sorted);
		sorted = HM.sortByRating(false);
		assertEquals(Arrays.asList(hotel_id1,hotel_id2,hotel_id3), sorted);

		List<Integer> list1 = Arrays.asList(8,5,6,7,1,2,3,4,5,6,7);
		List<Integer> list2 = Arrays.asList(9,4,5,6,8,1,2);
		List<Integer> marge = HM.intersectLists(list1, list2);
		assertEquals(marge, Arrays.asList(8,5,6,1,2,4));
		
		list1 = Arrays.asList(9,8,7,6,5,4,3);
		list2 = Arrays.asList(1,2,3,4,5,6);
		marge = HM.intersectLists(list1, list2);
		assertEquals(marge, Arrays.asList(6,5,4,3));
		
		list1 = Arrays.asList();
		list2 = Arrays.asList(1,2,3,4,5,6);
		marge = HM.intersectLists(list1, list2);
		assertEquals(marge, Arrays.asList());
		
		assertTrue(HM.deleteHotel(HM.getHotels(id1).get(0).getId()));
		assertTrue(HM.deleteHotel(HM.getHotels(id2).get(0).getId()));
		assertTrue(HM.deleteHotel(HM.getHotels(id2).get(0).getId()));
		
		AM.deleteAccount("devidevuka@mail.ru", "0406");
		AM.deleteAccount("dkhos17@freeuni.edu.ge", "0406");

	}
	
	@Test
	@Order(5)
	public void testComments() {
		Integer hotel_id = HM.addHotel("Radison", 5, "iveria", "reworked", "+995 ...", id1);
		HM.addComment(hotel_id, "devi", "great hotel! really nice! thnx");
		HM.addComment(hotel_id, "devi", "thnx again");
		HM.addComment(hotel_id, "sandro", "magadiaa");
		
		Map<String, List<String>> comments = HM.getComments(hotel_id);
		assertEquals(comments.size(), 2);
		assertEquals(comments.get("devi").size(), 2);
		assertEquals(comments.get("devi").get(0), "great hotel! really nice! thnx");
		assertEquals(comments.get("devi").get(1), "thnx again");
		assertEquals(comments.get("sandro").size(), 1);
		assertEquals(comments.get("sandro").get(0), "magadiaa");
		
		HM.deleteComments(hotel_id);
		comments = HM.getComments(hotel_id);
		assertEquals(comments.size(), 0);
		
		HM.addComment(hotel_id, "devi", "great hotel! really nice! thnx");
		HM.addComment(hotel_id, "devi", "thnx again");
		HM.addComment(hotel_id, "sandro", "magadiaa");
		
		assertTrue(HM.deleteHotel(hotel_id));
		
		AM.deleteAccount("devidevuka@mail.ru", "0406");
		AM.deleteAccount("dkhos17@freeuni.edu.ge", "0406");
	}
}
