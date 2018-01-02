package controller;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Startpage {
	
	@FXML
	Button Login;
	
	@FXML
	Button Signup;
	
	Stage startpage_stage = new Stage();
	Pane startpage_layout;
	
	public void prc_startpage() 
	{
		try{
		Stage temp=(Stage) Login.getScene().getWindow();	
		temp.close();
		startpage_layout = (Pane) FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
		Scene scene = new Scene(startpage_layout);
		startpage_stage.setScene(scene);
		startpage_stage.show();
		}
		
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
	public void prc_startpage2() 
	{
		try{
		Stage temp=(Stage)Signup.getScene().getWindow();	
		temp.close();
		startpage_layout = (Pane) FXMLLoader.load(getClass().getResource("../view/Signup.fxml"));
		Scene scene = new Scene(startpage_layout);
		startpage_stage.setScene(scene);
		startpage_stage.show();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
	
}
