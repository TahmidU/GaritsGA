/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.mechanic_menu;

import database.dao.job.JobSheetDAO;
import database.dao.job.TaskDAO;
import database.dao.job.VehicleDAO;
import database.dao.part.StockPartDAO;
import database.domain.job.Booking;
import database.domain.job.JobSheet;
import database.domain.job.Task;
import database.domain.job.Vehicle;
import database.domain.part.StockPart;
import garits.MainGUIController;
import garits.singleton.CurrentUser;
import garits.singleton.TaskSingleton;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import menus.receptionist_menu.booking.EditBookingController;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class UpdateJobController implements Initializable {

    private final ObservableList<String> options
            = FXCollections.observableArrayList(
                    "No",
                    "Yes"
            );

    JobSheet selectedJob;

    TaskDAO tDAO;
    ObservableList<Task> taskData;
    StockPartDAO spDAO;
    ObservableList<StockPart> partData;

    @FXML
    private Label loggedInAsText;

    @FXML
    private TextArea statusText;
    @FXML
    private TextArea problemText;
    @FXML
    private DatePicker dateCompletedText;
    @FXML
    private Label jobSuccessfulSave;

    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, Integer> taskIDCol;
    @FXML
    private TableColumn<Task, String> estDurationCol;
    @FXML
    private TableColumn<Task, String> dateCompletedCol;
    @FXML
    private TextArea taskDescriptionText;
    @FXML
    private TextField estDurationText;
    @FXML
    private ComboBox<String> taskCompletedText;
    @FXML
    private Label taskSuccessful;
    @FXML
    private Label noTaskSelected;

    @FXML
    private TableView<Task> taskTable2;
    @FXML
    private TableColumn<Task, Integer> taskIDCol2;
    @FXML
    private TableColumn<Task, String> estDurationCol2;
    @FXML
    private TableColumn<Task, String> dateCompletedCol2;

    @FXML
    private TableView<StockPart> partTable;
    @FXML
    private TableColumn<StockPart, String> partNameCol;
    @FXML
    private TableColumn<StockPart, String> partPriceCol;
    @FXML
    private TableColumn<StockPart, Integer> partQuantityCol;
    @FXML
    private TextField quantityUsedText;
    @FXML
    private TextField usedPartPriceText;
    @FXML
    private TextField usedPartNameText;
    @FXML
    private TextField usedPartQuantityUsedText;
    @FXML
    private Label partSuccessful;
    @FXML
    private Label noPartSelected;
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
    private Label vehicleSuccessful;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedInAsText.setText(CurrentUser.getInstance().getStaff().getUserName());
        taskCompletedText.getItems().addAll(options);

        tDAO = new TaskDAO();
        spDAO = new StockPartDAO();

        taskIDCol.setCellValueFactory(new PropertyValueFactory<Task, Integer>("id"));
        estDurationCol.setCellValueFactory(new PropertyValueFactory<Task, String>("estDuration"));
        dateCompletedCol.setCellValueFactory(new PropertyValueFactory<Task, String>("dateTaskComplete"));
        taskIDCol2.setCellValueFactory(new PropertyValueFactory<Task, Integer>("id"));
        estDurationCol2.setCellValueFactory(new PropertyValueFactory<Task, String>("estDuration"));
        dateCompletedCol2.setCellValueFactory(new PropertyValueFactory<Task, String>("dateTaskComplete"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<StockPart, String>("partName"));
        partPriceCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StockPart, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StockPart, String> partPriceCol) {
                return new ReadOnlyStringWrapper("£" + String.valueOf(partPriceCol.getValue().getPrice()));
            }
        });
        partQuantityCol.setCellValueFactory(new PropertyValueFactory<StockPart, Integer>("quantity"));

    }

    public void setSelectedJob(JobSheet selectedJob) {
        this.selectedJob = selectedJob;

        statusText.setText(selectedJob.getStatus());
        problemText.setText(selectedJob.getProblemDesc());
        if (selectedJob.getDateCompleted() != null) {
            dateCompletedText.setValue(selectedJob.getDateCompleted().toLocalDate());
        }
            makeText.setText(selectedJob.getVehicle().getMake());
            modelText.setText(selectedJob.getVehicle().getModel());
            engineSerialText.setText(selectedJob.getVehicle().getEngineSerial());
            chassisNumbText.setText(selectedJob.getVehicle().getChassisNum());
            colourText.setText(selectedJob.getVehicle().getColor());


        refreshTables();
    }

    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/mechanic_menu/MechanicMenu.fxml"));
        Parent root = (Parent) loader.load();

        MechanicMenuController controller = loader.getController();
        controller.switchTab(1);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    private void refreshTables() {
        taskData = FXCollections.observableArrayList(tDAO.getByJobNum(selectedJob.getJobNum()));
        taskTable.setItems(taskData);
        taskTable2.setItems(taskData);

        partData = FXCollections.observableArrayList(spDAO.getAll());
        partTable.setItems(partData);
    }

    @FXML
    private void jobSavePress(ActionEvent event) {

        Date date;
        if (dateCompletedText.getValue() == null) {
            date = null;
        } else {
            date = Date.valueOf(dateCompletedText.getValue());
        }

        JobSheetDAO jsDAO = new JobSheetDAO();
        JobSheet tmp = new JobSheet(selectedJob.getJobNum(), selectedJob.getStaffId(), selectedJob.getVehicleReg(),
                selectedJob.getBookingId(), statusText.getText(), selectedJob.getDateCreated(), problemText.getText(),
                date);
        jsDAO.update(tmp);
        jobSuccessfulSave.setText("Job Saved Successfully!");
    }

    @FXML
    private void taskTableViewingPress(MouseEvent event) {
        Task selectedTask = null;
        selectedTask = taskTable.getSelectionModel().getSelectedItem();

        if (selectedTask == null) {
        } else {
            taskDescriptionText.setText(selectedTask.getTaskDesc());
            estDurationText.setText(Integer.toString(selectedTask.getEstDuration()));
            taskCompletedText.setValue(selectedTask.getDateTaskComplete());
        }
    }

    @FXML
    private void taskTableViewingPress2(MouseEvent event) {
        Task selectedTask = null;
        selectedTask = taskTable2.getSelectionModel().getSelectedItem();
        TaskSingleton.getInstance().setTask(selectedTask);

        if (selectedTask == null || selectedTask.getStockPart() == null) {
            usedPartPriceText.setText("");
            usedPartNameText.setText("");
            usedPartQuantityUsedText.setText("");

        } else if (selectedTask.getStockPart() != null) {
            usedPartPriceText.setText("£" + Float.toString(selectedTask.getStockPart().getPrice() * selectedTask.getPartQty()));
            usedPartNameText.setText(selectedTask.getStockPart().getPartName());
            usedPartQuantityUsedText.setText(Integer.toString(selectedTask.getPartQty()));

        } else {
        }
    }

    @FXML
    private void generateTaskPress(ActionEvent event) {
        Task tmp = new Task(0, -1, selectedJob.getJobNum(), "#To Be Filled By Mechanic",
                2, 0, "No");
        tDAO.save(tmp);
        refreshTables();
        noTaskSelected.setText("");
        taskSuccessful.setText("Task Generated!");
    }

    @FXML
    private void updateTaskPress(ActionEvent event) throws IOException {
        Task selectedTask = null;
        selectedTask = taskTable.getSelectionModel().getSelectedItem();

        if (selectedTask == null) {
            taskSuccessful.setText("");
            noTaskSelected.setText("No Task Selected.");
        } else {
            int duration;
            if (estDurationText.getText() == null) {
                duration = 0;
            } else {
                duration = Integer.parseInt(estDurationText.getText());
            }

            Task tmp = new Task(selectedTask.getId(), selectedTask.getStockPartId(), selectedTask.getJobNum(),
                    taskDescriptionText.getText(), duration, selectedTask.getPartQty(), taskCompletedText.getValue());
            tDAO.update(tmp);
            refreshTables();
            taskSuccessful.setText("Task Successfully Updated!");
        }
    }

    @FXML
    private void removeTaskPress(ActionEvent event) throws IOException {
        Task selectedTask = null;
        selectedTask = taskTable.getSelectionModel().getSelectedItem();

        if (selectedTask == null) {
            taskSuccessful.setText("");
            noTaskSelected.setText("No Task Selected.");
        } else {
            MainGUIController guiController = new MainGUIController();
            guiController.popupConfirmation(event, "Are you sure you want to delete this task?");

            if (guiController.popupController.getConfirm()) {
                tDAO.delete(selectedTask);
                refreshTables();
                taskSuccessful.setText("Task Successfully Deleted!");
            }
        }
    }

    @FXML
    private void addPartPress(ActionEvent event) {
        StockPart selectedPart = null;
        selectedPart = partTable.getSelectionModel().getSelectedItem();

        if (TaskSingleton.getInstance().getTask() == null) {
            partSuccessful.setText("");
            noPartSelected.setText("No Task Selected.");
        }
        if (selectedPart == null) {
            partSuccessful.setText("");
            noPartSelected.setText("No Part Selected.");
        }
        if (quantityUsedText.getText().isEmpty()) {
            partSuccessful.setText("");
            noPartSelected.setText("Quantity Must be Specified");
        }
        if (TaskSingleton.getInstance().getTask().getStockPart() != null) {
            partSuccessful.setText("");
            noPartSelected.setText("Task Already Has A Part");

        } else {
            //Task tmp = new Task(selectedTask.getId(), selectedPart.getPartId(), selectedTask.getJobNum(), selectedTask.getTaskDesc(),
            //        selectedTask.getEstDuration(), Integer.parseInt(quantityUsedText.getText()), selectedTask.getDateTaskComplete());

            Task tmp = TaskSingleton.getInstance().getTask();
            tmp.setStockPartId(selectedPart.getPartId());
            tmp.setPartQty(Integer.parseInt(quantityUsedText.getText()));
            tDAO.update(tmp);

//            StockPart partTMP = new StockPart(selectedPart.getPartId(), selectedPart.getPartName(), selectedPart.getPrice(),
//                    selectedPart.getThreshold(), selectedPart.getManufacturer(), selectedPart.getVehicleType(),
//                    selectedPart.getStartYr(), selectedPart.getEndYr(), Integer.parseInt(quantityText.getText()));
            StockPart partTMP = selectedPart;
            partTMP.setQuantity(partTMP.getQuantity() - Integer.parseInt(quantityUsedText.getText()));
            spDAO.update(partTMP);

            refreshTables();
            quantityUsedText.setText(null);
            partSuccessful.setText("Part Successfully Added!");
        }
    }

    @FXML
    private void editVehicleSavePress(ActionEvent event) {

        VehicleDAO vDAO = new VehicleDAO();

        Vehicle tmp = new Vehicle(selectedJob.getVehicleReg(), selectedJob.getVehicle().getCustomerAcc().getNationalInsurance(),
                makeText.getText(), modelText.getText(), engineSerialText.getText(), chassisNumbText.getText(), colourText.getText());
        vDAO.update(tmp);
        vehicleSuccessful.setText("Vehicle Info Saved Successfully!");
    }

    @FXML
    private void backPress(ActionEvent event) throws IOException {
        back(event);
    }

}
