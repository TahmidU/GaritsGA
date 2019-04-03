/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.receptionist_menu.invoice;

import database.domain.payment.Invoice;
import garits.singleton.PaymentSingleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Huntees
 */
public class MakePaymentController implements Initializable {

    Invoice selectedInvoice;

    @FXML
    private TextField initialAmountText;
    @FXML
    private TextField customerDiscountText;
    @FXML
    private TextField amountDueText;
    @FXML
    private TextField customerPaymentText;
    @FXML
    private TextField newAmountText;
    @FXML
    private Label missingCustomerPayment;

    private boolean confirm = false;

    float initialAmount, customerDiscount, amountDue, newAmount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setSelectedInvoice(Invoice selectedInvoice) {
        this.selectedInvoice = selectedInvoice;

        initialAmount = selectedInvoice.getTotalAmount();

        if (selectedInvoice.getCustomerAcc().getAccountHolder() != null) {
            customerDiscount = selectedInvoice.getCustomerAcc().getAccountHolder().getDiscountPlan().getFixedDiscount().getPercentage();
        } else {
            customerDiscount = 0;
        }

        amountDue = initialAmount * (1 - customerDiscount);

        initialAmountText.setText(Float.toString(initialAmount));
        customerDiscountText.setText(Float.toString(customerDiscount * 100));
        amountDueText.setText(Float.toString(amountDue));
    }

    public boolean getConfirm() {
        return confirm;
    }

    @FXML
    private void noPress(ActionEvent event) {
        confirm = false;

        Stage confirmWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        confirmWindow.close();
    }

    @FXML
    private void yesPress(ActionEvent event) {

        if (customerPaymentText.getText().isEmpty()) {
            missingCustomerPayment.setText("No Payment Amount Specified!");
        } else if (newAmount < 0) {
            missingCustomerPayment.setText("Invalid Payment Amount!");
        } else {
            PaymentSingleton.getInstance().setAmount(newAmount);
            confirm = true;

            Stage confirmWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            confirmWindow.close();
        }
    }

    @FXML
    private void customerPaymentListener(KeyEvent event) {
        if (customerPaymentText.getText().isEmpty() || customerPaymentText.getText().matches(".*[a-z].*")
                || customerPaymentText.getText().contains(" ") || customerPaymentText.getText().matches(".*[\\p{L}]-=['?+.*]")) {
            newAmountText.setText(Float.toString(0));
        } else {
            newAmount = amountDue - (Float.parseFloat(customerPaymentText.getText()));
            newAmountText.setText(Float.toString(newAmount));
        }
    }

}
