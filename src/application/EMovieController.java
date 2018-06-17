package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class uses methods to display all the information relative to this selected movie from the comboBox in the previous page.
 * @author Federico Ziviani
 *
 */
public class EMovieController {
	


	/**
	 * Defines this private Label lblEMT.
	 */
	@FXML
	private Label lblEMT;
	
	/**
	 * Defines this private Label lblChangeDesc.
	 */
	@FXML
	private Label lblChangeDesc;
	
	/**
	 * Defines this private TextArea description.
	 */
	@FXML
	private TextArea description;
	
	/**
	 * Defines this private ImageView image2.
	 */
	@FXML
	private ImageView image2;
	
	/**
	 * Defines this private ComboBox dateCombo.
	 */
	@FXML
	private ComboBox dateCombo;
	
	/**
	 * Defines this private ComboBox timeCombo.
	 */
	@FXML
	private ComboBox timeCombo;

	/**
	 * Defines this private String date.
	 */
	private String date;
	/**
	 * Defines this private String title.
	 */
	private String title;
	
	/**
	 * Sets the parameter date equals to the instance variable date.
	 * @param date is the name of the string holding the value of the date.
	 */
	public void setDate(String date) {
		this.date=date;
	}
	
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
		lblEMT.setText(title);
	}
		
	/**
	 * Sets the text of this TextArea to the description in the database corresponding to this movie whose title is equal to this title.
	 * @throws Exception when the connection with the database fails. 
	 */
	public void setTextArea(){
		
		try{
			
		Database dB = new Database();
		description.setText(dB.getDescription(this.title));
		
		}catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
	}
	
	/**
	 * Sets the text of this ImageView to the image in the database corresponding to this movie whose title is equal to this title.
	 * @throws Exception when the connection with the database fails. 
	 * @throws Exception of invalid URL to set the image path.
	 */
	public void setMovieImage(){
		
		String path ="/application/";
		
		try{
			
			Database dB = new Database();
			path += dB.getPicture(this.title);

			
		}catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
		
		try{
			
			image2.setImage(new Image(path));
			image2.setPreserveRatio(true);
			
		}catch(Exception e){
			System.out.println("Invalid URL...");
		}
	}
	
	/**
	 * Populates the comboBox of the projections' date with the values coming from the database and corresponding to the available projection dates fro this movie title.
	 * @throws Exception when the connection with the database fails. 
	 */
	public void fillCombo(){
		
		try{
			Database d = new Database();
		
			dateCombo.getItems().clear();
			for(String s: d.getPjDate(title)) dateCombo.getItems().add(s);
		}
		catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
    }
	
	/**
	 * Populates the comboBox of times taking values from the projection times available for this selected date.
	 * @param e the event that occurs when the user picks a projection date.
	 * @throws Exception when the connection with the database fails. 
	 */
	public void setTimes(ActionEvent e){
		
		try{
		String date = dateCombo.getValue().toString();
        this.setDate(date);
        
        Database d = new Database();
		
        for(String t: d.getPjTime(date, title)) timeCombo.getItems().add(t); 
		}catch(Exception ex){
			System.out.println("Cannot connect to the database...");
		}
	}
	
	
	 /**
	  * Saves the changes made to the description.
	  * @param e event that occurs whenthe button is clicked.
	  */
     public void updateDesc(ActionEvent e){
		
		Database dB = new Database();
		
		dB.updateDescription(title, description.getText());
		lblChangeDesc.setText("description successfully updated");
		
	}
	
	/**
	 * Loads the page where the cinema room is displayed for the selected date and time pair. 
	 * @param e event that occurs when this button is clicked.
	 * @throws Exception when the fxml is an invalid file format.
	 */
	public void getRoom(ActionEvent e){
		
		try{
			
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EBooking.fxml"));
		Parent root = loader.load();
		EBookingController EBCtll = loader.getController();
		
		
		
		EBCtll.setDate(date);
		EBCtll.setTitle(title);
		EBCtll.setTime(timeCombo.getValue().toString());
		EBCtll.setText();
		EBCtll.setSeats();
		
		((ComboBox) e.getSource()).getScene().setRoot(root);
		
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
	}
	
		
	/**
	 * Loads the page to add a new projection
	 * @param e  event that occurs when this button is clicked.
	 * @throws Exception when the fxml is an invalid file format.
	 */
	public void addProjection(ActionEvent e){
		
		try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EMovieAddProjection.fxml"));
		Parent root = loader.load();
		EMovieAddProjectionController EMAPCtll = loader.getController();
		
		EMAPCtll.setTitle(title);
		EMAPCtll.setLabel();
		
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
	
	/**
	 * Loads back the home page. The movies' titles will still be in the comboBox.
	 * The method to fill the combo is called from the controller home.
	 * That in this case is the Employee Home page.
	 * @param e action to be executed when the home button is clicked.
	 * @throws Exception of an invalid fxml file when it tries to loads the page. 
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
