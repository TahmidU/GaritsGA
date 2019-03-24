/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus.AdminMenu;

import database.dao.DBHelper;
import database.dao.account.StaffDAO;
import database.dao.backup.BackUpDAO;
import database.domain.account.Staff;
import database.domain.backup.BackUp;
import garits.MainGUIController;
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
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private TableColumn<Staff, String> emailCol;
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
        emailCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("email"));

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menus/AdminMenu/AddAccount.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menus/AdminMenu/EditAccount.fxml"));
            Parent root = (Parent) loader.load();

            EditAccountController controller = loader.getController();
            controller.setLoggedInName(loggedInAsText.getText());
            controller.setSelectedStaff(selectedStaff);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    @FXML
    private void deleteAccountPress(ActionEvent event) {
        Staff selectedStaff = null;
        selectedStaff = staffTable.getSelectionModel().getSelectedItem();

        if (selectedStaff == null) {
            noAccountSelected.setText("No Account Selected.");
        } else {
            staffTable.getItems().remove(selectedStaff);
            sDAO.delete(selectedStaff);
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
    private void restoreBackupPress(ActionEvent event) {
        BackUp selectedBackup = null;
        selectedBackup = dbTable.getSelectionModel().getSelectedItem();

        if (selectedBackup == null) {
            noBackupSelected.setText("No Backup Selected.");
        } else {
            DBHelper dbH = new DBHelper();
            dbH.restoreDB(selectedBackup.getDir());
            
            ObservableList<Staff> accountData = FXCollections.observableArrayList(sDAO.getAll());
            staffTable.setItems(accountData);
            noBackupSelected.setText("Restored Sucessfully.");
        }
    }

    @FXML
    private void deleteBackupPress(ActionEvent event) throws InterruptedException {
        BackUp selectedBackup = null;
        selectedBackup = dbTable.getSelectionModel().getSelectedItem();

        if (selectedBackup == null) {
            noBackupSelected.setText("No Backup Selected.");
        } else {
            dbTable.getItems().remove(selectedBackup);
            bDAO.delete(selectedBackup);
            DBHelper dbH = new DBHelper();
            dbH.deleteDB(selectedBackup.getDir());
        }
    }

    @FXML
    private void logOutPress(ActionEvent event) throws IOException {
        MainGUIController guiController = new MainGUIController();
        guiController.logOut(event);
    }
}
