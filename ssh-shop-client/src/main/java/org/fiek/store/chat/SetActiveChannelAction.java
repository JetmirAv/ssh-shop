package org.fiek.store.chat;

import eu.lestard.fluxfx.Action;

public class SetActiveChannelAction implements Action {

    final Integer id;

    public SetActiveChannelAction(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
