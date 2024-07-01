package org.vannucherum.models.notifications;

import org.vannucherum.models.account.Customer;
import org.vannucherum.utils.AppLogger;

public class PhoneNotification extends Notification {
    private String phone;

    public PhoneNotification(Customer customer) {
        super(customer);
    }

    @Override
    public Notification sendNotification() {
        AppLogger.logInfo("Sending notification via Phone");
        
        return super.sendNotification();
    }
}
