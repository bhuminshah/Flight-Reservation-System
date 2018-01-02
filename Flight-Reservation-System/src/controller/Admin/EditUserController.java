package controller.Admin;

import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Admin.Admin;
import model.Admin.AdminDAO;

public class EditUserController {
	
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
	Button Edit;
	
	Alert a1,a2,a3;
	Admin ad;
	AdminDAO dao = new AdminDAO();

	
	public void setedituser(Admin ad){
		
		try{
		this.ad= new Admin();
		this.ad = ad;
		
		UserName.setText(ad.getUser_name());
		EmailId.setText(ad.getEmail_Id());
		Phone.setText(String.valueOf(ad.getPhone()));
		Street.setText(ad.getStreet());
		Password.setText(ad.getPassword());
		Type.setValue(ad.getType());
		
		ObservableList<String> Type_obj =FXCollections.observableArrayList();
		
		Type_obj.add("User");
		Type_obj.add("Employee");
		Type_obj.add("Admin");
		Type.setItems(Type_obj);
		
	}
		catch (Exception e){
			e.printStackTrace();
		}}
	
public void prc_EditUser(){
		
	try{
		
		ResultSet temp1 = dao.prc_username_check(UserName.getText());
		boolean null_flag = false;
		boolean exist_flag = false;
		if(UserName.getLength() == 0 || EmailId.getLength() == 0 || Phone.getLength() == 0 || Street.getLength() == 0 || Password.getLength() == 0 )
			null_flag = true;
		
		
		if (null_flag==false && exist_flag ==false)
		{
			
			ad.setUser_name(UserName.getText());
			ad.setEmail_Id(EmailId.getText());
			ad.setPhone(Integer.parseInt(Phone.getText()));
			ad.setStreet(Street.getText());
			ad.setPassword(Password.getText());
			ad.setType(Type.getValue());
	
			dao.prc_EditUser_dao(ad);
			Stage root = (Stage) Edit.getScene().getWindow();
			root.close();
			
		}
		
		else {
			if (null_flag == true)
			{
			a1 = new Alert(AlertType.ERROR);
			a1.setTitle("EMPTY FIELDS");
			a1.setContentText("Enter information in all the fields");		
			a1.show();
			}
			
			else if (exist_flag == true)
			{
				a3 = new Alert(AlertType.ERROR);
				a3.setTitle("USERNAME EXISTS");
				a3.setContentText("Username already exists");		
				a3.show();
				UserName.clear();
			}
		}
		
	}
		
		catch(Exception e){
			e.printStackTrace();
			//System.out.println(e.getMessage());
		}
	}
}
