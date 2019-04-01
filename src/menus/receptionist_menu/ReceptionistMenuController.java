/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.receptionist_menu;

import menus.receptionist_menu.customer.AddCustomerController;
import menus.receptionist_menu.booking.AssociateVehicleController;
import menus.receptionist_menu.booking.EditBookingController;
import menus.receptionist_menu.booking.MakeBookingController;
import database.dao.account.CustomerAccDAO;
import database.dao.job.BookingDAO;
import database.dao.job.VehicleDAO;
import database.domain.account.CustomerAcc;
import database.domain.job.Booking;
import database.domain.job.Vehicle;
import garits.MainGUIController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import menus.receptionist_menu.customer.EditCustomerController;
import menus.receptionist_menu.customer.ViewCustomerController;
import menus.receptionist_menu.vehicle.AddVehicleController;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class ReceptionistMenuController implements Initializable {

    BookingDAO bDAO;
    CustomerAccDAO caDAO;
    VehicleDAO vDAO;

    @FXML
    private Label loggedInAsText;
    @FXML
    private TabPane receptionistTab;
    @FXML
    private TableView<Booking> bookingTable;
    @FXML
    private TableColumn<Booking, Integer> bookingIDCol;
    @FXML
    private TableColumn<Booking, Date> bookingDateBookedCol;
    @FXML
    private TableColumn<Booking, String> bookingJobTypeCol;
    @FXML
    private TableColumn<Booking, String> bookingVehicleNoCol;
    @FXML
    private TableColumn<Booking, String> bookingFirstNameCol;
    @FXML
    private TableColumn<Booking, String> bookingLastNameCol;
    @FXML
    private TableColumn<Booking, String> bookingCheckedInCol;
    @FXML
    private Label noBookingSelected;

    @FXML
    private TableView<CustomerAcc> customerTable;
    @FXML
    private TableColumn<CustomerAcc, String> customerNationalInsuranceCol;
    @FXML
    private TableColumn<CustomerAcc, String> customerFirstNameCol;
    @FXML
    private TableColumn<CustomerAcc, String> customerLastNameCol;
    @FXML
    private TableColumn<CustomerAcc, String> customerEmailCol;
    @FXML
    private TableColumn<CustomerAcc, String> customerPhoneCol;
    @FXML
    private Label noCustomerSelected;

    @FXML
    private TableView<Vehicle> vehicleTable;
    @FXML
    private TableColumn<Vehicle, String> vehicleRegCol;
    @FXML
    private TableColumn<Vehicle, String> vehicleMakeCol;
    @FXML
    private TableColumn<Vehicle, String> vehicleModelCol;
    @FXML
    private TableColumn<Vehicle, String> vehicleColourCol;
    @FXML
    private TableColumn<Vehicle, String> vehicleOwnerCol;
    @FXML
    private Label noVehicleSelected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bDAO = new BookingDAO();
        ObservableList<Booking> bookingData = FXCollections.observableArrayList(bDAO.getAll());

        bookingIDCol.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("id"));
        bookingDateBookedCol.setCellValueFactory(new PropertyValueFactory<Booking, Date>("dateBooked"));
        bookingJobTypeCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("jobType"));
        bookingVehicleNoCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("vehicleRegistrationNumber"));
        bookingFirstNameCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("firstName"));
        bookingLastNameCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("lastName"));
        bookingCheckedInCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("checkIn"));

        bookingTable.setItems(bookingData);

        caDAO = new CustomerAccDAO();
        ObservableList<CustomerAcc> customerData = FXCollections.observableArrayList(caDAO.getAll());

        customerNationalInsuranceCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("nationalInsurance"));
        customerFirstNameCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("firstName"));
        customerLastNameCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("lastName"));
        customerEmailCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("email"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("phoneNumber"));

        customerTable.setItems(customerData);

        vDAO = new VehicleDAO();
        ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList(vDAO.getAll());

        vehicleRegCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicleRegistration"));
        vehicleMakeCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("make"));
        vehicleModelCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("model"));
        vehicleColourCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("color"));
        vehicleOwnerCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vehicle, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vehicle, String> nameCol) {
                return new ReadOnlyStringWrapper(nameCol.getValue().getCustomerAcc().getFullName());
            }
        });

        vehicleTable.setItems(vehicleData);

    }

    public void setLoggedInName(String s) {
        loggedInAsText.setText(s);
    }

    public void switchTab(int n) {
        SingleSelectionModel<Tab> selectionModel = receptionistTab.getSelectionModel();
        selectionModel.select(n);
    }

    /*
    -----------------------------------------------Booking Section------------------------------------------------------------
     */
    @FXML
    private void makeBookingPress(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/booking/MakeBooking.fxml"));
        Parent root = (Parent) loader.load();

        MakeBookingController controller = loader.getController();
        controller.setLoggedInName(loggedInAsText.getText());

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void editBookingPress(ActionEvent event) throws IOException {
        Booking selectedBooking = null;
        selectedBooking = bookingTable.getSelectionModel().getSelectedItem();

        if (selectedBooking == null) {
            noBookingSelected.setText("No Booking Selected.");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/booking/EditBooking.fxml"));
            Parent root = (Parent) loader.load();

            EditBookingController controller = loader.getController();
            controller.setLoggedInName(loggedInAsText.getText());
            controller.setSelectedBooking(selectedBooking);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    @FXML
    private void cancelBookingPress(ActionEvent event) throws IOException {
        Booking selectedBooking = null;
        selectedBooking = bookingTable.getSelectionModel().getSelectedItem();

        if (selectedBooking == null) {
            noBookingSelected.setText("No Booking Selected.");
        } else {
            MainGUIController guiController = new MainGUIController();
            guiController.popupConfirmation(event, "Are you sure you want to cancel this booking?");

            if (guiController.popupController.getConfirm()) {
                bookingTable.getItems().remove(selectedBooking);
                bDAO.delete(selectedBooking);
            }
        }
    }

    @FXML
    private void checkInPress(ActionEvent event) throws IOException {
        Booking selectedBooking = null;
        selectedBooking = bookingTable.getSelectionModel().getSelectedItem();

        if (selectedBooking == null) {
            noBookingSelected.setText("No Booking Selected.");
        } else {
            if (vDAO.getByRegNum(selectedBooking.getVehicleRegistrationNumber()) == null) {
                MainGUIController guiController = new MainGUIController();
                guiController.popupConfirmation(event, "Vehicle does not exist in database, a blank record will be created and a customer account will need to be associated. Continue?");

                if (guiController.popupController.getConfirm()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/booking/AssociateVehicle.fxml"));
                    Parent root = (Parent) loader.load();

                    AssociateVehicleController controller = loader.getController();
                    controller.setLoggedInName(loggedInAsText.getText());
                    controller.setSelectedBooking(selectedBooking);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(new Scene(root));
                }
            } else {
                //MORE TO DO HERE
                System.out.println("Vehicle detected.");
            }
        }
    }

    @FXML
    private void generateJobSheetPress(ActionEvent event) {
    }

    /*
    -----------------------------------------------Customer Section------------------------------------------------------------
     */
    @FXML
    private void addCustomerPress(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/customer/AddCustomer.fxml"));
        Parent root = (Parent) loader.load();

        AddCustomerController controller = loader.getController();
        controller.setLoggedInName(loggedInAsText.getText());

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void editCustomerPress(ActionEvent event) throws IOException {
        CustomerAcc selectedCustomer = null;
        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            noCustomerSelected.setText("No Customer Selected.");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/customer/EditCustomer.fxml"));
            Parent root = (Parent) loader.load();

            EditCustomerController controller = loader.getController();
            controller.setLoggedInName(loggedInAsText.getText());
            controller.setSelectedCustomer(selectedCustomer);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    @FXML
    private void removeCustomerPress(ActionEvent event) throws IOException {
        CustomerAcc selectedCustomer = null;
        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            noCustomerSelected.setText("No Customer Selected.");
        } else {
            MainGUIController guiController = new MainGUIController();
            guiController.popupConfirmation(event, "Are you sure you want to remove this customer?");

            if (guiController.popupController.getConfirm()) {
                customerTable.getItems().remove(selectedCustomer);
                caDAO.delete(selectedCustomer);
            }
        }
    }

    @FXML
    private void viewCustomerPress(ActionEvent event) throws IOException {
        CustomerAcc selectedCustomer = null;
        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            noCustomerSelected.setText("No Customer Selected.");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/customer/ViewCustomer.fxml"));
            Parent root = (Parent) loader.load();

            ViewCustomerController controller = loader.getController();
            controller.setLoggedInName(loggedInAsText.getText());
            controller.setSelectedCustomer(selectedCustomer);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    /*
    -----------------------------------------------Vehicle Section------------------------------------------------------------
     */
    @FXML
    private void addVehiclePress(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/vehicle/AddVehicle.fxml"));
        Parent root = (Parent) loader.load();

        AddVehicleController controller = loader.getController();
        controller.setLoggedInName(loggedInAsText.getText());

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void editVehiclePress(ActionEvent event) {
    }

    @FXML
    private void removeVehiclePress(ActionEvent event) throws IOException {
        Vehicle selectedVehicle = null;
        selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();

        if (selectedVehicle == null) {
            noVehicleSelected.setText("No Vehicle Selected.");
        } else {
            MainGUIController guiController = new MainGUIController();
            guiController.popupConfirmation(event, "Are you sure you want to remove this vehicle?");

            if (guiController.popupController.getConfirm()) {
                vehicleTable.getItems().remove(selectedVehicle);
                vDAO.delete(selectedVehicle);
            }
        }
    }

    @FXML
    private void viewVehiclePress(ActionEvent event) throws IOException {
        Vehicle selectedVehicle = null;
        selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();

        if (selectedVehicle == null) {
            noVehicleSelected.setText("No Vehicle Selected.");
        } else {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/vehicle/ViewVehicle.fxml"));
//            Parent root = (Parent) loader.load();
//
//            ViewCustomerController controller = loader.getController();
//            controller.setLoggedInName(loggedInAsText.getText());
//            controller.setSelectedVehicle(selectedVehicle);
//
//            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            window.setScene(new Scene(root));
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

    @FXML
    private void TEST(ActionEvent event) {
        Booking selectedBooking = bookingTable.getSelectionModel().getSelectedItem();
//        
//        VehicleDAO vDAO = new VehicleDAO();
//        
//        ArrayList<Vehicle> vehicles = vDAO.getAll();
//        for (int i = 0; i < 1; ++i) {
//            System.out.println(vehicles.get(i).getCustomerAcc().getFirstName());
//        }
        System.out.println(selectedBooking.getVehicle().getVehicleRegistration());
    }

}
