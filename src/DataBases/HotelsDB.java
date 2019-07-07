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

	static String account = MyDBInfo.MYSQL_USERNAME;
	static String password = MyDBInfo.MYSQL_PASSWORD;
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;
	static Connection con;

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
		//updateBase();
		return db;
	}

	public static Connection getConnection() {
		return con;
	}
	
//	private static void updateBase(){
//			try{
//				Connection con = getConnection();
//				String query = "select * from Hotels";
//				PreparedStatement stmt = con.prepareStatement(query);
//				ResultSet rs = stmt.executeQuery();
//				while(rs.next()){
//					String id = rs.getString("hotel_id");
//					String name = rs.getString("name");
//					String rank = rs.getString("rating");
//					String img = rs.getString("img");
//					String status = rs.getString("status");
//					String number = rs.getString("phone_number");
//					String acc_id = rs.getString("account_id");
//				}
//			}catch (SQLException e) {
//				e.printStackTrace();
//			}		
//	}
	
	public List<Integer> getAllHotelIDs(){
		try {
			List<Integer> hotel_ids = new ArrayList<Integer>();
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
		return null;
	}
	
	public Hotel getHotel(Integer hotel_id) {
		try {
			String query = "select * from Hotels where hotel_id = '" + Integer.toString(hotel_id) + "'";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);
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
			String query = "select * from HotelInfo where hotel_id = '" + Integer.toString(hotel_id) + "'";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);
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
			String query = "select * from Hotels h join Locations l on h.hotel_id == l.hotel_id where hotel_id = ?";
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
		try{
			List<Integer> hotel_ids = new ArrayList<Integer>();
			String query = "select hotel_id from Accounts a join Hotels h on a.account_id = h.account_id where h.account_id = '"
						+ Integer.toString(account_id) +"'";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				hotel_ids.add(rs.getInt("hotel_id"));
			}
			return hotel_ids;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public synchronized void addHotel(String name, Integer rating, String img, String status, String number, Integer account_id) {
		try {
			String query = "insert into Hotels (name, rating, img, status, phone_number, account_id) values (?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setInt(2, rating);
			stmt.setString(3, img);
			stmt.setString(4, status);
			stmt.setString(5, number);
			stmt.setInt(6, account_id);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized int getLastId() {
		try{
			String query = "select max(hotel_id) as m from Hotels";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				return rs.getInt("m");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void addFacilities(Integer hotel_id, String facility, boolean wifi, boolean parking, boolean beachfront, boolean woodfront) {
		try {
			String query = "insert into HotelInfo (facility, wifi, parking, beachfront, woodfront, hotel_id) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setBoolean(1, wifi);
			stmt.setBoolean(2, parking);
			stmt.setBoolean(3, beachfront);
			stmt.setBoolean(4, woodfront);
			stmt.setString(5, facility);
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
	
	public void addReservation(String from, String to, int room_id, int account_id) {
		try {
			String query = "insert into Reservation (reserved_from, reserved_to, room_id, account_id) values (?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, from);
			stmt.setString(2, to);
			stmt.setInt(3, room_id);
			stmt.setInt(4, account_id);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteReservation(int id) {
		try {
			String query = "delete from Reservation where reserved_id = '" + Integer.toString(id) + "'";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteHotel(Integer hotel_id) {
		try {
			this.deleteFacilities(hotel_id);
			this.deleteLocation(hotel_id);
			
			String query = "delete from Hotels where hotel_id = '" + Integer.toString(hotel_id) + "'";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteFacilities(Integer hotel_id) {
		try {
			String query = "delete from HotelInfo where hotel_id = '" + Integer.toString(hotel_id) + "'";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteLocation(Integer hotel_id) {
		try {
			String query = "delete from Locations where hotel_id = '" + Integer.toString(hotel_id) + "'";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateHotel(Integer hotel_id, String name, Integer rating, String img, String status, String number, Integer account_id) {
		try {
			String query = "update Hotels set name = ?, rating = ?, img = ?, status = ?, number = ?, account_id = ? where hotel_id = ?";
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
			String query = "update Locations set city = ?, addrress = ? where hotel_id = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, city);
			stmt.setString(2, address);
			stmt.setInt(3, hotel_id);
			stmt.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Integer> getFilteredHotels(Integer rating, Boolean beachfront, Boolean woodfront, Boolean wifi, Boolean parking) {
		List<Integer> hotels = new ArrayList<Integer>();
		try {
			String query = "select hotel_id from Hotels h join HotelInfo hi on h.hotel_id = hi.hotel_id where h.rating = ?";
			query += " and hi.beachfront = ? and hi.woodfront = ? and hi.wifi = ? and hi.parking = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, rating);
			stmt.setBoolean(2, beachfront);
			stmt.setBoolean(3, woodfront);
			stmt.setBoolean(4, wifi);
			stmt.setBoolean(5, parking);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				hotels.add(rs.getInt("hotel_id"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return hotels;
	}
	

	public List<Integer> getSearchedHotels(String city, String address) {
		List<Integer> hotels = new ArrayList<Integer>();
		try {
			String query = "select hotel_id from Hotels h join Locations l on h.hotel_id = l.hotel_id";
			if(city != null) query += " where l.city = ?";
			if(address != null) query += " and l.address = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			if(city != null) stmt.setString(1, city);
			if(address != null) stmt.setString(2, address);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				hotels.add(rs.getInt("hotel_id"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return hotels;
	}

}
