package org.vannucherum.models.notifications;

import org.vannucherum.models.account.Customer;

public class PhoneNotification extends Notification {
    private String phone;

    public PhoneNotification(Customer customer) {
        super(customer);
    }

    @Override
    public Notification sendNotification() {
        System.out.println("Sending notification via Phone");
        return super.sendNotification();
    }
}
