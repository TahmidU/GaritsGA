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
    private TextField addressText;
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
    private TextField searchBar;
    @FXML
    private Label noCustomerSelectedError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        caDAO = new CustomerAccDAO();
        customerData = FXCollections.observableArrayList(caDAO.getAll());

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

    public void setSelectedBooking(Booking selectedBooking) {
        this.selectedBooking = selectedBooking;
        firstNameText.setText(selectedBooking.getFirstName());
        lastNameText.setText(selectedBooking.getLastName());
    }

    private void back(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/ReceptionistMenu.fxml"));
        Parent root = (Parent) loader.load();

        ReceptionistMenuController controller = loader.getController();
        controller.setLoggedInName(loggedInAsText.getText());
        controller.switchTab(1);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
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

            customerData = FXCollections.observableArrayList(caDAO.getAll());
            associateTable.setItems(customerData);

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
    private void search(ActionEvent event) {
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
                    null, null, null , null, null);
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
