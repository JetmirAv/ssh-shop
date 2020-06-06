package org.fiek.models;

public class Message {

    String _id;
    Integer author_id;
    Integer channel_id;
    String content;

    User author;
    Channel channel;

    public Message(String _id, Integer author_id, Integer channel_id, String content) {
        this._id = _id;
        this.author_id = author_id;
        this.channel_id = channel_id;
        this.content = content;
    }

    public Message() {
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public Integer getAuthorId() {
        return author_id;
    }

    public void setAuthorId(Integer author_id) {
        this.author_id = author_id;
    }

    public Integer getChannelId() {
        return channel_id;
    }

    public void setChannelId(Integer channel_id) {
        this.channel_id = channel_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
