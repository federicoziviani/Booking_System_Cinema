package application;


import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * This class gives the employee access to the cinema room representation for a given projection time.
 * The seatings are represented with checkBoxes that are different if the seat is booked or available. 
 * @author Federico Ziviani
 *
 */
public class EBookingController {



	/**
	 * Defines this private Label projection.
	 */
	@FXML
	private Label projection;
	
	/**
	 * Defines this private Label available.
	 */
	@FXML
	private Label available;
	
	/**
	 * Defines this private Label  unavailable.
	 */
	@FXML
	private Label unavailable;
	
	/**
	 * Defines each of these 12 private checkBoxes  that represents the seats in this cinema room.
	 */
	@FXML
	private CheckBox A1;
	@FXML
	private CheckBox A2;
	@FXML
	private CheckBox A3;
	@FXML
	private CheckBox A4;
	@FXML
	private CheckBox B1;
	@FXML
	private CheckBox B2;
	@FXML
	private CheckBox B3;
	@FXML
	private CheckBox B4;
	@FXML
	private CheckBox C1;
	@FXML
	private CheckBox C2;
	@FXML
	private CheckBox C3;
	@FXML
	private CheckBox C4;
	
	
	/**
	 * Defines this private String title.
	 */
	private String title;
	
	/**
	 * Defines this private String date.
	 */
	private String date;
	
	/**
	 * Defines this private String time.
	 */
	private String time;
	
	/**
	 * Declares and initializes this private Array List of type String with the name bookedSeats. 
	 * And gives it an initial capacity of 12. 
	 */
	private ArrayList<String> bookedSeats = new ArrayList<String>(12);
	
	
	
	
	


	/**
	 * Sets the parameter time equals to the instance variable time.
	 * @param time is the name of the string holding the value of the time.
	 */
	public void setTime(String time){
		this.time = time;
	}
	
	/**
	 * Sets the parameter title equals to the instance variable title.
	 * @param title is the name of the string holding the value of the title.
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/**
	 * Sets the parameter date equals to the instance variable date.
	 * @param date is the name of the string holding the value of the date.
	 */
	public void setDate(String date){
		this.date = date;
	}
	
	/** 
	 * Sets the text of this label to the values of the parameters time, title and date.
	 */
	public void setText(){
		projection.setText(title + " AT " + time);
	}
	
	/**
	 * Sets the values of the 12 comboBoxes to available or unavailable.
	 * Retrieving from the database the data about all the bookings made for this projection. 
	 * @throws  Exception cannot connect to database when the connection to the database fails. 
	 */
	public void setSeats(){
		try{
			
		Database dB = new Database();
		int count = 0;
		
		for (String s: dB.getSeats(title, date, time)){
			count++;
			switch(s){
			case "A1":
				A1.setIndeterminate(true);
				A1.setDisable(true);
				break;
			case "A2":
				A2.setIndeterminate(true);
				A2.setDisable(true);
				break;
			case "A3":
				A3.setIndeterminate(true);
				A3.setDisable(true);
				break;
			case "A4":
				A4.setIndeterminate(true);
				A4.setDisable(true);
				break;
			case "B1":
				B1.setIndeterminate(true);
				B1.setDisable(true);
				break;
			case "B2":
				B2.setIndeterminate(true);
				B2.setDisable(true);
				break;
			case "B3":
				B3.setIndeterminate(true);
				B3.setDisable(true);
				break;
			case "B4":
				B4.setIndeterminate(true);
				B4.setDisable(true);
				break;
			case "C1":
				C1.setIndeterminate(true);
				C1.setDisable(true);
				break;
			case "C2":
				C2.setIndeterminate(true);
				C2.setDisable(true);
				break;
			case "C3":
				C3.setIndeterminate(true);
				C3.setDisable(true);
				break;
			case "C4":
				C4.setIndeterminate(true);
				C4.setDisable(true);
				break;
			default:
				break;
			}
			
		}
		
		unavailable.setText(new Integer(count).toString());
		available.setText(new Integer(12-count).toString());
		}catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
	}
	

	/**
	 * Loads back this previous page.
	 * Before reloading it it calls this controller to set the text of the movie title.
	 * set the description to the movie description. And set the image view to the movie image. 
	 * The method to fill the combo is called from the controller home.
	 * That in this case is the Employee Home page.
	 * @param e event that occurs when the home button is clicked.
	 * @throws Exception of an invalid fxml file when it tries to loads the page in a wrong format. 
	 */
	public void back(ActionEvent e){
		try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EMovie.fxml"));
		Parent root = loader.load();
		EMovieController EMCtll = loader.getController();
		
		EMCtll.setTitle(title);
		EMCtll.setLabel();
		EMCtll.setTextArea();
		EMCtll.setMovieImage();
		
		((Button) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
	}
	
	/**
	 * Loads the home page. The movies' titles will still be in the comboBox.
	 * The method to fill the combo is called from the controller home.
	 * That in this case is the Employee Home page.
	 * @param e the action to be executed when the home button is clicked.
	 * @throws the Exception of an invalid fxml file when it tries to loads the page in a wrong format. 
	 */
	public void home(ActionEvent e){
		try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EHome.fxml"));
		Parent root = loader.load();
		EHomeController eHomeCtll = loader.getController();
		eHomeCtll.fillCombo();
		((Button) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
	}
    }
