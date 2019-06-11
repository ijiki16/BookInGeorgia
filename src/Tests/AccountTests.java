package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Models.Account;

public class AccountTests {
	
	private Account account1;
	private Account account2;
	private Account account3;
	private Account account4;
	
	@Before
	public void Create() {
		account1 = new Account("Nika", "Basiashvili", "nbasi@gmail.com", "nika97", "123456", "1997-12-19", "1");
		account2 = new Account("Nika", "Basiashvili", "nbasi@gmail.com", "nika97", "123456", "1997-12-19", "2");
		account3 = new Account("James", "Charles", "jchar@gmail.com", "james123", "789554", "1992-02-20", "3");
		account4 = new Account("James", "Charles", "jchar@gmail.com", "james123", "789554", "1992-02-20", "4");
	}

	@Test
	public void testGetter1() {
		assertEquals("Nika", account1.getFirstName());
		assertEquals("Basiashvili", account1.getLastName());
		assertEquals("nbasi@gmail.com", account1.getEmail());
		assertEquals("nika97", account1.getUsername());
		assertEquals("123456", account1.getPassword());
		assertEquals("1997-12-19", account1.getBirthDate());
		
		assertEquals("Nika", account2.getFirstName());
		assertEquals("Basiashvili", account2.getLastName());
		assertEquals("nbasi@gmail.com", account2.getEmail());
		assertEquals("nika97", account2.getUsername());
		assertEquals("123456", account2.getPassword());
		assertEquals("1997-12-19", account2.getBirthDate());
		
		assertEquals("James", account3.getFirstName());
		assertEquals("Charles", account3.getLastName());
		assertEquals("jchar@gmail.com", account3.getEmail());
		assertEquals("james123", account3.getUsername());
		assertEquals("789554", account3.getPassword());
		assertEquals("1992-02-20", account3.getBirthDate());
		
		assertEquals("James", account4.getFirstName());
		assertEquals("Charles", account4.getLastName());
		assertEquals("jchar@gmail.com", account4.getEmail());
		assertEquals("james123", account4.getUsername());
		assertEquals("789554", account4.getPassword());
		assertEquals("1992-02-20", account4.getBirthDate());
	}
	
	@Test
	public void testEquals1() {
		
		assertTrue(account1.equals(account1));
		assertTrue(account2.equals(account2));
		assertTrue(account3.equals(account3));
		assertTrue(account4.equals(account4));
		
		assertFalse(account1.equals(new String("Hi this is Account")));
		assertFalse(account2.equals(new String("Hi this is Account")));
		assertFalse(account3.equals(new String("Hi this is Account")));
		assertFalse(account4.equals(new String("Hi this is Account")));
		
		assertTrue(account1.equals(account2));
		assertTrue(account2.equals(account1));
		assertTrue(account3.equals(account4));
		assertTrue(account4.equals(account3));
		
		assertFalse(account1.equals(account3));
		assertFalse(account1.equals(account4));
		
		assertFalse(account2.equals(account3));
		assertFalse(account2.equals(account4));
		
		assertFalse(account3.equals(account1));
		assertFalse(account3.equals(account2));
		
		assertFalse(account4.equals(account1));
		assertFalse(account4.equals(account2));
	}
	
	@Test
	public void setValues() {
		account1.setFirstName("Giorgi");
		account1.setLastName("Bakhtadze");
		account1.setEmail("gbakh@gmail.com");
		account1.setUsername("giogio");
		account1.setPassword("123123");
		account1.setBirthDate("1997-12-18");
		
		account3.setFirstName("Giorgi");
		account3.setLastName("Bakhtadze");
		account3.setEmail("gbakh@gmail.com");
		account3.setUsername("giogio");
		account3.setPassword("123123");
		account3.setBirthDate("1997-12-18");
		
		account2.setFirstName("Ann");
		account2.setLastName("Smith");
		account2.setEmail("anny@gmail.com");
		account2.setUsername("anny1");
		account2.setPassword("789789");
		account2.setBirthDate("1992-01-01");
		
		account4.setFirstName("Ann");
		account4.setLastName("Smith");
		account4.setEmail("anny@gmail.com");
		account4.setUsername("anny1");
		account4.setPassword("789789");
		account4.setBirthDate("1992-01-01");
		
		assertEquals("Giorgi", account1.getFirstName());
		assertEquals("Bakhtadze", account1.getLastName());
		assertEquals("gbakh@gmail.com", account1.getEmail());
		assertEquals("giogio", account1.getUsername());
		assertEquals("123123", account1.getPassword());
		assertEquals("1997-12-18", account1.getBirthDate());
		
		assertEquals("Ann", account2.getFirstName());
		assertEquals("Smith", account2.getLastName());
		assertEquals("anny@gmail.com", account2.getEmail());
		assertEquals("anny1", account2.getUsername());
		assertEquals("789789", account2.getPassword());
		assertEquals("1992-01-01", account2.getBirthDate());
		
		assertEquals("Giorgi", account3.getFirstName());
		assertEquals("Bakhtadze", account3.getLastName());
		assertEquals("gbakh@gmail.com", account3.getEmail());
		assertEquals("giogio", account3.getUsername());
		assertEquals("123123", account3.getPassword());
		assertEquals("1997-12-18", account3.getBirthDate());
		
		assertEquals("Ann", account4.getFirstName());
		assertEquals("Smith", account4.getLastName());
		assertEquals("anny@gmail.com", account4.getEmail());
		assertEquals("anny1", account4.getUsername());
		assertEquals("789789", account4.getPassword());
		assertEquals("1992-01-01", account4.getBirthDate());
		
		assertTrue(account1.equals(account3));
		assertTrue(account3.equals(account1));
		assertTrue(account2.equals(account4));
		assertTrue(account4.equals(account2));
		
		assertFalse(account1.equals(account2));
		assertFalse(account1.equals(account4));
		
		assertFalse(account2.equals(account1));
		assertFalse(account2.equals(account3));
		
		assertFalse(account3.equals(account2));
		assertFalse(account3.equals(account4));
		
		assertFalse(account4.equals(account1));
		assertFalse(account4.equals(account3));
		
	}
	
}

















