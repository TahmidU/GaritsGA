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
import database.dao.job.JobSheetDAO;
import database.dao.job.VehicleDAO;
import database.dao.part.StockPartDAO;
import database.dao.payment.InvoiceDAO;
import database.domain.account.CustomerAcc;
import database.domain.job.Booking;
import database.domain.job.JobSheet;
import database.domain.job.Vehicle;
import database.domain.part.StockPart;
import database.domain.payment.Invoice;
import garits.MainGUIController;
import garits.singleton.BookingSingleton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import menus.receptionist_menu.customer.EditCustomerController;
import menus.receptionist_menu.customer.ViewCustomerController;
import menus.receptionist_menu.job.ViewJobController;
import menus.receptionist_menu.part.AddPartController;
import menus.receptionist_menu.vehicle.AddVehicleController;
import menus.receptionist_menu.vehicle.EditVehicleController;
import menus.receptionist_menu.vehicle.ViewVehicleController;
import util.DBDateHelper;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class ReceptionistMenuController implements Initializable {

    BookingDAO bDAO;
    ObservableList<Booking> bookingData;

    JobSheetDAO jsDAO;
    ObservableList<JobSheet> jobData;

    InvoiceDAO iDAO;
    ObservableList<Invoice> invoiceData;

    StockPartDAO spDAO;
    ObservableList<StockPart> partData;

    CustomerAccDAO caDAO;
    ObservableList<CustomerAcc> customerData;

    VehicleDAO vDAO;
    ObservableList<Vehicle> vehicleData;

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
    private Label bookingSuccessful;

    @FXML
    private TableView<JobSheet> jobTable;
    @FXML
    private TableColumn<JobSheet, Integer> jobNoCol;
    @FXML
    private TableColumn<JobSheet, Date> jobDateCheckedCol;
    @FXML
    private TableColumn<JobSheet, String> jobTypeCol;
    @FXML
    private TableColumn<JobSheet, String> jobVehicleRegCol;
    @FXML
    private TableColumn<JobSheet, String> jobMechanicCol;
    @FXML
    private TableColumn<JobSheet, Date> jobDateCompletedCol;
    @FXML
    private TextArea jobStatusText;
    @FXML
    private TextArea jobProblemText;
    @FXML
    private Label noJobSelected;
    @FXML
    private Label jobSuccessful;

    @FXML
    private TableView<Invoice> invoiceTable;
    @FXML
    private TableColumn<Invoice, Integer> invoiceNoCol;
    @FXML
    private TableColumn<Invoice, Date> invoiceDateCol;
    @FXML
    private TableColumn<Invoice, String> invoiceNINoCol;
    @FXML
    private TableColumn<Invoice, String> invoiceCustomerNameCol;
    @FXML
    private TableColumn<Invoice, String> invoiceTotalAmountCol;
    @FXML
    private Label noInvoiceSelected;
    @FXML
    private Label invoiceSuccessful;

    @FXML
    private TableView<StockPart> partTable;
    @FXML
    private TableColumn<StockPart, Integer> partIDCol;
    @FXML
    private TableColumn<StockPart, String> partManufacturerCol;
    @FXML
    private TableColumn<StockPart, String> partNameCol;
    @FXML
    private TableColumn<StockPart, String> partVehicleTypeCol;
    @FXML
    private TableColumn<StockPart, String> partPriceCol;
    @FXML
    private TableColumn<StockPart, Integer> partThresholdCol;
    @FXML
    private TableColumn<StockPart, Integer> partQuantityCol;
    @FXML
    private Label noPartSelected;
    @FXML
    private Label partSuccessful;

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

        /*
    -----------------------------------------------Booking Table-------------------------------------------------------------------
         */
        bDAO = new BookingDAO();
        bookingData = FXCollections.observableArrayList(bDAO.getAll());

        bookingIDCol.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("id"));
        bookingDateBookedCol.setCellValueFactory(new PropertyValueFactory<Booking, Date>("dateBooked"));
        bookingJobTypeCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("jobType"));
        bookingVehicleNoCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("vehicleRegistrationNumber"));
        bookingFirstNameCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("firstName"));
        bookingLastNameCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("lastName"));
        bookingCheckedInCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("checkIn"));

        bookingTable.setItems(bookingData);

        /*
    -----------------------------------------------Job Table----------------------------------------------------------------------
         */
        jsDAO = new JobSheetDAO();
        jobData = FXCollections.observableArrayList(jsDAO.getAll());

        jobNoCol.setCellValueFactory(new PropertyValueFactory<JobSheet, Integer>("jobNum"));
        jobDateCheckedCol.setCellValueFactory(new PropertyValueFactory<JobSheet, Date>("dateCreated"));
        jobTypeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<JobSheet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<JobSheet, String> jobTypeCol) {
                return new ReadOnlyStringWrapper(jobTypeCol.getValue().getBooking().getJobType());
            }
        });
        jobVehicleRegCol.setCellValueFactory(new PropertyValueFactory<JobSheet, String>("vehicleReg"));

        jobMechanicCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<JobSheet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<JobSheet, String> jobMechanicCol) {
                if (jobMechanicCol.getValue().getStaff() == null) {
                    return new ReadOnlyStringWrapper(null);
                } else {
                    return new ReadOnlyStringWrapper(jobMechanicCol.getValue().getStaff().getFullName());
                }
            }
        });
        jobDateCompletedCol.setCellValueFactory(new PropertyValueFactory<JobSheet, Date>("dateCompleted"));

        jobTable.setItems(jobData);

        /*
    -----------------------------------------------Invoice Table-------------------------------------------------------------------
         */
        iDAO = new InvoiceDAO();
        invoiceData = FXCollections.observableArrayList(iDAO.getAll());

        invoiceNoCol.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("id"));
        invoiceDateCol.setCellValueFactory(new PropertyValueFactory<Invoice, Date>("dateCreated"));
        invoiceNINoCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Invoice, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Invoice, String> invoiceJobTypeCol) {
                return new ReadOnlyStringWrapper(invoiceJobTypeCol.getValue().getCustomerAcc().getNationalInsurance());
            }
        });
        invoiceCustomerNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Invoice, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Invoice, String> invoiceJobTypeCol) {
                return new ReadOnlyStringWrapper(invoiceJobTypeCol.getValue().getCustomerAcc().getFullName());
            }
        });
        invoiceTotalAmountCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Invoice, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Invoice, String> invoiceTotalAmountCol) {
                return new ReadOnlyStringWrapper("£" + String.valueOf(invoiceTotalAmountCol.getValue().getTotalAmount()));
            }
        });

        invoiceTable.setItems(invoiceData);

        /*
    -----------------------------------------------Part Table-------------------------------------------------------------------
         */
        spDAO = new StockPartDAO();
        partData = FXCollections.observableArrayList(spDAO.getAll());

        partIDCol.setCellValueFactory(new PropertyValueFactory<StockPart, Integer>("partId"));
        partManufacturerCol.setCellValueFactory(new PropertyValueFactory<StockPart, String>("manufacturer"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<StockPart, String>("partName"));
        partVehicleTypeCol.setCellValueFactory(new PropertyValueFactory<StockPart, String>("vehicleType"));
        partPriceCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StockPart, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StockPart, String> partPriceCol) {
                return new ReadOnlyStringWrapper("£" + String.valueOf(partPriceCol.getValue().getPrice()));
            }
        });
        partThresholdCol.setCellValueFactory(new PropertyValueFactory<StockPart, Integer>("threshold"));
        partQuantityCol.setCellValueFactory(new PropertyValueFactory<StockPart, Integer>("quantity"));

        partTable.setItems(partData);

        /*
    -----------------------------------------------Customer Table-------------------------------------------------------------------
         */
        caDAO = new CustomerAccDAO();
        customerData = FXCollections.observableArrayList(caDAO.getAll());

        customerNationalInsuranceCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("nationalInsurance"));
        customerFirstNameCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("firstName"));
        customerLastNameCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("lastName"));
        customerEmailCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("email"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("phoneNumber"));

        customerTable.setItems(customerData);

        /*
    -----------------------------------------------Vehicle Table-------------------------------------------------------------------
         */
        vDAO = new VehicleDAO();
        vehicleData = FXCollections.observableArrayList(vDAO.getAll());

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
            bookingSuccessful.setText("");
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
            bookingSuccessful.setText("");
            noBookingSelected.setText("No Booking Selected.");

        } else if (vDAO.getByRegNum(selectedBooking.getVehicleRegistrationNumber()) == null) {
            MainGUIController guiController = new MainGUIController();
            guiController.popupConfirmation(event, "Vehicle does not exist in database, a blank record will be created and a customer account will need to be associated. Continue?");

            if (guiController.popupController.getConfirm()) {
                BookingSingleton.getInstance().setBooking(selectedBooking);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/booking/AssociateVehicle.fxml"));
                Parent root = (Parent) loader.load();

                AssociateVehicleController controller = loader.getController();
                controller.setLoggedInName(loggedInAsText.getText());
                controller.setSelectedBooking(selectedBooking);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
            }
        } else {
            if (selectedBooking.getCheckIn().equals("No")) {

                Booking bookingTMP = new Booking(selectedBooking.getId(), selectedBooking.getJobType(), selectedBooking.getDateBooked(),
                        selectedBooking.getVehicleRegistrationNumber(), selectedBooking.getFirstName(), selectedBooking.getLastName(),
                        "Yes");
                bDAO.update(bookingTMP);
                bookingSuccessful.setText("Check-In Sucessful!");

            } else {
                Booking bookingTMP = new Booking(selectedBooking.getId(), selectedBooking.getJobType(), selectedBooking.getDateBooked(),
                        selectedBooking.getVehicleRegistrationNumber(), selectedBooking.getFirstName(), selectedBooking.getLastName(),
                        "No");
                bDAO.update(bookingTMP);
                bookingSuccessful.setText("Check-In Undone!");
            }
            noBookingSelected.setText("");
            bookingData = FXCollections.observableArrayList(bDAO.getAll());
            bookingTable.setItems(bookingData);
        }
    }

    @FXML
    private void generateJobSheetPress(ActionEvent event) {
        Booking selectedBooking = null;
        selectedBooking = bookingTable.getSelectionModel().getSelectedItem();

        if (selectedBooking == null) {
            bookingSuccessful.setText("");
            noBookingSelected.setText("No Booking Selected.");

        } else if (selectedBooking.getCheckIn().equals("No")) {
            bookingSuccessful.setText("");
            noBookingSelected.setText("Booking Needs To Be Checked-In First.");

//        } else if (jsDAO.getByBookingId(selectedBooking.getId()) != null) {
//            bookingSuccessful.setText("");
//            noBookingSelected.setText("Job Sheet Already Exist For This Booking");
        } else {
            JobSheet jobTMP = new JobSheet(0, -1, selectedBooking.getVehicleRegistrationNumber(), selectedBooking.getId(),
                    "To be added by Mechanic", DBDateHelper.parseCurrentDate(), "To be added by Mechanic", null);
            jsDAO.saveWithoutDate(jobTMP);

            jobData = FXCollections.observableArrayList(jsDAO.getAll());
            jobTable.setItems(jobData);
            bookingSuccessful.setText("Job Sheet Successfuly Generated!");
        }
    }

    /*
    -----------------------------------------------Job Section------------------------------------------------------------
     */
    @FXML
    private void editJobPress(ActionEvent event) {
    }

    @FXML
    private void deleteJobPress(ActionEvent event) throws IOException {
        JobSheet selectedJob = null;
        selectedJob = jobTable.getSelectionModel().getSelectedItem();

        if (selectedJob == null) {
            jobSuccessful.setText("");
            noJobSelected.setText("No Job Selected.");
        } else {
            MainGUIController guiController = new MainGUIController();
            guiController.popupConfirmation(event, "Are you sure you want to delete this job?");

            if (guiController.popupController.getConfirm()) {
                jobTable.getItems().remove(selectedJob);
                jsDAO.delete(selectedJob);
            }
        }
    }

    @FXML
    private void viewJobPress(ActionEvent event) throws IOException {
        JobSheet selectedJob = null;
        selectedJob = jobTable.getSelectionModel().getSelectedItem();

        if (selectedJob == null) {
            jobStatusText.setText("");
            jobProblemText.setText("");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/job/ViewJob.fxml"));
            Parent root = (Parent) loader.load();

            ViewJobController controller = loader.getController();
            controller.setLoggedInName(loggedInAsText.getText());
            controller.setSelectedJob(selectedJob);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    @FXML
    private void generateInvoicePress(ActionEvent event) {
    }

    @FXML
    private void jobViewingDescription(MouseEvent event) {
        JobSheet selectedJob = null;
        selectedJob = jobTable.getSelectionModel().getSelectedItem();

        if (selectedJob == null) {
            jobStatusText.setText("");
            jobProblemText.setText("");
        } else {
            jobStatusText.setText(selectedJob.getStatus());
            jobProblemText.setText(selectedJob.getProblemDesc());
        }
    }

    /*
    -----------------------------------------------Invoice Section------------------------------------------------------------
     */
    @FXML
    private void editInvoicePress(ActionEvent event) throws IOException {
    }

    @FXML
    private void deleteInvoicePress(ActionEvent event) {
    }

    @FXML
    private void viewInvoicePress(ActionEvent event) {
    }

    @FXML
    private void invoicePlaceholder(ActionEvent event) {
    }

    /*
    -----------------------------------------------Part Section-------------------------------------------------------------------
     */
    @FXML
    private void addPartPress(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/part/AddPart.fxml"));
        Parent root = (Parent) loader.load();

        AddPartController controller = loader.getController();
        controller.setLoggedInName(loggedInAsText.getText());

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void editPartPress(ActionEvent event) throws IOException {
    }

    @FXML
    private void removePartPress(ActionEvent event) {
    }

    @FXML
    private void viewPartPress(ActionEvent event) {
    }

    @FXML
    private void orderPartPress(ActionEvent event) {
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
            guiController.popupConfirmation(event, "Are you sure you want to remove this customer? All associated vehicles will be removed as well.");

            if (guiController.popupController.getConfirm()) {

                //Delete customer's vehicles first
                ArrayList<Vehicle> vehicles = vDAO.getByNI(selectedCustomer.getNationalInsurance());
                for (int i = 0; i < vehicles.size(); ++i) {
                    vDAO.delete(vehicles.get(i));
                }

                //refresh vehicle table
                vehicleData = FXCollections.observableArrayList(vDAO.getAll());
                vehicleTable.setItems(vehicleData);

                //finally deletes customer
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
    private void editVehiclePress(ActionEvent event) throws IOException {
        Vehicle selectedVehicle = null;
        selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();

        if (selectedVehicle == null) {
            noVehicleSelected.setText("No Vehicle Selected.");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/vehicle/EditVehicle.fxml"));
            Parent root = (Parent) loader.load();

            EditVehicleController controller = loader.getController();
            controller.setLoggedInName(loggedInAsText.getText());
            controller.setSelectedVehicle(selectedVehicle);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/vehicle/ViewVehicle.fxml"));
            Parent root = (Parent) loader.load();

            ViewVehicleController controller = loader.getController();
            controller.setLoggedInName(loggedInAsText.getText());
            controller.setSelectedVehicle(selectedVehicle);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
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
