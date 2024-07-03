package org.vannucherum.models.account;

import org.vannucherum.enums.AccountStatus;
import org.vannucherum.interfaces.ProductManager;
import org.vannucherum.models.catalog.Catalog;
import org.vannucherum.models.catalog.Product;
import org.vannucherum.utils.AppLogger;

import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashSet;
import java.util.Arrays;

public abstract class ManagerAccount extends Account implements ProductManager {
    protected ManagerAccount(String name, String email, String password, String phone, AccountStatus status) {
        super(name, email, password, phone, status);
    }

    private void addCatalogSearchKeywords(List<String> keyWordSet, Catalog catalog, String productId) {
        Map<String, Set<String>> searchKeywordProductIdMap = catalog.getSearchKeywordProductIdMap();
        keyWordSet.forEach((keyword) -> {
            if (searchKeywordProductIdMap.containsKey(keyword)) {
                searchKeywordProductIdMap.get(keyword).add(productId);
            }
            else {
                searchKeywordProductIdMap.put(keyword, new HashSet<>(Arrays.asList(productId)));
            }
        });

    }

    private void removeCatalogSearchKeywords(List<String> keyWordSet, Catalog catalog, String productId) {
        Map<String, Set<String>> searchKeywordProductIdMap = catalog.getSearchKeywordProductIdMap();
        keyWordSet.forEach((keyword) -> {
            if(searchKeywordProductIdMap.containsKey(keyword)) {
                searchKeywordProductIdMap.get(keyword).remove(productId);
            }
            if (searchKeywordProductIdMap.get(keyword).isEmpty()) {
                searchKeywordProductIdMap.remove(keyword);
            }
        });
    }

    @Override
    public Catalog addProduct(Catalog catalog, Product product) {
        catalog.getProducts().put(product.getId(), product);
        catalog.getCategoryProducts().get(product.getCategory().getId()).add(product);
        this.addCatalogSearchKeywords(product.getSearchKeywords(), catalog, product.getId());
        AppLogger.logInfo(String.format("Product: %s added to catalog", product.getName()));

        return catalog;
    }

    @Override
    public Catalog removeProduct(Catalog catalog, Product product) {
        catalog.getProducts().remove(product.getId());
        catalog.getCategoryProducts().get(product.getCategory().getId()).remove(product);
        this.removeCatalogSearchKeywords(product.getSearchKeywords(), catalog, product.getId());
        AppLogger.logInfo(String.format("Product %s removed from catalog", product.getId()));

        return catalog;
    }

    @Override
    public Catalog updateStock(Catalog catalog, Product product, int stock) {
        catalog.getProducts().get(product.getId()).setStock(stock);
        AppLogger.logInfo(String.format("Stock updated for product %s", product.getId()));

        return catalog;
    }
}
