package org.fiek.models;

public class Wishlist {
    final String tableName = "wish_lists";

    public int ID;
    public int user_id;
    public int product_id;

    public User user;
    public Product product;

    public Wishlist(int ID, int user_id, int product_id) {
        this.ID = ID;
        this.user_id = user_id;
        this.product_id = product_id;
    }

    public Wishlist() {
        this(-1, -1, -1);
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

    public int getProductId() {
        return product_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
