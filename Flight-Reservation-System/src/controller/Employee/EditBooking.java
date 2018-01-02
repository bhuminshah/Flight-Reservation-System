package controller.Employee;

import java.sql.ResultSet;

import java.time.LocalDate;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import model.Employee.Employee;
import model.Employee.EmployeeDAO;

public class EditBooking 
{
	

	@FXML
    private Label from_city;

    @FXML
    private Label to_city;

    @FXML
    private Label passengers;

    @FXML
    private DatePicker date;

    @FXML
    private Label total_cost;
    
    @FXML
    private Button Update;
	
    Alert a1, a2;
	
    Employee emp = new Employee();
	EmployeeDAO dao = new EmployeeDAO();
	ResultSet rs;
	
	public void setedituser(Employee emp){
		this.emp= new Employee();
		this.emp = emp;
		
		from_city.setText(emp.getFrom_city());
		to_city.setText(emp.getTo_city());
		passengers.setText(String.valueOf(emp.getPax_count()));
		total_cost.setText(String.valueOf (emp.getTotal_price()));
		
		}
	
    public void edit()
    {
    	try{
    	
    		LocalDate dte;
    		dte=date.getValue();
    	

    	System.out.println(from_city.getText()+" "+to_city.getText());
    	rs = dao.prc_checkBooking_dao(from_city.getText(),to_city.getText());
    	
    	if(rs.next())
    	{
    		System.out.println("In RS update");
   	 		emp.setFlight_date(date.getAccessibleText());
    		a2 = new Alert(AlertType.CONFIRMATION);
			a2.setTitle("CONFIRM BOOKING");
			a2.setHeaderText("CONFIRM BOOKING");
			a2.setContentText("Are you sure?");
			Optional<ButtonType> result = a2.showAndWait();
			if (result.get() == ButtonType.OK){
				

				dao.prc_updateBooking(emp.getBooking_id(),dte);
				System.out.println(emp.getBooking_id());
				
				}
			
			else{
				
				System.out.println("Thanks");
			}
		    		
    	}
    	else{
    		a1 = new Alert(AlertType.ERROR);
			a1.setTitle("NO FLIGHTS FOUND");
			a1.setHeaderText("NO FLIGHTS ON SELECTED DATE");
			a1.setContentText("No flights on the selected date, re-select the date");
    	}
    		
    }
    	catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    	
    	
    	Stage temp=(Stage) Update.getScene().getWindow();
		temp.close();		
		
    	
    }
    
}
