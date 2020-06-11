package org.fiek.store.product;

import eu.lestard.fluxfx.Action;

public class AddProductAction implements Action{
    private String product;

    public AddProductAction( String product){
        this.product = product;
    }

    public String getProduct() {
        return product;
    }
    
}