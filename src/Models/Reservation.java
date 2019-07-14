package Models;

import java.util.Date;

public class Reservation {
	
	private int id;
	private Date from;
	private Date to;
	private int room_id;
	private int account_id;

	public Reservation(int id, Date from, Date to, int room_id, int account_id) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.room_id = room_id;
		this.account_id = account_id;
	}
	
	/*
	 * Return id of the reservation
	 */
	public int getId() {
		return id;
	}
	
	/*
	 * Return start date of reservation
	 */
	public Date getFrom() {
		return from;
	}
	
	/*
	 * Return end date of reservation
	 */
	public Date getTo() {
		return to;
	}
	
	/*
	 * Return room id
	 */
	public int getRoomId() {
		return room_id;
	}
	
	/*
	 * Return account_id
	 */
	public int getAccountId() {
		return account_id;
	}

}
