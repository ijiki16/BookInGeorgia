package Models;

import java.util.Date;

public class Room {
	private int RoomId;
	private int hottelId, numberOfBeds;
	private Date startDate, endDate;
	/// roomsInfo
	private boolean wifi, tv, hotWater, airConditioning;

	/**
	 * Creates new Room Object with default parameters
	 */
	public Room() {
		RoomId = - 1;
		hottelId = -1;
		startDate = null;
		endDate = null;
		numberOfBeds = 0;
		// info
		wifi = false;
		tv = false;
		hotWater = false;
		airConditioning = false;
	};

	/**
	 * Creates new Room Object
	 * 
	 * @param Room       reservation starting Date
	 * @param Room       reservation end Date
	 * @param Hottel     ID in DB
	 * @param number     of beds in room
	 * @param has/hasn't wifi
	 * @param has/hasn't tv
	 * @param has/hasn't hot water
	 * @param has/hasn't air conditioning
	 */
	public Room(Date sDate, Date eData, int hotlId, int numberOfBeds, boolean wifi, boolean tv, boolean hotWater,
			boolean airConditioning) {
		hottelId = hotlId;
		startDate = sDate;
		endDate = eData;
		this.numberOfBeds = numberOfBeds;
		// info
		this.wifi = wifi;
		this.tv = tv;
		this.hotWater = hotWater;
		this.airConditioning = airConditioning;
	}

	/**
	 * Creates new Room Object
	 * 
	 * @param Room       ID in DB
	 * @param Room       reservation starting Date
	 * @param Room       reservation end Date
	 * @param Hottel     ID in DB
	 * @param number     of beds in room
	 * @param has/hasn't wifi
	 * @param has/hasn't tv
	 * @param has/hasn't hot water
	 * @param has/hasn't air conditioning
	 */
	public Room(int id, Date sDate, Date eData, int hotlId, int numberOfBeds, boolean wifi, boolean tv,
			boolean hotWater, boolean airConditioning) {
		RoomId = id;
		hottelId = hotlId;
		startDate = sDate;
		endDate = eData;
		this.numberOfBeds = numberOfBeds;
		// info
		this.wifi = wifi;
		this.tv = tv;
		this.hotWater = hotWater;
		this.airConditioning = airConditioning;
	}

	@Override
	public String toString() {
		return "hottelId: " + hottelId + " startDate: " + startDate + " endDate: " + endDate+"\n"
	+"info: numberOfBeds->"+numberOfBeds+" is Wifi->"+wifi+" is Tv->"+tv+"is HotWater->"+hotWater+" is AirConditioning->"+airConditioning;
	}

	// getters and setters
	/**
	 * @return Room ID of the Room.
	 */
	public int getRoomId() {
		return RoomId;
	}

	/**
	 * @return Hottel ID of the Room.
	 */
	public int getHottelId() {
		return hottelId;
	}

	/**
	 * @return number of beds in the Room.
	 */
	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	/**
	 * @return Room reservation starting Date.
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @return Room reservation end Date.
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @return if Room has wifi.
	 */
	public boolean isWifi() {
		return wifi;
	}

	/**
	 * @return if Room has TV.
	 */
	public boolean isTv() {
		return tv;
	}

	/**
	 * @return if Room has Hot Water.
	 */
	public boolean isHotWater() {
		return hotWater;
	}

	/**
	 * @return if Room has Air Conditioning.
	 */
	public boolean isAirConditioning() {
		return airConditioning;
	}
	
	//
	
	/**
	 * Updates room ID in DataBase.
	 * 
	 * @param roomId
	 */
	public void setRoomId(int roomId) {
		this.RoomId = roomId;
	}
	/**
	 * Updates hoottel ID in DataBase.
	 * 
	 * @param hottelId
	 */
	public void setHottelId(int hottelId) {
		this.hottelId = hottelId;
	}

	/**
	 * Updates number of beds in room.
	 * 
	 * @param numberOfBeds
	 */
	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	/**
	 * Updates Room reservation starting Date.
	 * 
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Updates Room reservation end Date.
	 * 
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Updates Wifi in Room.
	 * 
	 * @param hasWifi
	 */
	public void setWifi(boolean hasWifi) {
		this.wifi = hasWifi;
	}

	/**
	 * Updates TV in Room .
	 * 
	 * @param hasTv
	 */
	public void setTv(boolean hasTv) {
		this.tv = hasTv;
	}

	/**
	 * Updates hotWater in Room.
	 * 
	 * @param hasHotWater
	 */
	public void setHotWater(boolean hasHotWater) {
		this.hotWater = hasHotWater;
	}

	/**
	 * Updates AirConditioning in Room.
	 * 
	 * @param hasAirConditioning
	 */
	public void setAirConditioning(boolean hasAirConditioning) {
		this.airConditioning = hasAirConditioning;
	}
}
