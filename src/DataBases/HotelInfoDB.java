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
public class HotelInfoDB {
	private static HotelInfoDB db;
	
	static String account = MyDBInfo.MYSQL_USERNAME;
	static String password = MyDBInfo.MYSQL_PASSWORD;
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;

	
	private HotelInfoDB() {
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
	
	public static HotelInfoDB getInstance(){
		if(db == null){
			synchronized (HotelsDB.class){
                if (db == null){
                    db = new HotelInfoDB();
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
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
					
				}
				
				con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}		
	}
	
	
}
