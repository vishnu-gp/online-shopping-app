package org.vannucherum.models.notifications;

import org.vannucherum.enums.NotificationStatus;
import org.vannucherum.interfaces.Notifiable;
import org.vannucherum.models.account.Customer;
import org.vannucherum.utils.AppLogger;

import java.time.Instant;

public abstract class Notification implements Notifiable {
    private Customer customer;
    private Instant dispatchedAt;
    private NotificationStatus status;

    protected Notification(Customer customer) {
        this.customer = customer;
        this.status = NotificationStatus.CREATED;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Instant getDispatchedAt() {
        return dispatchedAt;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setDispatchedAt(Instant dispatchedAt) {
        this.dispatchedAt = dispatchedAt;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public Notification sendNotification() {
        this.setStatus(NotificationStatus.DISPATCHED);
        this.setDispatchedAt(Instant.now());
        AppLogger.logInfo("Notification dispatched successfully");

        return this;
    }
}
