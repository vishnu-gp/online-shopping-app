package org.vannucherum;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

enum AccountStatus {
    ACTIVE, BANNED, ARCHIEVED
}
class Address {
    private String line1;
    private String line2;
    private String phone;
}
class Account {
    private String name;
    private String email;
    private String password;
    private String phone;
    private AccountStatus status;

    public Account login() {
        // Impl
    }

    public Account register() {
        // Impl
    }

    public void logout() {
        // Impl
    }
}
class Customer extends Account {
    private List<Address> addresses;
}

class Seller extends Account {
    private Address address;
}

class Category {
    private String name;
    private String description;
}

class Review {
    private Customer customer;
    private String title;
    private String description;
    private double rating;

    public Review(Customer customer, String title, String description, double rating,) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.customer = customer;
    }
}

class Product {
    private String id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private Category category;
    private List<Review> reviews;
    private double rating;

    public Product updateStock(int quantity) {
        this.stock += quantity;
        return this;
    }

    public Review addReview (Customer customer, String title, String description, Double rating) {
        Review review = new Review(customer, title, description, rating);
        this.reviews.add(review);
        return review;
    }

}

class Catalog {
    private Map<String, Product> products;
    private List<Category> categories;
    private Map<String, List<Product>> categoryProducts;
    private Map<String, List<String>> searchKeywordProductIdMap;

    public List<Product> searchProducts(String keyword) {
        List<String> productIds = searchKeywordProductIdMap.get(keyword);
        return  productIds.stream()
                .map(products::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}

class Admin extends Account {
    public Account blockAccount(Account account) {
        // Impl
    }

    public Catalog addProduct(Catalog catalog, Product product) {
        // Impl
    }
    public Catalog addCategory(Catalog catalog, Category category) {
        // Impl
    }
}

enum PaymentStatus {
    PENDING, FAILED, COMPLETED, REFUNDED
}
class Payment {
    private double amount;
    private PaymentStatus status;

    public Order processPayment() {
        // Impl
    }
}

class CreditCardPayment extends Payment {
    // Impl
}

class BankTransferPayment extends Payment {
    // Impl
}

class CartProduct {
    private Product product;
    private int quantity;

    public CartProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void updateQuantity(int quantity) {
        this.quantity += quantity;
    }
}

class Cart {
    private Map<String, CartProduct> products;
    private double cartTotal;
    private Customer customer;

    public boolean addProduct(Product product, int quantity) {
        // Impl
    }

    public boolean removeProduct(Product product, int quantity) {
        // Impl
    }

    public Payment checkout() {
        // Impl
    }

    public Cart clearCart() {
        this.products.clear();
        this.cartTotal = 0;
        return this;
    }
}

enum OrderStatus {
    PENDING, ORDERED, SHIPPED, ONHOLD
}

class Order {
    private Customer customer;
    private OrderStatus status;
    private Instant orderedAt;
    private Payment payment;
    private Shipment shipment;

    public Order ShipOrder() {
        // Impl
    }

    public Notification sendNotification() {
        // Impl
    }


}

enum ShipmentStatus {
      PENDING, SHIPPED, DELIVERED, RETURNED, CANCELLED
}

class Shipment {
    private Instant shippedAt;
    private Instant EstimatedDeliveryAt;
    private ShipmentStatus status;
}


class Notification {
    private String title;
    private String description;
    private Instant dispatchedAt;
    private Customer customer;

    public sendNotification() {
        // Impl
    }
}

class EmailNotification extends Notification {
    private String email;

    @Override
    public sendNotification() {
        // Impl
    }
}

class PhoneNotification extends Notification {
    private String phone;

    @Override
    public sendNotification() {
        // Impl
    }
}
