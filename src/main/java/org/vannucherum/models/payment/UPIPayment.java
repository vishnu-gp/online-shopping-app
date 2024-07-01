package org.vannucherum.models.payment;

import org.vannucherum.models.account.Customer;
import org.vannucherum.utils.AppLogger;

public class UPIPayment extends Payment {
    public UPIPayment(double amount, Customer customer) {
        super(amount, customer);
    }

    @Override
    public boolean pay() {
        AppLogger.logInfo("Payment completed via UPI.");

        return true;
    }
}
