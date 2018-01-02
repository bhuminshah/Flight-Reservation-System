package model; //Package declaration
import java.sql.Connection; //Import for using the Connection Class
import java.sql.Date;
import java.sql.PreparedStatement; //Import for using the PreparedStatement methods
import java.sql.ResultSet; //Importing for using ResultSet for storing the objects
import java.sql.Statement; //Import for using the Statement class
import java.time.LocalDate;
import java.util.List;

import model.Admin.Userinfo;

public class DAOMODEL 
{
	Connector c  = new Connector(); //Connector object creation
	ResultSet rs_dao; //ResultSet object declaration
	
	
	public void prc_insert(Userinfo sp){
		Connection cn = null; //Connection variable declaration
		 //Statement variable declaration
		 int cc; //Counter variable to count number of insertions performed
		//String ins="insert into Userinfo(User_name,Email_id,Phone,Street,User_Id) values(?,?,?,?,?)"; //Insertion string
		String ins="insert into Userinfo_bhupra(User_name,Email_id,Phone,Street,Password,user_type) values(?,?,?,?,?,'User')"; //Insertion string
		
		try //Block to trap exceptions
		{
			cc=0; //Initiliasation to 0 for insertion counter
			cn=c.prc_connection(); //Call to prc_connection for establishing database connection
		PreparedStatement ps=cn.prepareStatement(ins); //PreparedStatement object creation by passing insertion query string

		System.out.println("Data insertion initiated"); // Data insertion begins

			ps.setString(1, sp.getUser_name()); 
			ps.setString(2, sp.getEmail_id()); 
			ps.setInt(3, sp.getPhone()); 
			ps.setString(4, sp.getStreet()); 
			ps.setString(5, sp.getPassword()); 
			ps.execute(); //Executing the prepared statement query
		

		
		cn.close(); //Closing the connection
		}
		
		catch(Exception e) //Catching the exceptions
		{
			System.out.println(e.getMessage()); //Printing the SQLException message
		}
	}
	

public ResultSet prc_retrive(String user_name, String password)
{	
  try //For trapping exceptions
  {
	Statement st=null; //Statement object declaration 
	Connection cn=null; //Connection object declaration
	cn=c.prc_connection(); //Call to accept the database connection
	st=cn.createStatement(); //Creating a statement variable
	rs_dao=st.executeQuery("Select user_name from Userinfo_bhupra where user_name='"+user_name+"' and password='"+password+"' and user_type = 'User'"); //Executing the select query and storing the result
	return rs_dao; //Return the resultSet to the calling method
  }
  
  catch(Exception e) //For catchingt the exceptions
  {
	System.out.println(e.getMessage()); //printing the exception message
  }
return rs_dao; //Returning the resultset object
}

public ResultSet prc_retrive_admin(String user_name, String password)
{	
  try //For trapping exceptions
  {
	Statement st=null; //Statement object declaration 
	Connection cn=null; //Connection object declaration
	cn=c.prc_connection(); //Call to accept the database connection
	st=cn.createStatement(); //Creating a statement variable
	rs_dao=st.executeQuery("Select user_name from Userinfo_bhupra where user_name='"+user_name+"' and password='"+password+"' and user_type = 'Admin'"); //Executing the select query and storing the result
	return rs_dao; //Return the resultSet to the calling method
	
  }
  
  catch(Exception e) //For catchingt the exceptions
  {
	System.out.println(e.getMessage()); //printing the exception message
  }
return rs_dao; //Returning the resultset object
}

		public ResultSet prc_retrive_employee(String user_name, String password)
		{	
		  try //For trapping exceptions
		  {
			ResultSet rs_emp;
			Statement st=null; //Statement object declaration 
			Connection cn=null; //Connection object declaration
			cn=c.prc_connection(); //Call to accept the database connection
			st=cn.createStatement(); //Creating a statement variable
			rs_emp = st.executeQuery("Select user_name from Userinfo_bhupra where user_name='"+user_name+"' and password='"+password+"' and user_type = 'Employee'"); //Executing the select query and storing the result
			return rs_emp; //Return the resultSet to the calling method
			
		  }
		  
		  catch(Exception e) //For catching the exceptions
		  {
			System.out.println(e.getMessage()); //printing the exception message
		  }
		return rs_dao; //Returning the resultset object
		}

	public ResultSet prc_username_check(String username)
	{	
	  try //For trapping exceptions
	  {
		Statement st=null; //Statement object declaration 
		Connection cn=null; //Connection object declaration
		cn=c.prc_connection(); //Call to accept the database connection
		st=cn.createStatement(); //Creating a statement variable
		rs_dao=st.executeQuery("Select user_name from Userinfo_bhupra where user_name = trim('"+username+"')"); //Executing the select query and storing the result
		return rs_dao; //Return the resultSet to the calling method
		
	  }
	  
	  catch(Exception e) //For catchingt the exceptions
	  {
		System.out.println(e.getMessage()); //printing the exception message
	  }
	return rs_dao; //Returning the resultset object
	}
	
public ResultSet prc_retrive_dropbox()
	{	
	  try //For trapping exceptions
	  {
		Statement st=null; //Statement object declaration 
		Connection cn=null; //Connection object declaration
		cn=c.prc_connection(); //Call to accept the database connection
		st=cn.createStatement(); //Creating a statement variable
		rs_dao=st.executeQuery("Select airport_Code from airport_bhupra order by airport_code"); //Executing the select query and storing the result
		return rs_dao; //Return the resultSet to the calling method
		
	  }
	  
	  catch(Exception e) //For catchingt the exceptions
	  {
		System.out.println(e.getMessage()); //printing the exception message
	  }
	return rs_dao; //Returning the resultset object
	}
	
	public ResultSet prc_viewBooking_dao()
	{
		try{
		
		Connection cn_Search=null;
		Statement st_Search=null;
		cn_Search=c.prc_connection();
		st_Search=cn_Search.createStatement();
		String query_str="Select from_city AS FROM_CITY, to_city AS TO_CITY, flight_date AS TRAVEL_DATE, pax_count AS PASSENGER_COUNT, total_price AS TOTAL_PRICE from booking_bhupra;"; //Executing the select query and storing the result
		rs_dao=st_Search.executeQuery(query_str);
		
		}
		catch (Exception e){
			System.out.println(e.getMessage()); //printing the exception message	
		
		}
		return rs_dao;
		
	}
		
public ResultSet prc_process_Search_results(String origin,String destination, Date from)
{
	try
	{
	Connection cn_Search=null;
	Statement st_Search=null;
	cn_Search=c.prc_connection();
	st_Search=cn_Search.createStatement();
	String query_str="select a.airport_name,a.airport_code,"+
	"a.airport_city,f.from_city,f.to_City,f.flight_date,ifnull(DATE_FORMAT(f.flight_time,'%H:%i:%s'),'00:30:30') time_of_Depart,f.flight_price,f.flight_int from flightschedule_bhupra f, airport_bhupra a where f.airport_id=a.airport_id and f.from_City=(select ab.airport_city from airport_bhupra ab where ab.airport_Code='"+origin+"')"+
	"and f.to_city=(select ab.airport_city from airport_bhupra ab where ab.airport_Code='"+destination+"') and flight_Date='"+from+"'";


System.out.println(origin);
System.out.println(origin+" "+from+" "+destination);
	
rs_dao=st_Search.executeQuery(query_str);
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage()); //printing the exception message	
	}
	return rs_dao;
	
}
	
	public void prc_deleteBooking_dao(int a)
	{
		Connection cn=null; //Connection object declration
		cn=c.prc_connection(); //Connection object instantiation
		System.out.println(a);
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
	
		public void prc_insert_payment(String payment_meth,int booking_id,int v_cost)
	{
		System.out.println("In insert1 "+id);
		Connection cn=null; //Connection variable declaration
		ResultSet rs;
		Statement st; //Statement variable declaration
		int cc; //Counter variable to count number of insertions performed
				String ins_pay="insert into payment_bhupra(booking_id,totalprice,payment_method) values(?,?,?)";
		try //Block to trap exceptions
		{
			cn=c.prc_connection(); //Call to prc_connection for establishing database connection
			System.out.println("In insert2");
		PreparedStatement ps=cn.prepareStatement(ins_pay); //PreparedStatement object creation by passing insertion query string

			ps.setInt(1, id); 
			ps.setInt(2, cost_c); 
			ps.setString(3, payment_meth); 
		
			ps.execute(); //Executing the prepared statement query
		
		
		cn.close(); //Closing the connection
		
		}
		
		catch(Exception e) //Catching the exceptions
		{
			System.out.println(e.getMessage()); //Printing the SQLException message
		}
	}
	
	public ResultSet prc_retrive_user_Details(String user,String password)
{	
  try //For trapping exceptions
  {
	  System.out.println(user+"In RS"+password);
	Statement st=null; //Statement object declaration 
	Connection cn=null; //Connection object declaration
	cn=c.prc_connection(); //Call to accept the database connection
	st=cn.createStatement(); //Creating a statement variable
	System.out.println(user+" "+password);
	rs_dao=st.executeQuery("select user_name,email_id,phone,user_id from userinfo_bhupra where user_name='"+user+"' and password='"+password+"'"); //Executing the select query and storing the result
	return rs_dao; //Return the resultSet to the calling method
	
  }
  
  catch(Exception e) //For catchingt the exceptions
  {
	System.out.println(e.getMessage()); //printing the exception message
  }
return rs_dao; //Returning the resultset object
}

public static int cost_c,id;
public void prc_insert_booking(String user_id,String from_City, String to_city,LocalDate depart_Date,int count,int cost,List<String> al)
{
	System.out.println("In insert1 ");
	Connection cn=null; //Connection variable declaration
	ResultSet rs;
	cost_c=cost;
	Statement st; //Statement variable declaration
	int cc; //Counter variable to count number of insertions performed
	String ins="insert into booking_bhupra(user_id,from_city,to_city,flight_Date,pax_count,total_price,payment_status) values(?,?,?,?,?,?,?)"; //Insertion string
	String ins_pass="insert into passengerDetails_bhupra(booking_id,passenger_name,cost) values(?,?,?)";
	try //Block to trap exceptions
	{
		cn=c.prc_connection(); //Call to prc_connection for establishing database connection
		System.out.println("In insert2 dao");
	PreparedStatement ps=cn.prepareStatement(ins,Statement.RETURN_GENERATED_KEYS); //PreparedStatement object creation by passing insertion query string
	PreparedStatement ps_pass=cn.prepareStatement(ins_pass); //PreparedStatement object creation by passing insertion query string

		ps.setString(1, user_id); 
		ps.setString(2, from_City); 
		ps.setString(3, to_city); 
		ps.setObject(4,depart_Date); 
		ps.setInt(5, count); 
		ps.setInt(6, cost); 
		ps.setString(7, "Pending");
		ps.execute(); //Executing the prepared statement query
	
		rs=ps.getGeneratedKeys();
	
	if(rs.next())
	id=rs.getInt(1);
	
	for(int i=0;i<al.size();i++)
	{
	ps_pass.setInt(1, id);
	ps_pass.setString(2,al.get(i));
	ps_pass.setInt(3, cost/count);
	ps_pass.execute();
	}
	
	
	cn.close(); //Closing the connection
	
	}
	
	catch(Exception e) //Catching the exceptions
	{
		System.out.println(e.getMessage()); //Printing the SQLException message
	}
}


	
}