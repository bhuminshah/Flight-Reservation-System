package model;

/**
 * Connector class performs a task of establishing a connection between the database schema and the java program for performing the required CRUD operations
 * */

import java.sql.Connection;
import java.sql.DriverManager;
public class Connector {

	/**
	 * It is a method which specifies the Driver name, server name, user name and password as inputs and gets a connection with the required credentials if they are matched correctly
	 * @return con A connection object with the database connectivity to the papademas server
	 */
	public Connection prc_connection() 
	{
		Connection con=null; //Connection object initiliasation
		try //For trapping exceptions
		{
			Class.forName("com.mysql.jdbc.Driver"); // Driver for JDbc is being created
			con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/fp","dbfp","510");
		return con; //returning a conncetion object
		}

		catch(Exception e) //For catching exceptions
		{
			System.out.println(e.getMessage()+" exception"); //Printing the exception message 
		}
		return con; //Returning a connection object
	}
}
