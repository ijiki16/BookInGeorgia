package Tests;







import java.util.Date;
import java.util.List;

import DataBases.RoomsDB;
import Models.Room;

public class testSimple {

	public static void main(String[] args) {
		RoomsDB db = new RoomsDB();
//		db.addRoom(new Date(), new Date(), 1, 2, true, true, true, false);
//		Room ret = db.getRoom(12);
//		String ks= ret.toString();
//		System.out.println(ks);
//		
//		//
//		ret.setNumberOfBeds(20);
//		db.updateRoom(ret);
//		Room ret2 = db.getRoom(12);
//		ks = ret2.toString();
//		System.out.println(ks);
		//db.deleteRoom(15);
//		List<Room> ht = db.getRoomByHottel(1);
//		for(int i=0; i<ht.size(); i++) System.out.println(ht.get(i).toString());
		// TODO Auto-generated method stub
		
		List<List<java.util.Date> > anw = db.getRoomReservations(12);
		for(int i=0; i<anw.size(); i++) {
			System.out.println("start: "+ anw.get(i).get(0) + " end: "+anw.get(i).get(1));
		}

	}

}
