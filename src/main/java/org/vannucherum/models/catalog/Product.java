package org.vannucherum.models.catalog;

import org.vannucherum.models.account.Customer;
import org.vannucherum.models.account.Seller;
import org.vannucherum.utils.AppLogger;
import org.vannucherum.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Product {
    private final String id;
    private String name;
    private String description;
    private double price;
    private double rating;
    private int stock;
    private Category category;
    private Seller seller;
    private Map<String, Review> reviews;

    public Product(String name, String description, double price, int stock, Category category, Seller seller) {
        this.id = StringUtil.generateId();
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = 0;
        this.stock = stock;
        this.category = category;
        this.seller = seller;
        this.reviews = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public int getStock() {
        return stock;
    }

    public Category getCategory() {
        return category;
    }

    public Seller getSeller() {
        return seller;
    }

    public Map<String, Review> getReviews() {
        return reviews;
    }

    public List<String> getSearchKeywords() {
        List<String> nameKeywords = Arrays.asList(this.name.toLowerCase().split(" "));
        List<String> descriptionKeywords = Arrays.asList(this.description.toLowerCase().split(" "));
        List<String> combinedList = new ArrayList<>(nameKeywords);
        combinedList.addAll(descriptionKeywords);

        return combinedList;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Review addReview (String title, String description, Double rating, Customer customer) {
        Review review = new Review(title, description, rating, customer);
        this.reviews.put(review.getId(), review);
        AppLogger.logInfo(String.format("Review added for product: %s", this.getId()));

        return review;
    }
}
