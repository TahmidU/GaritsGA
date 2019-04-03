/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garits.singleton;

import database.domain.job.Booking;

/**
 *
 * @author Huntees
 */
public class PaymentSingleton {

    private static PaymentSingleton instance = null;
    private static Float amount;

    public static PaymentSingleton getInstance() {
        if (instance == null) {
            instance = new PaymentSingleton();
        }

        return instance;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        PaymentSingleton.amount = amount;
    }
}
