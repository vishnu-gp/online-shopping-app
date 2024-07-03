package org.vannucherum;

import org.vannucherum.enums.AccountStatus;
import org.vannucherum.enums.OrderStatus;
import org.vannucherum.enums.PaymentMethod;
import org.vannucherum.models.Order;
import org.vannucherum.models.account.*;
import org.vannucherum.models.cart.Cart;
import org.vannucherum.models.catalog.Catalog;
import org.vannucherum.models.catalog.Category;
import org.vannucherum.models.catalog.Product;
import org.vannucherum.utils.AppLogger;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AppLogger.logInfo("Welcome to the online shopping app!");

        // Create Admin, Seller and Customer
        List<Address> aliceAddresses = List.of(
                new Address("Alice Mississippi", "711-2880 Nulla St.", "Mankato Mississippi 96522", "(257) 563-7401"),
                new Address("Alice NH", "606-3727 Ullamcorper. Street", "Roseville NH 11523", "(786) 713-8616")
        );

        Customer customer = new Customer("Alice","alice@vannucherum.com", "alicepassword", "(257) 563-7401", AccountStatus.ACTIVE, aliceAddresses);

        Address bobAddress = new Address("Bob", "P.O. Box 887 2508 Dolor. Av.", "Muskegon KY 12482", "(314) 244-6306");
        Seller seller = new Seller("Bob", "bob@vannucherum.com" ,"bobpassword", "(314) 274-6306", AccountStatus.ACTIVE, bobAddress);
        Seller seller2 = new Seller("Taylor", "taylor@vannucherum.com" ,"taylorpassword", "(314) 248-6306", AccountStatus.ACTIVE, bobAddress);

        Admin admin = new Admin("Vishnu", "vishnu@vannucherum.com", "vishnupassword", "(684) 579-1879", AccountStatus.ACTIVE);

        // Authentication should fail with a wrong password.
        admin.login("wrongPassword");
        admin.login("vishnupassword");

        // Create catalog and add products and categories to it
        Catalog catalog = new Catalog();
        Category categoryElectronics = new Category("Electronics", "All electronic products.");
        Category categoryBooks = new Category("Books", "All printed and eBooks.");
        Category categoryClothes = new Category("Clothes", "All clothes for men and women.");
        admin.addCategory(catalog, categoryElectronics);
        admin.addCategory(catalog, categoryBooks);
        admin.addCategory(catalog, categoryClothes);

        Product phone = new Product("iPhone 15 Pro", "Apple iPhone 15 Pro", 150000.00, 100, categoryElectronics, seller);
        Product laptop = new Product("MacBook Pro", "Apple MacBook Pro", 250000.00, 50, categoryElectronics, seller);
        Product book = new Product("Atomic Habits", "Book Atomic Habits", 750.00, 500, categoryBooks, seller);
        Product shirt = new Product("US Polo T-shirt", "US Polo men T-shirt", 500.00, 20, categoryClothes, seller);
        // Seller 2 should not be able to add products for Seller 1
        seller2.addProduct(catalog, phone);
        seller.addProduct(catalog, phone);
        seller.addProduct(catalog, laptop);
        seller.addProduct(catalog, book);
        seller.addProduct(catalog, shirt);

        // Search products
        List<Product> appleProducts = catalog.searchProducts("Apple");
        List<Product> books = catalog.getCategoryProducts().get(categoryBooks.getId());
        Product dress = catalog.getProducts().get(shirt.getId());

        // Add products to the cart
        Cart customerShoppingCart = Cart.getInstance(customer);
        customerShoppingCart.addProduct(dress, 10);
        appleProducts.forEach(product -> {
            customerShoppingCart.addProduct(product, 1);
        });
        books.forEach(categoryBook -> {
            customerShoppingCart.addProduct(categoryBook, 1);
        });

        // Checkout and pay
        Order order = customerShoppingCart.checkout(customer.getAddressList().getFirst(), PaymentMethod.CREDIT_CARD);

        if (order.getStatus().equals(OrderStatus.ORDERED)) {
            // Ship the product
            order.shipOrder();

            // Send notifications
            order.sendNotification();
        }

        AppLogger.logInfo("Thanks for shopping with us!");
    }
}