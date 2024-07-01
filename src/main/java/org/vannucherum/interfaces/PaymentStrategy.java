package org.vannucherum.interfaces;

import org.vannucherum.models.Order;
import org.vannucherum.models.account.Address;
import org.vannucherum.models.cart.Cart;

public interface PaymentStrategy {
    Order processPayment(Cart cart, Address deliveryAddress);
}
