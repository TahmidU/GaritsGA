/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus.AdminMenu;

import database.dao.account.StaffDAO;
import database.domain.account.Staff;
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
import javafx.scene.control.Button;
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
    private Label loggedInAsText;
    @FXML
    private TableView<Staff> accountTable;
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
    private Button addAccountButton;
    @FXML
    private TabPane adminTab;
    @FXML
    private Label testLabel;

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

        accountTable.setItems(data);
    }

    public void setLoggedInName(String s) {
        loggedInAsText.setText(s);
    }

    public void switchTab() {
        SingleSelectionModel<Tab> selectionModel = adminTab.getSelectionModel();
        selectionModel.select(1);
    }

    public void test() {
        testLabel.setText("change detected!");
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
    private void editAccountPress(ActionEvent event) {
        test();
    }
}
