package org.fiek.store.product;

import eu.lestard.fluxfx.Action;

public class FetchProductsAction implements Action {
    final String products;

    public FetchProductsAction(String products) {
        this.products = products;
    }

    public String getProducts() {
        return products;
    }
}
