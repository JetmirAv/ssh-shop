package org.fiek.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Product {

    final String tableName = "products";

    public String id;
    public int user_id;
    public String name;
    public String description;
    public int category_id;
    public float discount_pt;

    public User user;
    public Category category;
    public Variant variant;
    public Combinations combinations;

    public Product(String id, int user_id, String name, String description, int category_id, float discount_pt, Variant variant, Combinations combinations) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.description = description;
        this.category_id = category_id;
        this.discount_pt = discount_pt;
        this.variant = variant;
        this.combinations = combinations;
    }

    public Product() {
        this("", -1, "", "", -1, -1, null, null);
    }

    public String getTableName() {
        return tableName;
    }

    public String getId() {
        return id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
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

    public int getCategoryId() {
        return category_id;
    }

    public void setCategoryId(int category_id) {
        this.category_id = category_id;
    }

    public float getDiscount_pt() {
        return discount_pt;
    }

    public void setDiscount_pt(float discount_pt) {
        this.discount_pt = discount_pt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
