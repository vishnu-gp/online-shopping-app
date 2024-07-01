package org.vannucherum.interfaces;

import org.vannucherum.models.catalog.Catalog;
import org.vannucherum.models.catalog.Product;

public interface ProductManager {
    Catalog addProduct(Catalog catalog, Product product);
    Catalog removeProduct(Catalog catalog, Product product);
    Catalog updateStock(Catalog catalog, Product product, int stock);
}
