/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garits;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Huntees
 */
public class MainGUIController {
    
    public PopupConfirmationController popupController;
    
    public MainGUIController() {

    }

    public void logOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/garits/Login.fxml"));
        Parent root = (Parent) loader.load();

        //LoginController controller = loader.getController();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.setX(600);
        window.setY(300);
    }

    public void popupConfirmation(ActionEvent event, String message) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/garits/PopupConfirmation.fxml"));
        Parent root = (Parent) loader.load();

        popupController = loader.getController();
        popupController.setMessage(message);

        Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage confirmWindow = new Stage();
        confirmWindow.initModality(Modality.WINDOW_MODAL);
        confirmWindow.initOwner(mainWindow);
        confirmWindow.setResizable(false);
        confirmWindow.setTitle("Confirmation");
        confirmWindow.setScene(new Scene(root));
        confirmWindow.setX(700);
        confirmWindow.setY(400);
        confirmWindow.showAndWait();
    }

}
