package org.vannucherum.factories;

import org.vannucherum.enums.NotificationType;
import org.vannucherum.models.account.Customer;
import org.vannucherum.models.notifications.Notification;
import org.vannucherum.models.notifications.EmailNotification;
import org.vannucherum.models.notifications.PhoneNotification;

public class NotificationFactory extends BaseFactory{

    protected NotificationFactory() {
        super();
    }

    public static Notification createNotification(NotificationType type, Customer customer) {
        switch (type) {
            case EMAIL:
                return new EmailNotification(customer);
            case PHONE:
                return new PhoneNotification(customer);
            default:
                throw new IllegalArgumentException("Unknown notification type");
        }
    }
}
