package org.fiek.services.Cart;

import com.google.gson.JsonObject;

import eu.lestard.fluxfx.View;

import javafx.concurrent.Service;

import javafx.concurrent.Task;

import org.fiek.store.auth.GetAddressAction;

import org.fiek.store.auth.GetCityAction;

import org.fiek.store.auth.GetCountryByCityAction;

import org.fiek.store.cart.GetCartAction;

import org.fiek.utils.Ajax;

import java.util.Arrays;




public class GetCartService extends Service<Void> implements View {

    private int id;
    private int userId;


    public GetCartService(int id, int userId) {
        System.out.println("ne konstruktor");
        this.id = id;
        this.userId = userId;
    }


    private void getAddressById() throws Exception {
        Ajax request = new Ajax();
        JsonObject response = request.getAsJson("/users/" + userId + "/cart/" + id);
        String jsonCart = response.get("cart").toString();
        System.out.println("jsonCart:" + jsonCart);
        publishAction(new GetCartAction(jsonCart));
    }



    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                getAddressById();
                return null;
            }
        };
    }
}