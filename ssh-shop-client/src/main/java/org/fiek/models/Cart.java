package org.fiek.models;

import com.google.gson.annotations.Expose;

public class Cart {
    @Expose(serialize = false, deserialize = true)
    final String tableName = "carts";

    @Expose
    public int user_id;

    @Expose
    public String variant_id;

    @Expose
    public String product_id;


    @Expose(serialize = false, deserialize = true)
    public int id;

    @Expose
    public int quantity;
    @Expose
    public User user;

    @Expose
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Expose(serialize = false, deserialize = true)
    public Combinations combination;


    public Combinations getCombination() {
        return combination;
    }

    public Cart(int id, int user_id, String variant_id, int quantity, String product_id) {
        this.id = id;
        this.user_id = user_id;
        this.variant_id = variant_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.product_id = product_id;
    }

    public Cart() {
        this(-1, -1, "", -1,"");
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

    public String  getVariantId() {
        return variant_id;
    }

    public void setVariantId(String  variant_id) {
        this.variant_id = variant_id;
    }

    public String getProductId() {
        return product_id;
    }

    public void setProductId(String product_id) {
        this.product_id = product_id;
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
                ", product_id=" + product_id +
                ", name:" + name +
                ", Combinations=" + combination.getPrice() +
                '}';
    }
}