package org.vannucherum.models.payment;

import org.vannucherum.enums.OrderStatus;
import org.vannucherum.enums.PaymentStatus;
import org.vannucherum.interfaces.PaymentStrategy;
import org.vannucherum.models.Shipment;
import org.vannucherum.models.account.Address;
import org.vannucherum.models.account.Customer;
import org.vannucherum.models.cart.Cart;
import org.vannucherum.models.Order;
import org.vannucherum.utils.AppLogger;

import java.time.Instant;

public abstract class Payment implements PaymentStrategy {
    private double amount;
    private PaymentStatus status;
    private Customer customer;

    protected Payment(double amount, Customer customer) {
        this.amount = amount;
        this.status = PaymentStatus.CREATED;
        this.customer = customer;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
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
        AppLogger.logInfo(String.format("Order built successfully for customer: %s", cart.getCustomer().getName()));

        return orderBuilder;
    }

    private void revertStockAvailability(Cart cart) {
        cart.getProducts().forEach((productId, cartProduct) ->  {
            cartProduct.getProduct().setStock(cartProduct.getProduct().getStock() + cartProduct.getQuantity());
        });
    }

    protected abstract boolean pay();

    @Override
    public final Order processPayment(Cart cart, Address deliveryAddress) {
        AppLogger.logInfo("Processing payment");
        Order.Builder orderBuilder = buildOrder(cart);
        if (pay()) {
            this.setStatus(PaymentStatus.COMPLETED);
        }
        orderBuilder.setStatus(OrderStatus.INITIATED);
        if (this.status.equals(PaymentStatus.COMPLETED)) {
            orderBuilder.setStatus(OrderStatus.ORDERED);
            orderBuilder.setShipment(new Shipment(deliveryAddress));
        }
        else {
            revertStockAvailability(cart);
        }
        orderBuilder.setPayment(this);
        orderBuilder.setOrderedAt(Instant.now());

        return orderBuilder.build();
    }
}
