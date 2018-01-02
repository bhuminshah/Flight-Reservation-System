package controller.Employee;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Employee.EmployeeDAO;

public class FlightAddition implements Initializable{

	@FXML
	Label cs;
	
	@FXML
	Label fa_From;
	
	@FXML
	Label fa_To;
	
	@FXML
	Label fa_Depar_Date;
	
	@FXML
	Label fa_Airport;

	@FXML
	ChoiceBox<String> from_city;

	@FXML
	ChoiceBox<String> to_city;
	
	@FXML
	ChoiceBox<String> airport_name;
	
	@FXML
	DatePicker flight_date;
	
	@FXML
	Label Flight_Cost;
	
	@FXML
	TextField fcost;
	
	@FXML
	Button AddFlight;
	
	ObservableList<String> obj_1= FXCollections.observableArrayList();
	ObservableList<String> obj_2= FXCollections.observableArrayList();
	ObservableList<String> obj_3= FXCollections.observableArrayList();
	
	public void prc_populate_airports()
	{

		EmployeeDAO empdao=new EmployeeDAO();
		ResultSet rs_flight;
		try
		{
		rs_flight=empdao.prc_AirportList();
		while (rs_flight.next())
			{
				obj_1.add(rs_flight.getString(2));
			}										
			airport_name.setItems(obj_1);
			}

			catch(Exception e){
				System.out.println(e.getMessage());;
			}
			
	}
	
	public void prc_populate_from_Airport()
	{

		 ResultSet rs_from;
		 		
			try{
				 EmployeeDAO empdao=new EmployeeDAO();
			rs_from=empdao.prc_retrive_dropbox_airport();
			
			while (rs_from.next()){
				obj_2.add(rs_from.getString(1));
			}
			
												
		from_city.setItems(obj_2);
			}

			catch(Exception e){
				System.out.println(e.getMessage());;
			}
			
	}
	
	public void prc_populate_to_Airport()
	{

		 ResultSet rs_To;
		 
			
			try{
				 EmployeeDAO empdao=new EmployeeDAO();
			rs_To=empdao.prc_retrive_dropbox_airport();

			
			while (rs_To.next())
			{
				obj_3.add(rs_To.getString(1));
			}
														
			to_city.setItems(obj_3);
			}

			catch(Exception e){
				System.out.println(e.getMessage());;
			}
			
			
			
	}
	
	
	public void prc_flight_Addition()
			{
		try
		{
		
		ResultSet r1;
		int f_cost;
		String from,to,airport_nme;
		LocalDate dep_Date;
		
		int aid=0;
		from=from_city.getSelectionModel().getSelectedItem();
		to=to_city.getSelectionModel().getSelectedItem();
		dep_Date=flight_date.getValue();
		airport_nme=airport_name.getValue();
		
		f_cost=Integer.parseInt(String.valueOf(fcost.getText()));
		
		System.out.println(f_cost);
		
		System.out.println(from+" "+to+""+dep_Date+" "+airport_nme);
		EmployeeDAO empdao=new EmployeeDAO();
		//r1=empdao
		r1=empdao.prc_get_airport_id(airport_nme);
		
		while(r1.next())
		{
			aid=r1.getInt(1);
		}
		
		empdao.prc_AddingFlights(aid,from,to,dep_Date,f_cost);	
		
		
		Alert a2 = new Alert(AlertType.CONFIRMATION);
		a2.setTitle("SUCCESS");
		a2.setHeaderText("Flight Added");
		a2.setContentText("Flight has been added succesfully");
	
		Stage temp=(Stage) AddFlight.getScene().getWindow();
		temp.close();
		
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

			}

	public void initialize(URL url, ResourceBundle rb) {
		prc_populate_to_Airport();
		prc_populate_airports();
		prc_populate_from_Airport();

		
	}
	}
