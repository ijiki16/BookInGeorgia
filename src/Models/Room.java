package Models;

import java.util.Date;

public class Room {
	
	private int hottelId;
	private Date startDate;
	private Date endDate ;
	
	public Room() {
		hottelId = -1;
		startDate = null;
		endDate = null;
	};
	public Room(Date sDate, Date eData, int hotlId) {
		hottelId = hotlId;
		startDate = sDate;
		endDate =eData;
	}
	@Override
	public String toString() {
		return "hottelId: "+hottelId+" startDate: "+startDate+" endDate: "+endDate;
	}
}
