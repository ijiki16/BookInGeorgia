package Models;

public class Hotel {
	
	private String name;
	private float review;
	private String address;
	private String location;
	private String description;
	private String overview;
	
	/**
	 * Constructs a new hotel and it's main info.
	 * @param name
	 * @param review
	 * @param address
	 * @param location
	 * @param description
	 * @param overview
	 */
	public Hotel(String name, float review, String address, String location, String description, String overview) {
		this.name = name;
		this.review = review;
		this.address = address;
		this.location = location;
		this.description = description;
		this.overview = overview;
	}
	
	/**
	 * @return Name of the hotel.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return Review of the hotel.
	 */
	public float getReview() {
		return this.review;
	}
	
	/**
	 * @return Address of the hotel.
	 */
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * @return Location of the hotel.
	 */
	public String getLocation() {
		return this.location;
	}
	
	/**
	 * Hotels description in detail. Used for users inside the publication.
	 * @return Long description of hotel.
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Hotels small description and overview.
	 * Used in searching applications for users.
	 * @return Small description of hotel.
	 */
	public String getOverview() {
		return this.overview;
	}

}
