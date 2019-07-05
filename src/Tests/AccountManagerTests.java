package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import Managers.AccountManager;

import org.junit.jupiter.api.Order;



@TestMethodOrder(OrderAnnotation.class)
public class AccountManagerTests {
	
	private AccountManager manager;
	
	@Before
	public void setup() {
		manager = AccountManager.getInstance();
	}
	
	@Test
	@Order(1)
	public void test1() {
		assertTrue(manager.createAccount("Nika", "Basiashvili", "nbasi@gmail.com", "nika97", "123456", "1997-12-19"));
		assertFalse(manager.createAccount("Nika", "Basiashvili", null, "nikoloz97", "123456", "1997-12-19"));
		assertFalse(manager.createAccount("Nika", "Basiashvili", "nbasi@gmail.com", "nikoloz97", "123456", "1997-12-19"));
		assertFalse(manager.createAccount("Nika", "Basiashvili", "nbasi16@gmail.com", "nika97", "123456", "1997-12-19"));
		assertFalse(manager.createAccount("Nika", "Basiashvili", "nbasi16@gmail.com", null, "123456", "1997-12-19"));
		assertFalse(manager.createAccount("Nika", "Basiashvili", "nbasi16@gmail.com", "nikolaus97", null, "1997-12-19"));
		assertFalse(manager.createAccount(null, null, "nbasi16@gmail.com", "nikusha97", "paroli", null));
		assertTrue(manager.createAccount("Giorgi", "Kuprava", "gio's_mail@yahoo.com", "Gio's username", "giogio99", "1995-12-19"));
	}
	
	@Test
	@Order(2)
	public void test2() {
		assertFalse(manager.loginAccount("nbasi16@gmail.com", "paroli"));
		assertTrue(manager.loginAccount("nbasi@gmail.com", "123456"));
		assertTrue(manager.loginAccount("gio's_mail@yahoo.com", "giogio99"));
		assertFalse(manager.loginAccount(null, null));
		assertFalse(manager.loginAccount("nikusha@gmail.com", "123456"));
		assertFalse(manager.loginAccount("nbasi@gmail.com", "123123123"));
		assertFalse(manager.loginAccount("nbasi16@gmail.com", null));
		assertFalse(manager.loginAccount("nbasi16@gmail.com", "paroliparoli"));
	}
	
	@Test
	@Order(3)
	public void test3() {
		assertTrue(manager.updateAccount(manager.getAccount("nbasi16@gmail.com"), "password", "newparoli"));
		assertTrue(manager.loginAccount("nbasi16@gmail.com", "newparoli"));
		assertFalse(manager.deleteAccount(null, null));
		assertFalse(manager.deleteAccount("nbasi16@gmail.com", null));
		assertFalse(manager.deleteAccount("nb@gmail.com", null));
		assertFalse(manager.deleteAccount(null, "paroli"));
		assertFalse(manager.deleteAccount(null, "paroli123"));
		assertFalse(manager.deleteAccount("nb@gmail.com", "paroli123"));
		assertFalse(manager.deleteAccount("gio's_mail@yahoo.com", "gioogioooo99"));
		assertFalse(manager.deleteAccount("nbasi16@gmail.com", "paroli77"));
		
		assertTrue(manager.deleteAccount("gio's_mail@yahoo.com", "giogio99"));
		assertTrue(manager.deleteAccount("nbasi@gmail.com", "123456"));
		assertFalse(manager.deleteAccount("gio's_mail@yahoo.com", "giogio99"));
	}
	
	@Test
	@Order(4)
	public void test4() {
		assertTrue(manager.createAccount("Niko's", "Ranch", "n'i'k'a'@yahoo.com", "nik%''%'a", "888888777777", "1999-02-15"));
		assertTrue(manager.createAccount("Rezo", "Khachidze", "r_khach@gmail.com", "rezo\\*'", "rezo-rezo", "1998-02-15"));
		
		assertFalse(manager.updateAccount(manager.getAccount("r_khach@gmail.com"), "email", null));
		assertFalse(manager.updateAccount(manager.getAccount("r_khach@gmail.com"), "email", "n'i'k'a'@yahoo.com"));
		assertFalse(manager.updateAccount(manager.getAccount("r_khach@gmail.com"), "password", null));
		assertFalse(manager.updateAccount(manager.getAccount("r_khach@gmail.com"), "username", "nik%''%'a"));
		assertFalse(manager.updateAccount(manager.getAccount("r_khach@gmail.com"), "username", null));
		
		assertTrue(manager.updateAccount(manager.getAccount("r_khach@gmail.com"), "email", "rezo_khachidze@yahoo.com"));
		assertTrue(manager.updateAccount(manager.getAccount("rezo_khachidze@yahoo.com"), "username", "revaza98"));
		assertTrue(manager.updateAccount(manager.getAccount("rezo_khachidze@yahoo.com"), "password", "ajfoieje%454a84'f98'es4f4f524f#@vse4$$$0"));
		
		assertFalse(manager.updateAccount(manager.getAccount("n'i'k'a'@yahoo.com"),"email", "rezo_khachidze@yahoo.com"));
		
		assertTrue(manager.updateAccount(manager.getAccount("n'i'k'a'@yahoo.com"), "email", "niko_ramishvili@gmail.com"));
		assertTrue(manager.updateAccount(manager.getAccount("niko_ramishvili@gmail.com"), "username", "niko99"));
		assertTrue(manager.updateAccount(manager.getAccount("niko_ramishvili@gmail.com"), "first_name", "Niko"));
		assertTrue(manager.updateAccount(manager.getAccount("niko_ramishvili@gmail.com"), "last_name", "Ramishvili"));
		
		assertEquals(manager.getAccount(null), null);
		assertEquals(manager.getAccount("niko_ramishvili@yahoo.com"), null);
		
		assertFalse(manager.deleteAccount("n'i'k'a'@yahoo.com", "888888777777"));
		assertFalse(manager.deleteAccount(null, null));
		assertFalse(manager.deleteAccount("niko_ramishvili@gmail.com", "88880088777777"));
		assertFalse(manager.deleteAccount("niko_ramishvili@gmail.com", null));
		assertFalse(manager.deleteAccount("niko_ramishvili@gmail.com", "null"));
		
		assertTrue(manager.deleteAccount("niko_ramishvili@gmail.com", "888888777777"));
		
		assertFalse(manager.deleteAccount("r_khach@gmail.com", "rezo-rezo"));
		assertFalse(manager.deleteAccount("r_khach@gmail.com", "ajfoieje%454a84'f98'es4f4f524f#@vse4$$$0"));
		assertFalse(manager.deleteAccount("rezo_khachidze@yahoo.com", "88880088777777"));
		assertFalse(manager.deleteAccount("rezo_khachidze@yahoo.com", null));
		assertFalse(manager.deleteAccount("rezo_khachidze@yahoo.com", "null"));
		
		assertTrue(manager.deleteAccount("rezo_khachidze@yahoo.com", "ajfoieje%454a84'f98'es4f4f524f#@vse4$$$0"));
	
	}
	
}
