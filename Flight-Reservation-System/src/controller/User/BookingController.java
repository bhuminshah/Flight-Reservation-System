package controller.User;

import java.io.IOException;
import java.net.URL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import model.DAOMODEL;

public class BookingController implements Initializable 
{

	
	
@FXML
Button Book;

@FXML
Button PayNow;

LocalDate dept_Date;

@FXML
ChoiceBox<Integer> PaxCount;

@FXML
Label No_of_pax;

@FXML
AnchorPane Booking_pne;

@FXML
Label name1;
@FXML
Label name2;
@FXML
Label name3;
@FXML
Label name4;
@FXML
Label l_from_city,l_from_city_val;

@FXML
Label l_to_city,l_to_city_val;

@FXML
Label cost,cost_val;
@FXML
Label l_depart_Date,l_Depart_Date_val;


@FXML
TextField name1_tf;
@FXML
TextField name2_tf;
@FXML
TextField name3_tf;
@FXML
TextField name4_tf;

@FXML
Button ConfirmBooking;

public int count;
String a,b,c,d,v_user_name,v_password;


ObservableList<Integer> obj=FXCollections.observableArrayList();

int cost1;

List<String> names=new ArrayList<String>();


public void initialize(URL url, ResourceBundle rb) 
{
	// TODO Auto-generated method stub
	
	obj.add(1);
	obj.add(2);
	obj.add(3);
	obj.add(4);

	
	PaxCount.setItems(obj);
}

public void setTextfields(String from,String to, String dep_date,String cost,String user_name,String pass_word)
{
	System.out.println("from a "+from);
	a=from;
	b=to;
	c=dep_date;
	d=cost;
	v_user_name=user_name;
	v_password=pass_word;
	System.out.println(d);
}



public void BookClick()
{

	System.out.println(d);
	cost1=Integer.parseInt(d);
	System.out.println("In a"+a);

	count=PaxCount.getValue();

	
	
	if(count==1)
	{
	name1.setVisible(true);
	name1_tf.setVisible(true);
	name2.setVisible(false);
	name2_tf.setVisible(false);
	name3.setVisible(false);
	name3_tf.setVisible(false);
	name4.setVisible(false);
	name4_tf.setVisible(false);
	System.out.println(name1_tf.getText());
	names.add(name1_tf.getText());
	}
	else if(count==2)
	{
		cost1=cost1*2;
	name1.setVisible(true);
	name1_tf.setVisible(true);
	name2.setVisible(true);
	name2_tf.setVisible(true);
	name3.setVisible(false);
	name3_tf.setVisible(false);
	name4.setVisible(false);
	name4_tf.setVisible(false);
	}
	else if(count==3) 
	{
		cost1=cost1*3;
		name1.setVisible(true);
		name1_tf.setVisible(true);
		name2.setVisible(true);
		name2_tf.setVisible(true);
		name3.setVisible(true);
		name3_tf.setVisible(true);
		name4.setVisible(false);
		name4_tf.setVisible(false);
	}
	else if(count==4) 
	{
		cost1=cost1*4;
		name1.setVisible(true);
		name1_tf.setVisible(true);
		name2.setVisible(true);
		name2_tf.setVisible(true);
		name3.setVisible(true);
		name3_tf.setVisible(true);
		name4.setVisible(true);
		name4_tf.setVisible(true);
		
	}
	else 
	System.out.println("Invalid Input");
	
	l_from_city_val.setText(a);
	l_to_city_val.setText(b);
	l_Depart_Date_val.setText(c);

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	formatter=formatter.withLocale(Locale.US);
	
		 dept_Date = LocalDate.parse(c);
	
	System.out.println("In dept"+dept_Date);
	cost_val.setText(String.valueOf(cost1));
}

public void prc_confirm_booking()
{
	String user_id=null;
	
if(count==1)
{
	names.add(name1_tf.getText());

}
	else if(count==2){
	names.add(name1_tf.getText());
	names.add(name2_tf.getText());}
	else if(count==3){
		names.add(name1_tf.getText());
		names.add(name2_tf.getText());
		names.add(name3_tf.getText());
	}
	else if(count==3){
		names.add(name1_tf.getText());
		names.add(name2_tf.getText());
		names.add(name3_tf.getText());
		names.add(name4_tf.getText());
	}
	else System.out.println("Invalid choice");	

	for(int i=0;i<names.size();i++)
	{
		System.out.println(names.get(i));
	}

	
try
{
ResultSet r1;
DAOMODEL dao1=new DAOMODEL();
r1=dao1.prc_retrive_user_Details(v_user_name,v_password);
while(r1.next())
{
user_id=r1.getString(4);
System.out.println("User ID BEFORE insert"+user_id);
}
System.out.println("In booking"+dept_Date);

System.out.println("In insert 1 booking");
dao1.prc_insert_booking(user_id,l_from_city_val.getText(), l_to_city_val.getText(), dept_Date,count,cost1, names);
System.out.println("In insert 2 booking");
}
catch(Exception e)
{
		System.out.println(e.getMessage());
}
PayNow.setDisable(false);
ConfirmBooking.setDisable(true);
}
Pane PaymentInfo;
Stage PaymentInfo_stage = new Stage();


public void setBookingId(int i_Booking_id,int i_cost)
{


System.out.println("In Booking from set booking id: ");
System.out.println("In Booking from set booking id: ");

}


public void prc_payment()
{
	
	try {
		//FXMLLoader loader1=new FXMLLoader();
		

		/*
		PaymentInfo = loader1.load(getClass().getResource("../view/Payment.fxml").openStream());
		Scene scene1 = new Scene(PaymentInfo);
		PaymentInfo_stage.setScene(scene1);
		PaymentInfo_stage.show();
	
		
		Payment paymentViewController = (Payment)loader1.getController();
		paymentViewController.setBookingDetails(l_from_city_val.getText(),l_to_city_val.getText(),c,d,count);
	
		Stage temp=(Stage) ConfirmBooking.getScene().getWindow();
		temp.close();	
		*/
		
		/*
		
		Stage payment_stage=new Stage();
		Pane paymentRoot = FXMLLoader.load(getClass().getResource("/view/Payment.fxml"));
		Scene payment_scene=new Scene(paymentRoot);
		payment_stage.setScene(payment_scene);
		payment_stage.show();
		
		Stage temp=(Stage) ConfirmBooking.getScene().getWindow();
		temp.close();	
		
		*/
		Stage payment_stage=new Stage();
		
		FXMLLoader loader=new FXMLLoader();
		Pane paymentRoot = loader.load(getClass().getResource("/view/Payment.fxml").openStream());
		Payment paymentController = (Payment)loader.getController();
		paymentController.setBookingDetails(l_from_city_val.getText(),l_to_city_val.getText(),c,d,count);
		
		
		Scene scene = new Scene(paymentRoot);
		payment_stage.setScene(scene);
		payment_stage.show();
		Stage temp=(Stage) ConfirmBooking.getScene().getWindow();
		temp.close();	
		
		
		
		
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
	
}
}
