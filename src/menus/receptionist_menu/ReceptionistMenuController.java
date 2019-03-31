/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.receptionist_menu;

import database.dao.job.BookingDAO;
import database.dao.job.VehicleDAO;
import database.domain.job.Booking;
import database.domain.job.Vehicle;
import garits.MainGUIController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
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
public class ReceptionistMenuController implements Initializable {

    BookingDAO bDAO;
    @FXML
    private TabPane receptionistTab;
    @FXML
    private TableView<Booking> bookingTable;
    @FXML
    private TableColumn<Booking, Integer> idCol;
    @FXML
    private TableColumn<Booking, Date> dateBookedCol;
    @FXML
    private TableColumn<Booking, String> jobTypeCol;
    @FXML
    private TableColumn<Booking, String> vehicleNoCol;
    @FXML
    private TableColumn<Booking, String> firstNameCol;
    @FXML
    private TableColumn<Booking, String> lastNameCol;
    @FXML
    private TableColumn<Booking, String> checkedInCol;
    @FXML
    private Label noBookingSelected;
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
    @FXML
    private Label loggedInAsText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bDAO = new BookingDAO();
        ObservableList<Booking> bookingData = FXCollections.observableArrayList(bDAO.getAll());

        idCol.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("id"));
        dateBookedCol.setCellValueFactory(new PropertyValueFactory<Booking, Date>("dateBooked"));
        jobTypeCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("jobType"));
        vehicleNoCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("vehicleRegistrationNumber"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("lastName"));
        checkedInCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("checkIn"));
//        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Booking, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<Booking, String> nameCol) {
//                return new ReadOnlyStringWrapper(nameCol.getValue().getVehicle().getCustomerAcc().getFirstName());
//            }
//        });

        bookingTable.setItems(bookingData);
    }

    public void setLoggedInName(String s) {
        loggedInAsText.setText(s);
    }

    public void switchTab() {
        SingleSelectionModel<Tab> selectionModel = receptionistTab.getSelectionModel();
        selectionModel.select(1);
    }

    @FXML
    private void makeBookingPress(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/MakeBooking.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/EditBooking.fxml"));
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

            VehicleDAO vDAO = new VehicleDAO();

            if (vDAO.getByRegNum(selectedBooking.getVehicleRegistrationNumber()) == null) {
                MainGUIController guiController = new MainGUIController();
                guiController.popupConfirmation(event, "Vehicle does not exist in database, a blank record will be created and a customer account will need to be associated. Continue?");

                if (guiController.popupController.getConfirm()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/AssociateVehicle.fxml"));
                    Parent root = (Parent) loader.load();

                    AssociateVehicleController controller = loader.getController();
                    controller.setLoggedInName(loggedInAsText.getText());
                    controller.setSelectedBooking(selectedBooking);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(new Scene(root));
                }
            } else {
                System.out.println("Vehicle detected.");
            }
        }
    }

    @FXML
    private void generateJobSheetPress(ActionEvent event
    ) {
    }

    @FXML
    private void createBackupPress(ActionEvent event
    ) {
    }

    @FXML
    private void restoreBackupPress(ActionEvent event
    ) {
    }

    @FXML
    private void deleteBackupPress(ActionEvent event
    ) {
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
//        Booking selectedBooking = bookingTable.getSelectionModel().getSelectedItem();
//        
//        VehicleDAO vDAO = new VehicleDAO();
//        
//        ArrayList<Vehicle> vehicles = vDAO.getAll();
//        for (int i = 0; i < 1; ++i) {
//            System.out.println(vehicles.get(i).getCustomerAcc().getFirstName());
//        }

    Vehicle test = new Vehicle("test", "test", "N/A1", "N/A1", "N/A1" ,"N/A1", "N/A1");
    System.out.println(test.getCustomerAcc().getFirstName());
    }

}
