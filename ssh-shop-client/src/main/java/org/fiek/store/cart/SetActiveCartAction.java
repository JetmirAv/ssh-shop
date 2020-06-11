package org.fiek.store.cart;


import eu.lestard.fluxfx.Action;

import org.fiek.models.Address;

import org.fiek.models.Cart;
import org.fiek.models.User;


public class SetActiveCartAction implements Action {

    Cart cart;
    User user;

    public SetActiveCartAction(Cart cart, User user) {
        this.cart = cart;
        this.user = user;
    }


    public Cart getCart() {
        return cart;
    }

    public User getUser() { return  user ;}

}
