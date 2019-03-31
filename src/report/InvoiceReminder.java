package report;
/**
 * Sample Skeleton for 'invoice_reminder.fxml' Controller Class
 */

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class InvoiceReminder {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="customerAddressLabel"
    private Label customerAddressLabel; // Value injected by FXMLLoader

    @FXML // fx:id="garitsAddressLabel"
    private Label garitsAddressLabel; // Value injected by FXMLLoader

    @FXML // fx:id="DateLabel"
    private Label DateLabel; // Value injected by FXMLLoader

    @FXML // fx:id="DearLabel"
    private Label DearLabel; // Value injected by FXMLLoader

    @FXML // fx:id="InvoiceTitleLabel"
    private Label InvoiceTitleLabel; // Value injected by FXMLLoader

    @FXML // fx:id="VehicleRegistrationNoLabel"
    private Label VehicleRegistrationNoLabel; // Value injected by FXMLLoader

    @FXML // fx:id="DescriptionLabel"
    private Label DescriptionLabel; // Value injected by FXMLLoader

    @FXML // fx:id="signatureImage"
    private ImageView signatureImage; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {


    }
}

