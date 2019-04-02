/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.mechanic_menu;

import garits.MainGUIController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class MechanicMenuController implements Initializable {
    @FXML
    private Label loggedInAsText;
    @FXML
    private TabPane receptionistTab;
    @FXML
    private TableView<?> jobTable;
    @FXML
    private TableColumn<?, ?> jobNoCol;
    @FXML
    private TableColumn<?, ?> jobDateCheckedCol;
    @FXML
    private TableColumn<?, ?> jobTypeCol;
    @FXML
    private TableColumn<?, ?> jobVehicleRegCol;
    @FXML
    private TableColumn<?, ?> jobMechanicCol;
    @FXML
    private TableColumn<?, ?> jobDateCompletedCol;
    @FXML
    private Label noJobSelected;
    @FXML
    private Label JobSuccessful;
    @FXML
    private TextField jobStatusText;
    @FXML
    private TextField jobProblemText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setLoggedInName(String s) {
        loggedInAsText.setText(s);
    }
    @FXML
    private void logOutPress(ActionEvent event) throws IOException {
        
        MainGUIController guiController = new MainGUIController();
        guiController.popupConfirmation(event, "Are you sure you want to logout? Unsaved changes will not be stored.");
        
        if (guiController.popupController.getConfirm()) {
            guiController.logOut(event);
        }
    }


    @FXML
    private void jobViewingDescription(MouseEvent event) {
    }

    @FXML
    private void editJobPress(ActionEvent event) {
    }

    @FXML
    private void deleteJobPress(ActionEvent event) {
    }

    @FXML
    private void viewJobPress(ActionEvent event) {
    }

    @FXML
    private void generateInvoicePress(ActionEvent event) {
    }


}
