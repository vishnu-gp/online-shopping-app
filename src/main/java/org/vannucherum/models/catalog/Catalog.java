package org.vannucherum.models.catalog;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Objects;

public class Catalog {
    private Map<String, Product> products;
    private Map<String, Category> categories;
    private Map<String, List<Product>> categoryProducts;
    private Map<String, Set<String>> searchKeywordProductIdMap;

    public Catalog() {
        this.products = new HashMap<>();
        this.categories = new HashMap<>();
        this.categoryProducts = new HashMap<>();
        this.searchKeywordProductIdMap = new HashMap<>();
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public Map<String, Category> getCategories() {
        return categories;
    }

    public Map<String, List<Product>> getCategoryProducts() {
        return categoryProducts;
    }

    public Map<String, Set<String>> getSearchKeywordProductIdMap() {
        return searchKeywordProductIdMap;
    }

    public List<Product> searchProducts(String keyword) {
        Set<String> productIds = searchKeywordProductIdMap.get(keyword.toLowerCase());
        return  productIds.stream()
                .map(products::get)
                .filter(Objects::nonNull)
                .toList();
    }
}
