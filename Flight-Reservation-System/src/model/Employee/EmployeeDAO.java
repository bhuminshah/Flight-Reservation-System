package model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import model.Connector;

public class EmployeeDAO {
	
	Connector c  = new Connector(); //Connector object creation
	ResultSet rst, rst1, rs_updateflight; //ResultSet object declaration
	Statement st;

	public ResultSet prc_viewBooking_dao()
	{
		try{
		
		Connection cn = null;
		cn=c.prc_connection();
		st=cn.createStatement();
		String query_str="select booking_id,user_id,from_City,to_city,flight_Date,pax_Count,total_price,payment_status from booking_bhupra"; //Executing the select query and storing the result
		rst=st.executeQuery(query_str);
		}
		catch (Exception e){
			System.out.println(e.getMessage()); //printing the exception message	
		}
		return rst;	
	}
	
	public void prc_deleteBooking_dao(int a)
	{
		Connection cn=null; //Connection object declaration
		cn=c.prc_connection(); //Connection object instantiation
		String del="delete from booking_bhupra where booking_id ="+a; //Deletion query 
		
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
	Employee emp = new Employee();
	
	public ResultSet prc_checkBooking_dao(String from_city,String to_city){
		
		try{
			Connection cn = null;
			cn=c.prc_connection();
			st=cn.createStatement();
			String query_str="select flight_int,airport_id,from_City,to_City,flight_Date,flight_price from flightschedule_bhupra where from_city ='"+from_city+"' and to_city = '"+to_city+"'"; //Executing the select query and storing the result
			rst1=st.executeQuery(query_str);	
			return rst1;
		}
		
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		return rst;
	}
	
	public ResultSet prc_viewFlights_dao(){
		
		try{
			
			Connection cn = null;
			cn=c.prc_connection();
			st=cn.createStatement();
			String query_str="select flight_int,airport_id,from_city,to_City,flight_Date,flight_price from flightschedule_bhupra"; //Executing the select query and storing the result
			rst=st.executeQuery(query_str);
			}
			catch (Exception e){
				System.out.println(e.getMessage()); //printing the exception message	
			}
			return rst;	
	}
	
	public void prc_AddingFlights(int aid, String from, String to,LocalDate dte,int cost)
	{
		
		System.out.println("In insert1 "+aid);
		Connection cn=null; //Connection variable declaration
		ResultSet rs;
		Statement st; //Statement variable declaration
		int cc; //Counter variable to count number of insertions performed
				String ins_pay="insert into flightschedule_bhupra(airport_id,from_City,to_City,flight_Date,flight_price) values(?,?,?,?,?)";
		try //Block to trap exceptions
		{
			cn=c.prc_connection(); //Call to prc_connection for establishing database connection
			System.out.println("In insert2");
		PreparedStatement ps=cn.prepareStatement(ins_pay); //PreparedStatement object creation by passing insertion query string

			ps.setInt(1, aid); 
			ps.setString(2, from); 
			ps.setString(3, to);
			ps.setObject(4, dte);
			ps.setInt(5, cost);
			ps.execute(); //Executing the prepared statement query
		
		
		cn.close(); //Closing the connection
		
		}
		
		catch(Exception e) //Catching the exceptions
		{
			System.out.println(e.getMessage()); //Printing the SQLException message
		}
		
		
	}
	
	
	public ResultSet prc_retrive_dropbox_airport()
	{	
		ResultSet rs_dao=null;
	  try //For trapping exceptions
	  {
		  
		Statement st=null; //Statement object declaration 
		Connection cn=null; //Connection object declaration
		cn=c.prc_connection(); //Call to accept the database connection
		st=cn.createStatement(); //Creating a statement variable
		rs_dao=st.executeQuery("Select airport_city from airport_bhupra order by airport_code"); //Executing the select query and storing the result
		return rs_dao; //Return the resultSet to the calling method
		
	  }
	  
	  catch(Exception e) //For catchingt the exceptions
	  {
		System.out.println(e.getMessage()); //printing the exception message
	  }
	return rs_dao; //Returning the resultset object
	}
	
	
	
	
	
	public ResultSet prc_AirportList()
	{
		ResultSet rs_Airport=null;
		
		try{
			
			Connection cn = null;
			cn=c.prc_connection();
			st=cn.createStatement();
			String query_str="select airport_id,airport_name from airport_bhupra;"; //Executing the select query and storing the result
			rs_Airport=st.executeQuery(query_str);
			}
			catch (Exception e){
				System.out.println(e.getMessage()); //printing the exception message	
			}
			return rs_Airport;
			}
	
	
	public ResultSet prc_get_airport_id(String aname)
	{
		ResultSet rs_Airport=null;
		
		try{
			
			Connection cn = null;
			cn=c.prc_connection();
			st=cn.createStatement();
			String query_str="select airport_id from airport_bhupra where airport_name='"+aname+"'"; //Executing the select query and storing the result
			rs_Airport=st.executeQuery(query_str);
			}
			catch (Exception e){
				System.out.println(e.getMessage()); //printing the exception message	
			}
			return rs_Airport;
		}

	public void prc_updateBooking(int booking_id, LocalDate date) {
		// TODO Auto-generated method stub
		try{int i;
			System.out.println("In update");
			Connection cn = null;
			cn=c.prc_connection();
			st=cn.createStatement();
			String query_str="update booking_bhupra set flight_date ='"+date+"' where booking_id ="+booking_id+";"; //Executing the select query and storing the result
			 i=st.executeUpdate(query_str);
			 System.out.println(i+"Update result");
			}
			catch (Exception e){
				System.out.println(e.getMessage()); //printing the exception message	
			}
		
		
	}
	
}
