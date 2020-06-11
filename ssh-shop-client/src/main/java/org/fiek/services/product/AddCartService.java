package org.fiek.services.product;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.Cart;
import org.fiek.models.Product;
import org.fiek.store.product.AddProductAction;
import org.fiek.store.product.AddToCartAction;
import org.fiek.utils.Ajax;

public class AddCartService extends Service<Void> implements View {
    private Cart cart;
    private int userId;

    public AddCartService (int userId, Cart cart){
        this.cart = cart;
        this.userId = userId;
    }

    public void addCart() throws Exception {

        final String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this.cart, Cart.class);

        Ajax request = new Ajax();
        JsonObject response = request.post("users/"+this.userId + "/cart", json);
        String jsonCart = response.get("cart").toString();
        publishAction(new AddToCartAction(jsonCart));

    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                addCart();
                return null;
            }
        };
    }
}
