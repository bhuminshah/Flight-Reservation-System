package controller.Admin;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Admin.Admin;
import model.Admin.AdminDAO;

public class AddUser implements Initializable{
	
	
	@FXML
	TextField UserName; 
	
	@FXML
	TextField EmailId; 
	
	@FXML
	TextField Phone;
	
	@FXML
	TextField Street;
	
	@FXML
	PasswordField Password;
	
	@FXML
	ChoiceBox<String> Type;
	
	@FXML
	Button Create;
	
	Alert a1, a3;
	
	
	public void prc_AddUser(){
	try{
		Admin a = new Admin();
		AdminDAO dao = new AdminDAO();
		ResultSet temp1 = dao.prc_username_check(UserName.getText());
		boolean null_flag = false;
		boolean exist_flag = false;
		
		if(UserName.getLength() == 0 || EmailId.getLength() == 0 || Phone.getLength() == 0 || Street.getLength() == 0 || Password.getLength() == 0 )
			null_flag = true;
		while (temp1.next()){
			if(UserName.getText().contains(temp1.getString("User_Name"))){
			exist_flag = true;
		break;}
		};		
		
		if (null_flag==false && exist_flag ==false){
			
			a.setUser_name(UserName.getText()); 
			a.setEmail_Id(EmailId.getText());
			a.setPhone(Integer.parseInt(Phone.getText()));
			a.setStreet(Street.getText());
			a.setPassword(Password.getText());
			a.setType(Type.getSelectionModel().getSelectedItem());
			String temp = Type.getSelectionModel().getSelectedItem();
			dao.prc_addUser_dao(a);
			
			Stage root = (Stage) Create.getScene().getWindow();
			root.close();
			Pane root_layout;
			try{
			FXMLLoader loader = new FXMLLoader();
			root_layout = (Pane) FXMLLoader.load(getClass().getResource("/view/AddUser.fxml"));
			
			
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
			a3.setTitle("USERNAME EXISTS");
			a3.setContentText("Username already exists");		
			a3.show();
			UserName.clear();
		}
		}
		}
	catch (Exception e){
		e.printStackTrace();
		//System.out.println(e.getMessage()); 
	}
	

}

	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		ObservableList<String> Type_obj =FXCollections.observableArrayList();
		
		Type_obj.add("User");
		Type_obj.add("Employee");
		Type_obj.add("Admin");

		Type.setItems(Type_obj);
		
		
	}}
