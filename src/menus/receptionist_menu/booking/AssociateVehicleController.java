/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.receptionist_menu.booking;

import database.dao.account.CustomerAccDAO;
import database.dao.job.BookingDAO;
import database.dao.job.VehicleDAO;
import database.domain.account.CustomerAcc;
import database.domain.job.Booking;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import menus.foreperson_menu.ForepersonMenuController;
import menus.franchisee_menu.FranchiseeMenuController;
import menus.mechanic_menu.MechanicMenuController;
import menus.receptionist_menu.ReceptionistMenuController;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class AssociateVehicleController implements Initializable {

    CustomerAccDAO caDAO;
    ObservableList<CustomerAcc> customerData;

    private Booking selectedBooking;

    @FXML
    private Label loggedInAsText;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField phoneText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField nationalInsuranceText;
    @FXML
    private TextArea addressText;
    @FXML
    private TextField postcodeText;
    @FXML
    private Label missingDetailsError;
    @FXML
    private TableView<CustomerAcc> associateTable;
    @FXML
    private TableColumn<CustomerAcc, String> insuranceNoCol;
    @FXML
    private TableColumn<CustomerAcc, String> nameCol;
    @FXML
    private Label noCustomerSelectedError;
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

    public void setSelectedBooking(Booking selectedBooking) {
        this.selectedBooking = selectedBooking;
        firstNameText.setText(selectedBooking.getFirstName());
        lastNameText.setText(selectedBooking.getLastName());
    }

    private void back(ActionEvent event) throws IOException {
        
        if (CurrentUser.getInstance().getStaff().getType().equals("Franchisee")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/franchisee_menu/FranchiseeMenu.fxml"));
            Parent root = (Parent) loader.load();

            FranchiseeMenuController controller = loader.getController();
            controller.switchTab(1);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));

        } else if (CurrentUser.getInstance().getStaff().getType().equals("Foreperson")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/foreperson_menu/ForepersonMenu.fxml"));
            Parent root = (Parent) loader.load();

            ForepersonMenuController controller = loader.getController();
            controller.switchTab(1);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));

        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/ReceptionistMenu.fxml"));
            Parent root = (Parent) loader.load();

            ReceptionistMenuController controller = loader.getController();
            controller.switchTab(1);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
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
    private void backPress(ActionEvent event) throws IOException {
        back(event);
    }

    @FXML
    private void addCustomerSavePress(ActionEvent event) throws IOException {
        missingDetailsError.setText("");
        noCustomerSelectedError.setText("");

        if (firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() || phoneText.getText().isEmpty()
                || emailText.getText().isEmpty() || nationalInsuranceText.getText().isEmpty() || addressText.getText().isEmpty()
                || postcodeText.getText().isEmpty()) {

            missingDetailsError.setText("Missing Details");
        } else if (caDAO.getByNI(nationalInsuranceText.getText()) != null) {
            missingDetailsError.setText("National Insurance Number Already Exists");
        } else {
            CustomerAcc tmp = new CustomerAcc(nationalInsuranceText.getText(), firstNameText.getText(), lastNameText.getText(), addressText.getText(), postcodeText.getText(),
                    emailText.getText(), phoneText.getText());
            caDAO.save(tmp);

            refreshCustomerTable();

            firstNameText.setText("");
            lastNameText.setText("");
            phoneText.setText("");
            emailText.setText("");
            nationalInsuranceText.setText("");
            addressText.setText("");
            postcodeText.setText("");
        }
    }

    @FXML
    private void associateVehiclePress(ActionEvent event) throws IOException {
        CustomerAcc selectedCustomer = null;
        selectedCustomer = associateTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            missingDetailsError.setText("");
            noCustomerSelectedError.setText("No Customer Selected.");
        } else {
            VehicleDAO vDAO = new VehicleDAO();
            Vehicle tmp = new Vehicle(selectedBooking.getVehicleRegistrationNumber(), selectedCustomer.getNationalInsurance(),
                    null, null, null, null, null);
            vDAO.save(tmp);

            BookingDAO bDAO = new BookingDAO();
            Booking bookingTMP = new Booking(selectedBooking.getId(), selectedBooking.getJobType(), selectedBooking.getDateBooked(),
                    selectedBooking.getVehicleRegistrationNumber(), selectedBooking.getFirstName(), selectedBooking.getLastName(),
                    "Yes");
            bDAO.update(bookingTMP);
            back(event);
        }
    }

}
