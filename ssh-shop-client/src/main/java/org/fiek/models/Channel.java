package org.fiek.models;

public class Channel{
    final String tableName = "channels";

    public int ID;
    public int product_id;
    public int user_id;
    public String name;

    public User user;
    public Product product;


    public Channel(int ID,
                   int product_id,
                   int user_id,
                   String name,
                   User user,
                   Product product) {
        this.ID = ID;
        this.product_id = product_id;
        this.user_id = user_id;
        this.name = name;
        this.user = user;
        this.product = product;
    }

    public Channel(){
        this( -1, -1, -1, "", null, null );
    }


    public String getTableName() {
        return tableName;
    }

    public int getID() {
        return ID;
    }

    public int getProduct_id(){
        return product_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setProduct_id(int product_id){
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

