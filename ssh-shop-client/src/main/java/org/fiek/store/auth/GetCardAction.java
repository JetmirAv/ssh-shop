package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;

public class GetCardAction implements Action {

    private final String card;


    public GetCardAction(String card) {
        this.card = card;
    }


    public String getCard() {
        return card;
    }
}
