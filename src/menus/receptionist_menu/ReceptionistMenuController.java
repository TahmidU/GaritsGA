/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.receptionist_menu;

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

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class ReceptionistMenuController implements Initializable {

    @FXML
    private TabPane adminTab;
    @FXML
    private TableView<?> staffTable;
    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> usernameCol;
    @FXML
    private TableColumn<?, ?> roleCol;
    @FXML
    private TableColumn<?, ?> firstNameCol;
    @FXML
    private TableColumn<?, ?> lastNameCol;
    @FXML
    private TableColumn<?, ?> phoneCol;
    @FXML
    private TableColumn<?, ?> emailCol;
    @FXML
    private Label noAccountSelected;
    @FXML
    private TableView<?> dbTable;
    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private TableColumn<?, ?> timeCol;
    @FXML
    private TableColumn<?, ?> fileNameCol;
    @FXML
    private Label noBackupSelected;
    @FXML
    private Label loggedInAsText;

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
    private void addAccountPress(ActionEvent event) {
    }

    @FXML
    private void editAccountPress(ActionEvent event) {
    }

    @FXML
    private void deleteAccountPress(ActionEvent event) {
    }

    @FXML
    private void createBackupPress(ActionEvent event) {
    }

    @FXML
    private void restoreBackupPress(ActionEvent event) {
    }

    @FXML
    private void deleteBackupPress(ActionEvent event) {
    }

    @FXML
    private void logOutPress(ActionEvent event) throws IOException {
        MainGUIController guiController = new MainGUIController();
        guiController.logOut(event);
    }

}
