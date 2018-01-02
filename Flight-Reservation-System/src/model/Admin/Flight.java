package model.Admin;

import java.sql.Time;

public class Flight {
	
	private int flight_id;
	private int airport_id;
	private String from_city;
	private String to_city;
	private String flight_date;
	private Time flight_time;
	private int flight_price;
	public int getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}
	public int getAirport_id() {
		return airport_id;
	}
	public void setAirport_id(int airport_id) {
		this.airport_id = airport_id;
	}
	public String getFrom_city() {
		return from_city;
	}
	public void setFrom_city(String from_city) {
		this.from_city = from_city;
	}
	public String getTo_city() {
		return to_city;
	}
	public void setTo_city(String to_city) {
		this.to_city = to_city;
	}
	public String getFlight_date() {
		return flight_date;
	}
	public void setFlight_date(String flight_date) {
		this.flight_date = flight_date;
	}
	public Time getFlight_time() {
		return flight_time;
	}
	public void setFlight_time(Time flight_time) {
		this.flight_time = flight_time;
	}
	public int getFlight_price() {
		return flight_price;
	}
	public void setFlight_price(int flight_price) {
		this.flight_price = flight_price;
	}
	
	

}
