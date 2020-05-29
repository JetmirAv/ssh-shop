package org.fiek.models;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    final String tableName = "channels";

    public int id;
    public int product_id;
    public int user_id;
    public String name;

    public User user;
    public Product product;
    public ArrayList<Message> messages = new ArrayList<>();


    public Channel(int id,
                   int product_id,
                   int user_id,
                   String name
    ) {
        this.id = id;
        this.product_id = product_id;
        this.user_id = user_id;
        this.name = name;
    }

    public Channel() {
        this(-1, -1, -1, "");
    }


    public String getTableName() {
        return tableName;
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return product_id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public void setProductId(int product_id) {
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

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void addMessages(List<Message> messages){
        this.messages.addAll(messages);
    }
}

