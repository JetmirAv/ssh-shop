package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;
import org.fiek.models.Address;
import org.fiek.models.Card;

public class SetActiveCardAction implements Action {

    Card card;

    public SetActiveCardAction(Card card) {
        this.card = card;
    }


    public Card getCard() {
        return card;
    }
}
