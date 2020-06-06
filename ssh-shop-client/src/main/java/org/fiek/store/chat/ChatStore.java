package org.fiek.store.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.lestard.fluxfx.Store;
import eu.lestard.fluxfx.View;
import org.fiek.models.Channel;
import org.fiek.models.Message;
import org.fiek.services.chat.GetChannelMessagesService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatStore extends Store implements View {

    Channel selectedChannel;
    ArrayList<Channel> channels = null;
    Integer count = 0;

    public Channel getSelectedChannel() {
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

    public void setSelectedChannel(Channel selectedChannel) {
        Channel old = this.selectedChannel;

        this.selectedChannel = selectedChannel;

        GetChannelMessagesService service = new GetChannelMessagesService(selectedChannel.getId());
        service.start();

    }

    public void replaceChannel(Channel channel) {
        for (int i = 0; i < this.channels.size(); i++) {
            if (channel.getId() == this.channels.get(i).getId()) {
                this.channels.set(i, channel);
                return;
            }
        }
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
        System.out.println("Kenj po");
        Channel[] channelsArray = gson.fromJson(channels, Channel[].class);
        System.out.println("Kenj jo");
        List<Channel> channelList = Arrays.asList(channelsArray);
        addChannels(channelList);
        setCount(Integer.parseInt(count));
    }

    public void getMessageAction(String messages) {
        Gson gson = new Gson();
        Message[] messageArray = gson.fromJson(messages, Message[].class);
        List<Message> messageList = Arrays.asList(messageArray);
        this.getSelectedChannel().addMessages(messageList);
    }

    public Channel getActiveChannel() {
        return selectedChannel;
    }

    public void newMessageAction(String message) {
        Gson gson = new Gson();
        Message messageInstance = gson.fromJson(message, Message.class);

        if (messageInstance.getChannelId() == this.getSelectedChannel().getId()) {
            this.getActiveChannel().addMessage(messageInstance);
        }

        Channel channel = messageInstance.getChannel();
        if(channel != null) System.out.println("Not null");
        addChannel(channel);

//        messageInstance.getChannelId();

    }
}
