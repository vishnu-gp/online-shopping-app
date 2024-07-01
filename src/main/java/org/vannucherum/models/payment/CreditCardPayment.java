package org.vannucherum.models.payment;

import org.vannucherum.models.Order;
import org.vannucherum.models.account.Customer;

public class CreditCardPayment extends Payment {
    public CreditCardPayment(double amount, Customer customer) {
        super(amount, customer);
    }

    @Override
    public boolean pay() {
        System.out.println("Payment completed via Credit Card.");
        return true;
    }
}
