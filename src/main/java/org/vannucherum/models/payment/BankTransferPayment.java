package org.vannucherum.models.payment;

import org.vannucherum.models.account.Customer;
import org.vannucherum.utils.AppLogger;

public class BankTransferPayment extends Payment {

    public BankTransferPayment(double amount, Customer customer) {
        super(amount, customer);
    }

    @Override
    protected boolean pay() {
        AppLogger.logInfo("Payment completed via Bank Transfer");

        return true;
    }
}
