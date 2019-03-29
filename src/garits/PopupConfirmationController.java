/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garits;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class PopupConfirmationController implements Initializable {

    private boolean confirm = false;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label mainText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setMessage(String message) {
        mainText.setText(message);
    }
    
    public boolean getConfirm() {
        return confirm;
    }

    @FXML
    private void yesPress(ActionEvent event) {
        confirm = true;

        Stage confirmWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        confirmWindow.close();
    }

    @FXML
    private void noPress(ActionEvent event) {
        confirm = false;
        
        Stage confirmWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        confirmWindow.close();
    }

}
