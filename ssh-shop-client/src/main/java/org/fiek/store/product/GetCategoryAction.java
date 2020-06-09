package org.fiek.store.product;

import eu.lestard.fluxfx.Action;

public class GetCategoryAction implements Action{
    private final String [] categories;

    public GetCategoryAction(String [] categories){
        this.categories = categories;
    }

    public String [] getCategories() {
        return categories;
    }

}