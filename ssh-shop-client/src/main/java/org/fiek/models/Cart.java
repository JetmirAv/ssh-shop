package org.fiek.models;

public class Cart {
    final String tableName = "carts";

    public int id;
    public int user_id;
    public int variant_id;
    public int quantity;

    public User user;

    public Cart(int id, int user_id, int variant_id, int quantity) {
        this.id = id;
        this.user_id = user_id;
        this.variant_id = variant_id;
        this.quantity = quantity;
    }

    public Cart() {
        this(-1, -1, -1, -1);
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

    public int getVariantId() {
        return variant_id;
    }

    public void setVariantId(int variant_id) {
        this.variant_id = variant_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
