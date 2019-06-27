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
	
	private Connection ConnDB;
	
	public RoomsDB() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				ConnDB = DriverManager.getConnection("jdbc:mysql://"+ server, account, password);
				Statement stmt = ConnDB.createStatement();
				stmt.executeQuery("USE " + database);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Adds a row into the database table with given parameters
	 * @param startDate
	 * @param endDate
	 * @param hottelId
	 * @param numberOfBeds
	 * @param isWifi
	 * @param isTV
	 * @param isHotWater
	 * @param isAirConditiong
	 */
	public int addRoom(java.util.Date startDate, java.util.Date endData, int hottelId, int numberOfBeds, 
					boolean wifi, boolean tv, boolean hotWater, boolean airConditioning) {
		try {
			Statement stmt = ConnDB.createStatement();
			//insert room
			String ins = "insert into rooms(reserved_start, reserved_end, number_of_beds, hotel_id) values (?, ?, ?, ?);";
			PreparedStatement quer = ConnDB.prepareStatement(ins, stmt.RETURN_GENERATED_KEYS);
			//
			java.sql.Date stD = new Date(startDate.getTime());
			quer.setDate(1,  stD);
			java.sql.Date edD = new Date(endData.getTime());
			quer.setDate(2, edD);
			quer.setInt(3, numberOfBeds);
			quer.setInt(4, hottelId);
			quer.executeUpdate();
			//get last room id
			PreparedStatement quer2 = ConnDB.prepareStatement("SELECT  max(room_id)  from rooms;");
			ResultSet res = quer2.executeQuery();
			res.next();
			int roomId = res.getInt("max(room_id)");
			//insert room info
			String ins3 = "insert into roominfo (wifi, tv, hot_water, air_conditioning, room_id) values (?, ?, ?, ?, ?);";
			PreparedStatement quer3 = ConnDB.prepareStatement(ins3);
			quer3.setBoolean(1, wifi);
			quer3.setBoolean(2, tv);
			quer3.setBoolean(3, hotWater);
			quer3.setBoolean(4, airConditioning);
			quer3.setInt(5, roomId);
			quer3.executeUpdate();
			//
			//System.out.println(roomId);
			return roomId;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * Adds a row into the database table with given parameters
	 * @param newRoom
	 */
	public int addRoom(Room newRoom) {
		try {
			Statement stmt = ConnDB.createStatement();
			//insert room
			String ins = "insert into rooms(reserved_start, reserved_end, number_of_beds, hotel_id) values (?, ?, ?, ?);";
			PreparedStatement quer = ConnDB.prepareStatement(ins);
			//
			java.sql.Date stD = new Date(newRoom.getStartDate().getTime());
			quer.setDate(1,  stD);
			java.sql.Date edD = new Date(newRoom.getEndDate().getTime());
			quer.setDate(2, edD);
			quer.setInt(3, newRoom.getNumberOfBeds());
			quer.setInt(4, newRoom.getHottelId());
			quer.executeUpdate();
			//get last room id
			PreparedStatement quer2 = ConnDB.prepareStatement("SELECT  max(room_id)  from rooms;");
			ResultSet res = quer2.executeQuery();
			res.next();
			int roomId = res.getInt("max(room_id)");
			//insert room info
			String ins3 = "insert into roominfo (wifi, tv, hot_water, air_conditioning, room_id) values (?, ?, ?, ?, ?);";
			PreparedStatement quer3 = ConnDB.prepareStatement(ins3);
			quer3.setBoolean(1, newRoom.isWifi());
			quer3.setBoolean(2, newRoom.isTv());
			quer3.setBoolean(3, newRoom.isHotWater());
			quer3.setBoolean(4, newRoom.isAirConditioning());
			quer3.setInt(5, roomId);
			quer3.executeUpdate();
			//
			//System.out.println(roomId);
			return roomId;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	/**
	 * Adds a row into the database table with given parameters
	 * @param newRoom
	 */
	public int updateRoom (Room newRoom) {
		try {
			Statement stmt = ConnDB.createStatement();
			//insert room
			String ins = "update rooms set reserved_start = ?, reserved_end = ?, number_of_beds = ?, hotel_id = ? where room_id = ?;";
			PreparedStatement quer = ConnDB.prepareStatement(ins);
			//
			java.sql.Date stD = new Date(newRoom.getStartDate().getTime());
			quer.setDate(1,  stD);
			java.sql.Date edD = new Date(newRoom.getEndDate().getTime());
			quer.setDate(2, edD);
			quer.setInt(3, newRoom.getNumberOfBeds());
			quer.setInt(4, newRoom.getHottelId());
			quer.setInt(5, newRoom.getRoomId());
			quer.executeUpdate();
			//get last room id
			PreparedStatement quer2 = ConnDB.prepareStatement("SELECT  max(room_id)  from rooms;");
			ResultSet res = quer2.executeQuery();
			res.next();
			int roomId = res.getInt("max(room_id)");
			//insert room info
			String ins3 = "insert into roominfo (wifi, tv, hot_water, air_conditioning, room_id) values (?, ?, ?, ?, ?);";
			PreparedStatement quer3 = ConnDB.prepareStatement(ins3);
			quer3.setBoolean(1, newRoom.isWifi());
			quer3.setBoolean(2, newRoom.isTv());
			quer3.setBoolean(3, newRoom.isHotWater());
			quer3.setBoolean(4, newRoom.isAirConditioning());
			quer3.setInt(5, roomId);
			quer3.executeUpdate();
			//
			//System.out.println(roomId);
			return roomId;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	public void deleteRoom(int roomId) {
		try {
			Statement stmt = ConnDB.createStatement();
			//delete from roominfo
			String del1 = "delete from roominfo where room_id = ?";
			PreparedStatement quer1 = ConnDB.prepareStatement(del1);
			quer1.setInt(1, roomId);
			quer1.executeUpdate();
			//delete from rooms
			String del2 = "delete from rooms where room_id = ?";
			PreparedStatement quer2 = ConnDB.prepareStatement(del2);
			quer2.setInt(1, roomId);
			quer2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
		
	public Room getRoom(int id) {
		try {
			Statement stmt = ConnDB.createStatement();
			//
			String ins1 = "select * from rooms where room_id = ?;";
			PreparedStatement quer1 = ConnDB.prepareStatement(ins1);
			quer1.setInt(1, id);
			String ins2 = "select * from roominfo where room_id = ?;";
			PreparedStatement quer2 = ConnDB.prepareStatement(ins2);
			quer2.setInt(1, id);
			ResultSet res1 = quer1.executeQuery();
			ResultSet res2 = quer2.executeQuery();
			if(!res1.next() || !res2.next()) {
				return null;
			}else {
				Room rm = new Room(id, res1.getDate("reserved_start"), res1.getDate("reserved_end"), res1.getInt("hotel_id"),res1.getInt("number_of_beds"), 
						res2.getBoolean("wifi"), res2.getBoolean("tv"), res2.getBoolean("hot_water"), res2.getBoolean("air_conditioning"));
				return rm;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public  List<Room> getRoomByHottel(int hottelId) {
		try {
			Statement stmt = ConnDB.createStatement();
			//
			String ins1 = "select * from rooms where hotel_id = ?;";
			PreparedStatement quer1 = ConnDB.prepareStatement(ins1);
			quer1.setInt(1, hottelId);
			ResultSet res1 = quer1.executeQuery();
			List<Room> rooms = new ArrayList<Room>();
			while(res1.next()) {
				int roomId = res1.getInt("room_id");
				String ins2 = "select * from roominfo where room_id = ?;";
				PreparedStatement quer2 = ConnDB.prepareStatement(ins2);
				quer2.setInt(1, roomId);
				ResultSet res2 = quer2.executeQuery();
				if(!res2.next()) break;
				Room rm = new Room(res1.getInt("room_id"), res1.getDate("reserved_start"), res1.getDate("reserved_end"), res1.getInt("hotel_id"),res1.getInt("number_of_beds"), 
						res2.getBoolean("wifi"), res2.getBoolean("tv"), res2.getBoolean("hot_water"), res2.getBoolean("air_conditioning"));
				rooms.add(rm);
			}
			return rooms;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


}
