package Tests;







import java.util.Date;
import java.util.List;

import DataBases.RoomsDB;
import Models.Room;

public class testSimple {

	public static void main(String[] args) {
		RoomsDB db = new RoomsDB();
		//db.addRoom(new Date(), new Date(), 1, 3, false, true, false, true);
		//Room ret = db.getRoom(11);
		db.deleteRoom(13);
		List<Room> ht = db.getRoomByHottel(1);
		for(int i=0; i<ht.size(); i++) System.out.println(ht.get(i).toString());
		// TODO Auto-generated method stub

	}

}
