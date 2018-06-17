package application;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

/**
 * This class allows employees to upload new projections for any existing movie.
 * Storing their informations to the designed database table. 
 * This is possible through a set of ActionEvent void methods. 
 * And multiple comboBox with a defined hierarchy. 
 * @author Federico Ziviani
 *
 */
public class EAddProjectionController {

	
	/**
	 * Declares thisprivate ComboBox titlePj.
	 */
	@FXML
	private ComboBox titlePj;
	

	/**
	 * Declares this private Label lblMovieTitle.
	 */
	@FXML
	private Label lblMovieTitle;
	
	/**
	 * Declares this private Label confirmationAddPj.
	 */
	@FXML
	private Label confirmationAddPj;
	
	/**
	 * Declares this private ComboBox timePicke.
	 */
	@FXML
	private ComboBox timePicker;
	
	/**
	 * Declares this private DatePicker datePicker.
	 */
	@FXML
	private DatePicker datePicker;
	
	/**
	 * Declares this private String title.
	 */
	private String title;
	
	/**
	 * Sets the parameter title equals to the instance variable title.
	 * @param title is the name of the string holding the value of the title.
	 */
	public void setTitle(String title) {
		this.title=title;
	}
	
	/** 
	 * Sets the text of this label on the top of the page to the text of this title.
	 */
	public void setLabel(){
		this.lblMovieTitle.setText(title);
	}
	
	/**
	 * Fills the comboBox listing the movies available retrieving their titles from the database.
	 * @throws Exception cannot connect to database when the connection to the database fails. 
	 * The list of movie can not be displayed.
	 */
	public void fillCombo(){
		
		try{
		Database d = new Database();
		
		for(String s: d.getMovies()) titlePj.getItems().add(s);
		}catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
	}
	


	/**
	 * Fills the comboBox listing the movies available retrieving their titles from the database.
	 * @param e the event to be executed when the user picks a date from this datePicker.
	 * @throws Exception cannot connect to database when the connection to the database. 
	 * The list of movie can not be displayed.
	 */
	public void fillTimes(ActionEvent e){
		
		try{
        ArrayList<String> times = new ArrayList<String>(Arrays.asList("10am", "12pm", "2pm", "4pm", "6pm", "8pm", "10pm"));    
        Database d = new Database();
		
        for(String s: d.getPjTime(datePicker.getValue().toString(), titlePj.getValue().toString())) times.remove(s);
        
        timePicker.getItems().clear();
        for (String s : times) timePicker.getItems().add(s);
		}catch(Exception ex){
			System.out.println("Cannot connect to the database...");
		}
        
        
	}
	
	/** 
	 * Saves this new movie into the database. 
	 * By storing into the table projections, in the database cinemabookingsys, this title, this date and this time.
	 * By getting the value selected in the respective comboBoxes for each of this three String arguments. 
	 * The label is dynamically set to display this message if the action of saving this new movie has been successful.
	 * @param e the event to be executed when the user clicks the button to save the new projection.
	 * @throws Exception cannot connect to database when the connection to the database fails and this action cannot be executed.
	 */
	public void savePj(ActionEvent e) {
			
			try{
			Database dB = new Database();
			dB.addPj(titlePj.getValue().toString(), datePicker.getValue().toString(), timePicker.getValue().toString());
		    confirmationAddPj.setText("Projection successfully added");
			}catch(Exception ex){
				System.out.println("Cannot connect to the database...");
			}
	}
	
	
	/**
	 * Loads the home page. The movies' titles will still be in the comboBox.
	 * The method to fill the combo is called from the controller home.
	 * That in this case is the Employee Home page.
	 * @param e the action to be executed when the home button is clicked.
	 * @throws the Exception of an invalid fxml file when it tries to loads the page. 
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
	
	
	/**
	 * Loads back the home page. The movies' titles will still be in the comboBox.
	 * The method to fill the combo is called from the controller home.
	 * That in this case is the Employee Home page.
	 * @param e action to be executed when the home button is clicked.
	 * @throws Exception of an invalid fxml file when it tries to loads the page. 
	 */
	public void back(ActionEvent e){
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
