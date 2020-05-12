package org.fiek.models;

public class Cart {

    final String tableName = "carts";
    public int ID;
    public int user_id;
    public int variant_id;
    public int quantity;

    public User user;

    public Cart(int ID, int user_id, int variant_id, int quantity, User user) {
        this.ID = ID;
        this.user_id = user_id;
        this.variant_id = variant_id;
        this.quantity = quantity;
        this.user = user;
    }

    public Cart(){
        this(-1,-1,-1,-1,null);
    }

    public String getTableName() {
        return tableName;
    }

    public int getID() {
        return ID;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVariant_id() {
        return variant_id;
    }

    public void setVariant_id(int variant_id) {
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
