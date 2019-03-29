/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.admin_menu;

import database.dao.DBHelper;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class AdminMenuController implements Initializable {

    private StaffDAO sDAO;
    private BackUpDAO bDAO;

    @FXML
    private TabPane adminTab;
    @FXML
    private Label loggedInAsText;
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
    private Label restoreSucessful;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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

    public void switchTab() {
        SingleSelectionModel<Tab> selectionModel = adminTab.getSelectionModel();
        selectionModel.select(1);
    }

    @FXML
    private void addAccountPress(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/admin_menu/AddAccount.fxml"));
        Parent root = (Parent) loader.load();

        AddAccountController controller = loader.getController();
        controller.setLoggedInName(loggedInAsText.getText());

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void editAccountPress(ActionEvent event) throws IOException {
        Staff selectedStaff = null;
        selectedStaff = staffTable.getSelectionModel().getSelectedItem();

        if (selectedStaff == null) {
            noAccountSelected.setText("No Account Selected.");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/admin_menu/EditAccount.fxml"));
            Parent root = (Parent) loader.load();

            EditAccountController controller = loader.getController();
            controller.setLoggedInName(loggedInAsText.getText());
            controller.setSelectedStaff(selectedStaff);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    @FXML
    private void deleteAccountPress(ActionEvent event) throws IOException {
        Staff selectedStaff = null;
        selectedStaff = staffTable.getSelectionModel().getSelectedItem();

        if (selectedStaff == null) {
            noAccountSelected.setText("No Account Selected.");
        } else {

            if (CurrentUser.getInstance().getUserName().equals(selectedStaff.getUserName())) {
                noAccountSelected.setText("You Cannot Delete Yourself.");
            } else {
                
                MainGUIController guiController = new MainGUIController();
                guiController.popupConfirmation(event, "Are you sure you want to delete this account?");

                if (guiController.popupController.getConfirm()) {
                    staffTable.getItems().remove(selectedStaff);
                    sDAO.delete(selectedStaff);
                }
            }
        }
    }

    @FXML
    private void createBackupPress(ActionEvent event) {
        DBHelper dbH = new DBHelper();
        dbH.backUpDB();
        BackUp tmp = new BackUp(dbH.getBackupDir(), dbH.getBackupName(), dbH.getBackupDate(), dbH.getBackupTime());
        bDAO.save(tmp);

        dbTable.getItems().add(tmp);
    }

    @FXML
    private void restoreBackupPress(ActionEvent event) throws IOException {
        BackUp selectedBackup = null;
        selectedBackup = dbTable.getSelectionModel().getSelectedItem();

        if (selectedBackup == null) {
            noBackupSelected.setText("No Backup Selected.");
        } else {
            MainGUIController guiController = new MainGUIController();
            guiController.popupConfirmation(event, "Are you sure you want to restore database to this backup?");

            if (guiController.popupController.getConfirm()) {
                DBHelper dbH = new DBHelper();
                dbH.restoreDB(selectedBackup.getDir());

                ObservableList<Staff> accountData = FXCollections.observableArrayList(sDAO.getAll());
                staffTable.setItems(accountData);
                noBackupSelected.setText("");
                restoreSucessful.setText("Successfully Restored " + selectedBackup.getFileName() + ".");
            }
        }
    }

    @FXML
    private void deleteBackupPress(ActionEvent event) throws IOException {
        BackUp selectedBackup = null;
        selectedBackup = dbTable.getSelectionModel().getSelectedItem();

        if (selectedBackup == null) {
            noBackupSelected.setText("No Backup Selected.");
        } else {
            MainGUIController guiController = new MainGUIController();
            guiController.popupConfirmation(event, "Are you sure you want to delete this backup?");

            if (guiController.popupController.getConfirm()) {
                dbTable.getItems().remove(selectedBackup);
                bDAO.delete(selectedBackup);
                DBHelper dbH = new DBHelper();
                dbH.deleteDB(selectedBackup.getDir());
            }
        }
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
