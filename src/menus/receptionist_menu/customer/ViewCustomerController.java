/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.receptionist_menu.customer;

import database.dao.job.VehicleDAO;
import database.domain.account.CustomerAcc;
import database.domain.job.Vehicle;
import garits.singleton.CurrentUser;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import menus.foreperson_menu.ForepersonMenuController;
import menus.franchisee_menu.FranchiseeMenuController;
import menus.receptionist_menu.ReceptionistMenuController;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class ViewCustomerController implements Initializable {

    private CustomerAcc selectedCustomer;

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
    private TextField postcodeText;
    @FXML
    private TextArea addressText;
    @FXML
    private Label loggedInAsText;
    @FXML
    private TableView<Vehicle> vehicleTable;
    @FXML
    private TableColumn<Vehicle, String> vehicleNameCol;
    @FXML
    private TableColumn<Vehicle, String> vehicleRegCol;
    @FXML
    private TextField discountAmountText;
    @FXML
    private TextField accountHolderText;
    @FXML
    private TextField discountPackageText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedInAsText.setText(CurrentUser.getInstance().getStaff().getUserName());
    }

    public void setSelectedCustomer(CustomerAcc selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
        firstNameText.setText(selectedCustomer.getFirstName());
        lastNameText.setText(selectedCustomer.getLastName());
        phoneText.setText(selectedCustomer.getPhoneNumber());
        emailText.setText(selectedCustomer.getEmail());
        nationalInsuranceText.setText(selectedCustomer.getNationalInsurance());
        postcodeText.setText(selectedCustomer.getPostCode());
        addressText.setText(selectedCustomer.getAddressLine());

        VehicleDAO vDAO = new VehicleDAO();
        ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList(vDAO.getByNI(selectedCustomer.getNationalInsurance()));

        vehicleNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vehicle, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vehicle, String> vehicleNameCol) {
                return new ReadOnlyStringWrapper(vehicleNameCol.getValue().getVehicleName());
            }
        });
        vehicleRegCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicleRegistration"));

        vehicleTable.setItems(vehicleData);
        
        if(selectedCustomer.getAccountHolder() != null) {
            accountHolderText.setText("Yes");
            if(selectedCustomer.getAccountHolder().getDiscountPlan() != null) {
                discountPackageText.setText("Fixed Discount");
                discountAmountText.setText(selectedCustomer.getAccountHolder().getDiscountPlan().getType());
            }
        }
        else {
            accountHolderText.setText("No");
        }
    }

    private void back(ActionEvent event) throws IOException {

        if (CurrentUser.getInstance().getStaff().getType().equals("Franchisee")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/franchisee_menu/FranchiseeMenu.fxml"));
            Parent root = (Parent) loader.load();

            FranchiseeMenuController controller = loader.getController();
            controller.switchTab(5);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));

        } else if (CurrentUser.getInstance().getStaff().getType().equals("Foreperson")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/foreperson_menu/ForepersonMenu.fxml"));
            Parent root = (Parent) loader.load();

            ForepersonMenuController controller = loader.getController();
            controller.switchTab(5);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));

        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/ReceptionistMenu.fxml"));
            Parent root = (Parent) loader.load();

            ReceptionistMenuController controller = loader.getController();
            controller.switchTab(5);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    @FXML
    private void backPress(ActionEvent event) throws IOException {
        back(event);
    }

}
