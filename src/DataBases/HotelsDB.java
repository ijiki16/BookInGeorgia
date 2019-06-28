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
				Facilities facility = getFacilities(hotel);
				if(facility != null) hotel.setFacilities(facility);
				return hotel;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Facilities getFacilities(Hotel hotel) {
		try {
			String query = "select * from HotelInfo where hotel_id = '" + Integer.toString(hotel.getHotelId()) + "'";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				Facilities facil = new Facilities(hotel.getHotelId(),
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
	

	public void addHotel(String name, Integer rating, String img, String status, String number, Integer account_id) {
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
	
	public void deleteHotel(Integer hotel_id) {
		try {
			this.deleteFacilities(hotel_id);
			
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

}
