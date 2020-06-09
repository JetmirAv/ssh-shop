package org.fiek.services.Cart;


import com.google.gson.JsonObject;

import eu.lestard.fluxfx.View;

import javafx.concurrent.Service;

import javafx.concurrent.Task;

import org.fiek.models.Cart;

import org.fiek.models.Address;

import org.fiek.models.User;

import org.fiek.store.cart.AddCartsAction;

import org.fiek.utils.Ajax;

import java.util.ArrayList;

import java.util.Arrays;

public class CartService extends Service<Void> implements View {

    private int userId;

    public CartService(int userId) {
        this.userId = userId;
    }


    private void getAllCarts() throws Exception {
        Ajax request = new Ajax();
        System.out.println("Get user id : " + userId);
        JsonObject response = request.get("/users/" + userId + "/cart");
        String jsonCart = response.get("cart").toString();
        System.out.println("Cart obj:" + jsonCart) ;
        publishAction(new AddCartsAction(jsonCart));

    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                getAllCarts();
                return null;
            }
        };
    }
}