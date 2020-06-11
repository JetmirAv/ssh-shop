package org.fiek.models;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

public class Category {
    final String tableName = "categories";

    @Expose(serialize = false, deserialize = true)
    public int id;
    
    @Expose
    public String name;
    
    @Expose
    public String description;

    public ArrayList<Product> products;

    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category() {
        this(-1, "", "");
    }

    public String getTableName() {
        return tableName;
    }

    public int getId() {
        return id;
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
