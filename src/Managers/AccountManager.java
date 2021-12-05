package Managers;


import DataBases.AccountDB;
import Models.Account;
import Models.Reservation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountManager.
 */
public class AccountManager {
	
	/** The algorithm. */
	private static String ALGORITHM = "SHA-512";
	
	/** The manager. */
	private static AccountManager manager;
	
	/** The database. */
	private AccountDB database;
	
	/**
	 * Instantiates a new account manager.
	 */
	private AccountManager() {
		database = new AccountDB();
	}
	
	/**
	 * Gets the single instance of AccountManager.
	 *
	 * @return single instance of AccountManager
	 */
	public static AccountManager getInstance() {
		if (manager == null) {
			synchronized (AccountManager.class) {
				if (manager == null) {
					manager = new AccountManager();
				}
			}
		}
		return manager;
	}
	
	/**
	 * Creates the account.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email
	 * @param username the username
	 * @param password the password
	 * @param birthDate the birth date
	 * @return true, if successful
	 */
	public boolean createAccount(String firstName, String lastName, String email, String username, String password, String birthDate) {
		if(firstName == null || lastName == null || email == null || username == null || password == null || birthDate == null) return false;
		password = getHash(password);
		return database.add(firstName, lastName, email, username, password, birthDate);
	}
	
	/**
	 * Login account.
	 *
	 * @param email the email
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean loginAccount(String email, String password) {
		Account user = getAccount(email);
		if(user != null && password != null) {
			password = getHash(password);
			if(password.equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the hash.
	 *
	 * @param password the password
	 * @return the hash
	 */
	private String getHash(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] passwwordByte = password.getBytes();
		byte[] hashByte = md.digest(passwwordByte);
		return hexToString(hashByte);
	}
	
	/**
	 * Hex to string.
	 *
	 * @param bytes the bytes
	 * @return the string
	 */
	private String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
	
	/**
	 * Gets the account.
	 *
	 * @param email the email
	 * @return the account
	 */
	public Account getAccount(String email) {
		return database.getAccount(email);
	}
	
	/**
	 * Update account.
	 *
	 * @param account the account
	 * @param field the field
	 * @param newArg the new arg
	 * @return true, if successful
	 */
	public boolean updateAccount(Account account, String field, String newArg) {
		if(account == null || field == null || newArg == null) return false;
		if(field.equals("password")) {
			newArg = getHash(newArg);
		}
		return database.updateAccount(account, field, newArg);
	}
	
	/**
	 * Delete account.
	 *
	 * @param email the email
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean deleteAccount(String email, String password) {
		if(password != null)
			password = getHash(password);
		return database.deleteAccount(email, password);
	}
	
	/**
	 * Gets the reservations.
	 *
	 * @param account_id the account id
	 * @return the reservations
	 */
	public List<Reservation> getReservations(int account_id){
		return database.getAccountReservations(account_id);
	}
}
