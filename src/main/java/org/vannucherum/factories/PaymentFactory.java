package org.vannucherum.factories;

import org.vannucherum.enums.PaymentMethod;
import org.vannucherum.models.account.Customer;
import org.vannucherum.models.payment.*;

public class PaymentFactory extends BaseFactory {
    protected PaymentFactory() {
        super();
    }

    public static Payment createPayment(PaymentMethod paymentMethod, double cartTotal, Customer customer) {
        switch (paymentMethod) {
            case CREDIT_CARD -> {
                return new CreditCardPayment(cartTotal, customer);
            }
            case BANK_TRANSFER -> {
                return new BankTransferPayment(cartTotal, customer);
            }
            case UPI -> {
                return new UPIPayment(cartTotal, customer);
            }
            case CASH_ON_DELIVERY -> {
                return new CashOnDelivery(cartTotal, customer);
            }
            default ->
                throw new IllegalArgumentException("Unknown payment type");
        }
    }
}
