package org.vannucherum.models.payment;

import org.vannucherum.enums.OrderStatus;
import org.vannucherum.enums.PaymentStatus;
import org.vannucherum.interfaces.PaymentStrategy;
import org.vannucherum.models.Shipment;
import org.vannucherum.models.account.Address;
import org.vannucherum.models.account.Customer;
import org.vannucherum.models.cart.Cart;
import org.vannucherum.models.Order;

import java.time.Instant;

public abstract class Payment implements PaymentStrategy {
    private double amount;
    private PaymentStatus status;
    private Customer customer;

    public Payment(double amount, Customer customer) {
        this.amount = amount;
        this.status = PaymentStatus.CREATED;
        this.customer = customer;
    }

    private Order.Builder buildOrder(Cart cart) {
        Order.Builder orderBuilder =  new Order.Builder()
                .setCustomer(this.customer)
                .setStatus(OrderStatus.INITIATED);
        cart.getProducts().forEach((productId, cartProduct) -> {
            orderBuilder.addProduct(productId, cartProduct);
            // The catalog stock should be updated for the products to lock the product for the particular customer while doing the payment.
            cartProduct.getProduct().setStock(cartProduct.getProduct().getStock() - cartProduct.getQuantity());
        });
        System.out.println(String.format("Order built successfully for customer: %s", cart.getCustomer().getName()));

        return orderBuilder;
    }

    protected abstract boolean pay();

    @Override
    public final Order processPayment(Cart cart, Address deliveryAddress) {
        System.out.println(String.format("Processing payment"));
        Order.Builder orderBuilder = buildOrder(cart);
        pay();
        orderBuilder.setStatus(OrderStatus.INITIATED);
        if (this.status.equals(PaymentStatus.COMPLETED)) {
            orderBuilder.setStatus(OrderStatus.ORDERED);
            orderBuilder.setShipment(new Shipment(deliveryAddress));
        }
        orderBuilder.setPayment(this);
        orderBuilder.setOrderedAt(Instant.now());

        return orderBuilder.build();
    }
}
