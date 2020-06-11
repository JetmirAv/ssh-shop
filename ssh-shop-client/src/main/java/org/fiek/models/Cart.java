package org.fiek.models;

import com.google.gson.annotations.Expose;

public class Cart {
    @Expose(serialize = false, deserialize = true)
    final String tableName = "carts";

    @Expose(serialize = false, deserialize = true)
    public int ID;

    @Expose
    public int user_id;

    @Expose
    public String variant_id;

    @Expose
    public String product_id;

    @Expose
    public int quantity;

    public User user;

    public Cart(int ID, int user_id, String variant_id,String product_id, int quantity) {
        this.ID = ID;
        this.user_id = user_id;
        this.variant_id = variant_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public Cart() {
        this(-1, -1, "","", -1);
    }

    public String getTableName() {
        return tableName;
    }

    public int getID() {
        return ID;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getVariantId() {
        return variant_id;
    }

    public void setVariantId(String variant_id) {
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
}
