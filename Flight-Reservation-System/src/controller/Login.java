package controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import controller.User.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.DAOMODEL;
public class Login 
{
	@FXML
	TextField Login;
	
	@FXML
	PasswordField Password;
	
	@FXML
	Button Submit;
	
	String v_login;
	
	String v_pwd;
		
	ResultSet rs=null, rs1=null, rs2=null;
	
	ResultSetMetaData rsmd;
	
	Alert al,al1,al2;
	Stage login_stage = new Stage();
	Pane login_layout;
	
	public void prc_login() 
	{
			v_login=Login.getText();
			v_pwd=Password.getText();

			DAOMODEL dao=new DAOMODEL();
			boolean admin=false,user=false,emp=false;
			
			
			if (v_login.length()!=0 && v_pwd.length()!=0)
			{
				rs=dao.prc_retrive(v_login,v_pwd);
				rs1=dao.prc_retrive_admin(v_login,v_pwd);
				rs2 = dao.prc_retrive_employee(v_login, v_pwd);
				
				
				
					try 
					{
					
				
					if(rs.next() && rs!=null){
					rsmd=rs.getMetaData();
							
					FXMLLoader loader=new FXMLLoader();
					login_layout = loader.load(getClass().getResource("../view/Home.fxml").openStream());
					HomeController homeViewController = (HomeController)loader.getController();
					homeViewController.setUname(v_login,v_pwd);
					Scene scene = new Scene(login_layout);
					login_stage.setScene(scene);
					login_stage.setTitle("Home");
					login_stage.show();
					
				//	Stage temp=(Stage) Submit.getScene().getWindow();
					//temp.close();
									
					al2 = new Alert(AlertType.CONFIRMATION);
					al2.setTitle("LOGIN SUCCESFULL");
					al2.setContentText("Welcome back, "+rs.getString(1));		
					al2.show();
					}
					
				
					else if (rs1.next() && rs1!=null){
					rsmd=rs.getMetaData();
					//System.out.print(rs1.getString(1));
					
					//Stage temp=(Stage) Submit.getScene().getWindow();
					//temp.close();		
					
					login_layout = (Pane) FXMLLoader.load(getClass().getResource("../view/Admin.fxml"));
					Scene scene = new Scene(login_layout);
					
					login_stage.setScene(scene);
					login_stage.show();
	
					al2 = new Alert(AlertType.CONFIRMATION);
					al2.setTitle("LOGIN SUCCESFULL");
					al2.setContentText("Welcome back, "+rs.getString(1));		
					al2.show();
					}
				
					else if (rs2.next() && rs2!=null){
						rsmd=rs2.getMetaData();
						//System.out.print(rs2.getString(1));
						
						//Stage temp=(Stage) Submit.getScene().getWindow();
						//temp.close();		
						
						login_layout = (Pane) FXMLLoader.load(getClass().getResource("../view/Employee.fxml"));
						Scene scene = new Scene(login_layout);
					
						login_stage.setScene(scene);
						login_stage.show();
		
						al2 = new Alert(AlertType.CONFIRMATION);
						al2.setTitle("LOGIN SUCCESFULL");
						al2.setContentText("Welcome back, "+rs.getString(1));		
						al2.show();}
				
					else{
					al = new Alert(AlertType.ERROR);
					al.setTitle("INVALID LOGIN DETAILS");
					al.setContentText("Invalid UserName/Password entered");		
					al.show();
				}
			
				
			}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());	
				}
					catch(Exception e)
					{
					System.out.println(e.getMessage());	
					}
			}
	
			else 
				{
				al2 = new Alert(AlertType.ERROR);
				al2.setTitle("EMPTY FIELDS");
				al2.setContentText("Enter UserName/Password");		
				al2.show();
				}
			}
			
	
}
