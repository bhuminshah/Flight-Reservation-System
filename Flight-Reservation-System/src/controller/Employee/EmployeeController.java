package controller.Employee;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
//import java.time.LocalDate;
import java.util.Optional;

import controller.User.HomeController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Admin.AdminDAO;
import model.DAOMODEL;
import model.Employee.EmployeeDAO;
import model.Admin.Flight;
import model.Booking;
import controller.User.Payment;
import controller.SignupController;
import javafx.stage.Stage;
import java.io.IOException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
//import java.time.LocalDate;
import java.util.Optional;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.DAOMODEL;
import controller.SignupController;
import javafx.stage.Stage;
import java.io.IOException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
//import java.time.LocalDate;
import java.util.Optional;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.DAOMODEL;
import model.Booking;
import controller.SignupController;
import javafx.stage.Stage;
import model.Employee.*;

public class EmployeeController {

	@FXML
	 TableView Viewflights;
	
	@FXML
	 TableView Viewbooking;
	
	@FXML
	 Tab View_booking;
	
	@FXML
	 Tab View_Flights;
	
	@FXML
	 Button Edit;
	
	@FXML
	 Button AddFlight;
	
	
	@FXML
	 Button Delete;
	
	@FXML
	Button Logout1;
	
	 Pane logout_layout;
	
	 Stage logout_stage = new Stage();
	
	Alert a1,a2;
	
	Pane BookingInfo;
	
	Stage BookingInfo_stage = new Stage();
	
	
	Employee emp = new Employee();
	EmployeeDAO dao = new EmployeeDAO();
	Flight fl = new Flight();
	ObservableList<ObservableList> data_emp;
	ObservableList<ObservableList> data;
	int tempbooking;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void prc_viewBooking()
	{

		try{
			//Employee emp = new Employee();
			//EmployeeDAO dao=new EmployeeDAO();
			data_emp = FXCollections.observableArrayList();
			ResultSet rs_emp;
			Viewbooking.getColumns().clear();
			rs_emp=	dao.prc_viewBooking_dao();
			
			for(int i=0 ; i<rs_emp.getMetaData().getColumnCount(); i++){

	            final int j = i;                
	            TableColumn col = new TableColumn(rs_emp.getMetaData().getColumnName(i+1));
	            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>() {                    
	            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                            
	            return new SimpleStringProperty(param.getValue().get(j).toString());
	            }
	         }
	            );
	            Viewbooking.getColumns().addAll(col); 
	           }
			
			while(rs_emp.next()){
	            //Iterate Row
	            ObservableList<String> row = FXCollections.observableArrayList();
	            for(int i=1 ; i<=rs_emp.getMetaData().getColumnCount(); i++){
	                //Iterate Column
	                row.add(rs_emp.getString(i));
	            }

	            data_emp.add(row);

	        }

	        //FINALLY ADDED TO TableView
			Viewbooking.setItems(data_emp);
			
			
			Viewbooking.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
			        //Check whether item is selected and set value of selected item to Label
			        if(Viewbooking.getSelectionModel().getSelectedItem() != null) 
			        {    
			           TableViewSelectionModel selectionModel = Viewbooking.getSelectionModel();
			           ObservableList selectedItem = (ObservableList) selectionModel.getSelectedItem();
			        
			           
			           emp.setBooking_id(Integer.parseInt( (String) selectedItem.get(0)));
			           emp.setFlight_id(Integer.parseInt( (String) selectedItem.get(1)));
			           //emp.setUser_id(Integer.parseInt( (String) selectedItem.get(2)));
			           emp.setFrom_city((String) selectedItem.get(2));
			           emp.setTo_city((String) selectedItem.get(3));
			           emp.setFlight_date((String) selectedItem.get(4));
					   emp.setPax_count(Integer.parseInt( (String) selectedItem.get(5)));
					   emp.setTotal_price(Integer.parseInt( (String) selectedItem.get(6)));
					   emp.setPayment_status((String) selectedItem.get(7));
					   
			           System.out.println("In viewBooking"+Integer.parseInt( (String) selectedItem.get(0)));
			        }
			        Viewbooking.setItems(data_emp);
			     
			         }
			     });
		
			
			
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}

		
	}	 		
		 public void prc_deleteBooking()
			{
				try{
					//EmployeeDAO dao = new EmployeeDAO();
					//Employee emp = new Employee();
					Alert a2 = new Alert(AlertType.CONFIRMATION);
					a2.setTitle("CONFIRM DELETE");
					a2.setHeaderText("CONFIRM DELETE");
					a2.setContentText("Are you sure?");
					Optional<ButtonType> result = a2.showAndWait();
					if (result.get() == ButtonType.OK){
						
						//System.out.println(tempbooking);
						dao.prc_deleteBooking_dao(emp.getBooking_id());
						
						prc_viewBooking();
						}
					
					else{
						prc_viewBooking();
					}	
				}
		 
				catch (Exception e){
					System.out.println(e.getMessage());
				}
		}
		 
		 
		 
		 @SuppressWarnings({ "unchecked", "rawtypes" })
		public void prc_EditBooking() 
		 {

			 try
			 {
				 
				  
				 
				 
				 
				 System.out.println("In Edit");
				 Pane EditUser;
				 Stage edit_user_stage=new Stage();	 
				FXMLLoader loader_em=new FXMLLoader();
			 	EditUser = loader_em.load(getClass().getResource("/view/EditBooking.fxml").openStream());
			 	EditBooking editViewController = (EditBooking)loader_em.getController();
				editViewController.setedituser(emp);
				System.out.println(emp.getFrom_city());
			 	Scene scene = new Scene(EditUser);
				edit_user_stage.setScene(scene);
				edit_user_stage.show();
			 }
					
			 catch(Exception e)
			 {
				 System.out.println(e.getMessage());
			 }
			
			 
		 }
		 
	
		 
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void prc_viewFlights(){
				
				try{
					
					data = FXCollections.observableArrayList();
					EmployeeDAO dao = new EmployeeDAO();
					ResultSet rs_flg;
					Viewflights.getColumns().clear();
					rs_flg =	dao.prc_viewFlights_dao();
					
					for(int i=0 ; i<rs_flg.getMetaData().getColumnCount(); i++){

			            final int j = i;                
			            TableColumn col = new TableColumn(rs_flg.getMetaData().getColumnName(i+1));
			            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>() {                    
			            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                            
			            return new SimpleStringProperty(param.getValue().get(j).toString());   }
			         }
			            );
			            Viewflights.getColumns().addAll(col); 
			           }
					
					while(rs_flg.next()){
			            //Iterate Row
			            ObservableList<String> row = FXCollections.observableArrayList();
			            for(int i=1 ; i<=rs_flg.getMetaData().getColumnCount(); i++){
			                //Iterate Column
			                row.add(rs_flg.getString(i));
			            }

			            data.add(row);

			        }

			        //FINALLY ADDED TO TableView
					Viewflights.setItems(data);
					
					
					Viewflights.
					getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
					{
					    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) 
					    {
					    
					    
					        if(Viewflights.getSelectionModel().getSelectedItem() != null) 
					        {    
					           TableViewSelectionModel selectionModel = Viewflights.getSelectionModel();
					           ObservableList selectedItem = (ObservableList) selectionModel.getSelectedItem();
					           
					           fl.setFlight_id(Integer.parseInt((String) selectedItem.get(0)));
					           fl.setAirport_id(Integer.parseInt((String) selectedItem.get(1)));
					           fl.setFrom_city((String) selectedItem.get(2));
					           fl.setTo_city((String) selectedItem.get(3));
					           fl.setFlight_date((String) selectedItem.get(4));
							   fl.setFlight_price(Integer.parseInt((String) selectedItem.get(5)));
					         }
					        Viewflights.setItems(data);
					     
					         }
					     });
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
				

			} 	
			 public void prclogout()
			 {
				 System.out.println("Logout called");
				 Stage temp=(Stage) Logout1.getScene().getWindow();
					temp.close();		
					
					try {
						logout_layout = (Pane) FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Scene scene = new Scene(logout_layout);
				
					logout_stage.setScene(scene);
					logout_stage.show();
			 }		 
			 
						Pane FlightAddInfo;
				Stage FlightInfo_stage = new Stage();
				

				
				
				public void prc_AddFlight()
			 {
					System.out.println("Flight Addition Called");
									
					try {
						//FXMLLoader loader_f=new FXMLLoader();
						//FlightAddInfo = loader_f.load(getClass().getResource("../view/FlightAddition.fxml").openStream());
						//Scene scene_fx = new Scene(FlightAddInfo);
						//FlightInfo_stage.setScene(scene_fx);
						//FlightInfo_stage.show();
						
						
						//Stage stage = (Stage) Logout1.getScene().getWindow();
						//stage.close();
						//FXMLLoader loader = new FXMLLoader();
						Stage primaryStage = new Stage();
						//Pane root;
						Parent root = FXMLLoader.load(getClass().getResource("/view/FlightAddition.fxml"));
						Scene scene = new Scene(root);
						//scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
						primaryStage.setScene(scene);
						primaryStage.show();
						
					} 
					catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
				
			 }
			 			 
}
	
