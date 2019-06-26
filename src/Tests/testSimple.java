package Tests;







import java.util.Date;
import java.util.List;

import DataBases.RoomsDB;
import Models.Room;

public class testSimple {

	public static void main(String[] args) {
		RoomsDB db = new RoomsDB();
		db.addRoom(new Date(), new Date(), 1, 2, true, true, true, false);
		//Room ret = db.getRoom(12);
		//String ks= ret.toString();
		//System.out.println(ks);
		db.deleteRoom(15);
		List<Room> ht = db.getRoomByHottel(1);
		for(int i=0; i<ht.size(); i++) System.out.println(ht.get(i).toString());
		// TODO Auto-generated method stub

	}

}
