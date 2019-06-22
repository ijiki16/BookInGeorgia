package Models;

import DataBases.HotelsDB;

public class Facilities {
	
	/**
	 * Creates new Hotel Facilities Object
	 * @param hotel_id 
	 * @param extra_facility
	 * @param wifi
	 * @param parking
	 * @param beach/wood front
	 */
	
	private HotelsDB db;
	private String facility;
	private Integer hotel_id;
	private boolean wifi, parking, beachfront, woodfront;
	
	public Facilities(Integer hotel_id, String facility, boolean wifi, boolean parking, boolean beachfront, boolean woodfront) {
		setHotelId(hotel_id);
		setFacility(facility);
		setWiFi(wifi);
		setParking(parking);
		setBeachfront(beachfront);
		setWoodfront(woodfront);
	}
	
	/**
	 * @inserts Hotel Facilities into Data Base.
	 */
	public void insertFacilities() {
		db = HotelsDB.getInstance();
		db.addFacilities(hotel_id, facility, wifi, parking, beachfront, woodfront);
	}
	
	/**
	 * @return Hotel id, for which facilities are this.
	 */
	public Integer getHotelId() {
		return this.hotel_id;
	}
	
	/**
	 * Updates Hotel Id.
	 * @param
	 */
	public void setHotelId(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}

	/**
	 * @return extra facility.
	 */
	public String getFacility() {
		return this.facility;
	}
	
	/**
	 * Updates facility.
	 * @param
	 */
	public void setFacility(String facility) {
		this.facility = facility;
	}
	
	/**
	 * @return if Hotel has wi-fi.
	 */
	public boolean getWiFi() {
		return this.wifi;
	}
	
	/**
	 * Updates wifi.
	 * @param
	 */
	public void setWiFi(boolean wifi) {
		this.wifi = wifi;
	}
	
	/**
	 * @return if Hotel has parking place.
	 */
	public boolean getParking() {
		return this.parking;
	}
	
	/**
	 * Updates parking place.
	 * @param
	 */
	public void setParking(boolean parking) {
		this.parking = parking;
	}
	
	/**
	 * @return if Hotel is near wood.
	 */
	public boolean getWoodfront() {
		return this.woodfront;
	}
	
	/**
	 * Updates wood front place.
	 * @param
	 */
	public void setWoodfront(boolean woodfront) {
		this.woodfront = woodfront;
	}
	
	/**
	 * @return if Hotel is near beach.
	 */
	public boolean getBeachfront() {
		return this.beachfront;
	}
	
	/**
	 * Updates beach front place.
	 * @param
	 */
	public void setBeachfront(boolean beachfront) {
		this.beachfront = beachfront;
	}
	
	/**
	 * @deletes facilities from Data Base.
	 */
	public void eraseFacilities(Integer hotel_id) {
		db = HotelsDB.getInstance();
		db.deleteFacilities(hotel_id);		
	}
	
}
