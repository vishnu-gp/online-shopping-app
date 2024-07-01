package org.vannucherum.models.payment;

import org.vannucherum.models.Order;
import org.vannucherum.models.account.Customer;
import org.vannucherum.utils.AppLogger;

public class CreditCardPayment extends Payment {
    public CreditCardPayment(double amount, Customer customer) {
        super(amount, customer);
    }

    @Override
    public boolean pay() {
        AppLogger.logInfo("Payment completed via Credit Card.");

        return true;
    }
}
