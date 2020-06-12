package org.fiek.store.cart;

import eu.lestard.fluxfx.Action;

public class AddCartsAction implements Action {

    private final String carts;


    public AddCartsAction(String carts){
        this.carts = carts;
    }

    public String getCarts() {
        return carts;
    }
}