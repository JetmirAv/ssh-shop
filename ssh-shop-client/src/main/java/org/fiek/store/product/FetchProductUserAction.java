package org.fiek.store.product;

import eu.lestard.fluxfx.Action;



public class FetchProductUserAction implements Action {
    final String products;



    public FetchProductUserAction(String products) {
        this.products = products;
    }




    public String getProducts() {
        return products;
    }


}
