package DataBases;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Room;

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
	
	public static boolean addRoom(java.util.Date startDate, java.util.Date endData, int hottelId) {
		try {
			Connection con = getConnection();
			//
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			//
			String ins = "insert into rooms(reserved_start, reserved_end, hotel_id) values (?, ?, ?);";
			PreparedStatement quer = con.prepareStatement(ins, stmt.RETURN_GENERATED_KEYS);
			//
			java.sql.Date stD = new Date(startDate.getTime());
			quer.setDate(1,  stD);
			java.sql.Date edD = new Date(endData.getTime());
			quer.setDate(2, edD);
			quer.setInt(3, hottelId);
			quer.executeUpdate();
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Room getRoom(int id) {
		Connection con = getConnection();
		//
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			//
			String ins = "select * from rooms where room_id = ?;";
			PreparedStatement quer = con.prepareStatement(ins, stmt.RETURN_GENERATED_KEYS);
			quer.setInt(1, id);
			ResultSet res = quer.executeQuery();
			if(!res.next()) {
				con.close();
				return null;
			}else {
				Room rm = new Room(res.getDate("reserved_start"), res.getDate("reserved_end"), res.getInt("hotel_id"));
				con.close();
				return rm;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Room> getRoomByHottel(int hottelId) {
		Connection con = getConnection();
		//
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			//
			String ins = "select * from rooms where hotel_id = ?;";
			PreparedStatement quer = con.prepareStatement(ins, stmt.RETURN_GENERATED_KEYS);
			quer.setInt(1, hottelId);
			ResultSet res = quer.executeQuery();
			if(!res.next()) {
				con.close();
				return null;
			}else {
				List<Room> rooms = new ArrayList<Room>();
				Room rm = new Room(res.getDate("reserved_start"), res.getDate("reserved_end"), res.getInt("hotel_id"));
				rooms.add(rm);
				while(res.next()) {
					Room curRoom = new Room(res.getDate("reserved_start"), res.getDate("reserved_end"), res.getInt("hotel_id"));
					rooms.add(curRoom);
				}
				con.close();
				return rooms;
				//return rm;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
