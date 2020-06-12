package org.fiek.store.cart;

import eu.lestard.fluxfx.Action;



public class GetCartAction implements Action {

    private final String cart;


    public GetCartAction(String cart) {
        this.cart = cart;
    }



    public String getCart() {
        return cart;
    }



}
