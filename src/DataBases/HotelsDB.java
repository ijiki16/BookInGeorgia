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

	
	private HotelsDB() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
	
				Connection con = DriverManager.getConnection("jdbc:mysql://"
						+ server, account, password);
	
				Statement stmt = con.createStatement();
				stmt.executeQuery("USE " + database);
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}			
	}
	
	public static HotelsDB getInstance(){
		if(db == null){
			synchronized (HotelsDB.class){
                if (db == null){
                    db = new HotelsDB();
                }
            }
		}
		updateBase();
		return db;
	}
	
	
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection("jdbc:mysql://"
					+ server, account, password);
		} catch (SQLException e) {
			e.printStackTrace();
		};
		return null;
		
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
				
				con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}		
	}
	
	public String addHotel(String name, String rating, String img, String status, String number, String acc_id) {
		try {
			Connection con = getConnection();
			String query = "insert into Hotels (name, rating, img, status, phone_number, account_id) values (";
			query += name + "," + rating + "," + img + "," + status + "," + number + "," + acc_id  + ")";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.execute();
			
			query = "select max(hotel_id) max_id from Hotels";
			stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			con.close();
					
			if(rs.next()) return rs.getString("max_id");
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
			con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	public void addFecilities(String hotel_id, String facility, boolean wifi, boolean parking, boolean beachfront, boolean woodfront) {
		try {
			Connection con = getConnection();
			String query = "insert into Hotels (wifi, parking, beachfront, woodfront, facility, hotel_id) values (";
			query += wifi + "," + parking + "," + beachfront + "," + woodfront + "," + facility + "," + hotel_id  + ")";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.execute();
			con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void deleteHotel(String hotel_id) {
		try {
			Connection con = getConnection();
			String query = "delete from HotelInfo where hotel_id = '" + hotel_id + "'";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.execute();
			
			query = "delete from Hotels where hotel_id = '" + hotel_id + "'";
			stmt = con.prepareStatement(query);
			stmt.execute();
			con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
