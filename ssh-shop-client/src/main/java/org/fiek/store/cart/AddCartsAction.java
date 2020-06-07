package org.fiek.store.cart;

import eu.lestard.fluxfx.Action;

public class AddCartsAction implements Action {

    private final String carts;
    private final String count;


    public AddCartsAction(String carts, String count){
        this.carts = carts;
        this.count = count;
    }

    public String getCount() {
        return count;
    }

    public String getCarts() {
        return carts;
    }
}
