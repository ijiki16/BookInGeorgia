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
	
	private static void updateBase(){
			try{
				Connection con = getConnection();
				String query = "select * from Hotels";
				PreparedStatement stmt = con.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					String id = rs.getString("hotel_id");
					String name = rs.getString("name");
					String rank = rs.getString("rating");
					String img = rs.getString("img");
					String status = rs.getString("status");
					String number = rs.getString("phone_number");
					String acc_id = rs.getString("account_id");
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}		
	}
	
	public Integer addHotel(String name, Integer rating, String img, String status, String number, Integer account_id) {
		try {
			Connection con = getConnection();
			String query = "insert into Hotels (name, rating, img, status, phone_number, account_id) values (?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setInt(2, rating);
			stmt.setString(3, img);
			stmt.setString(4, status);
			stmt.setString(5, number);
			stmt.setInt(6, account_id);
			stmt.execute();
			query = "select max(hotel_id) max_id from Hotels";
			ResultSet rs = stmt.executeQuery(query);	
			if(rs.next()) return rs.getInt("max_id");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getHotelById(String hotel_id) {
		List<String> row = new ArrayList<String>();
		if(hotel_id == null) return row;
		try {
			Connection con = getConnection();
			String query = "select * from Hotels where hotel_id = '" + hotel_id + "'";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				row.add(rs.getString("name"));
				row.add(rs.getString("rating"));
				row.add(rs.getString("img"));
				row.add(rs.getString("status"));
				row.add(rs.getString("phone_number"));
				row.add(rs.getString("account_id"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	public void addFacilities(Integer hotel_id, String facility, boolean wifi, boolean parking, boolean beachfront, boolean woodfront) {
		try {
			Connection con = getConnection();
			String query = "insert into HotelInfo (wifi, parking, beachfront, woodfront, facility, hotel_id) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setBoolean(1, wifi);
			stmt.setBoolean(2, parking);
			stmt.setBoolean(3, beachfront);
			stmt.setBoolean(4, woodfront);
			stmt.setString(5, facility);
			stmt.setInt(6, hotel_id);
			stmt.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void deleteHotel(Integer hotel_id) {
		try {
			Connection con = getConnection();
			String query = "delete from HotelInfo where hotel_id = '" + Integer.toString(hotel_id) + "'";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.execute();
			
			query = "delete from Hotels where hotel_id = '" + hotel_id + "'";
			stmt = con.prepareStatement(query);
			stmt.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteFacilities(Integer hotel_id) {
		try {
			Connection con = getConnection();
			String query = "delete from HotelInfo where hotel_id = '" + Integer.toString(hotel_id) + "'";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.execute();
			
			query = "delete from HotelInfo where hotel_id = '" + hotel_id + "'";
			stmt = con.prepareStatement(query);
			stmt.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
