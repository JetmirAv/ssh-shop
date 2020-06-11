/**
 * Sample Skeleton for 'cart.fxml' Controller Class
 */


package org.fiek.controllers.Cart;

import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXComboBox;

import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.lang.reflect.Array;

import java.net.URL;

import eu.lestard.fluxfx.View;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;

import javafx.scene.image.Image;

import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;

import org.fiek.models.Address;

import org.fiek.models.City;

import org.fiek.models.Country;

import org.fiek.models.User;

import org.fiek.services.Cart.CartService;
import org.fiek.services.auth.*;

import org.fiek.services.chat.FindAndCountChannelsService;
import org.fiek.store.BaseStore;

import org.fiek.store.cart.CartStore;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.ResourceBundle;

import javafx.fxml.FXML;

import org.fiek.store.auth.AuthStore;

import org.fiek.store.auth.GetCitiesByCountryAction;

import org.fiek.store.chat.ChatStore;
import org.fiek.utils.Loading;

import org.fiek.utils.Tuple;

import org.fiek.App;



public class CartController implements View {


    BaseStore baseStore = App.context.getInstance(BaseStore.class);

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;


    @FXML // fx:id="root"
    private AnchorPane root; // Value injected by FXMLLoader


    @FXML // fx:id="buybutton"
    private JFXButton buybutton; // Value injected by FXMLLoader


    @FXML // fx:id="totalLb"
    private Label totalLb; // Value injected by FXMLLoader

    @FXML
    private VBox vboxId;


    public static Label totalLb1;
    FXMLLoader fxmlLoader;
    AuthStore authStore = baseStore.getAuthStore();
    CartStore cartStore = baseStore.getCartStore();
    User user = authStore.getUser();
    Loading loading = new Loading();

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert totalLb != null : "fx:id=\"totalLb\" was not injected: check your FXML file 'cart.fxml'.";
        assert buybutton != null : "fx:id=\"buybutton\" was not injected: check your FXML file 'cart.fxml'.";
        totalLb1 = totalLb;
        cartStore = baseStore.getCartStore();
        baseStore.getCartStoreEventStream().subscribe(this::displayChat);


    }


    private void displayChat(CartStore cartStore) {
        try {
            vboxId.getChildren().add(App.loadFXML("views/cart/list-cart"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}