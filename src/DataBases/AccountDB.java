package DataBases;

import Models.Account;
import Models.Reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDB{
	
	static String account = MyDBInfo.MYSQL_USERNAME;
	static String password = MyDBInfo.MYSQL_PASSWORD;
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;
	
	private Connection connection;
	
	/**
	 * Constructor. Connects to MySQL and uses the given database name.
	 */
	public AccountDB(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://"
					+ server, account, password);

			Statement statement = connection.createStatement();
			statement.executeQuery("USE " + database);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a row into the database table with given parameters
	 * @param firstName No constraints.
	 * @param lastName No constraints.
	 * @param email Must be unique and not null.
	 * @param username Must be unique and not null.
	 * @param password Must not be null.
	 * @param birthdate No constraints.
	 * @return True if adding row was a success, false otherwise.
	 */
	public boolean add(String firstName, String lastName, String email, String username, String password, String birthdate){
		try {
			if(contains(email, username)) return false;
			PreparedStatement p = connection.prepareStatement(getAddingString());
			p.setString(1, firstName);
			p.setString(2, lastName);
			p.setString(3, email);
			p.setString(4, username);
			p.setString(5, password);
			p.setString(6, birthdate);
			p.executeUpdate();
			//statement.executeUpdate(getAddingString(firstName, lastName, email, username, password, birthdate));
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//Returns SQL statement for adding row.
	private String getAddingString() {
		String result = "insert into Accounts(first_name, last_name, email, username, password, birth_date) values (";
		result += "?, ?, ?, ?, ?, ?);";
		return result;
	}
	
	/**
	 * @param email
	 * @return True if row with given email exists, false otherwise.
	 */
	public boolean contains(String email, String username) {
		try {
			PreparedStatement p = connection.prepareStatement("select count(*) as num from Accounts where email = ? or username = ?;");
			p.setString(1, email);
			p.setString(2, username);
			ResultSet result = p.executeQuery();
			result.next();
			if(result.getInt("num") > 0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @param email
	 * @return Account object, null if the corresponding row wasn't found in database.
	 */
	public Account getAccount(String email) {
		try {
			if(email == null) return null;
			PreparedStatement p = connection.prepareStatement("select * from Accounts where email = ?;");
			p.setString(1, email);
			ResultSet result = p.executeQuery();
			if(!result.next()) {
				return null;
			}else {
				Account user = new Account(result.getString("first_name"),
					result.getString("last_name"),
					result.getString("email"),
					result.getString("username"),
					result.getString("password"),
					result.getString("birth_date"),
					result.getString("account_id"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param account Account Object.
	 * @param field The column that is updated (first_name, last_name, username, email, password, birth_date).
	 * @param newArg Replacing argument.
	 * @return True if update was a success, false otherwise.
	 */
	public boolean updateAccount(Account account, String field, String newArg) {
		try {
			if((field.equals("username") && contains("", newArg)) || (field.equals("email") && contains(newArg, ""))) return false;
			PreparedStatement p = connection.prepareStatement("update Accounts set " + field + " = ? where email = ?;");
			p.setString(1, newArg);
			p.setString(2, account.getEmail());
			p.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Deletes corresponding row from the database table.
	 * @param email
	 * @param password
	 * @return True if deletion was a success, false otherwise.
	 */
	public boolean deleteAccount(String email, String password) {
		try {
			if(getAccount(email) == null) {
				return false;
			}else {
				if(!getAccount(email).getPassword().equals(password))
					return false;
			}
			PreparedStatement p = connection.prepareStatement("delete from Accounts where email = ? and password = ?;");
			p.setString(1, email);
			p.setString(2, password);
			p.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Gets the searched reservations.
	 *
	 * @param account id
	 * @return the searched reservations
	 */
	public List<Reservation> getAccountReservations(int account_id) {
		List<Reservation> reservations = new ArrayList<>();
		try {
			String query = "select * from Reservation where account_id = ?";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, account_id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				reservations.add(new Reservation(rs.getInt("reserved_id"), rs.getDate("reserved_from"), rs.getDate("reserved_to"), rs.getInt("room_id"), rs.getInt("account_id")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return reservations;
	}
}















