/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.franchisee_menu;

import database.dao.account.StaffDAO;
import database.dao.backup.BackUpDAO;
import database.domain.account.Staff;
import database.domain.backup.BackUp;
import garits.CurrentUser;
import garits.MainGUIController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class FranchiseeMenuController implements Initializable {

    private StaffDAO sDAO;
    private BackUpDAO bDAO;

    @FXML
    private TabPane adminTab;
    @FXML
    private TableView<Staff> staffTable;
    @FXML
    private TableColumn<Staff, Integer> idCol;
    @FXML
    private TableColumn<Staff, String> usernameCol;
    @FXML
    private TableColumn<Staff, String> roleCol;
    @FXML
    private TableColumn<Staff, String> firstNameCol;
    @FXML
    private TableColumn<Staff, String> lastNameCol;
    @FXML
    private TableColumn<Staff, String> phoneCol;
    @FXML
    private TableColumn<Staff, String> passwordCol;
    @FXML
    private Label noAccountSelected;
    @FXML
    private TableView<BackUp> dbTable;
    @FXML
    private TableColumn<BackUp, String> dateCol;
    @FXML
    private TableColumn<BackUp, String> timeCol;
    @FXML
    private TableColumn<BackUp, String> fileNameCol;
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

        sDAO = new StaffDAO();
        ObservableList<Staff> accountData = FXCollections.observableArrayList(sDAO.getAll());

        idCol.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("id"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("userName"));
        roleCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("type"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("lastName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("phoneNum"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("password"));

        staffTable.setItems(accountData);

        bDAO = new BackUpDAO();
        ObservableList<BackUp> backupData = FXCollections.observableArrayList(bDAO.getAll());

        dateCol.setCellValueFactory(new PropertyValueFactory<BackUp, String>("dateCreated"));
        timeCol.setCellValueFactory(new PropertyValueFactory<BackUp, String>("timeCreated"));
        fileNameCol.setCellValueFactory(new PropertyValueFactory<BackUp, String>("fileName"));

        dbTable.setItems(backupData);
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
    private void deleteAccountPress(ActionEvent event) throws IOException {
        Staff selectedStaff = null;
        selectedStaff = staffTable.getSelectionModel().getSelectedItem();

        if (selectedStaff == null) {
            noAccountSelected.setText("No Account Selected.");
        }else if(CurrentUser.getInstance().getUserName().equals(selectedStaff.getUserName()))
        {
            noAccountSelected.setText("You Cannot Delete Yourself.");
        }else if(selectedStaff.getType().equals(Staff.ADMIN))
        {
            noAccountSelected.setText("You Cannot Remove An Administrator.");
        }
        else {
            MainGUIController guiController = new MainGUIController();
            guiController.popupConfirmation(event, "Are you sure you want to delete this account?");

            if (guiController.popupController.getConfirm()) {
                staffTable.getItems().remove(selectedStaff);
                sDAO.delete(selectedStaff);
            }

        }
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
        guiController.popupConfirmation(event, "Are you sure you want to logout? Unsaved changes will not be stored.");

        if (guiController.popupController.getConfirm()) {
            guiController.logOut(event);
        }
    }

}
