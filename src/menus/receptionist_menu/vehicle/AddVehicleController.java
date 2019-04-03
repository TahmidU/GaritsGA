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
import garits.singleton.CurrentUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

    CustomerAccDAO caDAO;
    ObservableList<CustomerAcc> customerData;

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
    private Label loggedInAsText;
    @FXML
    private TextField customerSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedInAsText.setText(CurrentUser.getInstance().getStaff().getUserName());

        caDAO = new CustomerAccDAO();

        insuranceNoCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("nationalInsurance"));
        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerAcc, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CustomerAcc, String> nameCol) {
                return new ReadOnlyStringWrapper(nameCol.getValue().getFullName());
            }
        });

        refreshCustomerTable();
    }

    private void back(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/ReceptionistMenu.fxml"));
        Parent root = (Parent) loader.load();

        ReceptionistMenuController controller = loader.getController();
        controller.switchTab(6);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    private void refreshCustomerTable() {
        customerData = FXCollections.observableArrayList(caDAO.getAll());
        associateTable.setItems(customerData);

        FilteredList<CustomerAcc> filteredCustomer = new FilteredList<>(customerData, p -> true);
        customerSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredCustomer.setPredicate(customer -> {
                // If filter text is empty, display all
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare
                String lowerCaseFilter = newValue.toLowerCase();

                if (customer.getNationalInsurance().toLowerCase().contains(lowerCaseFilter)
                        || customer.getFirstName().toLowerCase().contains(lowerCaseFilter)
                        || customer.getLastName().toLowerCase().contains(lowerCaseFilter)
                        || customer.getFullName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<CustomerAcc> sortedCustomer = new SortedList<>(filteredCustomer);
        sortedCustomer.comparatorProperty().bind(associateTable.comparatorProperty());
        associateTable.setItems(sortedCustomer);
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

}
