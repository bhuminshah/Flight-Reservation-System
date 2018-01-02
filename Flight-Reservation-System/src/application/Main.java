package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application
{
/**
 * Method to Launch the application
 */
@Override
	public void start(Stage root) throws Exception {
		// TODO Auto-generated method stub
			try{
				Pane root_layout = (Pane) FXMLLoader.load(getClass().getResource("/view/Startpage.fxml"));
				Scene scene = new Scene(root_layout);
				root.setScene(scene);
				root.setTitle("Flight Reservation System");
				root.show();
			    }
		        catch (Exception e)
				{
		    		// TODO Auto-generated catch block
		    		System.out.println(e.getMessage());
		    	}   
		    }

/**
 * Main method to call the application launch
 * @param args Default Argument
 */
public static void main(String[] args) 
{
	// TODO Auto-generated method stub
	try{
launch(args);
	}
	catch(Exception e)
	{
		System.out.println("");
	}
}
}