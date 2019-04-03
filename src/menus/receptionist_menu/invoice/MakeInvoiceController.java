/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.receptionist_menu.invoice;

import database.dao.account.CustomerAccDAO;
import database.dao.payment.InvoiceDAO;
import database.domain.account.CustomerAcc;
import database.domain.payment.Invoice;
import garits.singleton.CurrentUser;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import menus.foreperson_menu.ForepersonMenuController;
import menus.franchisee_menu.FranchiseeMenuController;
import menus.receptionist_menu.ReceptionistMenuController;
import util.DBDateHelper;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class MakeInvoiceController implements Initializable {

    CustomerAccDAO caDAO;
    ObservableList<CustomerAcc> customerData;

    @FXML
    private Label loggedInAsText;
    @FXML
    private TableView<CustomerAcc> associateTable;
    @FXML
    private TableColumn<CustomerAcc, String> insuranceNoCol;
    @FXML
    private TableColumn<CustomerAcc, String> nameCol;
    @FXML
    private TextField invoiceAmountText;
    @FXML
    private Label missingDetailsError;
    @FXML
    private TextField customerSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedInAsText.setText(CurrentUser.getInstance().getStaff().getUserName());

        caDAO = new CustomerAccDAO();

        insuranceNoCol.setCellValueFactory(new PropertyValueFactory<CustomerAcc, String>("nationalInsurance"));
        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerAcc, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CustomerAcc, String> nameCol) {
                return new ReadOnlyStringWrapper(nameCol.getValue().getFullName());
            }
        });

        refreshCustomerTable();
    }

    private void back(ActionEvent event) throws IOException {

        if (CurrentUser.getInstance().getStaff().getType().equals("Franchisee")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/franchisee_menu/FranchiseeMenu.fxml"));
            Parent root = (Parent) loader.load();

            FranchiseeMenuController controller = loader.getController();
            controller.switchTab(3);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));

        } else if (CurrentUser.getInstance().getStaff().getType().equals("Foreperson")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/foreperson_menu/ForepersonMenu.fxml"));
            Parent root = (Parent) loader.load();

            ForepersonMenuController controller = loader.getController();
            controller.switchTab(3);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));

        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/ReceptionistMenu.fxml"));
            Parent root = (Parent) loader.load();

            ReceptionistMenuController controller = loader.getController();
            controller.switchTab(3);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    private void refreshCustomerTable() {
        customerData = FXCollections.observableArrayList(caDAO.getAll());
        associateTable.setItems(customerData);

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
                        || customer.getFullName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<CustomerAcc> sortedCustomer = new SortedList<>(filteredCustomer);
        sortedCustomer.comparatorProperty().bind(associateTable.comparatorProperty());
        associateTable.setItems(sortedCustomer);
    }

    @FXML
    private void makeInvoiceSave(ActionEvent event) throws IOException {
        CustomerAcc selectedCustomer = null;
        selectedCustomer = associateTable.getSelectionModel().getSelectedItem();

        if (invoiceAmountText.getText().isEmpty() || selectedCustomer == null) {

            missingDetailsError.setText("Missing Details");

        } else if (invoiceAmountText.getText().matches(".*[a-z].*")) {
            missingDetailsError.setText("Invalid Invoice Amount");
        } else {
            InvoiceDAO iDAO = new InvoiceDAO();
            Invoice tmp = new Invoice(0, selectedCustomer.getNationalInsurance(), DBDateHelper.parseCurrentDate(),
                    Float.parseFloat(invoiceAmountText.getText()), -1);
            iDAO.save(tmp);
            back(event);
        }
    }

    @FXML
    private void backPress(ActionEvent event) throws IOException {
        back(event);
    }

}
