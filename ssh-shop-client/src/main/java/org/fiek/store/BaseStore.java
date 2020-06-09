package org.fiek.store;

import eu.lestard.fluxfx.Store;
import org.fiek.store.auth.AddTokenAction;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.auth.EditUserAction;
import org.fiek.store.chat.*;
import org.fiek.store.product.AddProductAction;
import org.fiek.store.product.GetCategoryAction;
import org.fiek.store.product.ProductStore;
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

    //ProductStore
    private final EventSource<ProductStore> productStoreEventSource = new EventSource<>();
    private final ProductStore productStore = new ProductStore();

    public EventStream<ProductStore> getProductStoreEventStream() {
        return productStoreEventSource;
    }

    public ProductStore getProductStore() {
        return productStore;
    }

    public BaseStore() {
        authStoreEventSource.push(authStore);
        subscribe(AddTokenAction.class, this::addTokenAction);
        subscribe(EditUserAction.class, this::editUserAction);

        chatStoreEventSource.push(chatStore);
        subscribe(AddChannelsAction.class, this::addChannelsAction);
        subscribe(SetActiveChannelAction.class, this::setActiveChannelAction);
        subscribe(GetMessagesAction.class, this::getMessageAction);
        subscribe(NewMessageAction.class, this::newMessageAction);
        subscribe(IncrementOffsetAction.class, this::incrementOffsetAction);

        productStoreEventSource.push(productStore);
        subscribe(AddProductAction.class, this::addProductAction);
        subscribe(GetCategoryAction.class, this::getCategoryAction);
    }

    private void incrementOffsetAction(IncrementOffsetAction action) {
        chatStore.getActiveChannel().setOffset();
    }

    private void newMessageAction(NewMessageAction action) {
        chatStore.newMessageAction(action.getMessage());
        chatStoreEventSource.push(chatStore);
    }

    private void getMessageAction(GetMessagesAction action) {
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

    private void addProductAction(AddProductAction action) {
        productStore.addProductAction(action.getProduct());
        productStoreEventSource.push(productStore);
    }

    private void getCategoryAction(GetCategoryAction action) {
        productStore.GetCategoryAction(action.getCategories());
        productStoreEventSource.push(productStore);
    }
}
