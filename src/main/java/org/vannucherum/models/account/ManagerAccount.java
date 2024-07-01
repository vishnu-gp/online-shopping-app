package org.vannucherum.models.account;

import org.vannucherum.enums.AccountStatus;
import org.vannucherum.interfaces.ProductManager;
import org.vannucherum.models.catalog.Catalog;
import org.vannucherum.models.catalog.Product;

public abstract class ManagerAccount extends Account implements ProductManager {
    public ManagerAccount(String name, String email, String password, String phone, AccountStatus status) {
        super(name, email, password, phone, status);
    }

    @Override
    public Catalog addProduct(Catalog catalog, Product product) {
        catalog.getProducts().put(product.getId(), product);
        catalog.getCategoryProducts().get(product.getCategory().getId()).add(product);
        System.out.println(String.format("Product %s added to catalog", product.getName()));

        return catalog;
    }

    @Override
    public Catalog removeProduct(Catalog catalog, Product product) {
        catalog.getProducts().remove(product.getId());
        catalog.getCategoryProducts().get(product.getCategory().getId()).remove(product);
        System.out.println(String.format("Product %s removed from catalog", product.getId()));

        return catalog;
    }

    @Override
    public Catalog updateStock(Catalog catalog, Product product, int stock) {
        catalog.getProducts().get(product.getId()).setStock(stock);
        System.out.println(String.format("Stock updated for product %s", product.getId()));

        return catalog;
    }
}
