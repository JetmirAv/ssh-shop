/**
 * Sample Skeleton for 'list-cartController.fxml' Controller Class
 */

package org.fiek.controllers.Cart;

import java.net.URL;

import java.util.ResourceBundle;

import eu.lestard.fluxfx.View;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

import javafx.scene.text.Text;

import java.io.LineNumberReader;

import java.io.FileReader;

import java.io.IOException;

import org.fiek.App;

import org.fiek.services.Cart.CartService;

import org.fiek.store.*;

import org.fiek.models.*;

import org.fiek.models.Cart;

import org.fiek.store.auth.*;

import org.fiek.App;

import org.fiek.controllers.chat.ListItemController;

import org.fiek.models.Address;

import org.fiek.models.Channel;

import org.fiek.models.User;

import org.fiek.services.auth.AddressService;

import org.fiek.store.BaseStore;

import org.fiek.store.auth.AuthStore;

import org.fiek.store.chat.ChatStore;

import java.util.ArrayList;

import org.fiek.store.cart.CartStore;

import org.fiek.utils.Loading;





public class listcartController implements View {
    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    FXMLLoader fxmlLoader;
    User user;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private VBox cartList;

    CartStore cartStore;

    @FXML
// This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cartList != null : "fx:id=\"cartList\" was not injected: check your FXML file 'list-cart.fxml'.";

        renderCarts(baseStore.getCartStore());
        baseStore.getCartStoreEventStream().subscribe(this::renderCarts);
    }

    private void renderCarts(CartStore cartStore) {
        ArrayList<Cart> carts = cartStore.getCarts();
        if (carts != null) {
            cartList.getChildren().clear();
            try {
                for (Cart cart : carts) {
                    fxmlLoader = new FXMLLoader(App.class.getResource("views/cart/list-item-cart.fxml"));
                    fxmlLoader.setController(new listitemcartController(cart));
                    HBox hbox = fxmlLoader.load();
                    cartList.getChildren().add(hbox);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}



