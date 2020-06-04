package org.fiek.store.home;

import com.google.gson.GsonBuilder;
import eu.lestard.fluxfx.Store;
import org.fiek.models.Category;
import org.fiek.models.Product;
import org.fiek.models.User;

import java.util.ArrayList;

public class HomeStore extends Store {

    public String token;
    public User user;
    public Product product;
    public Category category;
    public String name;
    public float price;
    public String description;

    public ArrayList<Product> products;


    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }



    public ArrayList<Product> getProducts() {
        return products;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }


    public void addTokenAction(String token, String user) {
        final User actionUser = new GsonBuilder().create().fromJson(user, User.class);
        if (token != null && !token.trim().isEmpty())
            this.token = token;

        if (user != null)
            this.user = actionUser;

    }


    public void editUserAction(String user) {
        final User actionUser = new GsonBuilder().create().fromJson(user, User.class);

        if (user != null)
            this.user = actionUser;

    }
}
