package org.fiek.services.Cart;

import com.google.gson.GsonBuilder;

import com.google.gson.JsonObject;

import eu.lestard.fluxfx.View;

import javafx.concurrent.Service;

import javafx.concurrent.Task;

import org.fiek.models.Address;

import org.fiek.models.Cart;

import org.fiek.models.User;

import org.fiek.store.auth.AddAddressAction;

import org.fiek.store.auth.EditAddressAction;

import org.fiek.store.cart.DeleteCartAction;

import org.fiek.utils.Ajax;

import org.fiek.utils.JsonHelper;

import java.util.ArrayList;

import java.util.Arrays;




public class DeleteCartService extends Service<Void> implements View {
    private Cart cart;
    int userId;

    public DeleteCartService(int userId, Cart cart) {
        this.userId = userId;
        this.cart = cart;
    }



    private void deleteCart() throws Exception {
        Ajax request = new Ajax();
        String  response = request.delete("users/" + userId + "/cart/" + cart.getId());
        publishAction(new DeleteCartAction(cart.getId()));

    }



    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                deleteCart();
                return null;
            }
        };
    }
}
