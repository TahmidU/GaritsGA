/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garits;

import Menus.AdminMenu.AdminMenuController;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label failedLoginText;

    public String username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loginPress(ActionEvent event) throws IOException {

        if (verifyLogin()) {
            username = usernameField.getText();
//            Parent root = FXMLLoader.load(getClass().getResource("/Menus/AdminMenu/AdminMenu.fxml"));
//            Scene scene = new Scene(root);
//
//            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//            window.setScene(scene);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menus/AdminMenu/AdminMenu.fxml"));
            Parent root = (Parent) loader.load();

            AdminMenuController controller = loader.getController();
            controller.setLoggedInName(username);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));

        } else {
            failedLoginText.setText("Wrong username or password.");
        }
    }

    private boolean verifyLogin() {
        //return usernameField.getText().equals("admin") && passwordField.getText().equals("admin");
        return true;
    }

}
