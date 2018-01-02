package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BookingDetails {
	
	private final SimpleIntegerProperty booking_id;
	private final SimpleIntegerProperty Flight_int;
	private final SimpleStringProperty from_city;
	private final SimpleStringProperty to_city;
	private final SimpleStringProperty flight_date;
	private final SimpleIntegerProperty pax_count;
	private final SimpleDoubleProperty total_price;
	
	
	public BookingDetails(int booking_id, int flight_int, String from_city, String to_city, String flight_date, int pax_count,
			double total_price) {
		super();
		this.booking_id = new SimpleIntegerProperty(booking_id);
		this.Flight_int = new SimpleIntegerProperty (flight_int);
		this.from_city = new SimpleStringProperty (from_city);
		this.to_city = new SimpleStringProperty (to_city);
		this.flight_date = new SimpleStringProperty (flight_date);
		this.pax_count = new SimpleIntegerProperty (pax_count);
		this.total_price = new SimpleDoubleProperty (total_price);
	}
	public int getBooking_id() {
		return booking_id.get();
	}
	public int getFlight_int() {
		return Flight_int.get();
	}
	public String getFrom_city() {
		return from_city.get();
	}
	public String getTo_city() {
		return to_city.get();
	}
	public String getFlight_date() {
		return flight_date.get();
	}
	public int getPax_count() {
		return pax_count.get();
	}
	public double getTotal_price() {
		return total_price.get();
	}
	
	
	
	
	

}
