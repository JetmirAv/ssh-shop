/**
 * Sample Skeleton for 'list-cartController.fxml' Controller Class
 */

package org.fiek.controllers.Cart;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

import eu.lestard.fluxfx.View;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

import org.fiek.App;

import org.fiek.services.Cart.CartService;

import org.fiek.models.Cart;

import org.fiek.models.User;

import org.fiek.store.BaseStore;

import org.fiek.store.auth.AuthStore;

import java.util.ArrayList;

import org.fiek.store.cart.CartStore;

import org.fiek.utils.Loading;





public class listCartController implements View {
    BaseStore baseStore = App.context.getInstance(BaseStore.class);

    FXMLLoader fxmlLoader;
    AuthStore authStore = baseStore.getAuthStore();

    CartStore cartStore = baseStore.getCartStore();
    User user = authStore.getUser();


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private VBox cartList;

    Loading loading = new Loading();

    @FXML


// This method is called by the FXMLLoader when initialization is complete


    void initialize() {
        assert cartList != null : "fx:id=\"cartList\" was not injected: check your FXML file 'list-cart.fxml'.";

        renderCarts(baseStore.getCartStore());
        baseStore.getCartStoreEventStream().subscribe(this::renderCarts);
    }


    private void renderCarts(CartStore cartStore) {
        fetchCarts();
        ArrayList<Cart> carts = cartStore.getCarts();
        if (carts != null && !carts.isEmpty()) {
            cartList.getChildren().clear();
            try {
                for (Cart cart : carts) {
                    fxmlLoader = new FXMLLoader(App.class.getResource("views/cart/list-item-cart.fxml"));
                    fxmlLoader.setController(new listItemCartController(cart));
                    HBox hbox = null;
                    try {
                        hbox = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    cartList.getChildren().add(hbox);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }


    private void fetchCarts() {
        System.out.println("brenda fetch!");
        if (cartStore.getCarts() == null) {
            CartService service = new CartService(user.getId());
            service.start();

            service.setOnRunning(e -> {
                cartList.getChildren().add(loading);
            });

            service.setOnFailed(e -> {
                cartList.getChildren().remove(loading);
            });

            service.setOnCancelled(e -> {
                cartList.getChildren().remove(loading);
            });

            service.setOnSucceeded(e -> {
                cartList.getChildren().remove(loading);
            });
        }
    }
}


