package org.fiek.store.product;

import eu.lestard.fluxfx.Action;



public class FetchWishListAction implements Action {
    final String products;



    public FetchWishListAction(String products) {
        this.products = products;
    }




    public String getProducts() {
        return products;
    }


}
