package Managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DataBases.HotelsDB;
import DataBases.RoomsDB;
import Models.Facilities;
import Models.Hotel;
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
	public void addHotel(String name, Integer rating, String img, String status, String number, Integer account_id) {
		db.addHotel(name, rating, img, status, number, account_id);
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
	public void updateFaciliies(Integer hotel_id, String facility, boolean wifi, boolean parking, boolean beachfront, boolean woodfront) {
		db.updateFacilities(hotel_id, facility, wifi, parking, beachfront, woodfront);
	}
	
	/**
	 * @deletes Hotel Facilities from Data Base.
	 */
	public void deleteFacilites(Integer hotel_id) {
		db.deleteFacilities(hotel_id);
	}
	
	/**
	 * @inserts Room into Hotel.
	 */
	public void addRoom(Date sDate, Date eData, Integer hotlId, Integer numberOfBeds, boolean wifi, boolean tv,
			boolean hotWater, boolean airConditioning) {
		rdb.addRoom(sDate, eData, hotlId, numberOfBeds, wifi, tv, hotWater, airConditioning);
	}
	
	/**
	 * @updates Room in Data Base.
	 */
	public void updateRooms(Integer room_id, Date sDate, Date eData, Integer hotlId, Integer numberOfBeds, boolean wifi, boolean tv,
			boolean hotWater, boolean airConditioning) {
		rdb.updateRoom(room_id, sDate, eData, hotlId, numberOfBeds, wifi, tv, hotWater, airConditioning);
	}
	
	/**
	 * @deletes Room from Data Base.
	 */
	public void deleteRoom(Integer room_id) {
		rdb.deleteRoom(room_id);
	}
	
	/**
	 * @return Room by room_id.
	 */
	public Room getRoom(Integer room_id) {
		return rdb.getRoom(room_id);
	}
	
	/**
	 * @returns Hotels rooms from data base as a list.
	 */
	public List<Room> getRooms(Integer hotel_id) {
		return rdb.getRoomByHottel(hotel_id);
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
		List<Integer> hotel_ids = db.getHotelIDs(account_id);
		List<Hotel> Hotels = new ArrayList<Hotel>();
		for(Integer hotel_id : hotel_ids) {
			Hotels.add(this.getHotel(hotel_id));
		}
		return Hotels;
	}
	
}
