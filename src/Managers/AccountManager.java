package Managers;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Models.Account;
import DataBases.AccountDB;

public class AccountManager {
	
	private static String ALGORITHM = "SHA-512";
	private AccountDB database;
	
	public AccountManager() {
		database = new AccountDB();
	}
	
	public boolean createAccount(String firstName, String lastName, String email, String username, String password, String birthDate) {
		if(password != null)
			password = getHash(password);
		return database.add(firstName, lastName, email, username, password, birthDate);
	}
	
	public boolean loginAccount(String email, String password) {
		Account user = getAccount(email);
		if(user != null) {
			password = getHash(password);
			if(password.equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	private String getHash(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		byte[] passwwordByte = password.getBytes();
		byte[] hashByte = md.digest(passwwordByte);
		return hexToString(hashByte);
	}
	
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
	
	public Account getAccount(String email) {
		return database.getAccount(email);
	}
	
	public boolean updateAccount(Account account, String field, String newArg) {
		return database.updateAccount(account, field, newArg);
	}
	
	public boolean deleteAccount(String email, String password) {
		if(password != null)
			password = getHash(password);
		return database.deleteAccount(email, password);
	}
	
	public static void main(String[] args) {
		System.out.println("hi");
	}
}
