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
 * This class contains methods and fields to display add a new projection for a given movie, directly from this movie poage. 
 * @author Federico Ziviani
 *
 */
public class EMovieAddProjectionController {
		
	/**
	 * Defines this private Label lblMovieTitle.
	 */
	@FXML
	private Label lblMovieTitle;
	
	/**
	 * Defines this private Label confirmationAddPj.
	 */
	@FXML
	private Label confirmationAddPj;
		
	/**
	 * Defines this private ComboBox timePicker.
	 */
	@FXML
	private ComboBox timePicker;
	
	/**
	 * Defines this private DatePicker datePicker.
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
	public void setLabel() {
		lblMovieTitle.setText(title);
	}
		


		
	/**
	 * Populates the comboBox times with the values corresponding to the array list times, where the values corresponding to any already existing  projection are removed from the list.
	 * The trigger for this event is the date picked from the datePicker. 
	 * @param e event that occurs when a time is selected from the datePicker.
	 * @throws Exception when the method called cannot set a connection with the database.
	 */
	public void fillTimes(ActionEvent e) {
	
	    ArrayList<String> times = new ArrayList<String>(Arrays.asList("10am", "12pm", "2pm", "4pm", "6pm", "8pm", "10pm"));
	    
	    try{
	    Database d = new Database();
			
	    for (String s: d.getPjTime(datePicker.getValue().toString(), title.toString())) times.remove(s);
	    timePicker.getItems().clear();
	    for (String s : times) timePicker.getItems().add(s);
	    
	    }catch(Exception ex){
			System.out.println("Cannot connect to the database...");
		}    
	        
	}
		

		
	/**
	 * Saves a new projection once a date and time has been selected from the respective comboBoxes.
	 * Also a message confirmation is displayed in the label if the projection is successfully added to the database.
	 * @param e event that occurs when the button is clicked.
	 */
	public void savePj(ActionEvent e) {
		
		try{
		Database dB = new Database();
		dB.addPj(lblMovieTitle.getText().toString(), datePicker.getValue().toString(), timePicker.getValue().toString());
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
	 * Loads the previous page. alling this controller and its method.
	 * The values of its comboBox are set to the dates and times of projections available for this movie. calling this controller and its method.
	 * Also the image and the description referring to the specific movie are loaded.
	 * @param e event that occurs when the button is clicked.
	 * @throws Exception of invalid fxml file format.
	 */
	public void back(ActionEvent e){
		try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EMovie.fxml"));
		Parent root = loader.load();
		EMovieController EMCtll = loader.getController();
	
		EMCtll.setTitle(title);
		EMCtll.setLabel();
		EMCtll.fillCombo();
		EMCtll.setTextArea();
		EMCtll.setMovieImage();
		((Button) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
		
		}

	}

