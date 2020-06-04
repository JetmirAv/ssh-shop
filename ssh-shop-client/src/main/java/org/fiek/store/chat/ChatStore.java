package org.fiek.store.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.lestard.fluxfx.Store;
import org.fiek.models.Channel;
import org.fiek.models.Message;

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
        if (this.channels == null) {
            this.channels = new ArrayList<>();
        }
        for (int i = 0; i < this.channels.size(); i++) {
            if (this.channels.get(i).getId() == channel.getId()) {
                this.channels.remove(i);
                break;
            }
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
        if (this.channels == null) {
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

    public void getMessageAction(String messages) {
        Gson gson = new Gson();
        Message[] messageArray = gson.fromJson(messages, Message[].class);
        List<Message> messageList = Arrays.asList(messageArray);
        this.getActiveChannel().addMessages(messageList);
//        this.getActiveChannel().setOffset(messageList.size());
    }

    public Channel getActiveChannel() {
        for (Channel channel : this.channels) {
            if (channel.getId() == this.selectedChannel) {
                return channel;
            }
        }
        return null;
    }

    public void newMessageAction(String message) {
        Gson gson = new Gson();
        Message messageInstance = gson.fromJson(message, Message.class);
        this.getActiveChannel().addMessage(messageInstance);
        Channel channel = messageInstance.getChannel();
        addChannel(channel);

//        messageInstance.getChannelId();

    }
}
