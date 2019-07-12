package DataBases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Models.Facilities;
import Models.Hotel;
import Models.Location;
import Models.Room;


public class HotelsDB {
	private static HotelsDB db;

	private static String account = MyDBInfo.MYSQL_USERNAME;
	private static String password = MyDBInfo.MYSQL_PASSWORD;
	private static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	private static String database = MyDBInfo.MYSQL_DATABASE_NAME;
	private static Connection con;

	private HotelsDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static HotelsDB getInstance() {
		if (db == null) {
			synchronized (HotelsDB.class) {
				if (db == null) {
					db = new HotelsDB();
				}
			}
		}
		return db;
	}

	public List<Integer> getAllHotelIDs(){
		List<Integer> hotel_ids = new ArrayList<Integer>();
		try {
			String query = "select hotel_id from Hotels;";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				hotel_ids.add(rs.getInt("hotel_id"));
			}
			return hotel_ids;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return hotel_ids;
	}
	
	public Hotel getHotel(Integer hotel_id) {
		try {
			String query = "select * from Hotels where hotel_id = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, hotel_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Hotel hotel = new Hotel(rs.getString("name"),
						rs.getInt("rating"),
						rs.getString("img"),
						rs.getString("status"),
						rs.getString("phone_number"),
						rs.getInt("account_id"),
						hotel_id);
				Facilities facility = getFacilities(hotel_id);
				Location location = getLocation(hotel_id);
				if(facility != null) hotel.setFacilities(facility);
				if(location != null) hotel.setLocation(location);
				return hotel;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Facilities getFacilities(Integer hotel_id) {
		try {
			String query = "select * from HotelInfo where hotel_id = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, hotel_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Facilities facil = new Facilities(hotel_id,
						rs.getString("facility"),
						rs.getBoolean("wifi"),
						rs.getBoolean("parking"),
						rs.getBoolean("beachfront"),
						rs.getBoolean("woodfront"));
				return facil;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Location getLocation(Integer hotel_id) {
		try {
			String query = "select * from Hotels h join Locations l on h.hotel_id = l.hotel_id where l.hotel_id = ?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, hotel_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) return new Location(hotel_id, rs.getString("city"), rs.getString("address"));
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Integer> getHotelIDs(Integer account_id){
		List<Integer> hotel_ids = new ArrayList<Integer>();
		try{
			String query = "select h.hotel_id as id from Accounts a join Hotels h on a.account_id = h.account_id where h.account_id = ?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, account_id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				hotel_ids.add(rs.getInt("id"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return hotel_ids;
	}

	public Integer addHotel(String name, Integer rating, String img, String status, String number, Integer account_id) {
		Integer hotel_id = -1;
		try {
			String query = "insert into Hotels (name, rating, img, status, phone_number, account_id) values (?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, name);
			stmt.setInt(2, rating);
			stmt.setString(3, img);
			stmt.setString(4, status);
			stmt.setString(5, number);
			stmt.setInt(6, account_id);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) hotel_id = rs.getInt(1);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return hotel_id;
	}
	
	public void addFacilities(Integer hotel_id, String facility, boolean wifi, boolean parking, boolean beachfront, boolean woodfront) {
		try {
			String query = "insert into HotelInfo (facility, wifi, parking, beachfront, woodfront, hotel_id) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, facility);
			stmt.setBoolean(2, wifi);
			stmt.setBoolean(3, parking);
			stmt.setBoolean(4, beachfront);
			stmt.setBoolean(5, woodfront);
			stmt.setInt(6, hotel_id);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addLocation(Integer hotel_id, String city, String address) {
		try {
			String query = "insert into Locations (city, address, hotel_id) values (?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, city);
			stmt.setString(2, address);
			stmt.setInt(3, hotel_id);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteHotel(Integer hotel_id) {
		try {
			this.deleteFacilities(hotel_id);
			this.deleteLocation(hotel_id);
			
			String query = "delete from Hotels where hotel_id = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, hotel_id);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteFacilities(Integer hotel_id) {
		try {
			String query = "delete from HotelInfo where hotel_id = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, hotel_id);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteLocation(Integer hotel_id) {
		try {
			String query = "delete from Locations where hotel_id = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, hotel_id);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateHotel(Integer hotel_id, String name, Integer rating, String img, String status, String number, Integer account_id) {
		try {
			String query = "update Hotels set name = ?, rating = ?, img = ?, status = ?, phone_number = ?, account_id = ? where hotel_id = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setInt(2, rating);
			stmt.setString(3, img);
			stmt.setString(4, status);
			stmt.setString(5, number);
			stmt.setInt(6, account_id);
			stmt.setInt(7, hotel_id);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void updateFacilities(Integer hotel_id, String facility, boolean wifi, boolean parking,
			boolean beachfront, boolean woodfront) {
		try {
			String query = "update HotelInfo set facility = ?, wifi = ?, parking = ?, beachfront = ?, woodfront = ? where hotel_id = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, facility);
			stmt.setBoolean(2, wifi);
			stmt.setBoolean(3, parking);
			stmt.setBoolean(4, beachfront);
			stmt.setBoolean(5, woodfront);
			stmt.setInt(6, hotel_id);
			stmt.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateLocation(Integer hotel_id, String city, String address) {
		try {
			String query = "update Locations set city = ?, address = ? where hotel_id = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, city);
			stmt.setString(2, address);
			stmt.setInt(3, hotel_id);
			stmt.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getAllLocations(){
		List<String> locations = new ArrayList<>();
		try {
			String query = "select distinct city from Hotels h join Locations l on h.hotel_id = l.hotel_id";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				locations.add(rs.getString("city"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return locations;
	}
	
	public List<Integer> getFilteredHotels(Integer rating, Boolean beachfront, Boolean woodfront, Boolean wifi, Boolean parking) {
		List<Integer> hotels = new ArrayList<Integer>();
		try {
			String query = "select hi.hotel_id from Hotels h join HotelInfo hi on h.hotel_id = hi.hotel_id where h.rating = ?";
			if(beachfront) query += " and hi.beachfront = ?";
			if(woodfront) query	+= " and hi.woodfront = ?";
			if(wifi) query += " and hi.wifi = ?";
			if(parking) query += " and hi.parking = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			int idx = 1;
			stmt.setInt(idx++, rating);
			if(beachfront) stmt.setBoolean(idx++, beachfront);
			if(woodfront) stmt.setBoolean(idx++, woodfront);
			if(wifi) stmt.setBoolean(idx++, wifi);
			if(parking) stmt.setBoolean(idx++, parking);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				hotels.add(rs.getInt("hotel_id"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return hotels;
	}
	

	public List<Integer> getSearchedHotels(String city, String name) {
		List<Integer> hotels = new ArrayList<Integer>();
		try {
			String query = "select h.hotel_id from Hotels h join Locations l on h.hotel_id = l.hotel_id where";
			if(city != null) query += " l.city = ?" + (name == null ? "" : " and");
			if(name != null) query += " h.name = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			int idx = 1;
			if(city != null) stmt.setString(idx++, city);
			if(name != null) stmt.setString(idx++, name);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				hotels.add(rs.getInt("hotel_id"));
			}
		}catch (SQLException e) { 
			e.printStackTrace();
		}
		return hotels;
	}

	public List<Integer> sortByRating(boolean desc) {
		List<Integer> sorted = new ArrayList<Integer>();
		try {
			String query = "select hotel_id from Hotels order by hotel_id ";
			if(desc) query += "desc";
			else query += "asc";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				sorted.add(rs.getInt("hotel_id"));
			}
		}catch (SQLException e) { 
			e.printStackTrace();
		}
		return sorted;
	}

	public Integer getMinPrice(Integer hotel_id) {
		Integer min_price = 0;
		try {
			String query = "select min(price_per_day) as price from Hotels h join Rooms r on h.hotel_id = r.hotel_id where r.hotel_id = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, hotel_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) min_price = rs.getInt("price");
		}catch (SQLException e) { 
			e.printStackTrace();
		}
		return min_price;
	}

	public Integer getMaxPrice(Integer hotel_id) {
		Integer max_price = 0;
		try {
			String query = "select max(price_per_day) as price from Hotels h join Rooms r on h.hotel_id = r.hotel_id where r.hotel_id = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, hotel_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) max_price = rs.getInt("price");
		}catch (SQLException e) { 
			e.printStackTrace();
		}
		return max_price;
	}

}
