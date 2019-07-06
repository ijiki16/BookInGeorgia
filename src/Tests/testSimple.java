package Tests;







import java.util.Date;
import java.util.List;

import DataBases.RoomsDB;
import Models.Room;

public class testSimple {

	public static void main(String[] args) {
		RoomsDB db = RoomsDB.getInstance();
//		db.addRoom(new Date(), new Date(), 300, 1, 4, true, false, true, false);
//		Room ret = db.getRoom(4);
//		String ks= ret.toString();
//		System.out.println(ks);
//		ret.setNumberOfBeds(201);
//		ret.setAirConditioning(false);
//		db.updateRoom(ret);
//		Room ret2 = db.getRoom(4);
//		ks = ret2.toString();
//		System.out.println(ks);
//		db.deleteRoom(5);
		//List<Room> ht = db.getRoomByHottel(1);
		//for(int i=0; i<ht.size(); i++) System.out.println(ht.get(i).toString());
		//db.bookRoom(4, new Date(), new Date(),1);
		List<List<java.util.Date> > anw = db.getRoomReservations(4);
		//db.unbookRoom(14, anw.get(1).get(0), anw.get(1).get(1), 1);
		for(int i=0; i<anw.size(); i++) {
			System.out.println("start: "+ anw.get(i).get(0) + " end: "+anw.get(i).get(1));
//			db.unbookRoom(14, anw.get(i).get(0), anw.get(i).get(1), 1);
		}

	}

}
