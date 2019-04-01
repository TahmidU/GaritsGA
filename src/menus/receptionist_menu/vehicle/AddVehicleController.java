/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.receptionist_menu.vehicle;

import database.dao.account.CustomerAccDAO;
import database.dao.job.VehicleDAO;
import database.domain.account.CustomerAcc;
import database.domain.job.Vehicle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import menus.receptionist_menu.ReceptionistMenuController;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class AddVehicleController implements Initializable {

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
    private Label missingDetailsError;
    @FXML
    private TableView<CustomerAcc> associateTable;
    @FXML
    private TableColumn<CustomerAcc, String> insuranceNoCol;
    @FXML
    private TableColumn<CustomerAcc, String> nameCol;
    @FXML
    private TextField searchBar;
    @FXML
    private Label loggedInAsText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CustomerAccDAO caDAO = new CustomerAccDAO();
        ObservableList<CustomerAcc> customerData = FXCollections.observableArrayList(caDAO.getAll());

        insuranceNoCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("nationalInsurance"));
        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerAcc, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CustomerAcc, String> nameCol) {
                return new ReadOnlyStringWrapper(nameCol.getValue().getFullName());
            }
        });

        associateTable.setItems(customerData);
    }

    public void setLoggedInName(String s) {
        loggedInAsText.setText(s);
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
    private void addVehicleSavePress(ActionEvent event) throws IOException {
        CustomerAcc selectedCustomer = null;
        selectedCustomer = associateTable.getSelectionModel().getSelectedItem();
        
        VehicleDAO vDAO = new VehicleDAO();

        if (vehicleRegText.getText().isEmpty() || selectedCustomer == null) {

            missingDetailsError.setText("Missing Details");
        } else if (vDAO.getByRegNum(vehicleRegText.getText()) != null) {

            missingDetailsError.setText("Vehicle Registration Number Already Exists");
        } else {
            Vehicle tmp = new Vehicle(vehicleRegText.getText(), selectedCustomer.getNationalInsurance(), makeText.getText(), 
                    modelText.getText(), engineSerialText.getText(), chassisNumbText.getText(), colourText.getText());
            vDAO.save(tmp);
            back(event);
        }
    }

    @FXML
    private void backPress(ActionEvent event) throws IOException {
        back(event);
    }

    @FXML
    private void search(ActionEvent event) {
    }

}
