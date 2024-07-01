package org.vannucherum.models.catalog;

import org.vannucherum.utils.StringUtil;

public class Category {
    private final String id;
    private String name;
    private String description;

    public Category(String name, String description) {
        this.id = StringUtil.generateId();
        this.name = name;
        this.description = description;
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
}
