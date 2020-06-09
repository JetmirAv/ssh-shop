package org.fiek.store.cart;

import eu.lestard.fluxfx.Action;

import org.fiek.models.User;






public class DeleteCartAction implements Action {

    private final int id;

    public DeleteCartAction(int id) {
        this.id = id;
    }



    public int getId() {
        return id;
    }
}
