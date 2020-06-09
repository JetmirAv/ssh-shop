package org.fiek.store.cart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.lestard.fluxfx.Store;
import org.fiek.models.Cart;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.lestard.fluxfx.Store;
import org.fiek.models.Channel;
import org.fiek.models.Message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

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
import org.fiek.models.Cart;
import org.fiek.models.Country;
import org.fiek.models.User;
import org.fiek.services.Cart.DeleteCartService;
import org.fiek.services.Cart.GetCartService;
import org.fiek.services.auth.*;
import org.fiek.store.BaseStore;
import org.fiek.store.cart.CartStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.auth.GetCitiesByCountryAction;
import org.fiek.utils.Loading;
import org.fiek.utils.Tuple;
import org.fiek.App;


public class CartStore extends Store {

    public User user;
    public ArrayList<Cart> carts;
    Integer count = 0;
    Cart selectedCart;
    public String isDelete;



    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public Cart getSelectedCart() {
        return selectedCart;
    }

    public void setSelectedCart(Cart selectedCart) {
        System.out.println("BRenda selected");
        System.out.println("Selected:" + selectedCart);
        if(this.selectedCart == null || this.selectedCart.getId() != selectedCart.getId()){
            if(selectedCart.getId() > 0){
                DeleteCartService cartService = new DeleteCartService(selectedCart.getUserId(),selectedCart);
                cartService.start();
            }
            System.out.println("Jasht");
        }
        System.out.println("Shum Jasht");

    }

    public Integer getCount() {
        return count;
    }


    public void setCount(Integer count) {
        this.count = count;
    }

    public void addCarts(List<Cart> carts) {
        if (this.carts == null) {
            this.carts = new ArrayList<>();
        }
        this.carts.addAll(carts);
    }


    public void addCartAction(String cartList) {
        final Cart[] carts = new GsonBuilder().create().fromJson(cartList, Cart[].class);
        this.addCarts(Arrays.asList(carts));
    }

    public void isDeleteCart (int deleteCart) {
        ArrayList<Cart> carts1 = carts;

        for (int i = 0; i < carts1.size() ; i++) {
            if (deleteCart==carts1.get(i).getId()){
                carts1.remove(i);
                break;
            }
        }
    }


    public void GetCartAction(String cart) {
        final Cart actionCart = new GsonBuilder().create().fromJson(cart, Cart.class);
        this.selectedCart = actionCart;
    }

}
