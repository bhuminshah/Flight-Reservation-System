package controller.Admin;

import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Admin.Airport;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Admin.AdminDAO;

public class AddAirport {

	@FXML
	TextField AirportName;
	
	@FXML
	TextField AirportCity;
	
	@FXML
	TextField AirportCode;
	
	@FXML
	Button Add;
	
	Alert a1, a3;

	@SuppressWarnings("unused")
	public void prc_addAirport(){

		try{
			Airport a = new Airport();
			AdminDAO dao = new AdminDAO();
			ResultSet temp1 = dao.prc_airport_check(AirportCode.getText());
			boolean null_flag = false;
			boolean exist_flag = false;
			if(AirportName.getLength() == 0 || AirportCity.getLength() == 0 || AirportCode.getLength() == 0 )
				null_flag = true;
			while (temp1.next()){
				if(AirportCode.getText().contains(temp1.getString("airport_code"))){
				exist_flag = true;
			break;}
			};
		
			System.out.println(null_flag);
			System.out.println(exist_flag);
			
			
			if (null_flag==false && exist_flag ==false){
				a.setAirport_name(AirportName.getText()); 
				a.setAirport_City(AirportCity.getText());
				a.setAirport_code(AirportCode.getText());
				dao.prc_addAirport_dao(a);
				
				Stage root = (Stage) Add.getScene().getWindow();
				root.close();
				
				try{
					Pane root_layout;
					FXMLLoader loader = new FXMLLoader();
				root_layout = (Pane) FXMLLoader.load(getClass().getResource("/view/AddAirport.fxml"));
				
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
			else {
			if (null_flag == true){
			a1 = new Alert(AlertType.ERROR);
			a1.setTitle("EMPTY FIELDS");
			a1.setContentText("Enter information in all the fields");		
			a1.show();}
			
			else if (exist_flag == true){
				a3 = new Alert(AlertType.ERROR);
				a3.setTitle("AIRPORT EXISTS");
				a3.setContentText("Airport already exists");		
				a3.show();
				AirportName.clear();
				AirportCode.clear();
				AirportCity.clear();
			}
			}
			}
		catch (Exception e){
			System.out.println(e.getMessage()); 
		}
		

	}
			
}