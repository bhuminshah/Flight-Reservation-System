package model;

import java.util.Date;

public class Booking extends controller.User.HomeController{
	
	private int booking_id;
	public int getBooking_id() {
		return booking_id;
	}
	public int getFlight_id() {
		return flight_id;
	}
	@Override
	public String getFrom_city() {
		return from_city;
	}
	@Override
	public String getTo_city() {
		return to_city;
	}
	public Date getFligt_date() {
		return fligt_date;
	}
	public int getPax_count() {
		return pax_count;
	}
	public double getTotal_cost() {
		return total_cost;
	}
	private int flight_id;
	private String from_city;
	private String to_city;
	private Date fligt_date;
	private int pax_count;
	private double total_cost;
	
	

	
}
