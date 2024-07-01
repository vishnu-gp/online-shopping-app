package org.vannucherum.models.notifications;

import org.vannucherum.models.account.Customer;
import org.vannucherum.utils.AppLogger;

public class EmailNotification extends Notification {
    private String subject;
    private String content;

    public EmailNotification(Customer customer) {
        super(customer);
    }

    @Override
    public Notification sendNotification() {
        AppLogger.logInfo("Sending notification via Email");

        return super.sendNotification();
    }
}
