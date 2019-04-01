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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import menus.receptionist_menu.ReceptionistMenuController;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class AddCustomerController implements Initializable {

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
    private Label missingDetailsError;
    @FXML
    private TextField addressText;
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
    private void addCustomerSavePress(ActionEvent event) throws IOException {
        missingDetailsError.setText("");
        CustomerAccDAO caDAO = new CustomerAccDAO();

        if (firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() || phoneText.getText().isEmpty()
                || emailText.getText().isEmpty() || nationalInsuranceText.getText().isEmpty() || addressText.getText().isEmpty()
                || postcodeText.getText().isEmpty()) {

            missingDetailsError.setText("Missing Details");
        } else if (caDAO.getByNI(nationalInsuranceText.getText()) != null) {
            
            missingDetailsError.setText("National Insurance Number Already Exists");
        } else {
            CustomerAcc tmp = new CustomerAcc(nationalInsuranceText.getText(), firstNameText.getText(), lastNameText.getText(), addressText.getText(), postcodeText.getText(),
                    emailText.getText(), phoneText.getText());
            caDAO.save(tmp);
            back(event);
        }
    }

    @FXML
    private void backPress(ActionEvent event) throws IOException {
        back(event);
    }

}
