package org.vannucherum.models.cart;

import org.vannucherum.enums.PaymentMethod;
import org.vannucherum.models.Order;
import org.vannucherum.models.account.Address;
import org.vannucherum.models.payment.BankTransferPayment;
import org.vannucherum.models.account.Customer;
import org.vannucherum.models.catalog.Product;
import org.vannucherum.models.payment.CashOnDelivery;
import org.vannucherum.models.payment.CreditCardPayment;
import org.vannucherum.models.payment.UPIPayment;
import org.vannucherum.utils.AppLogger;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private static Map<Customer, Cart> instances = new HashMap<>();
    private Map<String, CartProduct> products = new HashMap<>();
    private double cartTotal;
    private Customer customer;

    private Cart(Customer customer) {
        this.customer = customer;
    }

    public Map<String, CartProduct> getProducts() {
        return products;
    }

    public double getCartTotal() {
        return cartTotal;
    }

    public Customer getCustomer() {
        return customer;
    }

    public static Cart getInstance(Customer customer) {
        return instances.computeIfAbsent(customer, Cart::new);
    }

    public boolean addProduct(Product product, int quantity) {
        if (products.containsKey(product.getId())) {
            products.get(product.getId()).updateQuantity(quantity);
        } else {
            products.put(product.getId(), new CartProduct(product, quantity));
        }
        cartTotal += product.getPrice() * quantity;
        AppLogger.logInfo(String.format("Product: %s added to the cart", product.getName()));

        return true;
    }

    public boolean removeProduct(Product product, int quantity) {
        if (products.containsKey(product.getId())) {
            CartProduct cartProduct = products.get(product.getId());
            cartProduct.updateQuantity(-quantity);
            if (cartProduct.getQuantity() <= 0) {
                products.remove(product.getId());
            }
            cartTotal -= product.getPrice() * quantity;
            AppLogger.logInfo(String.format("Product: %s removed from the cart", product.getName()));
            return true;
        }

        return false;
    }

    public Cart clearCart() {
        this.products.clear();
        this.cartTotal = 0;
        AppLogger.logInfo("Cart cleared successfully");

        return this;
    }

    public Order checkout(Address address, PaymentMethod paymentMethod) {
        AppLogger.logInfo("Checking out cart");
        Order order = null;
        switch (paymentMethod) {
            case CREDIT_CARD -> {
                CreditCardPayment creditCardPayment = new CreditCardPayment(this.getCartTotal(), this.getCustomer());
                order = creditCardPayment.processPayment(this, address);
            }
            case BANK_TRANSFER -> {
                BankTransferPayment bankTransferPayment = new BankTransferPayment(this.getCartTotal(), this.getCustomer());
                order = bankTransferPayment.processPayment(this, address);
            }
            case UPI -> {
                UPIPayment upiPayment = new UPIPayment(this.getCartTotal(), this.getCustomer());
                order = upiPayment.processPayment(this, address);
            }
            case CASH_ON_DELIVERY -> {
                CashOnDelivery cashOnDelivery = new CashOnDelivery(this.getCartTotal(), this.getCustomer());
                order = cashOnDelivery.processPayment(this, address);
            }
        }
        clearCart();

        return order;
    }
}
