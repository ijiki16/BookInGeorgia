package Models;

public class Location {
	
	private Integer hotel_id;
	private String city, address;
	
	public Location(Integer hotel_id, String city, String address) {
		this.setHotelId(hotel_id);
		this.setCity(city);
		this.setAddress(address);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getHotelId() {
		return hotel_id;
	}
	
	public void setHotelId(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}
}
