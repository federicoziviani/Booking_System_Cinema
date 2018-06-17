package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * This class uses methods to display the ratings of each movie. 
 * @author Federico Ziviani
 *
 */
public class ERatingsListController {
	
	/**
	 * Defines the private TableView tableRatings.
	 */
	@FXML
	private TableView tableRatings;
	
	/**
	 * Defines the private TableColumn columnTitle.
	 */
	@FXML
	private TableColumn columnTitle;
	
	/**
	 * Defines the private TableColumn columnRating.
	 */
	@FXML
	private TableColumn columnRating;
	
	/**
	 * Populates this TableView with the title and rating for each movie.
	 * @throws Exception when the connection with the database fails. 
	 */
	public void loadRatings(){
		
		columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		columnRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
		
		try{
			
			Database dB = new Database();
		
			for(String title: dB.getAllRatedMovies()){
			
				tableRatings.getItems().add(new Rating(title,dB.getMovieRating(title)));
				
			}
		
		}catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
	}

	

	/**
	 * Loads back the home page. The movies' titles will still be in the comboBox.
	 * The method to fill the combo is called from the controller home.
	 * That in this case is the Employee Home page.
	 * @param e action to be executed when the home button is clicked.
	 * @throws Exception of an invalid fxml file when it tries to loads the page. 
	 */
	public void goHome(ActionEvent e) {
		
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