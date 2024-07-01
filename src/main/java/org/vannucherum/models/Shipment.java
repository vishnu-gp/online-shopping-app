package org.vannucherum.models;

import org.vannucherum.models.account.Address;

import java.time.Instant;

public class Shipment {
    private Address address;
    private Instant createdAt;
    private Instant shippedAt;
    private Instant deliveryDate;

    public Shipment(Address address) {
        this.address = address;
        this.createdAt = Instant.now();
    }

    public Address getAddress() {
        return address;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getShippedAt() {
        return shippedAt;
    }

    public Instant getDeliveryDate() {
        return deliveryDate;
    }

    public void setShippedAt(Instant shippedAt) {
        this.shippedAt = shippedAt;
    }

    public void setDeliveryDate(Instant deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
