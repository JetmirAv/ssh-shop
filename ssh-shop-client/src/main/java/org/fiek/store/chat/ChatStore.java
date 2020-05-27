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
    ArrayList<Channel> channels = new ArrayList<>();
    Integer count = 0;

    public Integer getSelectedChannel() {
        return selectedChannel;
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void addChannel(Channel channel) {
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
