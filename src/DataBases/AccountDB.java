package DataBases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Models.Account;
import Models.Account;

public class AccountDB{
	
	static String account = MyDBInfo.MYSQL_USERNAME;
	static String password = MyDBInfo.MYSQL_PASSWORD;
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;
	
	private Connection connection;
	private Statement statement;
	
	/**
	 * Constructor. Connects to MySQL and uses the given database name.
	 */
	public AccountDB(){
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://"
					+ server + "?useSSL=false", account, password);

			statement = connection.createStatement();
			statement.executeQuery("USE " + database);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean add(String firstName, String lastName, String email, String username, String password, String birthdate){
		try {
			statement.executeUpdate(getAddingString(firstName, lastName, email, username, password, birthdate));
			return true;
		} catch (SQLException e) {
			return false;
		}
		
	}
	
	private String getAddingString(String firstName, String lastName, String email, String username, String password, String birthDate) {
		firstName = check(firstName);
		lastName = check(lastName);
		email = check(email);
		username = check(username);
		password = check(password);
		birthDate = check(birthDate);
		String result = "insert into accounts(first_name, last_name, email, username, password, birth_date) values ("; 
		result += firstName + ", " + lastName + ", " + email + ", " + username + ", " + password + ", " + birthDate + ");";
		return result;
	} 
	
	private String check(String str) {
		String s = str;
		if(s != null) {
			for(int i = 0; i<str.length(); i++) {
				if(str.charAt(i) == '\'') {
					s = str.substring(0, i);
					s += "\\";
					s += str.substring(i);
				}
			}
			s = "\'" + s + "\'";
		}else {
			s = "null";
		}
		return s;
	}

	public boolean contains(String email) {
		try {
			ResultSet result = statement.executeQuery("select count(email) as num from users where email = " + email + ";");
			if(result.getInt("num") > 0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	
	public Account getAccount(String email) {
		try {
			ResultSet result = statement.executeQuery("select * from accounts where email = '" + email + "';");
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
			return null;
		}
	}

	public boolean updateAccount(Account account, String field, String newArg) {
		try {
			statement.executeUpdate(getUpdateString(account, field, newArg));
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	private String getUpdateString(Account account, String field, String newArg) {
		String result;
		if(newArg != null)
			result = "update accounts set " + field + " = '" + newArg + "' where email = '" + account.getEmail() + "';";
		else 
			result = "update accounts set " + field + " = null where email = '" + account.getEmail() + "';";
		return result;
	}

	public boolean deleteAccount(String email, String password) {
		try {
			statement.executeUpdate(getDeletingString(email, password));
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	private String getDeletingString(String email, String password) {
		String result = "delete from accounts where ";
		result += "email = " + check(email) + " and ";
		result += "password = " + check(password) + ";";
		return result;
	}
		
}















