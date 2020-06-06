package org.fiek.store.chat;

import eu.lestard.fluxfx.Action;

public class NewMessageAction implements Action {

    final String message;

    public NewMessageAction(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
