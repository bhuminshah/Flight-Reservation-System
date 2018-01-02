package controller.Admin;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
//import java.time.LocalDate;
import java.util.Optional;

import controller.SignupController;
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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;

import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;

import javafx.scene.control.Alert.AlertType;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Admin.AdminDAO;

import model.Admin.Admin;
import view.*;

import model.Admin.Airport;

public class AdminController {
	

	
	 @FXML
	    private Tab User;

	    @FXML
	    private TableView Users;

	    @FXML
	    private Button Add;

	    @FXML
	    private Button Edit;

	    @FXML
	    private Button Delete;

	    @FXML
	    private Tab airport;

	    @FXML
	    private TableView Airport;

	    @FXML
	    private Button AddAirport;

	    @FXML
	    Button Logout_admin;
	    
	    @FXML
	    private Label Welcome;
	    
	Alert a1, a2, a3;
	Stage login_stage = new Stage();
	Pane login_layout;
	private ObservableList<ObservableList> data;
	Admin ad = new Admin();
	Airport ar = new Airport();
	AdminDAO dao = new AdminDAO();
	Pane logout_layout1;
	Stage logout_stage1 = new Stage();
	
	 public void prc_welcome()
		{
			String st=Welcome.getText();
			SignupController si=new SignupController();
			st.concat(si.username.getText());
			Welcome.setText(st);
		}
	 
	
	public void prc_viewUser(){
		
		try{
			data = FXCollections.observableArrayList();
			
			ResultSet rs;
			Users.getColumns().clear();
			rs=	dao.viewUser_dao();
			
			for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){

	            final int j = i;                
	            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
	            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>() {                    
	            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                            
	            return new SimpleStringProperty(param.getValue().get(j).toString());   }
	         }
	            );
	            Users.getColumns().addAll(col); 
	           }
			
			while(rs.next()){
	            //Iterate Row
	            ObservableList<String> row = FXCollections.observableArrayList();
	            for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
	                //Iterate Column
	                row.add(rs.getString(i));
	            }

	            data.add(row);

	        }

	        //FINALLY ADDED TO TableView
			Users.setItems(data);
			
			Users.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
			        //Check whether item is selected and set value of selected item to Label
			       if(Users.getSelectionModel().getSelectedItem() != null) 
			        {    
			           TableViewSelectionModel selectionModel = Users.getSelectionModel();
			           ObservableList selectedItem = (ObservableList) selectionModel.getSelectedItem();
			           
			           ad.setUser_name((String) selectedItem.get(0));
			           ad.setEmail_Id((String)selectedItem.get(1));
			           ad.setPhone(Integer.parseInt( (String) selectedItem.get(2)));
			           ad.setStreet((String)selectedItem.get(3));
			           ad.setUser_ID(Integer.parseInt( (String) selectedItem.get(4)));
			           ad.setPassword((String)selectedItem.get(5));
			           ad.setType((String)selectedItem.get(6));
			          // a = ad.getUser_ID();
			        }
			        Users.setItems(data);
			         }
			     });
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}
	
	public void prc_deleteUser()
	{
		try{
			
			Alert a2 = new Alert(AlertType.CONFIRMATION);
			a2.setTitle("CONFIRM DELETE");
			a2.setHeaderText("CONFIRM DELETE");
			a2.setContentText("Are you sure?");
			
			Optional<ButtonType> result = a2.showAndWait();
			if (result.get() == ButtonType.OK){
				
				
				dao.prc_deleteUser_dao(ad.getUser_ID());
				prc_viewUser();
				
			} else {
				prc_viewUser();
			}
		
			
		}
 
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
			 
	//Stage startpage_stage = new Stage();
	//Pane startpage_layout;
	
	public void prc_addUser(){
		
		try
		{
		Stage adduser_stage=new Stage();
		Pane adduserRoot = FXMLLoader.load(getClass().getResource("/view/AddUser.fxml"));
		Scene adduser_scene=new Scene(adduserRoot);
		adduser_stage.setScene(adduser_scene);
		adduser_stage.setTitle("Add User");
		adduser_stage.show();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	
		}
	public void prc_editUser()
	{
		try {
			
			FXMLLoader loader=new FXMLLoader();
			login_layout = loader.load(getClass().getResource("/view/EditUser.fxml").openStream());
			EditUserController editViewController = (EditUserController)loader.getController();
			editViewController.setedituser(ad);
			//System.out.println("From Login"+v_login);	
			Scene scene = new Scene(login_layout);
			login_stage.setScene(scene);
			login_stage.show();
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(e.getMessage());
		}
}
	
	public void prc_viewAirport(){

		try{

			data = FXCollections.observableArrayList();
			
			ResultSet rs;
			Airport.getColumns().clear();
			rs=	dao.viewAirport_dao();

			for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){

				final int j = i;                
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
				col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>() {                    
					public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                            
						return new SimpleStringProperty(param.getValue().get(j).toString());   }
				}
						);
				Airport.getColumns().addAll(col); 
			}

			while(rs.next()){
				//Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
					//Iterate Column
					row.add(rs.getString(i));
				}

				data.add(row);

			}

			//FINALLY ADDED TO TableView
			Airport.setItems(data);

			Airport.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
				public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
					//Check whether item is selected and set value of selected item to Label
					if(Airport.getSelectionModel().getSelectedItem() != null) 
					{    
						TableViewSelectionModel selectionModel = Airport.getSelectionModel();
						ObservableList selectedItem = (ObservableList) selectionModel.getSelectedItem();

						ar.setAirport_id(Integer.parseInt( (String) selectedItem.get(0)));
						ar.setAirport_name((String)selectedItem.get(1));
						ar.setAirport_City((String) selectedItem.get(2));
						ar.setAirport_code((String) selectedItem.get(3));

					}
					Airport.setItems(data);

				  }
		     });
		}
			catch (Exception e){
				System.out.println(e.getMessage());
			}



		}
	
	public void prc_addAirport() 
	{
		/*
		Stage login_stage = new Stage();
		
		Pane login_layout;
		Stage temp=(Stage) Add.getScene().getWindow();
		//temp.close();		
		
		login_layout = (Pane) FXMLLoader.load(getClass().getResource("../view/AddAirport.fxml"));
		Scene scene = new Scene(login_layout);
	
		login_stage.setScene(scene);
		login_stage.show();	
		*/
		
		try
		{
		Stage airport_stage=new Stage();
		Pane airportRoot = FXMLLoader.load(getClass().getResource("/view/AddAirport.fxml"));
		Scene airport_scene=new Scene(airportRoot);
		airport_stage.setScene(airport_scene);
		airport_stage.setTitle("Add an Airport");
		airport_stage.show();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		
	}

	public void prc_logout() 
	{

		try {
			
			Stage logout_stage = (Stage) Logout_admin.getScene().getWindow();
			logout_stage.close();
			Stage logout_ps = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/view/Startpage.fxml"));
			Scene scene_lo = new Scene(root);
			logout_ps.setScene(scene_lo);
			logout_ps.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}