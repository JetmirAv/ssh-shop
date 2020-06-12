package org.fiek.store.product;

import eu.lestard.fluxfx.Action;

public class AddToCartAction implements Action {
    private String cart;


    public AddToCartAction( String cart){
        this.cart = cart;
    }

    public String getCart() {
        return cart;
    }
}
