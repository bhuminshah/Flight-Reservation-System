package controller;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;
import model.Admin.Userinfo;

import java.io.IOException;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class SignupController 
{

	@FXML
	public TextField username;

	@FXML
	private TextField email_id;
	
	@FXML
	private TextField phone;

	@FXML
	private TextField street;
	
	@FXML
	private PasswordField Pwd;
		
	@FXML
	private Button Submit;

	@FXML
	private Button Register;
	
	@FXML
	private Pane mainpane;
	
	Alert a1, a2;
	
	public String s;
	
	
	public void prc_handle_register(){
		try{
		DAOMODEL dao=new DAOMODEL();
		Userinfo sp = new Userinfo();
		ResultSet temp1 = dao.prc_username_check(username.getText());
		boolean null_flag = false;
		boolean exist_flag = false;
		if(username.getLength() == 0 || email_id.getLength() == 0 || phone.getLength() == 0 || street.getLength() == 0 || Pwd.getLength() == 0 )
			null_flag = true;
		while (temp1.next()){
			if(username.getText().contains(temp1.getString("User_name"))){
			exist_flag = true;
		break;}
		};
	
		System.out.println(null_flag);
		System.out.println(exist_flag);
		
		
		if (null_flag==false && exist_flag ==false){
			sp.setUser_name(username.getText()); 
			sp.setEmail_id(email_id.getText());
			sp.setPhone(Integer.parseInt(phone.getText()));
			sp.setStreet(street.getText());
			sp.setPassword(Pwd.getText());
			dao.prc_insert(sp);
			System.out.println("User "+username.getText()+" registered succesfully");
			
			Stage root = new Stage();
			Pane root_layout;
			try{
			//FXMLLoader loader = new FXMLLoader();
			root_layout = (Pane) FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
		
			Scene scene = new Scene(root_layout);
			root.setScene(scene);
			root.show();
			Stage temp=(Stage) Register.getScene().getWindow();
			temp.close();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
		else {
		if (null_flag == true){
		a2 = new Alert(AlertType.ERROR);
		a2.setTitle("EMPTY FIELDS");
		a2.setContentText("Enter information in all the fields");		
		a2.show();}
		
		else if (exist_flag == true){
			a1 = new Alert(AlertType.ERROR);
			a1.setTitle("USERNAME EXISTS");
			a1.setContentText("Username already exists");		
			a1.show();
			username.clear();
		}
		}
		}
	catch (Exception e){
		System.out.println(e.getMessage()); 
	}
	
}}
