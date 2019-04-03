/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.receptionist_menu.part;

import database.dao.part.StockPartDAO;
import database.domain.part.StockPart;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import menus.receptionist_menu.ReceptionistMenuController;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class AddPartController implements Initializable {

    @FXML
    private TextField manufacturerText;
    @FXML
    private TextField partNameText;
    @FXML
    private TextField vehicleTypeText;
    @FXML
    private TextField startYearText;
    @FXML
    private TextField endYearText;
    @FXML
    private TextField partPriceText;
    @FXML
    private TextField quantityText;
    @FXML
    private TextField thresholdText;
    @FXML
    private Label loggedInAsText;
    @FXML
    private Label missingDetailsError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loggedInAsText.setText(CurrentUser.getInstance().getStaff().getUserName());
    }

    private void back(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menus/receptionist_menu/ReceptionistMenu.fxml"));
        Parent root = (Parent) loader.load();

        ReceptionistMenuController controller = loader.getController();
        controller.switchTab(4);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void addPartSavePress(ActionEvent event) throws IOException {
        missingDetailsError.setText("");

        if (manufacturerText.getText().isEmpty() || partNameText.getText().isEmpty() || vehicleTypeText.getText().isEmpty()
                || startYearText.getText().isEmpty() || endYearText.getText().isEmpty() || partPriceText.getText().isEmpty()
                || quantityText.getText().isEmpty() || thresholdText.getText().isEmpty()) {

            missingDetailsError.setText("Missing Details");
        } else {
            StockPartDAO spDAO = new StockPartDAO();
            StockPart tmp = new StockPart(0, partNameText.getText(), Float.valueOf(partPriceText.getText()),
                    Integer.parseInt(thresholdText.getText()), manufacturerText.getText(), vehicleTypeText.getText(), 
                    startYearText.getText(), endYearText.getText(), Integer.parseInt(quantityText.getText()));
            spDAO.save(tmp);
            back(event);
        }
    }

    @FXML
    private void backPress(ActionEvent event) throws IOException {
        back(event);
    }

}
