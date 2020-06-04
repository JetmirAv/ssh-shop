package org.fiek.store.chat;

import eu.lestard.fluxfx.Action;

public class GetMessagesAction implements Action {

    final String messages;

    public GetMessagesAction(String messages) {
        this.messages = messages;
    }

    public String getMessages() {
        return messages;
    }
}
