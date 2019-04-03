/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.franchisee_menu;

import database.dao.account.CustomerAccDAO;
import database.dao.account.StaffDAO;
import database.dao.backup.BackUpDAO;
import database.dao.job.BookingDAO;
import database.dao.job.JobSheetDAO;
import database.dao.job.VehicleDAO;
import database.dao.part.StockPartDAO;
import database.dao.payment.InvoiceDAO;
import database.domain.account.CustomerAcc;
import database.domain.account.Staff;
import database.domain.backup.BackUp;
import database.domain.job.Booking;
import database.domain.job.JobSheet;
import database.domain.job.Task;
import database.domain.job.Vehicle;
import database.domain.part.StockPart;
import database.domain.payment.Invoice;
import garits.singleton.CurrentUser;
import garits.MainGUIController;
import garits.singleton.BookingSingleton;
import garits.singleton.PaymentSingleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import menus.receptionist_menu.booking.AssociateVehicleController;
import menus.receptionist_menu.booking.EditBookingController;
import menus.receptionist_menu.customer.EditCustomerController;
import menus.receptionist_menu.customer.ViewCustomerController;
import menus.receptionist_menu.invoice.MakePaymentController;
import menus.receptionist_menu.invoice.ViewInvoiceController;
import menus.receptionist_menu.job.ViewJobController;
import menus.receptionist_menu.part.EditPartController;
import menus.receptionist_menu.part.ViewPartController;
import menus.receptionist_menu.vehicle.EditVehicleController;
import menus.receptionist_menu.vehicle.ViewVehicleController;
import util.DBDateHelper;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class FranchiseeMenuController implements Initializable {

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
    private TabPane franchiseeTab;

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
    private TextField bookingSearch;

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
    private TextField jobSearch;

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
    private TextField invoiceSearch;

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
    private TextField partSearch;

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
    private TextField customerSearch;

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
    @FXML
    private TextField vehicleSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedInAsText.setText(CurrentUser.getInstance().getStaff().getUserName());

        /*
    -----------------------------------------------Booking Table-------------------------------------------------------------------
         */
        bDAO = new BookingDAO();

        bookingIDCol.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("id"));
        bookingDateBookedCol.setCellValueFactory(new PropertyValueFactory<Booking, Date>("dateBooked"));
        bookingJobTypeCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("jobType"));
        bookingVehicleNoCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("vehicleRegistrationNumber"));
        bookingFirstNameCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("firstName"));
        bookingLastNameCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("lastName"));
        bookingCheckedInCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("checkIn"));

        refreshBookingTable();

        /*
    -----------------------------------------------Job Table----------------------------------------------------------------------
         */
        jsDAO = new JobSheetDAO();

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

        refreshJobTable();
        /*
    -----------------------------------------------Invoice Table-------------------------------------------------------------------
         */
        iDAO = new InvoiceDAO();

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

        refreshInvoiceTable();

        /*
    -----------------------------------------------Part Table-------------------------------------------------------------------
         */
        spDAO = new StockPartDAO();

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

        refreshPartTable();

        /*
    -----------------------------------------------Customer Table-------------------------------------------------------------------
         */
        caDAO = new CustomerAccDAO();

        customerNationalInsuranceCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("nationalInsurance"));
        customerFirstNameCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("firstName"));
        customerLastNameCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("lastName"));
        customerEmailCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("email"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("phoneNumber"));

        refreshCustomerTable();

        /*
    -----------------------------------------------Vehicle Table-------------------------------------------------------------------
         */
        vDAO = new VehicleDAO();

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

        refreshVehicleTable();

    }

    public void switchTab(int n) {
        SingleSelectionModel<Tab> selectionModel = franchiseeTab.getSelectionModel();
        selectionModel.select(n);
    }

//=================================================================================================================================
    /*
    -----------------------------------------------Booking Section------------------------------------------------------------
     */
    private void refreshBookingTable() {
        bookingData = FXCollections.observableArrayList(bDAO.getAll());
        bookingTable.setItems(bookingData);

        FilteredList<Booking> filteredBooking = new FilteredList<>(bookingData, p -> true);
        bookingSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredBooking.setPredicate(booking -> {
                // If filter text is empty, display all
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare 
                String lowerCaseFilter = newValue.toLowerCase();

                if (booking.getFirstName().toLowerCase().contains(lowerCaseFilter)
                        || booking.getLastName().toLowerCase().contains(lowerCaseFilter)
                        || String.valueOf(booking.getId()).toLowerCase().contains(lowerCaseFilter)
                        || booking.getDateBooked().toString().toLowerCase().contains(lowerCaseFilter)
                        || booking.getJobType().toLowerCase().contains(lowerCaseFilter)
                        || booking.getVehicleRegistrationNumber().toLowerCase().contains(lowerCaseFilter)
                        || booking.getCheckIn().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Booking> sortedBooking = new SortedList<>(filteredBooking);
        sortedBooking.comparatorProperty().bind(bookingTable.comparatorProperty());
        bookingTable.setItems(sortedBooking);
    }

    @FXML
    private void makeBookingPress(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/booking/MakeBooking.fxml"));
        Parent root = (Parent) loader.load();

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
                bDAO.delete(selectedBooking);
                refreshBookingTable();
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
                bookingSuccessful.setText("Check-In Successful!");

            } else {
                MainGUIController guiController = new MainGUIController();
                guiController.popupConfirmation(event, "Are you sure you want to undo check-in for this booking?");

                if (guiController.popupController.getConfirm()) {
                    Booking bookingTMP = new Booking(selectedBooking.getId(), selectedBooking.getJobType(), selectedBooking.getDateBooked(),
                            selectedBooking.getVehicleRegistrationNumber(), selectedBooking.getFirstName(), selectedBooking.getLastName(),
                            "No");
                    bDAO.update(bookingTMP);
                    bookingSuccessful.setText("Check-In Undone!");
                }
            }
            noBookingSelected.setText("");
            refreshBookingTable();
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

        } else if (jsDAO.getByBookingId(selectedBooking.getId()) != null) {
            bookingSuccessful.setText("");
            noBookingSelected.setText("Job Sheet Already Exist For This Booking");
        } else {
            JobSheet jobTMP = new JobSheet(0, -1, selectedBooking.getVehicleRegistrationNumber(), selectedBooking.getId(),
                    "#To be filled by Mechanic#", DBDateHelper.parseCurrentDate(), "#Created on "
                    + DBDateHelper.parseCurrentDate().toString() + "#" + " Waiting for a Mechanic. #Rest to be filled by Mechanic#",
                    null);
            jsDAO.save(jobTMP);

            refreshJobTable();
            bookingSuccessful.setText("Job Sheet Successfuly Generated!");
        }
    }

    /*
    -----------------------------------------------Job Section------------------------------------------------------------
     */
    private void refreshJobTable() {
        jobData = FXCollections.observableArrayList(jsDAO.getAll());
        jobTable.setItems(jobData);

        jobTable.setItems(jobData);

        FilteredList<JobSheet> filteredJob = new FilteredList<>(jobData, p -> true);
        jobSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredJob.setPredicate(job -> {
                // If filter text is empty, display all
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(job.getJobNum()).toLowerCase().contains(lowerCaseFilter)
                        || job.getDateCreated().toString().toLowerCase().contains(lowerCaseFilter)
                        || job.getBooking().getJobType().toLowerCase().contains(lowerCaseFilter)
                        || job.getVehicleReg().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (job.getStaff() != null) {
                    if (job.getStaff().getFullName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                } else if (job.getDateCompleted() != null) {
                    if (job.getDateCompleted().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                }
                return false;
            });
        });
        SortedList<JobSheet> sortedJob = new SortedList<>(filteredJob);
        sortedJob.comparatorProperty().bind(jobTable.comparatorProperty());
        jobTable.setItems(sortedJob);
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
                jsDAO.delete(selectedJob);
                refreshJobTable();
            }
        }
    }

    @FXML
    private void viewJobPress(ActionEvent event) throws IOException {
        JobSheet selectedJob = null;
        selectedJob = jobTable.getSelectionModel().getSelectedItem();

        if (selectedJob == null) {
            jobSuccessful.setText("");
            noJobSelected.setText("No Job Selected.");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/job/ViewJob.fxml"));
            Parent root = (Parent) loader.load();

            ViewJobController controller = loader.getController();
            controller.setSelectedJob(selectedJob);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    @FXML
    private void generateInvoicePress(ActionEvent event) {
        JobSheet selectedJob = null;
        selectedJob = jobTable.getSelectionModel().getSelectedItem();

        if (selectedJob == null) {
            jobSuccessful.setText("");
            noJobSelected.setText("No Job Selected.");

        } else if (selectedJob.getDateCompleted() == null) {
            jobSuccessful.setText("");
            noJobSelected.setText("Job Needs To Be Completed First");

        } else if (iDAO.getByJobNum(selectedJob.getJobNum()) != null) {
            jobSuccessful.setText("");
            noJobSelected.setText("Job Sheet Already Exist For This Booking");
        } else {
            float mechanicRate = selectedJob.getStaff().getLabourRate();
            float total = 0, partCost = 0, labourCost = 0;

            ArrayList<Task> taskCost = jsDAO.getByJobNum(selectedJob.getJobNum()).getTasks();
            for (int i = 0; i < taskCost.size(); i++) {
                partCost = partCost + (taskCost.get(i).getStockPart().getPrice() * taskCost.get(i).getPartQty());
                labourCost = labourCost + (taskCost.get(i).getEstDuration() * mechanicRate);
            }
            total = partCost + labourCost;

            Invoice tmp = new Invoice(0, selectedJob.getVehicle().getNationalInsurance(), DBDateHelper.parseCurrentDate(),
                    total, selectedJob.getJobNum());
            iDAO.save(tmp);

            refreshInvoiceTable();
            bookingSuccessful.setText("Invoice Successfuly Generated!");
        }
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
    private void refreshInvoiceTable() {
        invoiceData = FXCollections.observableArrayList(iDAO.getAll());
        invoiceTable.setItems(invoiceData);

        FilteredList<Invoice> filteredInvoice = new FilteredList<>(invoiceData, p -> true);
        invoiceSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredInvoice.setPredicate(invoice -> {
                // If filter text is empty, display all
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(invoice.getId()).toLowerCase().contains(lowerCaseFilter)
                        || invoice.getDateCreated().toString().toLowerCase().contains(lowerCaseFilter)
                        || invoice.getNationalInsurance().toLowerCase().contains(lowerCaseFilter)
                        || invoice.getCustomerAcc().getFirstName().toLowerCase().contains(lowerCaseFilter)
                        || invoice.getCustomerAcc().getLastName().toLowerCase().contains(lowerCaseFilter)
                        || invoice.getCustomerAcc().getFullName().toLowerCase().contains(lowerCaseFilter)
                        || String.valueOf(invoice.getTotalAmount()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Invoice> sortedInvoice = new SortedList<>(filteredInvoice);
        sortedInvoice.comparatorProperty().bind(invoiceTable.comparatorProperty());
        invoiceTable.setItems(sortedInvoice);
    }

    @FXML
    private void makeInvoicePress(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/invoice/MakeInvoice.fxml"));
        Parent root = (Parent) loader.load();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void deleteInvoicePress(ActionEvent event) throws IOException {
        Invoice selectedInvoice = null;
        selectedInvoice = invoiceTable.getSelectionModel().getSelectedItem();

        if (selectedInvoice == null) {

            noInvoiceSelected.setText("No Invoice Selected.");

        } else {
            MainGUIController guiController = new MainGUIController();
            guiController.popupConfirmation(event, "Are you sure you want to delete this invoice?");

            if (guiController.popupController.getConfirm()) {
                iDAO.delete(selectedInvoice);
                refreshInvoiceTable();
            }
        }
    }

    @FXML
    private void viewInvoicePress(ActionEvent event) throws IOException {
        Invoice selectedInvoice = null;
        selectedInvoice = invoiceTable.getSelectionModel().getSelectedItem();

        if (selectedInvoice == null) {

            noInvoiceSelected.setText("No Invoice Selected.");

        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/invoice/ViewInvoice.fxml"));
            Parent root = (Parent) loader.load();

            ViewInvoiceController controller = loader.getController();
            controller.setSelectedInvoice(selectedInvoice);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    @FXML
    private void makePaymentPress(ActionEvent event) throws IOException {
        Invoice selectedInvoice = null;
        selectedInvoice = invoiceTable.getSelectionModel().getSelectedItem();

        if (selectedInvoice == null) {

            noInvoiceSelected.setText("No Invoice Selected.");

        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/invoice/MakePayment.fxml"));
            Parent root = (Parent) loader.load();

            MakePaymentController controller = loader.getController();
            controller.setSelectedInvoice(selectedInvoice);

            Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Stage confirmWindow = new Stage();
            confirmWindow.initModality(Modality.WINDOW_MODAL);
            confirmWindow.initOwner(mainWindow);
            confirmWindow.setResizable(false);
            confirmWindow.setTitle("Make Payment");
            confirmWindow.setScene(new Scene(root));
            confirmWindow.setX(700);
            confirmWindow.setY(300);
            confirmWindow.showAndWait();
            
            if(controller.getConfirm()) {
                float total = PaymentSingleton.getInstance().getAmount();
                      
                Invoice tmp = selectedInvoice;
                tmp.setTotalAmount(total);
                iDAO.update(tmp);
                refreshInvoiceTable();  
            }
        }
    }

    /*
    -----------------------------------------------Part Section-------------------------------------------------------------------
     */
    private void refreshPartTable() {

        partData = FXCollections.observableArrayList(spDAO.getAll());
        partTable.setItems(partData);

        FilteredList<StockPart> filteredStockPart = new FilteredList<>(partData, p -> true);
        partSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredStockPart.setPredicate(stockPart -> {
                // If filter text is empty, display all
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(stockPart.getPartId()).toLowerCase().contains(lowerCaseFilter)
                        || stockPart.getManufacturer().toLowerCase().contains(lowerCaseFilter)
                        || stockPart.getPartName().toLowerCase().contains(lowerCaseFilter)
                        || stockPart.getVehicleType().toLowerCase().contains(lowerCaseFilter)
                        || String.valueOf(stockPart.getPrice()).toLowerCase().contains(lowerCaseFilter)
                        || String.valueOf(stockPart.getThreshold()).toLowerCase().contains(lowerCaseFilter)
                        || String.valueOf(stockPart.getQuantity()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<StockPart> sortedStock = new SortedList<>(filteredStockPart);
        sortedStock.comparatorProperty().bind(partTable.comparatorProperty());
        partTable.setItems(sortedStock);
    }

    @FXML
    private void addPartPress(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/part/AddPart.fxml"));
        Parent root = (Parent) loader.load();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void editPartPress(ActionEvent event) throws IOException {
        StockPart selectedPart = null;
        selectedPart = partTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            partSuccessful.setText("");
            noPartSelected.setText("No Part Selected.");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/part/EditPart.fxml"));
            Parent root = (Parent) loader.load();

            EditPartController controller = loader.getController();
            controller.setSelectedPart(selectedPart);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    @FXML
    private void removePartPress(ActionEvent event) throws IOException {
        StockPart selectedPart = null;
        selectedPart = partTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            partSuccessful.setText("");
            noPartSelected.setText("No Part Selected.");
        } else {
            MainGUIController guiController = new MainGUIController();
            guiController.popupConfirmation(event, "Are you sure you want to remove this part?");

            if (guiController.popupController.getConfirm()) {
                spDAO.delete(selectedPart);
                refreshPartTable();
            }
        }
    }

    @FXML
    private void viewPartPress(ActionEvent event) throws IOException {
        StockPart selectedPart = null;
        selectedPart = partTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            partSuccessful.setText("");
            noPartSelected.setText("No Part Selected.");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/part/ViewPart.fxml"));
            Parent root = (Parent) loader.load();

            ViewPartController controller = loader.getController();
            controller.setSelectedPart(selectedPart);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }


    /*
    -----------------------------------------------Customer Section------------------------------------------------------------
     */
    private void refreshCustomerTable() {
        customerData = FXCollections.observableArrayList(caDAO.getAll());
        customerTable.setItems(customerData);

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
                        || customer.getEmail().toLowerCase().contains(lowerCaseFilter)
                        || customer.getPhoneNumber().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<CustomerAcc> sortedCustomer = new SortedList<>(filteredCustomer);
        sortedCustomer.comparatorProperty().bind(customerTable.comparatorProperty());
        customerTable.setItems(sortedCustomer);
    }

    @FXML
    private void addCustomerPress(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/customer/AddCustomer.fxml"));
        Parent root = (Parent) loader.load();

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/franchisee_menu/FranchiseeEditCustomer.fxml"));
            Parent root = (Parent) loader.load();

            FranchiseeEditCustomerController controller = loader.getController();
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
                refreshVehicleTable();

                //finally deletes customer
                caDAO.delete(selectedCustomer);
                refreshCustomerTable();

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
            loggedInAsText.setText(CurrentUser.getInstance().getStaff().getUserName());
            controller.setSelectedCustomer(selectedCustomer);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    /*
    -----------------------------------------------Vehicle Section------------------------------------------------------------
     */
    private void refreshVehicleTable() {
        vehicleData = FXCollections.observableArrayList(vDAO.getAll());
        vehicleTable.setItems(vehicleData);

        FilteredList<Vehicle> filteredVehicle = new FilteredList<>(vehicleData, p -> true);
        vehicleSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredVehicle.setPredicate(vehicle -> {
                // If filter text is empty, display all
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare
                String lowerCaseFilter = newValue.toLowerCase();

                if (vehicle.getMake() != null) {
                    if (vehicle.getMake().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                } else if (vehicle.getModel() != null) {
                    if (vehicle.getModel().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                } else if (vehicle.getColor() != null) {
                    if (vehicle.getColor().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                } else if (vehicle.getVehicleRegistration().toLowerCase().contains(lowerCaseFilter)
                        || vehicle.getCustomerAcc().getFirstName().toLowerCase().contains(lowerCaseFilter)
                        || vehicle.getCustomerAcc().getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });
        SortedList<Vehicle> sortedVehicle = new SortedList<>(filteredVehicle);
        sortedVehicle.comparatorProperty().bind(vehicleTable.comparatorProperty());
        vehicleTable.setItems(sortedVehicle);
    }

    @FXML
    private void addVehiclePress(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/vehicle/AddVehicle.fxml"));
        Parent root = (Parent) loader.load();

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
                vDAO.delete(selectedVehicle);
                refreshVehicleTable();
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
