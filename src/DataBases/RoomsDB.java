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

import javax.xml.namespace.QName;

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
	
	public static boolean addRoom(java.util.Date startDate, java.util.Date endData, int hottelId, int numberOfBeds, 
					boolean wifi, boolean tv, boolean hotWater, boolean airConditioning) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			//insert room
			String ins = "insert into rooms(reserved_start, reserved_end, number_of_beds, hotel_id) values (?, ?, ?, ?);";
			PreparedStatement quer = con.prepareStatement(ins, stmt.RETURN_GENERATED_KEYS);
			//
			java.sql.Date stD = new Date(startDate.getTime());
			quer.setDate(1,  stD);
			java.sql.Date edD = new Date(endData.getTime());
			quer.setDate(2, edD);
			quer.setInt(3, numberOfBeds);
			quer.setInt(4, hottelId);
			quer.executeUpdate();
			//get last room id
			PreparedStatement quer2 = con.prepareStatement("SELECT  max(room_id)  from rooms;");
			ResultSet res = quer2.executeQuery();
			res.next();
			int roomId = res.getInt("max(room_id)");
			//insert room info
			String ins3 = "insert into roominfo (wifi, tv, hot_water, air_conditioning, room_id) values (?, ?, ?, ?, ?);";
			PreparedStatement quer3 = con.prepareStatement(ins3);
			quer3.setBoolean(1, wifi);
			quer3.setBoolean(2, tv);
			quer3.setBoolean(3, hotWater);
			quer3.setBoolean(4, airConditioning);
			quer3.setInt(5, roomId);
			quer3.executeUpdate();
			//
			System.out.println(roomId);
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void deleteRoom(int roomId) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			//delete from roominfo
			String del1 = "delete from roominfo where room_id = ?";
			PreparedStatement quer1 = con.prepareStatement(del1);
			quer1.setInt(1, roomId);
			quer1.executeUpdate();
			//delete from rooms
			String del2 = "delete from rooms where room_id = ?";
			PreparedStatement quer2 = con.prepareStatement(del2);
			quer2.setInt(1, roomId);
			quer2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				Room rm = new Room(res.getDate("reserved_start"), res.getDate("reserved_end"), res.getInt("hotel_id"),res.getInt("number_of_beds"), 
						false, false, false ,false);
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
			List<Room> rooms = new ArrayList<Room>();
			while(res.next()) {
				Room rm = new Room(res.getDate("reserved_start"), res.getDate("reserved_end"), res.getInt("hotel_id"),res.getInt("number_of_beds"), 
						false, false, false ,false);
				rooms.add(rm);
			}
			con.close();
			return rooms;
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
