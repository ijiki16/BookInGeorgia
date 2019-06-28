package Models;

import java.util.ArrayList;
import java.util.List;

import DataBases.HotelsDB;

public class Hotel {
	
	/**
	 * Creates new Hotel Object
	 * @param hotel_name 
	 * @param hotel_rating/stars
	 * @param hotel_image
	 * @param hotel_status
	 * @param hotel_contact_number
	 * @param hotel_owner_id
	 */
	private String name, img, status, number;
	private Integer rating, account_id, hotel_id;
	private Facilities facilities;
	private List<Room> rooms;
	
	public Hotel(String name, Integer rating, String img, String status, String number, Integer account_id, Integer hotel_id) {
		setName(name);
		setRating(rating);
		setImage(img);
		setStatus(status);
		setNumber(number);
		setAccountId(account_id);
		setHotelId(hotel_id);
		rooms = new ArrayList<Room>();
	}
	
	/**
	 * @return name of the Hotel.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Updates name of the Hotel.
	 * @param
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return rating of the Hotel.
	 */
	public Integer getRating() {
		return rating;
	}
	
	/**
	 * Updates rating of the Hotel.
	 * @param
	 */
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	/**
	 * @return image of the Hotel.
	 */
	public String getImage() {
		return img;
	}

	/**
	 * Updates image of the Hotel.
	 * @param
	 */
	public void setImage(String img) {
		this.img = img;
	}
	
	/**
	 * @return status of the Hotel.
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Updates status of the Hotel.
	 * @param
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return phone number of Hotel.
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * Updates phone number of Hotel.
	 * @param
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**
	 * @return Hotel owner id.
	 */
	public Integer getAccountId() {
		return account_id;
	}
	
	/**
	 * Updates id of the Hotel owner.
	 * @param
	 */
	public void setAccountId(Integer account_id) {
		this.account_id = account_id;
	}
	
	/**
	 * @return Hotel facilities.
	 */
	public Facilities getFacilities() {
		return facilities;
	}
	
	/**
	 * Updates Hotel facilities.
	 * @param
	 */
	public void setFacilities(Facilities facilities) {
		this.facilities = facilities;
	}
	
	/**
	 * @return Hotel ID.
	 */
	public Integer getHotelId() {
		return hotel_id;
	}
	
	/**
	 * Updates Hotel ID.
	 * @param
	 */
	public void setHotelId(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}
	
	/**
	 * @return Hotel rooms.
	 */
	public List<Room> getRooms() {
		return rooms;
	}
	
	/**
	 * Updates Hotel rooms.
	 * @param
	 */
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

}
