package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;

public class AddNewCardAction implements Action {

    private final String cards;

    public AddNewCardAction(String cards) {
        this.cards = cards;
    }

    public String getCards() {
        return cards;
    }
}

