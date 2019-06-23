package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DataBases.AccountDB;
import DataBases.HotelsDB;
import Managers.AccountManager;
import Models.Account;

public class HotelDBTests {

	private HotelsDB db;
	private Account acc;
	private AccountManager mng;
	private AccountDB adb;
	
	@Before
	public void Create() {
		db = HotelsDB.getInstance();
		mng = AccountManager.getInstance();
		adb = new AccountDB();
	}
	
	@Test
	public void test() {
		assertTrue(HotelsDB.getConnection() != null);
		assertTrue(mng.createAccount("Nika", "Basiashvili", "nbasi@gmail.com", "nika97", "123456", "1997-12-19"));
//		acc = mng.getAccount("nbasi@gmail.com");
//		
//		System.out.println(acc.getId());
//		String hotel_id = db.addHotel("Tiflisi", "5", "abcdef", "Hotel Tiflisi 24/24", "+995 999 955", acc.getId());
//		List<String> row = db.getHotelById(hotel_id);
//		assertEquals(row.get(0), "Tiflisi");
//		assertEquals(row.get(1), "5");
//		assertEquals(row.get(2), null);
//		assertEquals(row.get(4), "+995 999 955");
//		assertEquals(row.get(4), acc.getId());
//		
//		db.deleteHotel(hotel_id);
//		assertEquals(db.getHotelById(hotel_id).size(), 0);
		
		
	}

	
}
