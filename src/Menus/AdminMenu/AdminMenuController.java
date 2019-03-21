/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus.AdminMenu;

import database.dao.account.StaffDAO;
import database.domain.account.Staff;
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
    private TableView<?> dbTable;
    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private TableColumn<?, ?> timeCol;
    @FXML
    private TableColumn<?, ?> fileNameCol;
    @FXML
    private Label noBackupSelected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        StaffDAO sDAO = new StaffDAO();

        ObservableList<Staff> data = FXCollections.observableArrayList(sDAO.getAll());

        idCol.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("id"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("userName"));
        roleCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("type"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("lastName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("phoneNum"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("email"));

        staffTable.setItems(data);
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
        staffTable.getItems().remove(selectedStaff);

        if (selectedStaff == null) {
            noAccountSelected.setText("No Account Selected.");
        } else {
            StaffDAO sDAO = new StaffDAO();
            sDAO.delete(selectedStaff);
        }
    }

    @FXML
    private void createBackupPress(ActionEvent event) {
    }

    @FXML
    private void restoreBackupPress(ActionEvent event) {
//        DB selectedBackup = null;
//        selectedBackup = dbTable.getSelectionModel().getSelectedItem();
//
//        if (selectedBackup == null) {
//            noBackupSelected.setText("No Account Selected.");
//        } else {
//            
//        }
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
