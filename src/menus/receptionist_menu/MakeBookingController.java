/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.receptionist_menu;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class MakeBookingController implements Initializable {

    private final ObservableList<String> options
            = FXCollections.observableArrayList(
                    "MOT",
                    "Repair"
            );
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField vehicleRegText;
    @FXML
    private ComboBox<String> jobCombo;
    @FXML
    private Label passwordMatchError;
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
        jobCombo.getItems().addAll(options);
    }

    public void setLoggedInName(String s) {
        loggedInAsText.setText(s);
    }

    private void back(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/ReceptionistMenu.fxml"));
        Parent root = (Parent) loader.load();

        ReceptionistMenuController controller = loader.getController();
        controller.setLoggedInName(loggedInAsText.getText());
        controller.switchTab();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    private void addAccountSavePress(ActionEvent event) throws IOException {
        missingDetailsError.setText("");
        passwordMatchError.setText("");
//
//        if (firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() || phoneText.getText().isEmpty()
//                || emailText.getText().isEmpty() || typeCombo.getValue() == null || usernameText.getText().isEmpty()
//                || passwordText.getText().isEmpty() || retypeText.getText().isEmpty()) {
//
//            missingDetailsError.setText("Missing Details");
//        } else if (!passwordText.getText().equals(retypeText.getText())) {
//            passwordMatchError.setText("Password does not match");
//        } else {
//            StaffDAO sDAO = new StaffDAO();
//            Staff tmp = new Staff(0, usernameText.getText(), passwordText.getText(), firstNameText.getText(), lastNameText.getText(),
//                    phoneText.getText(), emailText.getText(), typeCombo.getValue());
//            sDAO.save(tmp);
//            back(event);
//        }
    }

    @FXML
    private void backPress(ActionEvent event) throws IOException {
        back(event);
    }

    @FXML
    private void makeBookingSavePress(ActionEvent event) {
    }

}
