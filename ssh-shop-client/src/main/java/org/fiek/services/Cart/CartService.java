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
    private User user;
    public CartService(User user) {
        this.user = user;
    }

    private void getAllCarts() throws Exception {
        Ajax request = new Ajax();
        JsonObject response = request.get("/users/2/cart/2");
        response = response.get("carts").getAsJsonObject();
        String rows = response.get("rows").toString();
        String count = response.get("count").toString();

        publishAction(new AddCartsAction(rows, count));

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
