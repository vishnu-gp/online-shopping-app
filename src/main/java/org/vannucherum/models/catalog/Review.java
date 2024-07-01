package org.vannucherum.models.catalog;

import org.vannucherum.models.account.Customer;
import org.vannucherum.utils.StringUtil;

public class Review {
    private final String id;
    private String title;
    private String description;
    private double rating;
    private Customer customer;

    public Review(String title, String description, double rating, Customer customer) {
        this.id = StringUtil.generateId();
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getRating() {
        return rating;
    }

    public Customer getCustomer() {
        return customer;
    }
}
