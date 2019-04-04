/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.franchisee_menu;

import database.dao.account.AccountHolderDAO;
import database.dao.account.CustomerAccDAO;
import database.dao.discount.DiscountPlanDAO;
import database.dao.discount.FixedDiscountDAO;
import database.domain.account.AccountHolder;
import database.domain.account.CustomerAcc;
import database.domain.discount.DiscountPlan;
import garits.singleton.CurrentUser;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.DBDateHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class FranchiseeEditCustomerController implements Initializable {

    private final ObservableList<String> options
            = FXCollections.observableArrayList(
                    "No",
                    "Yes"
            );

    private final ObservableList<String> discountOptions
            = FXCollections.observableArrayList(
                    "Fixed Discount",
                    "Variable Discount",
                    "Flexible Discount",
                    "None"
            );

    private CustomerAcc selectedCustomer;
    CustomerAccDAO caDAO;
    AccountHolderDAO ahDAO;
    FixedDiscountDAO fdDAO;
    DiscountPlanDAO dpDAO;

    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField phoneText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField postcodeText;
    @FXML
    private TextArea addressText;
    @FXML
    private Label missingDetailsError;
    @FXML
    private Label loggedInAsText;
    @FXML
    private ComboBox<String> accountHolderText;
    @FXML
    private ComboBox<String> discountPackageText;
    @FXML
    private TextField discountAmountText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ahDAO = new AccountHolderDAO();
        caDAO = new CustomerAccDAO();
        fdDAO = new FixedDiscountDAO();
        dpDAO = new DiscountPlanDAO();

        loggedInAsText.setText(CurrentUser.getInstance().getStaff().getUserName());
        accountHolderText.getItems().addAll(options);
        discountPackageText.getItems().addAll(discountOptions);

    }

    public void setSelectedCustomer(CustomerAcc selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
        firstNameText.setText(selectedCustomer.getFirstName());
        lastNameText.setText(selectedCustomer.getLastName());
        phoneText.setText(selectedCustomer.getPhoneNumber());
        emailText.setText(selectedCustomer.getEmail());
        postcodeText.setText(selectedCustomer.getPostCode());
        addressText.setText(selectedCustomer.getAddressLine());

        if (selectedCustomer.getAccountHolder() == null) {
            accountHolderText.setValue("No");
            discountPackageText.setValue("");
            discountAmountText.setText("");
        } else {
            accountHolderText.setValue("Yes");

            if (selectedCustomer.getAccountHolder().getDiscountPlan() == null) {
                discountPackageText.setValue("None");
                discountAmountText.setText("");
            } else {
                discountPackageText.setValue("Fixed Discount");
                discountAmountText.setText(selectedCustomer.getAccountHolder().getDiscountPlan().getType());
            }
        }
    }

    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/franchisee_menu/FranchiseeMenu.fxml"));
        Parent root = (Parent) loader.load();

        FranchiseeMenuController controller = loader.getController();
        controller.switchTab(5);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @FXML
    private void editCustomerSavePress(ActionEvent event) throws IOException {
        missingDetailsError.setText("");

        if (firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() || phoneText.getText().isEmpty()
                || emailText.getText().isEmpty() || addressText.getText().isEmpty() || postcodeText.getText().isEmpty()) {

            missingDetailsError.setText("Missing Details");
        } else {
            CustomerAcc tmp = new CustomerAcc(selectedCustomer.getNationalInsurance(), firstNameText.getText(), lastNameText.getText(), addressText.getText(), postcodeText.getText(),
                    emailText.getText(), phoneText.getText());
            caDAO.update(tmp);
        }

        //Create Account Holder
        if (accountHolderText.getValue().equals("Yes") && selectedCustomer.getAccountHolder() == null) {
            AccountHolder tmp = new AccountHolder(0, selectedCustomer.getNationalInsurance(), DBDateHelper.parseCurrentDate());
            ahDAO.save(tmp);

        }

        //Create Discount Plan
        if (accountHolderText.getValue().equals("Yes") && selectedCustomer.getAccountHolder() != null
                && (!discountPackageText.getValue().equals("None")) && (!discountAmountText.getText().isEmpty())
                && selectedCustomer.getAccountHolder().getDiscountPlan() == null) {
            dpDAO.save(new DiscountPlan(0, discountAmountText.getText(), ahDAO.getByNI(selectedCustomer.getNationalInsurance()).getId()));
        }

        //Delete Account Holder & Discount Plan
        if (accountHolderText.getValue().equals("No") && selectedCustomer.getAccountHolder() != null) {
            if (selectedCustomer.getAccountHolder().getDiscountPlan() != null) {
                dpDAO.delete(selectedCustomer.getAccountHolder().getDiscountPlan());
            }
            ahDAO.delete(selectedCustomer.getAccountHolder());
        }

        //Update Discount Value
        if (accountHolderText.getValue().equals("Yes") && selectedCustomer.getAccountHolder() != null
                && (!discountAmountText.getText().isEmpty()) && (!discountPackageText.getValue().equals("None"))
                && selectedCustomer.getAccountHolder().getDiscountPlan() != null) {
            DiscountPlan tmp = selectedCustomer.getAccountHolder().getDiscountPlan();
            tmp.setType(discountAmountText.getText());
            dpDAO.update(tmp);
        }

        //Delete Discount Plan
        if (accountHolderText.getValue().equals("Yes") && selectedCustomer.getAccountHolder() != null && discountPackageText.getValue().equals("None")
                && selectedCustomer.getAccountHolder().getDiscountPlan() != null) {
            dpDAO.delete(selectedCustomer.getAccountHolder().getDiscountPlan());
        }
        back(event);
    }

    @FXML
    private void backPress(ActionEvent event) throws IOException {
        back(event);
    }

}
