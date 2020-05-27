package org.fiek.store.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.lestard.fluxfx.Store;
import org.fiek.models.Channel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatStore extends Store {

    Integer selectedChannel;
    ArrayList<Channel> channels = null;
    Integer count = 0;

    public Integer getSelectedChannel() {
        return selectedChannel;
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void addChannel(Channel channel) {
        if(this.channels == null ){
            this.channels = new ArrayList<>();
        }
        channels.add(0, channel);
    }

    public Integer getCount() {
        return count;
    }

    public void setSelectedChannel(Integer selectedChannel) {
        this.selectedChannel = selectedChannel;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void addChannels(List<Channel> channels) {
        if(this.channels == null ){
            this.channels = new ArrayList<>();
        }
        this.channels.addAll(channels);
    }

    public void addChannelsAction(String channels, String count) {
        Gson gson = new Gson();
        Channel[] channelsArray = gson.fromJson(channels, Channel[].class);
        List<Channel> channelList = Arrays.asList(channelsArray);
        addChannels(channelList);
        setCount(Integer.parseInt(count));
    }
}
