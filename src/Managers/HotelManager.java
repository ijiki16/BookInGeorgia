package Managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import DataBases.HotelsDB;
import DataBases.RoomsDB;
import Models.Facilities;
import Models.Hotel;
import Models.Location;
import Models.Room;

public class HotelManager {
	private static HotelManager hm;
	private HotelsDB db;
	private RoomsDB rdb;
	
	private HotelManager() {
		db = HotelsDB.getInstance();
		rdb = RoomsDB.getInstance();
	}
	
	public static HotelManager getInstance() {
		if (hm == null) {
			synchronized (HotelManager.class) {
				if (hm == null) {
					hm = new HotelManager();
				}
			}
		}
		return hm;
	}
	
	/**
	 * @inserts Hotel into Data Base.
	 */
	public Integer addHotel(String name, Integer rating, String img, String status, String number, Integer account_id) {
		return db.addHotel(name, rating, img, status, number, account_id);
	}
	
	/**
	 * @deletes Hotel from Data Base.
	 */
	public void deleteHotel(Integer hotel_id) {
		db.deleteHotel(hotel_id);
	}
	
	/**
	 * @updates Hotel in Data Base.
	 */
	public void updateHotel(Integer hotel_id, String name, Integer rating, String img, String status, String number, Integer account_id) {
		db.updateHotel(hotel_id, name, rating, img, status, number, account_id);
	}
	
	/**
	 * @inserts Hotel Facilities into Data Base.
	 */
	public void addFacilities(Integer hotel_id, String facility, boolean wifi, boolean parking, boolean beachfront, boolean woodfront) {
		db.addFacilities(hotel_id, facility, wifi, parking, beachfront, woodfront);
	}
	
	/**
	 * @updates Hotel Facilities in Data Base.
	 */
	public void updateFacilities(Integer hotel_id, String facility, boolean wifi, boolean parking, boolean beachfront, boolean woodfront) {
		db.updateFacilities(hotel_id, facility, wifi, parking, beachfront, woodfront);
	}
	
	/**
	 * @deletes Hotel Facilities from Data Base.
	 */
	public void deleteFacilites(Integer hotel_id) {
		db.deleteFacilities(hotel_id);
	}
	
	/**
	 * @inserts Hotel location into Data Base.
	 */
	public void addLocation(Integer hotel_id, String city, String address) {
		db.addLocation(hotel_id, city, address);
	}
	
	/**
	 * @updates Hotel location in Data Base.
	 */
	public void updateLocation(Integer hotel_id, String city, String address) {
		db.updateLocation(hotel_id, city, address);
	}
	
	/**
	 * @deletes Hotel location from Data Base.
	 */
	public void deleteLocation(Integer hotel_id) {
		db.deleteLocation(hotel_id);
	}
	
	
	/**
	 * @return Hotel by hotel_id.
	 */
	public Hotel getHotel(Integer hotel_id) {
		return db.getHotel(hotel_id);
	}
	
	/**
	 * @returns Users Hotels list.
	 */
	public List<Hotel> getHotels(Integer account_id){
		List<Hotel> Hotels = new ArrayList<Hotel>();
		for(Integer hotel_id : db.getHotelIDs(account_id)) {
			Hotels.add(this.getHotel(hotel_id));
		}
		return Hotels;
	}
	
	/**
	 * @returns All city locations as a list.
	 */
	public List<String> getAllLocations() {
		return db.getAllLocations();
	}
	
	/**
	 * @returns Hotels list according to filtered items.
	 */
	public List<Integer> getFilteredHotels(boolean[] ratings, boolean beachfront, boolean woodfront, boolean wifi, boolean parking){
		List<Integer> hotels = new ArrayList<>();
		boolean rate = false;
		boolean facil = beachfront || wifi || parking;
		for(int i = 0; i < ratings.length; i++) {
			rate |= ratings[i];
			if(ratings[i]) {
				List<Integer> filtr = db.getFilteredHotels(i+1, beachfront, woodfront, wifi, parking);
				for(Integer h : filtr) {
					hotels.add(h);
				}
			}
		}
		if(!rate && !facil) return this.getSearchedHotels(null, null);
		for(int i = 0; i < ratings.length; i++) {
			List<Integer> filtr = db.getFilteredHotels(i+1, beachfront, woodfront, wifi, parking);
			for(Integer h : filtr) {
				hotels.add(h);
			}
		}
		return hotels;
	}
	
	/**
	 * @returns Hotel's cheapest room.
	 */
	public Integer getMinPrice(Integer hotel_id) {
		return db.getMinPrice(hotel_id);
	}
	
	/**
	 * @returns Hotel's most expensive room.
	 */
	public Integer getMaxPrice(Integer hotel_id) {
		return db.getMaxPrice(hotel_id);
	}
	
	/**
	 * @returns Hotels list according to search items.
	 */
	public List<Integer> getSearchedHotels(String city, String name){
		if(city == null && name == null) return db.getAllHotelIDs();
		return db.getSearchedHotels(city, name);
	}
	
	public List<Integer> sortByRating(boolean desc){
		return db.sortByRating(desc);
	}
	
	/**
	 * @returns Hotels list according to search and filter items.
	 */
	public List<Integer> intersectLists(List<Integer> list1, List<Integer> list2) {
		return list1.stream().distinct().filter(list2::contains).collect(Collectors.toList());
	}
}
