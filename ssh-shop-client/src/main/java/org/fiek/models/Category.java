package org.fiek.models;

public class Category {
    final String tableName = "categories";

    public String name;
    public String description;

    public ArrayList<Product> products;

    public Category(
            String name,
            String description,
            ArrayList<Product> products) {
        this.name = name;
        this.description = description;
        this.products = products;
    }

    public Category() {
        this("", "", new ArrayList<Product>());

    }

    public String getTableName() {
        return tableName;
    }
}
