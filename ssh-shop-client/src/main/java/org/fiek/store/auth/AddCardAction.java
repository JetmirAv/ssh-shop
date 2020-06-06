package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;

public class AddCardAction implements Action {

    private final String []  cards;


    public AddCardAction(String [] cards) {
        this.cards = cards;
    }


    public String[] getCards() {
        return cards;
    }
}
