package org.vannucherum.models.catalog;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Catalog {
    private Map<String, Product> products;
    private Map<String, Category> categories;
    private Map<String, List<Product>> categoryProducts;
    private Map<String, List<String>> searchKeywordProductIdMap;

    public Catalog(Map<String, Product> products, Map<String, Category> categories, Map<String, List<Product>> categoryProducts, Map<String, List<String>> searchKeywordProductIdMap) {
        this.products = products;
        this.categories = categories;
        this.categoryProducts = categoryProducts;
        this.searchKeywordProductIdMap = searchKeywordProductIdMap;
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

    public Map<String, List<String>> getSearchKeywordProductIdMap() {
        return searchKeywordProductIdMap;
    }

    public List<Product> searchProducts(String keyword) {
        List<String> productIds = searchKeywordProductIdMap.get(keyword);
        return  productIds.stream()
                .map(products::get)
                .filter(Objects::nonNull)
                .toList();
    }
}
