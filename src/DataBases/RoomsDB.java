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

import Models.Facilities;
import Models.Reservation;
import Models.Room;

public class RoomsDB {
	
	static String account = MyDBInfo.MYSQL_USERNAME;
	static String password = MyDBInfo.MYSQL_PASSWORD;
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;
	
	private Connection ConnDB;
	private static RoomsDB dataB;
	
	private RoomsDB() {
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
	
	public static RoomsDB getInstance() {
		if (dataB == null) {
			synchronized (RoomsDB.class) {
				if (dataB == null) {
					dataB = new RoomsDB();
				}
			}
		}
		return dataB;
	}
	/**
	 * Adds a row into the database table with given parameters
	 * @param startDate
	 * @param endDate
	 * @param pricePerDay
	 * @param hottelId
	 * @param numberOfBeds
	 * @param isWifi
	 * @param isTV
	 * @param isHotWater
	 * @param isAirConditiong
	 */
	public Integer addRoom(java.util.Date startDate, java.util.Date endData, Integer pricePerDay,  String img,Integer hottelId, Integer numberOfBeds, 
					boolean wifi, boolean tv, boolean hotWater, boolean airConditioning) {
		try {
			Statement stmt = ConnDB.createStatement();
			//insert room
			String ins = "insert into rooms(reserved_start, reserved_end, price_per_day, number_of_beds, hotel_id, img) values (?, ?, ?, ?, ?, ?);";
			PreparedStatement quer = ConnDB.prepareStatement(ins);
			//
			java.sql.Date stD = new Date(startDate.getTime());
			quer.setDate(1,  stD);
			java.sql.Date edD = new Date(endData.getTime());
			quer.setDate(2, edD);
			quer.setInt(3, pricePerDay);
			quer.setInt(4, numberOfBeds);
			quer.setInt(5, hottelId);
			quer.setString(6, img);
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
	 * @param room ID
	 * @param start Date
	 * @param end Date
	 * @param price per Day
	 * @param Hotel Id
	 * @param number Of Beds
	 * @param is Wifi
	 * @param is TV
	 * @param is hot water
	 * @param is Air Conditioning
	 */
	public boolean updateRoom (Integer room_id, java.util.Date sDate, java.util.Date eData, Integer pricePerDay, String img, Integer hotelId, Integer numberOfBeds, boolean wifi, boolean tv,
			boolean hotWater, boolean airConditioning) {
		try {
			Statement stmt = ConnDB.createStatement();
			//update room
			String ins = "update rooms set reserved_start = ?, reserved_end = ?, price_per_day=?, number_of_beds = ?, hotel_id = ?, img = ? where room_id = ?;";
			PreparedStatement quer = ConnDB.prepareStatement(ins);
			//
			java.sql.Date stD = new Date(sDate.getTime());
			quer.setDate(1,  stD);
			java.sql.Date edD = new Date(eData.getTime());
			quer.setDate(2, edD);
			quer.setInt(3, pricePerDay);
			quer.setInt(4, numberOfBeds);
			quer.setInt(5, hotelId);
			quer.setString(6, img);
			quer.setInt(7, room_id);
			quer.executeUpdate();
			//insert room info
			String ins3 = "update roominfo set wifi = ?, tv = ?, hot_water = ?, air_conditioning = ? where room_id = ?;";
			PreparedStatement quer3 = ConnDB.prepareStatement(ins3);
			quer3.setBoolean(1, wifi);
			quer3.setBoolean(2, tv);
			quer3.setBoolean(3, hotWater);
			quer3.setBoolean(4, airConditioning);
			quer3.setInt(5, room_id);
			quer3.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void deleteRooms(Integer hotel_id) {
		try {
			for(Room r : this.getRoomByHottel(hotel_id)) {
				String query = "delete from Reservation where room_id = ?";
				PreparedStatement stmt = ConnDB.prepareStatement(query);
				stmt.setInt(1, r.getRoomId());
				stmt.executeUpdate();

				query = "delete from RoomInfo where room_id = ?";
				stmt = ConnDB.prepareStatement(query);
				stmt.setInt(1, r.getRoomId());
				stmt.executeUpdate();
			}
			
			String query = "delete from Rooms where hotel_id = ?";
			PreparedStatement stmt = ConnDB.prepareStatement(query);
			stmt.setInt(1, hotel_id);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean deleteRoom(int roomId) {
		try {
			Statement stmt = ConnDB.createStatement();
			//delete from resrvatons
			String del1 = "delete from reservation where room_id = ?";
			PreparedStatement quer1 = ConnDB.prepareStatement(del1);
			quer1.setInt(1, roomId);
			quer1.executeUpdate();
			//delete from roominfo
			String del2 = "delete from roominfo where room_id = ?";
			PreparedStatement quer2 = ConnDB.prepareStatement(del2);
			quer2.setInt(1, roomId);
			quer2.executeUpdate();
			//delete from rooms
			String del3 = "delete from rooms where room_id = ?";
			PreparedStatement quer3 = ConnDB.prepareStatement(del3);
			quer3.setInt(1, roomId);
			quer3.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
				Room rm = new Room(id, res1.getDate("reserved_start"), res1.getDate("reserved_end"), res1.getInt("price_per_day"), res1.getString("img"), res1.getInt("hotel_id"),res1.getInt("number_of_beds"), 
						res2.getBoolean("wifi"), res2.getBoolean("tv"), res2.getBoolean("hot_water"), res2.getBoolean("air_conditioning"));
				return rm;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Room> getRoomByHottel(int hottelId) {
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
				Room rm = new Room(res1.getInt("room_id"), res1.getDate("reserved_start"), res1.getDate("reserved_end"), res1.getInt("price_per_day"), res1.getString("img"), res1.getInt("hotel_id"),res1.getInt("number_of_beds"), 
						res2.getBoolean("wifi"), res2.getBoolean("tv"), res2.getBoolean("hot_water"), res2.getBoolean("air_conditioning"));
				rooms.add(rm);
			}
			return rooms;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<List<java.util.Date>> getRoomReservations(int roomId){
		List<List<java.util.Date>> reservations = new ArrayList<List<java.util.Date>>();
		try {
			Statement stmt = ConnDB.createStatement();
			//
			String ins = "select * from reservation where room_id = ?;";
			PreparedStatement quer = ConnDB.prepareStatement(ins);
			quer.setInt(1, roomId);
			ResultSet res = quer.executeQuery();
			while(res.next()) {
				List<java.util.Date> datas = new ArrayList<java.util.Date>();
				datas.add(res.getTimestamp("reserved_from"));
				datas.add(res.getTimestamp("reserved_to"));
				reservations.add(datas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return reservations;	
	}
	
	public boolean bookRoom(Integer room_id, java.util.Date sDate, java.util.Date eDate, Integer accId) {
		try {
			Statement stmt = ConnDB.createStatement();
			//
			String ins = "insert into reservation (reserved_from, reserved_to, room_id, account_id) values (?, ?, ?, ?);";
			PreparedStatement quer = ConnDB.prepareStatement(ins);
			java.sql.Date sDt = new Date(sDate.getTime());
			quer.setDate(1, sDt);
			java.sql.Date eDt = new Date(eDate.getTime());
			quer.setDate(2, eDt);
			quer.setInt(3, room_id);
			quer.setInt(4, accId);
			quer.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean unbookRoom(Integer room_id, java.util.Date sDate, java.util.Date eDate, Integer accId) {
		try {
			String del = "delete from reservation where room_id = ? and reserved_from = ? and reserved_to = ? and account_id = ?";
			PreparedStatement quer = ConnDB.prepareStatement(del);
			java.sql.Date sDt = new Date(sDate.getTime());
			quer.setDate(2, sDt);
			java.sql.Date eDt = new Date(eDate.getTime());
			quer.setDate(3, eDt);
			quer.setInt(1, room_id);
			quer.setInt(4, accId);
			quer.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean unbookRoom(int reserved_id) {
		try {
			String del = "delete from reservation where reserved_id = ?";
			PreparedStatement quer = ConnDB.prepareStatement(del);
			quer.setInt(1, reserved_id);
			quer.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Reservation getReservation(Integer reserved_id) {
		try {
			String query = "select * from Reservation where reserved_id = ?";
			PreparedStatement stmt = ConnDB.prepareStatement(query);
			stmt.setInt(1, reserved_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Reservation rsr = new Reservation(reserved_id,
						rs.getDate("reserved_from"),
						rs.getDate("reserved_to"),
						rs.getInt("room_id"),
						rs.getInt("account_id"));
				return rsr;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
