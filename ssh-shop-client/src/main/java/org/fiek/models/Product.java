package org.fiek.models;

public class Product {

    final String tableName = "products";

    public enum Category {
        Clothing,
        Shoes,
        ConsumerElectronics,
        Books,
        Sports

    }

    public int id;
    public int user_id;
    public String name;
    public String description;
    public int category_id;
    public float discount_pt;

    public User user;
    public Category category;

    public Product(int id, int user_id, String name, String description, int category_id, float discount_pt) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.description = description;
        this.category_id = category_id;
        this.discount_pt = discount_pt;
    }

    public Product() {
        this(-1, -1, "", "", -1, -1);
    }

    public String getTableName() {
        return tableName;
    }

    public int getID() {
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
