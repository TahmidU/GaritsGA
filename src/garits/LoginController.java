/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garits;

import database.dao.account.StaffDAO;
import database.domain.account.Staff;
import garits.singleton.CurrentUser;
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
import menus.admin_menu.AdminMenuController;
import menus.foreperson_menu.ForepersonMenuController;
import menus.franchisee_menu.FranchiseeMenuController;
import menus.mechanic_menu.MechanicMenuController;
import menus.receptionist_menu.ReceptionistMenuController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    private Staff loggingStaff;
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

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menus/AdminMenu/AdminMenu.fxml"));
//        Parent root = (Parent) loader.load();
//
//        AdminMenuController controller = loader.getController();
//        controller.setLoggedInName(username);
//
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(new Scene(root));
//        window.setX(500);
//        window.setY(200);
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || !verifyLogin()) {

            failedLoginText.setText("Wrong username or password.");

        } else {
            username = usernameField.getText();
            displayMenu(event);
        }
    }

    private boolean verifyLogin() {
        StaffDAO sDAO = new StaffDAO();
        loggingStaff = sDAO.getByUserName(usernameField.getText());

        if (loggingStaff == null) {

            return false;

        } else {
            if (loggingStaff.getPassword().equals(passwordField.getText())) {
                CurrentUser.getInstance().setStaff(loggingStaff);
                return true;

            } else {

                return false;

            }
        }
        //return true;
    }

    private void displayMenu(ActionEvent event) throws IOException {
        if (loggingStaff.getType().equals("Administrator")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/admin_menu/AdminMenu.fxml"));
            Parent root = (Parent) loader.load();

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.setX(500);
            window.setY(200);

        } else if (loggingStaff.getType().equals("Franchisee")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/franchisee_menu/FranchiseeMenu.fxml"));
            Parent root = (Parent) loader.load();

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.setX(500);
            window.setY(200);

        } else if (loggingStaff.getType().equals("Foreperson")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/foreperson_menu/ForepersonMenu.fxml"));
            Parent root = (Parent) loader.load();

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.setX(500);
            window.setY(200);

        } else if (loggingStaff.getType().equals("Mechanic")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/mechanic_menu/MechanicMenu.fxml"));
            Parent root = (Parent) loader.load();

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.setX(500);
            window.setY(200);

        } else if (loggingStaff.getType().equals("Receptionist")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/ReceptionistMenu.fxml"));
            Parent root = (Parent) loader.load();

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.setX(500);
            window.setY(200);

        } else {
            System.out.println("Unknown role!!!");
        }
    }
}
