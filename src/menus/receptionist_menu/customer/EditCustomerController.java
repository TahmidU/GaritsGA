/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.receptionist_menu.customer;

import database.dao.account.CustomerAccDAO;
import database.domain.account.CustomerAcc;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import menus.receptionist_menu.ReceptionistMenuController;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class EditCustomerController implements Initializable {

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
    private TextField postcodeText;
    @FXML
    private TextArea addressText;
    @FXML
    private Label missingDetailsError;
    @FXML
    private Label loggedInAsText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setLoggedInName(String s) {
        loggedInAsText.setText(s);
    }

    public void setSelectedCustomer(CustomerAcc selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
        firstNameText.setText(selectedCustomer.getFirstName());
        lastNameText.setText(selectedCustomer.getLastName());
        phoneText.setText(selectedCustomer.getPhoneNumber());
        emailText.setText(selectedCustomer.getEmail());
        postcodeText.setText(selectedCustomer.getPostCode());
        addressText.setText(selectedCustomer.getAddressLine());
    }

    private void back(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/ReceptionistMenu.fxml"));
        Parent root = (Parent) loader.load();

        ReceptionistMenuController controller = loader.getController();
        controller.setLoggedInName(loggedInAsText.getText());
        controller.switchTab(5);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void editCustomerSavePress(ActionEvent event) throws IOException {
        missingDetailsError.setText("");
        CustomerAccDAO caDAO = new CustomerAccDAO();

        if (firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() || phoneText.getText().isEmpty()
                || emailText.getText().isEmpty() || addressText.getText().isEmpty() || postcodeText.getText().isEmpty()) {

            missingDetailsError.setText("Missing Details");
        } else {
            CustomerAcc tmp = new CustomerAcc(selectedCustomer.getNationalInsurance(), firstNameText.getText(), lastNameText.getText(), addressText.getText(), postcodeText.getText(),
                    emailText.getText(), phoneText.getText());
            caDAO.update(tmp);
            back(event);
        }
    }

    @FXML
    private void backPress(ActionEvent event) throws IOException {
        back(event);
    }

}
