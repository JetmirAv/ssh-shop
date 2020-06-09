package org.fiek.store.cart;


import eu.lestard.fluxfx.Action;

import org.fiek.models.Address;

import org.fiek.models.Cart;



public class SetActiveCartAction implements Action {

    Cart cart;

    public SetActiveCartAction(Cart cart) {
        System.out.println("Cart active:" + cart);
        this.cart = cart;
    }


    public Cart getCart() {
        return cart;
    }

}
