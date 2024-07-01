package org.vannucherum.interfaces;

import org.vannucherum.models.catalog.Catalog;
import org.vannucherum.models.catalog.Category;

public interface CategoryManger {
    Catalog addCategory(Catalog catalog, Category category);
    Catalog removeCategory(Catalog catalog, Category category);
    Catalog updateCategory(Catalog catalog, Category category);
}
