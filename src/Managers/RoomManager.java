package Managers;

import java.util.Date;
import java.util.List;

import DataBases.HotelsDB;
import DataBases.RoomsDB;
import Models.Room;

public class RoomManager {
	
	private static RoomManager rm;
	private HotelsDB hdb;
	private RoomsDB rdb;
	
	private RoomManager() {
		hdb = HotelsDB.getInstance();
		rdb = RoomsDB.getInstance();
	}
	
	public static RoomManager getInstance() {
		if (rm == null) {
			synchronized (RoomManager.class) {
				if (rm == null) {
					rm = new RoomManager();
				}
			}
		}
		return rm;
	}
	
	/**
	 * @inserts Room into Hotel.
	 */
	public void addRoom(Date sDate, Date eData, Integer pricePerDay, Integer hotlId, Integer numberOfBeds, boolean wifi, boolean tv,
			boolean hotWater, boolean airConditioning) {
		rdb.addRoom(sDate, eData, pricePerDay, hotlId, numberOfBeds, wifi, tv, hotWater, airConditioning);
	}
	
	/**
	 * @updates Room in Data Base.
	 */
	public void updateRoom(Integer room_id, Date sDate, Date eData, Integer pricePerDay, Integer hotlId, Integer numberOfBeds, boolean wifi, boolean tv,
			boolean hotWater, boolean airConditioning) {
		rdb.updateRoom(room_id, sDate, eData, pricePerDay, hotlId, numberOfBeds, wifi, tv, hotWater, airConditioning);
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
	 * @returns if room booked.
	 */
	public boolean bookRoom(Integer hotel_id, Integer room_id, Integer account_id, Date sDate, Date eDate) {
		return rdb.bookRoom(room_id, sDate, eDate, account_id);
	}
	

	/**
	 * @returns if room unbooked.
	 */
	public boolean unbookRoom(Integer hotel_id, Integer room_id, Integer account_id, Date sDate, Date eDate) {
		return rdb.unbookRoom(room_id, sDate, eDate, account_id);
	}
	
	/**
	 * @adds reservation
	 */
	public void addReservation(String from, String to, Integer room_id, Integer account_id) {
		rdb.addReservation(from, to, room_id, account_id);
	}
	
	/**
	 * @deletes reservation
	 */
	public void deleteReservation(Integer reserved_id) {
		rdb.deleteReservation(reserved_id);
	}
	
}
