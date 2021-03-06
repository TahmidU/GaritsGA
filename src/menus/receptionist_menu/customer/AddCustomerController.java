/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.receptionist_menu.customer;

import database.dao.account.CustomerAccDAO;
import database.domain.account.CustomerAcc;
import garits.singleton.CurrentUser;
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
import menus.foreperson_menu.ForepersonMenuController;
import menus.franchisee_menu.FranchiseeMenuController;
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
    private TextArea addressText;
    @FXML
    private Label loggedInAsText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loggedInAsText.setText(CurrentUser.getInstance().getStaff().getUserName());
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
