package org.fiek.models;

public class Category {
    final String tableName = "categories";

    public int ID;
    public String name;
    public String description;

    public ArrayList<Product> products;

    public Category(int ID, String name, String description) {
        this.ID = ID;
        this.name = name;
        this.description = description;
    }

    public Category() {
        this(-1, "", "");
    }

    public String getTableName() {
        return tableName;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
