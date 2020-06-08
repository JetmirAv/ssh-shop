package org.fiek.models;

import com.google.gson.annotations.Expose;

public class Cart {
    final String tableName = "carts";

    @Expose(serialize = false, deserialize = true)
    public int id;
    @Expose
    public int user_id;
    @Expose
    public int variant_id;
    @Expose
    public int quantity;
    @Expose
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

    public int getId() {
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

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", variant_id=" + variant_id +
                ", quantity=" + quantity +
                ", user=" + user +
                '}';
    }
}