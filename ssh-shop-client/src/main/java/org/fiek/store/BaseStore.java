package org.fiek.store;

import eu.lestard.fluxfx.Action;
import eu.lestard.fluxfx.Store;
import org.fiek.store.auth.AddTokenAction;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.auth.EditUserAction;
import org.fiek.store.chat.*;
import org.reactfx.EventSource;
import org.reactfx.EventStream;

import javax.inject.Singleton;

@Singleton
public class BaseStore extends Store {

    //AuthStore
    private final EventSource<AuthStore> authStoreEventSource = new EventSource<>();
    private final AuthStore authStore = new AuthStore();

    public EventStream<AuthStore> getAuthStoreEventStream() {
        return authStoreEventSource;
    }

    public AuthStore getAuthStore() {
        return authStore;
    }

    //ChatStore
    private final EventSource<ChatStore> chatStoreEventSource = new EventSource<>();
    private final ChatStore chatStore = new ChatStore();

    public EventStream<ChatStore> getChatStoreEventStream() {
        return chatStoreEventSource;
    }

    public ChatStore getChatStore() {
        return chatStore;
    }

    public BaseStore() {
        authStoreEventSource.push(authStore);
        subscribe(AddTokenAction.class, this::addTokenAction);
        subscribe(EditUserAction.class, this::editUserAction);

        chatStoreEventSource.push(chatStore);
        subscribe(AddChannelsAction.class, this::addChannelsAction);
        subscribe(SetActiveChannelAction.class, this::setActiveChannelAction);
        subscribe(GetMessagesAction.class, this::getMeesageAction);
        subscribe(NewMessageAction.class, this::newMessageAction);
    }

    private void newMessageAction(NewMessageAction action) {
        chatStore.newMessageAction(action.getMessage());
        chatStoreEventSource.push(chatStore);
    }

    private void getMeesageAction(GetMessagesAction action) {
        chatStore.getMessageAction(action.getMessages());
        chatStoreEventSource.push(chatStore);
    }

    private void setActiveChannelAction(SetActiveChannelAction action) {
        chatStore.setSelectedChannel(action.getId());
        chatStoreEventSource.push(chatStore);
    }

    private void addChannelsAction(AddChannelsAction action) {
        chatStore.addChannelsAction(action.getChannels(), action.getCount());
        chatStoreEventSource.push(chatStore);
    }

    private void editUserAction(EditUserAction action) {
        authStore.editUserAction(action.getUser());
        authStoreEventSource.push(authStore);
    }

    private void addTokenAction(AddTokenAction action) {
        authStore.addTokenAction(action.getToken(), action.getUser());
        authStoreEventSource.push(authStore);
    }


}
