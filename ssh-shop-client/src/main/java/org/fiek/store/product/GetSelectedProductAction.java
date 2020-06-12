package org.fiek.store.product;

import eu.lestard.fluxfx.Action;

public class GetSelectedProductAction implements Action{
    private String product;

    public GetSelectedProductAction( String product){
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

}