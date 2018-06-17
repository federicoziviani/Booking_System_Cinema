package application;

import java.util.ArrayList;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * This class gives the employee access to this available and booked seats for a given projection. 
 * @author Federico Ziviani
 *
 */
public class ECinemaRoomController {
	/**
	 * Defines each of these 12 private checkBoxes that represents the seats in this cinema room.
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
	 * Defines the private label lblHeaderECR.
	 */
	@FXML
	private Label lblHeaderECR;
	
	/**
	 * Defines the private label lblBookedSeatsECR.
	 */
	@FXML
	private Label lblBookedSeatsECR;
	
	/**
	 * Defines the private label lblAvailableSeatsECR.
	 */
	@FXML
	private Label lblAvailableSeatsECR;
	
	/**
	 * Declares a private int bookedSeats and it defines it equals to zero.
	 */
	private int bookedSeats=0;
	
	/**
	 * Declares a private ArrayList of type String with the name seatsBooked.
	 */
	private ArrayList<String> seatsBooked;
			
	/**
	 * Counts the number of checked checkBoxes and sets this number equals to bookedSeats. 
	 * Each booked seat is added to the array list seatsBooked increasing its size.
	 * This sets the text of the label booked seats to the int representing the size of the ArrayList.
	 * This number of seats available is set as the difference between the total seats available and the int representing the size of this ArrayList.
	 * @param e event that occurs when the home button is clicked.
	 */
	public void checkEvent(ActionEvent e) {
		seatsBooked.add(((CheckBox) e.getSource()).getText());
		bookedSeats=seatsBooked.size();
		lblBookedSeatsECR.setText(new Integer(bookedSeats).toString());
		lblAvailableSeatsECR.setText(new Integer(bookedSeats-12).toString());
	}
	
}
