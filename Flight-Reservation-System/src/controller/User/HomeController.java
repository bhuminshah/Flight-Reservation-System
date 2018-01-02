package controller.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
//import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.SignupController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.DAOMODEL;


public class HomeController implements Initializable
{
	
	@FXML
	private TextField To;
	
	@FXML
	private Tab Search_Flight;

	@FXML
	private Label Welcome;
	
	@FXML
	private DatePicker from_date;
	
	@FXML
	private DatePicker to_date;
	
	@FXML
	private ChoiceBox<String> origin;
	
	@FXML
	private ChoiceBox<String> destination;
	
	@FXML
	private TextField pax_count;
	
	@FXML
	private TextField cost;

	@FXML
	private Button Search;
	
	@FXML
	private TableView Results;
	
	@FXML
	private TableView Viewbooking;
	
	@FXML
	private Tab View_booking;
	
	@FXML
	private Button Proceed;
	
	@FXML
	private Button Edit;
	
	@FXML
	private Button Delete;
	
	@FXML
	private TextField From;
	
	@FXML
	private Button Logout;
	
	Pane logout_layout1;
	
	Stage logout_stage1 = new Stage();
	
	private String from_city,to_city,depart_Date,price;
	
	String origin_val,dest_val, user;
	
	private String v_user_name,v_password;
	
	private ResultSet rsd,rsd1;
	
	private int a;
	
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
	
	public String getDepart_Date() {
		return depart_Date;
	}

	public void setDepart_Date(String depart_Date) {
		this.depart_Date = depart_Date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
		 
	 public String getCity() {
			return from_city;
		}
	 
	 public void setCity(String from_city) {
			this.from_city = from_city;
	 }
	

	public void initialize(URL url, ResourceBundle rb) {
	
		prc_process_origin();
		prc_process_destination();
	}
	
	
	ObservableList<ObservableList> data_user, data_user1;
	ObservableList<String> sel = FXCollections.observableArrayList();
	
	Pane BookingInfo;
	Stage BookingInfo_stage = new Stage();
	
	
	/**
	 *Method to Welcome user/employee/admin with an alert 
	 */
	
	
	public void prc_welcome()
	{
		String st=Welcome.getText();
		SignupController si=new SignupController();
		st.concat(si.username.getText());
		Welcome.setText(st);
	}
	
	/**
	 * Method to retrieve airport code to display from city while booking flight 
	 */
	public void prc_process_origin()
	{
		ObservableList<String> obj= FXCollections.observableArrayList();
		
		try{
			
		DAOMODEL dao=new DAOMODEL();
		rsd=dao.prc_retrive_dropbox();

		while (rsd.next()){
			obj.add(rsd.getString(1));
		}
		
		origin.setItems(obj);
		}

		catch(Exception e){
			System.out.println(e.getMessage());;
		}
	}
	
	/**
	 * Method to retrieve airport code to display to city while booking flight 
	 */
	public void prc_process_destination(){

		ObservableList<String> obj1= FXCollections.observableArrayList();
		
		try{
		DAOMODEL dao1=new DAOMODEL();
		rsd1=dao1.prc_retrive_dropbox();
		
		while (rsd1.next()){
			obj1.add(rsd1.getString(1));
			
		}
		destination.setItems(obj1);
		}

		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void prc_process_Search()
	{
		Date f_Date;
		try{
			f_Date=Date.valueOf(from_date.getValue());
			Results.getColumns().clear();
			data_user = FXCollections.observableArrayList();
			DAOMODEL dao=new DAOMODEL();
			ResultSet rs;
			ResultSetMetaData rsmd;
			rs=	dao.prc_process_Search_results(origin.getValue(),destination.getValue(),f_Date);
			
				for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
            
					final int j = i;                
					TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
					col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>() {                    
						
						public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
						return new SimpleStringProperty(param.getValue().get(j).toString());}                
            });

            Results.getColumns().addAll(col);    
          }
		
		while(rs.next()){
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                //Iterate Column
                row.add(rs.getString(i));
        }

        data_user.add(row);
        }
		
		Results.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
		    
			public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
		        //Check whether item is selected and set value of selected item to Label
		        if(Results.getSelectionModel().getSelectedItem() != null){    
		           TableViewSelectionModel selectionModel = Results.getSelectionModel();          
		           ObservableList selectedCells = (ObservableList) selectionModel.getSelectedItem();

		           from_city=(String) selectedCells.get(3);
		           setFrom_city(from_city);
		           
		           to_city=(String) selectedCells.get(4);
		            		depart_Date=(String) selectedCells.get(5);
		        		           
		           price=(String) selectedCells.get(7);
		        
		           }
		     }
		     });
	
       //FINALLY ADDED TO TableView
        Results.setItems(data_user);
        Proceed.setDisable(false);
            
   		}
		
		catch(Exception e){
			System.out.println(e.getMessage());
		}

	}
	
	 
	 public void setUname(String uname,String password){
		 v_user_name=uname;
		 v_password=password;
	 }

	public void prc_process_proceed(){		
		
		try {
			FXMLLoader loader=new FXMLLoader();
			BookingInfo = loader.load(getClass().getResource("/view/Booking.fxml").openStream());
			BookingController testUserViewController = (BookingController)loader.getController();
			testUserViewController.setTextfields(from_city,to_city,depart_Date,price,v_user_name,v_password);	
			Scene scene = new Scene(BookingInfo);
			BookingInfo_stage.setScene(scene);
			BookingInfo_stage.show();
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void prc_Set_user_Details(String user_name,String password)
	{
		v_user_name=user_name;
		v_password=password;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void prc_viewBooking(){
		
		try{
		data_user1 = FXCollections.observableArrayList();
		DAOMODEL dao_user = new DAOMODEL();
		ResultSet rs_view;
		Viewbooking.getColumns().clear(); 
		rs_view = dao_user.prc_viewBooking_dao();
		
		for(int i=0 ; i<rs_view.getMetaData().getColumnCount(); i++){

            final int j = i;                
            TableColumn col_user = new TableColumn(rs_view.getMetaData().getColumnName(i+1));
            col_user.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>() {                    
            
			public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                            
            return new SimpleStringProperty(param.getValue().get(j).toString());   }
         });
            Viewbooking.getColumns().addAll(col_user); 
           }
		
		while(rs_view.next()){
            //Iterate Row
            ObservableList<String> row1 = FXCollections.observableArrayList();
            for(int i=1 ; i<=rs_view.getMetaData().getColumnCount(); i++){
                //Iterate Column
                row1.add(rs_view.getString(i));
            }

            data_user1.add(row1);

        }

        //FINALLY ADDED TO TableView
		Viewbooking.setItems(data_user1);
		
		
		Viewbooking.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
		    
		    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
		        //Check whether item is selected and set value of selected item to Label
		        if(Viewbooking.getSelectionModel().getSelectedItem() != null) 
		        {    
		           TableViewSelectionModel selectionModel1 = Viewbooking.getSelectionModel();
		           ObservableList selectedItem1 = (ObservableList) selectionModel1.getSelectedItem();
		           
		           from_city = (String) selectedItem1.get(0);
		           setCity(from_city);
		         }
		        Viewbooking.setItems(data_user1);
		     
		         }
		     });
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	 		
		 public void prc_deleteBooking(){
			 
				try{
					
					Alert a2 = new Alert(AlertType.CONFIRMATION);
					a2.setTitle("CONFIRM DELETE");
					a2.setHeaderText("CONFIRM DELETE");
					a2.setContentText("Are you sure?");

					Optional<ButtonType> result = a2.showAndWait();
					if (result.get() == ButtonType.OK){
						DAOMODEL dao = new DAOMODEL();
						
						dao.prc_deleteBooking_dao(a);
						prc_viewBooking();
						
					} else {
						prc_viewBooking();
					}
				
					
				}
		 
				catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
		 

		 public void prc_logout1(){

			 try {
	
				Stage logout_stage = (Stage) Logout.getScene().getWindow();
				logout_stage.close();
				Stage logout_ps = new Stage();
				Parent root;
				root = FXMLLoader.load(getClass().getResource("/view/Startpage.fxml"));
				Scene scene_lo = new Scene(root);
				logout_ps.setScene(scene_lo);
				logout_ps.show();
			 } 
			 
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		 }
}
