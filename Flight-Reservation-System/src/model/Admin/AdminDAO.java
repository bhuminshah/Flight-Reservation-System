package model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Connector;

public class AdminDAO {

	
	Connector c  = new Connector(); //Connector object creation
	ResultSet rs; //ResultSet object declaration
	
	public  ResultSet viewUser_dao(){
		
		
		try //For trapping exceptions
		  {
			Statement st=null; //Statement object declaration 
			Connection cn=null; //Connection object declaration
			cn=c.prc_connection(); //Call to accept the database connection
			st=cn.createStatement(); //Creating a statement variable
			rs=st.executeQuery("Select * from userinfo_bhupra;"); //Executing the select query and storing the result
			return rs; //Return the resultSet to the calling method
		  }
		  
		  catch(Exception e) //For catchingt the exceptions
		  {
			System.out.println(e.getMessage()); //printing the exception message
		  }
		return rs; //Returning the resultset object
		}
	
		
	public void prc_deleteUser_dao(int a){
		Connection cn=null; //Connection object declration
		cn=c.prc_connection(); //Connection object instantiation
		String del="delete from userinfo_bhupra where user_id ="+a; //Deletion query 
		
		try //For trapping exceptions
		{
		PreparedStatement ps=cn.prepareStatement(del); //PreparedStatement Object creation by passing the deletion strin
		ps.execute(); //Executing the PreparedStatement
		cn.close();	//Closing the connection
		}
		
		catch(Exception e) //For Catching the exceptions 
		{
			System.out.println(e.getMessage()); //Printing the exception message
		}
		
	}
	
	public ResultSet prc_username_check(String username)
	{	
	  try //For trapping exceptions
	  {
		Statement st=null; //Statement object declaration 
		Connection cn=null; //Connection object declaration
		cn=c.prc_connection(); //Call to accept the database connection
		st=cn.createStatement(); //Creating a statement variable
		rs=st.executeQuery("Select user_name from Userinfo_bhupra where user_name = trim('"+username+"')"); //Executing the select query and storing the result
		return rs; //Return the resultSet to the calling method
		
	  }
	  
	  catch(Exception e) //For catchingt the exceptions
	  {
		System.out.println(e.getMessage()); //printing the exception message
	  }
	return rs; //Returning the resultset object
	}
	
	
	
	
	public void prc_addUser_dao(Admin a){
		
		Connection cn = null; //Connection variable declaration
		ResultSet rs; //ResultSet variable declaration
		Statement st; //Statement variable declaration
		String ins="insert into Userinfo_bhupra(User_name,Email_id,Phone,Street,Password,user_type) values(?,?,?,?,?,?)"; //Insertion string
		
		try //Block to trap exceptions
		{
			int cc=0; //Initiliasation to 0 for insertion counter
			cn=c.prc_connection(); //Call to prc_connection for establishing database connection
		PreparedStatement ps=cn.prepareStatement(ins); //PreparedStatement object creation by passing insertion query string

		System.out.println("Data insertion initiated"); // Data insertion begins

			ps.setString(1, a.getUser_name()); 
			ps.setString(2, a.getEmail_Id()); 
			ps.setInt(3, a.getPhone()); 
			ps.setString(4, a.getStreet()); 
			ps.setString(5, a.getPassword()); 
			ps.setString(6, a.getType());
			ps.execute(); //Executing the prepared statement query
		

		
		cn.close(); //Closing the connection
		}
		
		catch(Exception e) //Catching the exceptions
		{
			System.out.println(e.getMessage()); //Printing the SQLException message
		}
		
	}
	
	public ResultSet prc_retreive_dao(int UserId)
	{	
	  try //For trapping exceptions
	  {
		Statement st=null; //Statement object declaration 
		Connection cn=null; //Connection object declaration
		cn=c.prc_connection(); //Call to accept the database connection
		st=cn.createStatement(); //Creating a statement variable
		rs=st.executeQuery("Select User_name, Email_id, Phone, Street, Password, user_type from Userinfo_bhupra where user_name = trim('"+UserId+"')"); //Executing the select query and storing the result
		return rs; //Return the resultSet to the calling method
		
	  }
	  
	  catch(Exception e) //For catchingt the exceptions
	  {
		System.out.println(e.getMessage()); //printing the exception message
	  }
	return rs; //Returning the resultset object
	}
	
	public void prc_EditUser_dao(Admin ads){
		Connection cn=null; //Connection object declration
		cn=c.prc_connection(); //Connection object instantiation
		String edit ="UPDATE userinfo_bhupra set user_name ='"+ads.getUser_name()+"',Email_Id ='"+ads.getEmail_Id()+"', Phone ="+ads.getPhone()+", Street ='"+ads.getStreet()+"', Password ='"+ads.getPassword()+"', user_type= '"+ads.getType()+"' where User_Id ="+ads.getUser_ID(); //Deletion query 
		
		try //For trapping exceptions
		{
		PreparedStatement ps=cn.prepareStatement(edit); //PreparedStatement Object creation by passing the deletion strin
		ps.execute(); //Executing the PreparedStatement
		cn.close();	//Closing the connection
		}
		
		catch(Exception e) //For Catching the exceptions 
		{
			e.printStackTrace();
			//System.out.println(e.getMessage()); //Printing the exception message
		}
		
	}
	public ResultSet prc_airport_check(String airport)
	{	
	  try //For trapping exceptions
	  {
		Statement st=null; //Statement object declaration 
		Connection cn=null; //Connection object declaration
		cn=c.prc_connection(); //Call to accept the database connection
		st=cn.createStatement(); //Creating a statement variable
		rs=st.executeQuery("Select airport_code from airport_bhupra where airport_code = trim('"+airport+"')"); //Executing the select query and storing the result
		return rs; //Return the resultSet to the calling method
		
	  }
	  
	  catch(Exception e) //For catchingt the exceptions
	  {
		System.out.println(e.getMessage()); //printing the exception message
	  }
	return rs; //Returning the resultset object
	}
	
	public void prc_addAirport_dao(Airport a){
		
		Connection cn = null; //Connection variable declaration
		ResultSet rs; //ResultSet variable declaration
		Statement st; //Statement variable declaration
		String ins="insert into Airport_bhupra(airport_name,airport_city,airport_code) values(?,?,?)"; //Insertion string
		
		try //Block to trap exceptions
		{
			int cc=0; //Initiliasation to 0 for insertion counter
			cn=c.prc_connection(); //Call to prc_connection for establishing database connection
		PreparedStatement ps=cn.prepareStatement(ins); //PreparedStatement object creation by passing insertion query string

		System.out.println("Data insertion initiated"); // Data insertion begins

			ps.setString(1, a.getAirport_name()); 
			ps.setString(2, a.getAirport_City()); 
			ps.setString(3, a.getAirport_code()); 
			ps.execute(); //Executing the prepared statement query
		

		
		cn.close(); //Closing the connection
		}
		
		catch(Exception e) //Catching the exceptions
		{
			System.out.println(e.getMessage()); //Printing the SQLException message
		}
		
	}
public  ResultSet viewAirport_dao(){
		
		
		try //For trapping exceptions
		  {
			Statement st=null; //Statement object declaration 
			Connection cn=null; //Connection object declaration
			cn=c.prc_connection(); //Call to accept the database connection
			st=cn.createStatement(); //Creating a statement variable
			rs=st.executeQuery("Select * from airport_bhupra;"); //Executing the select query and storing the result
			return rs; //Return the resultSet to the calling method
			
		  }
		  
		  catch(Exception e) //For catchingt the exceptions
		  {
			System.out.println(e.getMessage()); //printing the exception message
		  }
		return rs; //Returning the resultset object
		
}
	}
	
	
	
	

