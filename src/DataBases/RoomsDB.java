package DataBases;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomsDB {
	
	static String account = MyDBInfo.MYSQL_USERNAME;
	static String password = MyDBInfo.MYSQL_PASSWORD;
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;
	
	
	private static RoomsDB dataB;
	
	public RoomsDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+ server, account, password);
			Statement stmt = conn.createStatement();
			stmt.executeQuery("USE " + database);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}			
	}
	
	public static RoomsDB getInstance(){
		if(dataB == null){
			synchronized (HotelsDB.class){
                if (dataB == null)dataB = new RoomsDB();
            }
		}
		updateBase();
		return dataB;
	}
	
	private static void updateBase() {
		try {
			Connection con = getConnection();
			String query = "select * from Rooms";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet res = stmt.executeQuery(query);
			while (res.next()) {
				String id = res.getString("room_id");
				String startDate = res.getString("reserved_start");
				String endData = res.getString("reserved_end");
				String hotelId = res.getString("hotel_id");
				System.out.print(id+") ["+startDate+":"+endData+"] hotel: "+hotelId);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addRoom(/*Date startDate, Date endData,*/ int hottelId) {
		try {
			Connection con = getConnection();
			//
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			//
			String ins = "insert into rooms(reserved_start, reserved_end, hotel_id) values (?, ?, ?);";
			PreparedStatement quer = con.prepareStatement(ins, stmt.RETURN_GENERATED_KEYS);
			quer.setDate(1,  null);
			quer.setDate(2, null);
			quer.setInt(3, hottelId);
			quer.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection("jdbc:mysql://"+ server, account, password);
		} catch (SQLException e) {
			e.printStackTrace();
		};
		return null;
		
	}


}
