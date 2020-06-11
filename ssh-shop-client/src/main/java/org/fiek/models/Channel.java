package org.fiek.models;

import eu.lestard.fluxfx.Store;
import org.reactfx.EventSource;
import org.reactfx.EventStream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Channel extends Store implements Comparable {
    final String tableName = "channels";

    public int id;
    public String product_id;
    public int user_id;
    public String name;
    public Date created_at;
    public Date updated_at;

    public User user;
    public Product product;

    private final EventSource<ArrayList<Message>> messageListEventSource = new EventSource<>();
    public ArrayList<Message> messages = new ArrayList<>();

    public EventStream<ArrayList<Message>> messageListEventStream() {
        return messageListEventSource;
    }

    public Integer offset = 0;

    public Integer getOffset() {
        return offset;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void setOffset() {
        this.offset = messages.size();
    }

    public Channel(int id,
                   String product_id,
                   int user_id,
                   String name
    ) {
        this.id = id;
        this.product_id = product_id;
        this.user_id = user_id;
        this.name = name;
    }

    public Channel() {
        this(-1, "-1", -1, "");
    }


    public String getTableName() {
        return tableName;
    }

    public int getId() {
        return id;
    }

    public String getProductId() {
        return product_id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public void setProductId(String product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public ArrayList<Message> getLeftMessages() {
        return new ArrayList<>(messages.subList(this.offset, this.messages.size()));
    }


    public void addMessages(List<Message> messages) {
        this.messages.clear();
        this.messages.addAll(messages);
        this.messageListEventSource.push(this.messages);

    }

    public void addMessage(Message messages) {
        this.messages.add(0, messages);
        this.messageListEventSource.push(this.messages);
    }

    @Override
    public int compareTo(Object o) {
        return Comparator.comparing(Channel::getCreatedAt).compare(this, (Channel) o);
    }
}

