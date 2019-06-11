package DataBases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

//by Deavi
public class HotelsDB {
	private static HotelsDB db;

	static String account = MyDBInfo.MYSQL_USERNAME;
	static String password = MyDBInfo.MYSQL_PASSWORD;
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;

	private HotelsDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://" + server, account, password);

			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			con.close();
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
		updateBase();
		return db;
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://" + server, account, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		;
		return null;

	}

	private static void updateBase() {
		try {
			Connection con = getConnection();
			String query = "select * from Hotels";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String id = rs.getString("hotel_id");
				String name = rs.getString("name");
				String rank = rs.getString("rating");
				String img = rs.getString("img");
				String status = rs.getString("status");
				String number = rs.getString("phone_number");
				String acc_id = rs.getString("account_id");
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addHotel(String name, String rating, String img, String status, String number, String acc_id) {
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement("select max(hotel_id) from Hotels");
			ResultSet rs = stmt.executeQuery("select max(hotel_id) from Hotels");
			String id = "none";
			if (rs.next())
				id = rs.getString("hotel_id");
			String query = "insert into Hotels (hotel_id, name, rating, img, status, phone_number, account_id) values (";
			query += id + name + "," + rating + "," + img + "," + status + "," + number + "," + acc_id + ")";
			stmt = con.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
