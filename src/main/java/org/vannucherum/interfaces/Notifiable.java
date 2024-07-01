package org.vannucherum.interfaces;

import org.vannucherum.models.notifications.Notification;

public interface Notifiable {
    Notification sendNotification();
}
