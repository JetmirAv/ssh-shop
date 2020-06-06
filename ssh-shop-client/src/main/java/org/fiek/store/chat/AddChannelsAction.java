package org.fiek.store.chat;

import eu.lestard.fluxfx.Action;

public class AddChannelsAction implements Action {

    private final String channels;
    private final String count;


    public AddChannelsAction(String channels, String count){
        this.channels = channels;
        this.count = count;
    }

    public String getCount() {
        return count;
    }

    public String getChannels() {
        return channels;
    }
}
