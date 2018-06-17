package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * This class uses methods to give the employee access to all the functionalities required in this cinema booking system.
 * @author Federico Ziviani
 *
 */
public class EHomeController {


	/** 
	 * Defines the private ComboBox movies.
	 */
	@FXML
	private ComboBox movies;
	
	/** 
	 * Defines the private Label export.
	 */
	@FXML
	private Label export;
	
	/** 
	 * Defines the private String title.
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
	 * Fills the comboBox listing the movies available retrieving their titles from the database.
	 * @throws Exception cannot connect to database when the connection to the database fails. 
	 * The list of movie can not be displayed.
	 */
	public void fillCombo(){
		
		try{
		Database d = new Database();
		
		for(String s: d.getMovies()) movies.getItems().add(s);
		}catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
		
	}
	
	/**
	 * Loads this next page when the user selects a movie title from the comboBox.
	 * The controller of the next page is called here to set its label to the value selected in the comboBox. 
	 * Its TextArea to the movie description. Its ImageView to its image. And its comboBox is populated with values calling the method to fill this comboBox.
	 * @param e event that occurs when a value is selected in the comboBox.
	 * @throws Exception of an invalid fxml file when it tries to loads the page. 
	 */
	public void comboChange(ActionEvent e){
		
		try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EMovie.fxml"));
		Parent root = loader.load();
		EMovieController EMCtll = loader.getController();
		
		EMCtll.setTitle(((ComboBox) e.getSource()).getValue().toString());
		EMCtll.setLabel();
		EMCtll.setTextArea();
		EMCtll.setMovieImage();
		EMCtll.fillCombo();
		
		((ComboBox) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
	}
	
	/** 
	 * Loads this page where the employee can add a new movie.
	 * @param e event that occurs when this button is clicked.
	 * @throws Exception of an invalid fxml file when it tries to loads the page. 
	 */
	public void addMovie(ActionEvent e){
		
		try{
		Parent root = FXMLLoader.load(getClass().getResource("/application/EAddMovie.fxml"));
		((Button) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
		
	}
	
	/**
	 * Exports all projections information such as movie title, date, time, booked and available seats 
	 * to a text file in this project.
	 * @param e event that occurs when this button is clicked.
	 * @throws Input Output Exceptions 
	 */
	public void exportProjections(ActionEvent e){
		
		ArrayList<String> lines = new ArrayList<String>(100);
		
		try{
			
			Database dB = new Database();
			lines = dB.export();
		
		}catch (Exception ex){
			System.out.println("Cannot connect to the database...");
		}
			
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			
			fw = new FileWriter("src\\application\\export.txt");
			bw = new BufferedWriter(fw);
			
			for(String line: lines){
				bw.write(line+",");
				bw.newLine();
			}
			
			export.setText("Projections successfully exported!");

		} catch (IOException ex) {

			System.out.println("Invalid file");

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		
	}
	
	/**
	 * Loads the page where the employee can add a new projection time and date for an existing movie title.
	 * Calling the controller of the page it is loading, fils its comboBox with the titles of the movies.  
	 * @param e event that occurs when this button is clicked.
	 * @throws Exception of an invalid fxml file when it tries to loads the page. 
	 */
	public void addProjection(ActionEvent e){
		
		try{
			
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EAddProjection.fxml"));
		Parent root = loader.load();
		EAddProjectionController EAPCtll = loader.getController();
		EAPCtll.fillCombo();
		EAPCtll.setTitle(title);
		((Button) e.getSource()).getScene().setRoot(root);
		
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
		
	}
	
	/**
	 * Loads the page where all average ratings for the movies in the system are displayed.
	 * Calling the controller of the page it is loading and its method to populate the TableView with ratings. 
	 * @param e event that occurs when this button is clicked.
	 * @throws Input Output Exception.
	 */
	public void seeRatings(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ERatingsList.fxml"));
		Parent root = loader.load();
		ERatingsListController ERLCtll = loader.getController();
		
		ERLCtll.loadRatings();
		
		((Button) e.getSource()).getScene().setRoot(root);
	}
	
	/**
	 * Logs you out from the system.
	 * @param e event that occurs when this button is clicked.
	 * @throws Exception of an invalid fxml file when it tries to loads the page. 
	 */
	public void logOut(ActionEvent e){
		
		try{
			
		Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		((Button) e.getSource()).getScene().setRoot(root);
		
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
		
	}
}
