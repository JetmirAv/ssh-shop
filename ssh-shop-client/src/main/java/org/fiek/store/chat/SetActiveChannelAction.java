package org.fiek.store.chat;

import eu.lestard.fluxfx.Action;
import org.fiek.models.Channel;

public class SetActiveChannelAction implements Action {

    final Channel channel;

    public SetActiveChannelAction(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }
}
