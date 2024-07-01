package org.vannucherum.models.payment;

import org.vannucherum.models.account.Customer;
import org.vannucherum.utils.AppLogger;

public class CashOnDelivery extends Payment {
    public CashOnDelivery(double amount, Customer customer) {
        super(amount, customer);
    }

    @Override
    public boolean pay() {
        AppLogger.logInfo("Payment will be done on delivery via Cash.");

        return true;
    }
}
