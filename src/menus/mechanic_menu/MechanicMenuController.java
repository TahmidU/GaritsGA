/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.mechanic_menu;

import database.dao.job.JobSheetDAO;
import database.dao.part.StockPartDAO;
import database.domain.job.Booking;
import database.domain.job.JobSheet;
import database.domain.part.StockPart;
import garits.MainGUIController;
import garits.singleton.CurrentUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import menus.receptionist_menu.booking.EditBookingController;
import menus.receptionist_menu.job.ViewJobController;
import menus.receptionist_menu.part.ViewPartController;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class MechanicMenuController implements Initializable {

    JobSheetDAO jsDAO;
    ObservableList<JobSheet> jobData;

    StockPartDAO spDAO;
    ObservableList<StockPart> partData;

    @FXML
    private Label loggedInAsText;
    @FXML
    private TabPane mechanicTab;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedInAsText.setText(CurrentUser.getInstance().getStaff().getUserName());

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
    }

    public void switchTab(int n) {
        SingleSelectionModel<Tab> selectionModel = mechanicTab.getSelectionModel();
        selectionModel.select(n);
    }

//================================================================================================================================
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
    private void pickJobPress(ActionEvent event) throws IOException {
        JobSheet selectedJob = null;
        selectedJob = jobTable.getSelectionModel().getSelectedItem();

        if (selectedJob == null) {
            jobSuccessful.setText("");
            noJobSelected.setText("No Job Selected.");

        } else if (selectedJob.getStaff() != null && CurrentUser.getInstance().getStaff().getId() != selectedJob.getStaffId()) {
            noJobSelected.setText("Job Already Picked.");

        } else {
            if (selectedJob.getStaff() == null) {
                JobSheet jobTMP = new JobSheet(selectedJob.getJobNum(), CurrentUser.getInstance().getStaff().getId(),
                        selectedJob.getVehicleReg(), selectedJob.getBookingId(), selectedJob.getProblemDesc(),
                        selectedJob.getDateCreated(), selectedJob.getStatus(), selectedJob.getDateCompleted());
                jsDAO.update(jobTMP);
                jobSuccessful.setText("Job Pick Successful!");
            } else {
                MainGUIController guiController = new MainGUIController();
                guiController.popupConfirmation(event, "Are you sure you want to remove yourself from this job?");

                if (guiController.popupController.getConfirm()) {
                    JobSheet jobTMP = new JobSheet(selectedJob.getJobNum(), -1,
                            selectedJob.getVehicleReg(), selectedJob.getBookingId(), selectedJob.getProblemDesc(),
                            selectedJob.getDateCreated(), selectedJob.getStatus(), selectedJob.getDateCompleted());
                    jsDAO.update(jobTMP);
                    jobSuccessful.setText("Job Pick Successful!");
                }
            }
            noJobSelected.setText("");
            refreshJobTable();
        }
    }

    @FXML
    private void updateJobPress(ActionEvent event) throws IOException {
        JobSheet selectedJob = null;
        selectedJob = jobTable.getSelectionModel().getSelectedItem();

        if (selectedJob == null) {
            jobSuccessful.setText("");
            noJobSelected.setText("No Job Selected.");

        } else if (selectedJob.getStaff() == null || CurrentUser.getInstance().getStaff().getId() != selectedJob.getStaffId()) {
            noJobSelected.setText("You Cannot Update This Job.");

        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/mechanic_menu/UpdateJob.fxml"));
            Parent root = (Parent) loader.load();

            UpdateJobController controller = loader.getController();
            controller.setSelectedJob(selectedJob);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
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

    @FXML
    private void logOutPress(ActionEvent event) throws IOException {

        MainGUIController guiController = new MainGUIController();
        guiController.popupConfirmation(event, "Are you sure you want to logout? Unsaved changes will not be stored.");

        if (guiController.popupController.getConfirm()) {
            guiController.logOut(event);
        }
    }
}
