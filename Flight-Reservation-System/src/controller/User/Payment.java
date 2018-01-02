package controller.User;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.DAOMODEL;

public class Payment implements Initializable
{
	
	@FXML
	Label l1;
	
	@FXML
	Label l2;
	
	@FXML
	Label l3;
	
	@FXML
	Label l4;
	
	@FXML
	Label l5;
	
	@FXML
	Label l6;
	
	@FXML
	Label l7;
	
	@FXML
	Label l_from_city;
	
	@FXML
	Label l_to_City;
	
	@FXML
	Label l_pax_travelling;
	
	@FXML
	Label l_cost;
	
	@FXML
	Label l_Departure_date;
	
	@FXML
	ChoiceBox<String> payment_method;
	
	@FXML
	Button pay;
	
	ObservableList<String> payment_method_obj=FXCollections.observableArrayList();
	

	
	String from,to,cost;
	String depart_Date;
	
	int final_cost;
	int final_booking_id;

	
	
	String payment_meth;
	
	
	

	public void initialize(URL url, ResourceBundle rb) 
	{
		// TODO Auto-generated method stub
		
		payment_method_obj.add("CCAV");
		payment_method_obj.add("Credit Card");
		payment_method_obj.add("Debit Card");
		payment_method_obj.add("Cheque");

		payment_method.setItems(payment_method_obj);
		
	
	}

	public void setBookingDetails(String from,String to, String dep_date,String cost,int count)
	{
		System.out.println("from a "+from);
		from=from;
		to=to;
		depart_Date=dep_date;
		cost=cost;
		//final_cost=v_cost;
		//final_booking_id=Booking_id;
		System.out.println("From Payment"+final_cost+" "+final_booking_id);
		
		
		l_from_city.setText(from);
		l_to_City.setText(to);
		l_Departure_date.setText(depart_Date);
		l_cost.setText(cost);
		l_pax_travelling.setText(String.valueOf(count));
		
	}
	

	public void prc_pay_click()
	{
		payment_meth=payment_method.getSelectionModel().getSelectedItem();
		System.out.println(payment_meth);
		//System.out.println("pay click"+getV_cost());
		DAOMODEL dao=new DAOMODEL();
		//System.out.println("pay click"+v_Booking_id+" "+v_cost);
		int bid;
		//bid=Integer.valueOf(v_Booking_id);
		System.out.println("Booking ID in payment"+final_booking_id);
		dao.prc_insert_payment(payment_meth,final_booking_id,final_cost);
		Stage temp=(Stage) pay.getScene().getWindow();
		temp.close();	
	}

}
