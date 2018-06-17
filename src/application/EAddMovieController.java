package application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * This class allows employees to add new movies to the system.
 * Storing their informations to the designed database table. 
 * This is possible through a set of ActionEvent void methods. 
 * @author Federico Ziviani
 *
 */
public class EAddMovieController {
	/**
	 * Declares this private private TextField title.
	 */
	@FXML
	private TextField title;
	
	/**
	 * Declares this private private TextArea description.
	 */
	@FXML
	private TextArea description;
	
	/**
	 * Declares this private ImageView image.
	*/
	@FXML
	private ImageView image;
	
	/**
	 * Declares this private Button browseButton.
	*/
	@FXML
	private Button browseButton;
	
	/**
	 * Declares this privateLabel lblMessage.
	*/
	@FXML
	private Label lblMessage;
	
	/**
	 * Defines this String imgName as an empty String.
	*/
	private String imgName="";
	
	
	
	/**
	 * Saves this image by getting its name and saving it into the database using the URL.
	 * This dialogue window to browse the images from the computer is opened with this file chooser.
	 * The image URL is stored in the String URL, that is set to be initially empty.
	 * With the if statement it checks if the image is not null before storing its name into the database.
	 * It also shows this image into the ImageView, preserving its proportions.
	 * 
	 * @throws Invalid URL Exception if the URL exceeds the limit of parameters. 
	 * @param e the event to be executed when the user clicks the browse button.
	 * @see image
	 */
	public void browsePicture(ActionEvent e){
		
		String URL = "";
		FileChooser fc = new FileChooser();
		File f = fc.showOpenDialog(null);
		imgName = f.getName();
		
		if (f != null){
			
			URL = "file:///" + f.getAbsolutePath().replace("\\","/");
			
			try{
				
				image.setImage(new Image(URL));
				image.setPreserveRatio(true);
	
			}catch(Exception ex){
				System.out.println("Invalid URL. Try again!");
			}
			
		}
	
	}
	
	/** 
	 * Saves this new movie into the database. 
	 * By storing into the table movies, in the database cinemabookingsys, this title, this description and this image name.
	 * By reading the user input for each of this three String arguments. 
	 * The label is dynamically set to display this message if the action of saving this new movie has been successful.
	 * @param e the event to be executed when the user clicks the saveMovie button.
	 * @throws Exception cannot connect to database when the connection to the database fails and this movie can not be stored.
	 */
	public void saveMovie(ActionEvent e) {
		try{
		Database dB = new Database();
		dB.addMovie(title.getText(), description.getText(), imgName);
		lblMessage.setText("New Movie successfully added. Access all movies from Home button");
		}catch(Exception ex){
			System.out.println("Cannot connect to database...");
		}

	}

	/**
	 * Loads back the previous page.
	 * That in this case is the Employee Home page.
	 * @param e the action to be executed when the back button is clicked.
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
	
	
	/**
	 * Loads the home page.
	 * That in this case is the Employee Home page.
	 * @param e the action to be executed when the home button is clicked.
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
