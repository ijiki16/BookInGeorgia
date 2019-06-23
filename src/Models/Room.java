package Models;

import java.util.Date;

public class Room {

	private int hottelId;
	private Date startDate;
	private Date endDate;
	int numberOfBeds;
	///roomsInfo
	boolean wifi;
	boolean tv;
	boolean hotWater;
	boolean airConditioning;

	public Room() {
		hottelId = -1;
		startDate = null;
		endDate = null;
		//info
		wifi = false;
		tv = false;
		hotWater = false;
		airConditioning= false;
	};

	public Room(Date sDate, Date eData, int hotlId, int numberOfBeds, 
			boolean wifi, boolean tv, boolean hotWater, boolean airConditioning) {
		hottelId = hotlId;
		startDate = sDate;
		endDate = eData;
		this.numberOfBeds = numberOfBeds;
		//info
		this.wifi = wifi;
		this.tv = tv;
		this.hotWater = hotWater;
		this.airConditioning = airConditioning;
	}

	@Override
	public String toString() {
		return "hottelId: " + hottelId + " startDate: " + startDate + " endDate: " + endDate;
	}
}
