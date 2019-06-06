package DataBases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Models.Account;

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
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://"
					+ server + "?useSSL=false", account, password);

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
			return false;
		}
		
	}
	
	//Returns SQL statement for adding row.
	private String getAddingString() {
		String result = "insert into accounts(first_name, last_name, email, username, password, birth_date) values (";
		result += "?, ?, ?, ?, ?, ?);";
		return result;
	}
	
	/**
	 * @param email
	 * @return True if row with given email exists, false otherwise.
	 */
	public boolean contains(String email) {
		try {
			PreparedStatement p = connection.prepareStatement("select count(email) as num from accounts where email = ?;");
			p.setString(1, email);
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
			PreparedStatement p = connection.prepareStatement("select * from accounts where email = ?;");
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
					result.getString("birth_date"));
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
			PreparedStatement p = connection.prepareStatement("update accounts set " + field + " = ? where email = ?;");
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
			if(!contains(email)) {
				return false;
			}else {
				if(!getAccount(email).getPassword().equals(password))
					return false;
			}
			PreparedStatement p = connection.prepareStatement("delete from accounts where email = ? and password = ?;");
			p.setString(1, email);
			p.setString(2, password);
			p.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
		
}















