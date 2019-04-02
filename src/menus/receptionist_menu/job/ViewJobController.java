/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.receptionist_menu.job;

import database.dao.job.TaskDAO;
import database.domain.job.JobSheet;
import database.domain.job.Task;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import menus.receptionist_menu.ReceptionistMenuController;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class ViewJobController implements Initializable {

    TaskDAO tDAO;
    ObservableList<Task> taskData;

    JobSheet selectedJob;

    @FXML
    private Label loggedInAsText;
    @FXML
    private TextField jobNumberText;
    @FXML
    private TextField mechanicText;
    @FXML
    private TextField bookingIDText;
    @FXML
    private TextField vehicleRegText;
    @FXML
    private TextField dateCreatedText;
    @FXML
    private TextField dateCompletedText;
    @FXML
    private TextArea statusText;
    @FXML
    private TextArea problemText;

    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, Integer> taskIDCol;
    @FXML
    private TableColumn<Task, Integer> estDurationCol;
    @FXML
    private TableColumn<Task, Date> dateCompletedCol;
    @FXML
    private TextArea taskDescriptionText;
    @FXML
    private TextField partIDText;
    @FXML
    private TextField partNameText;
    @FXML
    private TextField quantityUsedText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setLoggedInName(String s) {
        loggedInAsText.setText(s);
    }

    public void setSelectedJob(JobSheet selectedJob) {
        this.selectedJob = selectedJob;
        jobNumberText.setText(Integer.toString(selectedJob.getBookingId()));
        if (selectedJob.getStaff() == null) {
            mechanicText.setText("");
        } else {
            mechanicText.setText(selectedJob.getStaff().getFullName());
        }
        bookingIDText.setText(Integer.toString(selectedJob.getBookingId()));
        vehicleRegText.setText(selectedJob.getVehicleReg());
        dateCreatedText.setText(selectedJob.getDateCreated().toString());
        if (selectedJob.getDateCompleted() == null) {
            dateCompletedText.setText("");
        } else {
            dateCompletedText.setText(selectedJob.getDateCompleted().toString());
        }
        statusText.setText(selectedJob.getStatus());
        problemText.setText(selectedJob.getProblemDesc());

        tDAO = new TaskDAO();
        taskData = FXCollections.observableArrayList(tDAO.getByJobNum(selectedJob.getJobNum()));

        taskIDCol.setCellValueFactory(new PropertyValueFactory<Task, Integer>("id"));
        estDurationCol.setCellValueFactory(new PropertyValueFactory<Task, Integer>("estDuration"));
        dateCompletedCol.setCellValueFactory(new PropertyValueFactory<Task, Date>("dateTaskComplete"));

        taskTable.setItems(taskData);
    }

    private void back(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/ReceptionistMenu.fxml"));
        Parent root = (Parent) loader.load();

        ReceptionistMenuController controller = loader.getController();
        controller.setLoggedInName(loggedInAsText.getText());
        controller.switchTab(2);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void viewingTaskPress(MouseEvent event) {
        Task selectedTask = null;
        selectedTask = taskTable.getSelectionModel().getSelectedItem();

        if (selectedTask == null) {
            taskDescriptionText.setText("");
            partIDText.setText("");
            partNameText.setText("");
            quantityUsedText.setText("");
        } else {
            taskDescriptionText.setText(selectedTask.getTaskDesc());
            partIDText.setText(Integer.toString(selectedTask.getStockPartId()));
            partNameText.setText(selectedTask.getStockPart().getPartName());
            quantityUsedText.setText(Integer.toString(selectedTask.getPartQty()));
        }
    }

    @FXML
    private void backPress(ActionEvent event) throws IOException {
        back(event);
    }

}
