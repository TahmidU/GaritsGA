/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.admin_menu;

import database.dao.account.StaffDAO;
import database.domain.account.Staff;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class AddAccountController implements Initializable {

    private final ObservableList<String> options
            = FXCollections.observableArrayList(
                    "Administrator",
                    "Franchisee",
                    "Foreperson",
                    "Mechanic",
                    "Receptionist"
            );

    @FXML
    private Label loggedInAsText;
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private PasswordField retypeText;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField phoneText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField lastNameText;
    @FXML
    private ComboBox<String> typeCombo;
    @FXML
    private Label passwordMatchError;
    @FXML
    private Label missingDetailsError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loggedInAsText.setText(CurrentUser.getInstance().getStaff().getUserName());
        typeCombo.getItems().addAll(options);
    }

    private void back(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/admin_menu/AdminMenu.fxml"));
        Parent root = (Parent) loader.load();

        AdminMenuController controller = loader.getController();
        controller.switchTab();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void addAccountSavePress(ActionEvent event) throws IOException {
        missingDetailsError.setText("");
        passwordMatchError.setText("");

        if (firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() || phoneText.getText().isEmpty()
                || emailText.getText().isEmpty() || typeCombo.getValue() == null || usernameText.getText().isEmpty()
                || passwordText.getText().isEmpty() || retypeText.getText().isEmpty()) {

            missingDetailsError.setText("Missing Details");
        } else if (!passwordText.getText().equals(retypeText.getText())) {
            passwordMatchError.setText("Password does not match");
        } else if(new StaffDAO().getByUserName(usernameText.getText()) != null)
        {
            passwordMatchError.setText("Username already exists");
        } else {
            StaffDAO sDAO = new StaffDAO();
            Staff tmp = new Staff(0, usernameText.getText(), passwordText.getText(), firstNameText.getText(), lastNameText.getText(),
                    phoneText.getText(), emailText.getText(), typeCombo.getValue(), 0f);
            sDAO.save(tmp);
            back(event);
        }
    }

    @FXML
    private void backPress(ActionEvent event) throws IOException {
        back(event);
    }
}
