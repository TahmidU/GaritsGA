/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.receptionist_menu.job;

import database.dao.job.TaskDAO;
import database.domain.job.JobSheet;
import database.domain.job.Task;
import garits.singleton.CurrentUser;
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
import menus.foreperson_menu.ForepersonMenuController;
import menus.franchisee_menu.FranchiseeMenuController;
import menus.mechanic_menu.MechanicMenuController;
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
    @FXML
    private TextField totalPriceText;
    @FXML
    private TextField mechanicRateText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedInAsText.setText(CurrentUser.getInstance().getStaff().getUserName());
    }

    public void setSelectedJob(JobSheet selectedJob) {
        this.selectedJob = selectedJob;
        jobNumberText.setText(Integer.toString(selectedJob.getBookingId()));
        if (selectedJob.getStaff() == null) {
            mechanicText.setText("");
            mechanicRateText.setText("");
        } else {
            mechanicText.setText(selectedJob.getStaff().getFullName());
            mechanicRateText.setText(Float.toString(selectedJob.getStaff().getLabourRate()));
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

        if (CurrentUser.getInstance().getStaff().getType().equals("Franchisee")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/franchisee_menu/FranchiseeMenu.fxml"));
            Parent root = (Parent) loader.load();

            FranchiseeMenuController controller = loader.getController();
            controller.switchTab(2);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));

        } else if (CurrentUser.getInstance().getStaff().getType().equals("Foreperson")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/foreperson_menu/ForepersonMenu.fxml"));
            Parent root = (Parent) loader.load();

            ForepersonMenuController controller = loader.getController();
            controller.switchTab(2);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
        else if (CurrentUser.getInstance().getStaff().getType().equals("Mechanic")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/mechanic_menu/MechanicMenu.fxml"));
            Parent root = (Parent) loader.load();

            MechanicMenuController controller = loader.getController();
            controller.switchTab(1);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/ReceptionistMenu.fxml"));
            Parent root = (Parent) loader.load();

            ReceptionistMenuController controller = loader.getController();
            controller.switchTab(2);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    @FXML
    private void viewingTaskPress(MouseEvent event) {
        Task selectedTask = null;
        selectedTask = taskTable.getSelectionModel().getSelectedItem();

        if (selectedTask == null || selectedTask.getStockPart() == null) {
            taskDescriptionText.setText("");
            partIDText.setText("");
            partNameText.setText("");
            quantityUsedText.setText("");
            totalPriceText.setText("");
        } else {
            taskDescriptionText.setText(selectedTask.getTaskDesc());
            partIDText.setText(Integer.toString(selectedTask.getStockPartId()));
            partNameText.setText(selectedTask.getStockPart().getPartName());
            quantityUsedText.setText(Integer.toString(selectedTask.getPartQty()));
            totalPriceText.setText("£" + Float.toString(selectedTask.getStockPart().getPrice() * selectedTask.getPartQty()));
        }
    }

    @FXML
    private void backPress(ActionEvent event) throws IOException {
        back(event);
    }

}
