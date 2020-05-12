package org.fiek.models;

public class Wishlist {
    final String tableName = "wish_lists";

    public int ID;
    public int user_id;
    public int product_id;

    public User user;
    public Product product;

    public Wishlist(int ID,
                   int user_id,
                   int product_id,
                   User user,
                   Product product) {
        this.ID = ID;
        this.user_id = user_id;
        this.product_id = product_id;
        this.user = user;
        this.product = product;
    }

    public Wishlist(){
         this( -1, -1, -1, null,null);
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

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
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
