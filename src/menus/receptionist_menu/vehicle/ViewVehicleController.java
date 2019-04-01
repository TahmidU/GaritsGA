/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.receptionist_menu.vehicle;

import database.dao.job.VehicleDAO;
import database.domain.job.Vehicle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import menus.receptionist_menu.ReceptionistMenuController;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class ViewVehicleController implements Initializable {

    private Vehicle selectedVehicle;

    @FXML
    private TextField vehicleRegText;
    @FXML
    private TextField makeText;
    @FXML
    private TextField modelText;
    @FXML
    private TextField engineSerialText;
    @FXML
    private TextField chassisNumbText;
    @FXML
    private TextField colourText;
    @FXML
    private Label loggedInAsText;
    @FXML
    private TextField fullNameText;
    @FXML
    private TextField nationalInsuranceText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setLoggedInName(String s) {
        loggedInAsText.setText(s);
    }

    public void setSelectedVehicle(Vehicle selectedVehicle) {
        this.selectedVehicle = selectedVehicle;
        vehicleRegText.setText(selectedVehicle.getVehicleRegistration());
        makeText.setText(selectedVehicle.getMake());
        modelText.setText(selectedVehicle.getModel());
        engineSerialText.setText(selectedVehicle.getEngineSerial());
        chassisNumbText.setText(selectedVehicle.getChassisNum());
        colourText.setText(selectedVehicle.getColor());
        fullNameText.setText(selectedVehicle.getCustomerAcc().getFullName());
        nationalInsuranceText.setText(selectedVehicle.getNationalInsurance());
    }

    private void back(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/ReceptionistMenu.fxml"));
        Parent root = (Parent) loader.load();

        ReceptionistMenuController controller = loader.getController();
        controller.setLoggedInName(loggedInAsText.getText());
        controller.switchTab(6);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void backPress(ActionEvent event) throws IOException {
        back(event);
    }
}
