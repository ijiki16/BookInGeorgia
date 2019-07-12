package Managers;

import java.util.Date;
import java.util.List;

import DataBases.HotelsDB;
import DataBases.RoomsDB;
import Models.Room;

public class RoomManager {

	private static RoomManager rm;
	private RoomsDB rdb;

	private RoomManager() {
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
	public Integer addRoom(Date sDate, Date eDate, Integer pricePerDay, String img, Integer hotlId,
			Integer numberOfBeds, boolean wifi, boolean tv, boolean hotWater, boolean airConditioning) {
		if (!sDate.before(eDate) || pricePerDay < 0 || hotlId < 1 || numberOfBeds < 0)
			return -1;
		return rdb.addRoom(sDate, eDate, pricePerDay, img, hotlId, numberOfBeds, wifi, tv, hotWater, airConditioning);
	}

	/**
	 * @updates Room in Data Base.
	 */
	public boolean updateRoom(Integer room_id, Date sDate, Date eData,  Integer pricePerDay, String img, Integer hotlId,
			Integer numberOfBeds, boolean wifi, boolean tv, boolean hotWater, boolean airConditioning) {
		if (room_id < 1 || !sDate.before(eData) || pricePerDay < 0 || hotlId < 1 || numberOfBeds < 0) return false;
		return rdb.updateRoom(room_id, sDate, eData, pricePerDay, img, hotlId, numberOfBeds, wifi, tv, hotWater,
				airConditioning);
	}

	/**
	 * @deletes Room from Data Base.
	 */
	public boolean deleteRoom(Integer room_id) {
		if(room_id<0) return false;
		return rdb.deleteRoom(room_id);
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
	 * @returns Reservations of the room
	 */
	public List<List<java.util.Date>> getRoomReservations(Integer roomId) {
		return rdb.getRoomReservations(roomId);
	}

	/**
	 * @returns if room booked.
	 */
	public boolean bookRoom(Integer room_id, Integer account_id, Date sDate, Date eDate) {
		if(room_id < 1 || !sDate.before(eDate) || account_id<0) return false;
		Room curRoom = getRoom(room_id);
		if(!(eDate.before(curRoom.getEndDate()) && curRoom.getStartDate().before(sDate))) return false;
		List<List<java.util.Date> > ress = rdb.getRoomReservations(room_id);
		for(int i=0; i<ress.size(); i++) {
			Date dt1 = ress.get(i).get(0);
			Date dt2 = ress.get(i).get(1);
			if(sDate.before(dt2) && !sDate.before(dt1)) return false;
			if(eDate.before(dt2) && !eDate.before(dt1)) return false;
		}
		return rdb.bookRoom(room_id, sDate, eDate, account_id);
	}

	/**
	 * @returns if room unbooked.
	 */
	public boolean unbookRoom(Integer room_id, Integer account_id, Date sDate, Date eDate) {
		if(room_id < 1 || !sDate.before(eDate) || account_id<0) return false;
		return rdb.unbookRoom(room_id, sDate, eDate, account_id);
	}
}
