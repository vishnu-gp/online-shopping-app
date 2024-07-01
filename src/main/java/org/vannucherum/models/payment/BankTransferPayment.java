package org.vannucherum.models.payment;

import org.vannucherum.models.account.Customer;

public class BankTransferPayment extends Payment {

    public BankTransferPayment(double amount, Customer customer) {
        super(amount, customer);
    }

    @Override
    protected boolean pay() {
        System.out.println("Payment completed via Bank Transfer");
        return true;
    }
}
