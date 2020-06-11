package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;
import org.fiek.models.User;

public class EditCardAction implements Action {

    private final String  card;

    public EditCardAction(String card) {
        this.card = card;
    }

    public String getCard() {
        return card;
    }
}
