package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;

public class AddCardAction implements Action {

    private final String card;

    public AddCardAction(String card) {
        this.card = card;
    }

    public String getCard() {
        return card;
    }
}
