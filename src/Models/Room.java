package Models;

import java.util.Date;

public class Room {
	private int RoomId, pricePerDay;
	private int hottelId, numberOfBeds;
	private Date startDate, endDate;
	private String img;
	/// roomsInfo
	private boolean wifi, tv, hotWater, airConditioning;

	/**
	 * Creates new Room Object with default parameters
	 */
	public Room() {
		RoomId = - 1;
		pricePerDay = 0;
		hottelId = -1;
		img = "";
		startDate = null;
		endDate = null;
		numberOfBeds = 0;
		// info
		wifi = false;
		tv = false;
		hotWater = false;
		airConditioning = false;
	}

	/**
	 * Creates new Room Object
	 * 
	 * @param Room       ID in DB
	 * @param Room       reservation starting Date
	 * @param Room       reservation end Date
	 * @param Price per Day
	 * @param Hottel     ID in DB
	 * @param number     of beds in room
	 * @param has/hasn't wifi
	 * @param has/hasn't tv
	 * @param has/hasn't hot water
	 * @param has/hasn't air conditioning
	 */
	public Room(int id, Date sDate, Date eData, int pricePerDay, String img, int hotlId, int numberOfBeds, boolean wifi, boolean tv,
			boolean hotWater, boolean airConditioning) {
		this.RoomId = id;
		this.hottelId = hotlId;
		startDate = sDate;
		endDate = eData;
		this.img = img;
		this.pricePerDay = pricePerDay;
		this.numberOfBeds = numberOfBeds;
		// info
		this.wifi = wifi;
		this.tv = tv;
		this.hotWater = hotWater;
		this.airConditioning = airConditioning;
	}

	@Override
	public String toString() {
		return "RoomID: "+RoomId+" $:"+pricePerDay+" hottelId: " + hottelId + " startDate: " + startDate + " endDate: " + endDate+"\n"
	+"info: numberOfBeds->"+numberOfBeds+" is Wifi->"+wifi+" is Tv->"+tv+" is HotWater->"+hotWater+" is AirConditioning->"+airConditioning;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(!(obj instanceof Room)) return false;
		Room second = (Room) obj;
		return same(second);
	}

	private boolean same(Room second) {
		// TODO Auto-generated method stub
		if(this.RoomId != second.RoomId)return false;
		if(this.hottelId != second.hottelId)return false;
		if(!this.startDate.equals(second.startDate))return false;
		if(!this.endDate.equals(second.endDate))return false;
		if(this.pricePerDay != second.pricePerDay) return false;
		if(this.numberOfBeds != second.numberOfBeds)return false;
		if(!this.img.equals(second.img)) return false;
		if(this.wifi != second.wifi) return false;
		if(this.tv != second.tv) return false;
		if(this.hotWater != second.hotWater) return false;
		if(this.airConditioning != second.airConditioning) return false;
		return true;
	}

	

	// getters and setters
	/**
	 * @return Room ID of the Room.
	 */
	public Integer getRoomId() {
		return RoomId;
	}

	/**
	 * @return Hottel ID of the Room.
	 */
	public Integer getHottelId() {
		return hottelId;
	}
	
	public String getImage() {
		return img;
	}
	
	/**
	 * @return Price of Room per Day
	 */
	public int getPricePerDay() {
		return pricePerDay;
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
	
	/**
	 * Updates room ID in DataBase.
	 * 
	 * @param roomId
	 */
	public void setRoomId(int roomId) {
		this.RoomId = roomId;
	}
	/**
	 * Update price of Room per Day
	 * @param pricePerDay
	 */
	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
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
	
	public void setImage(String img) {
		this.img = img;
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
