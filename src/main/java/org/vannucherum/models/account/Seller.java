package org.vannucherum.models.account;

import org.vannucherum.enums.AccountStatus;
import org.vannucherum.models.catalog.Catalog;
import org.vannucherum.models.catalog.Product;
import org.vannucherum.utils.AppLogger;

public class Seller extends ManagerAccount {
    private Address address;

    public Seller(String name, String email, String password, String phone, AccountStatus status, Address address) {
        super(name, email, password, phone, status);
        this.address = address;
    }

    @Override
    public Catalog addProduct(Catalog catalog, Product product) {
        if(!this.authorizeProduct(product)) {
            AppLogger.logError(String.format("Unable to add product: %s", product.getId()));

            return null;
        }

        return super.addProduct(catalog, product);
    }

    @Override
    public Catalog removeProduct(Catalog catalog, Product product) {
        if(!this.authorizeProduct(product)) {
            AppLogger.logError(String.format("Unable to remove product: %s", product.getId()));
            return null;
        }

        return super.removeProduct(catalog, product);
    }

    @Override
    public Catalog updateStock(Catalog catalog, Product product, int stock) {
        if(!this.authorizeProduct(product)) {
            AppLogger.logError(String.format("Unable to update stock of product: %s", product.getId()));
            return null;
        }

        return super.updateStock(catalog, product, stock);
    }

    /**
     * Seller can make changes to their products only in the catalog.
     * @param product
     * @return
     */
    private boolean authorizeProduct(Product product) {
        if (product.getSeller().equals(this)) {
            return true;
        }
        AppLogger.logError(String.format("Product: %s does not belong to seller: %s", product.getId(), this.getName()));

        return false;
    }
}
