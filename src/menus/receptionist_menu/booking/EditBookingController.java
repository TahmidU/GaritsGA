/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.receptionist_menu.booking;

import database.dao.job.BookingDAO;
import database.domain.job.Booking;
import garits.singleton.CurrentUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import menus.foreperson_menu.ForepersonMenuController;
import menus.receptionist_menu.ReceptionistMenuController;
import util.DBDateHelper;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class EditBookingController implements Initializable {

    private final ObservableList<String> options
            = FXCollections.observableArrayList(
                    "MOT",
                    "Repair",
                    "Annual Service"
            );
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField vehicleRegText;
    @FXML
    private ComboBox<String> jobCombo;
    @FXML
    private DatePicker dateBox;
    @FXML
    private Label missingDetailsError;
    @FXML
    private Label loggedInAsText;

    public Booking selectedBooking;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loggedInAsText.setText(CurrentUser.getInstance().getStaff().getUserName());
        jobCombo.getItems().addAll(options);
    }

    public void setSelectedBooking(Booking selectedBooking) {
        this.selectedBooking = selectedBooking;
        firstNameText.setText(selectedBooking.getFirstName());
        lastNameText.setText(selectedBooking.getLastName());
        vehicleRegText.setText(selectedBooking.getVehicleRegistrationNumber());
        jobCombo.setValue(selectedBooking.getJobType());
        dateBox.setValue(selectedBooking.getDateBooked().toLocalDate());
    }

    private void back(ActionEvent event) throws IOException {

        if (CurrentUser.getInstance().getStaff().getType().equals("Foreperson")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/foreperson_menu/ForepersonMenu.fxml"));
            Parent root = (Parent) loader.load();

            ForepersonMenuController controller = loader.getController();
            controller.switchTab(1);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));

        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/ReceptionistMenu.fxml"));
            Parent root = (Parent) loader.load();

            ReceptionistMenuController controller = loader.getController();
            controller.switchTab(1);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    @FXML
    private void editBookingSavePress(ActionEvent event) throws IOException {
        missingDetailsError.setText("");

        if (firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() || vehicleRegText.getText().isEmpty()
                || jobCombo.getValue() == null || dateBox.getValue() == null) {

            missingDetailsError.setText("Missing Details");
        } else {
            BookingDAO bDAO = new BookingDAO();
            Booking tmp = new Booking(selectedBooking.getId(), jobCombo.getValue(), DBDateHelper.parseDate(dateBox.getValue().toString()),
                    vehicleRegText.getText(), firstNameText.getText(), lastNameText.getText(), "No");
            bDAO.update(tmp);
            back(event);
        }
    }

    @FXML
    private void backPress(ActionEvent event) throws IOException {
        back(event);
    }
}
