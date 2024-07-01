package org.vannucherum.models.account;

import org.vannucherum.enums.AccountStatus;
import org.vannucherum.interfaces.CategoryManger;
import org.vannucherum.interfaces.ProductManager;
import org.vannucherum.models.catalog.Catalog;
import org.vannucherum.models.catalog.Category;

public class Admin extends ManagerAccount implements ProductManager, CategoryManger {

    public Admin(String name, String email, String password, String phone, AccountStatus status) {
        super(name, email, password, phone, status);
    }

    @Override
    public Catalog addCategory(Catalog catalog, Category category) {
        catalog.getCategories().put(category.getId(), category);
        System.out.println(String.format("Category: %s added to catalog.", category.getName()));

        return catalog;
    }

    @Override
    public Catalog removeCategory(Catalog catalog, Category category) {
        catalog.getCategories().remove(category);
        // TODO: Remove products that belongs to the category or change it's category.
        // TODO: Remove products from the search map in catalog if product is removed.
        System.out.println(String.format("Category: %s removed from the catalog", category.getName()));

        return catalog;
    }

    @Override
    public Catalog updateCategory(Catalog catalog, Category category) {
        catalog.getCategories().put(category.getId(), category);
        // TODO: Update the change in Category on all products

        return catalog;
    }
}
