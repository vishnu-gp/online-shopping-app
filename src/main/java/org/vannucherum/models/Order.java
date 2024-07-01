package org.vannucherum.models;

import org.vannucherum.enums.NotificationType;
import org.vannucherum.enums.OrderStatus;
import org.vannucherum.factories.NotificationFactory;
import org.vannucherum.interfaces.PaymentStrategy;
import org.vannucherum.models.account.Customer;
import org.vannucherum.models.cart.CartProduct;
import org.vannucherum.models.notifications.Notification;
import org.vannucherum.utils.AppLogger;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private final Customer customer;
    private OrderStatus status;
    private final Instant orderedAt;
    private final PaymentStrategy payment;
    private final Shipment shipment;
    private final Map<String, CartProduct> products;

    private Order(Customer customer, OrderStatus status, Instant orderedAt, PaymentStrategy payment, Shipment shipment, Map<String, CartProduct> products) {
        this.customer = customer;
        this.status = status;
        this.orderedAt = orderedAt;
        this.payment = payment;
        this.shipment = shipment;
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }


    public OrderStatus getStatus() {
        return status;
    }

    public Instant getOrderedAt() {
        return orderedAt;
    }

    public PaymentStrategy getPayment() {
        return payment;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public static class Builder {
        private Customer customer;
        private OrderStatus status;
        private Instant orderedAt;
        private PaymentStrategy payment;
        private Shipment shipment;
        private Map<String, CartProduct> products = new HashMap<>();

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder setStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder setOrderedAt(Instant orderedAt) {
            this.orderedAt = orderedAt;
            return this;
        }

        public Builder setPayment(PaymentStrategy payment) {
            this.payment = payment;
            return this;
        }

        public Builder setShipment(Shipment shipment) {
            this.shipment = shipment;
            return this;
        }

        public Builder addProduct(String productId, CartProduct product) {
            this.products.put(productId, product);
            return this;
        }

        public Order build() {
            return new Order(customer, status, orderedAt, payment, shipment, products);
        }
    }

    public Order shipOrder() {
        this.getShipment().setShippedAt(Instant.now());
        this.getShipment().setDeliveryDate(Instant.now().plus(7, ChronoUnit.DAYS));
        this.setStatus(OrderStatus.SHIPPED);
        AppLogger.logInfo(String.format("Order shipped. Expect Delivery to %s on %s", this.getShipment().getAddress().getFullAddress(), this.getShipment().getDeliveryDate().toString()));

        return this;
    }

    public boolean sendNotification() {
        List<Notification> notifications = new ArrayList<>();
        Customer recipient  = this.getCustomer();
        if (recipient.getEmail() != null) {
            notifications.add(NotificationFactory.createNotification(NotificationType.EMAIL, recipient));
        }
        if (recipient.getPhone() != null) {
            notifications.add(NotificationFactory.createNotification(NotificationType.PHONE, recipient));
        }

        for (Notification notification : notifications) {
            notification.sendNotification();
        }

        return true;
    }
}
