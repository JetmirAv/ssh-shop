package org.fiek.store;

import eu.lestard.fluxfx.Store;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import org.fiek.models.Channel;
import org.fiek.services.chat.GetChannelMessagesService;
import org.fiek.store.auth.*;
import org.fiek.store.auth.AddTokenAction;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.auth.EditUserAction;
import org.fiek.store.chat.*;
import org.reactfx.EventSource;
import org.reactfx.EventStream;

import javax.inject.Singleton;

@Singleton
public class BaseStore extends Store {

    //Service
    private Service service;

    public Service getService() {
        return service;
    }

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
        subscribe(AddAddressAction.class, this::addAddressAction);
        subscribe(CityAction.class, this::GetCityAction);
        subscribe(CountryAction.class, this::GetCountryAction);
        subscribe(GetCountryByCityAction.class, this::GetCountryByCity);
        subscribe(AddCardAction.class, this::addCardAction);
        subscribe(GetCityAction.class, this::GetCityAction);
        subscribe(GetCitiesByCountryAction.class, this::GetCityByCountryAction);
        subscribe(GetCountryByNameAction.class, this::GetCountryByName);
        subscribe(GetCityFromComboAction.class, this::GetCityByComboAction);
        subscribe(EditAddressAction.class, this::editAddressAction);
        subscribe(SetActiveAddressAction.class, this::setActiveAddressAction);
        subscribe(GetAddressAction.class, this::GetAddressAction);

        chatStoreEventSource.push(chatStore);
        subscribe(AddChannelsAction.class, this::addChannelsAction);
        subscribe(SetActiveChannelAction.class, this::setActiveChannelAction);
        subscribe(GetMessagesAction.class, this::getMessageAction);
        subscribe(NewMessageAction.class, this::newMessageAction);
        subscribe(IncrementOffsetAction.class, this::incrementOffsetAction);
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
        Channel oldSelectedChannel = chatStore.getSelectedChannel();
        if (oldSelectedChannel == null || oldSelectedChannel.getId() != action.getChannel().getId()) {
            chatStore.setSelectedChannel(action.getChannel());
            chatStoreEventSource.push(chatStore);
        }
    }

    private void setActiveAddressAction(SetActiveAddressAction action) {
        authStore.setSelectedAddress(action.getAddress());
        authStoreEventSource.push(authStore);
    }

    private void addChannelsAction(AddChannelsAction action) {
        chatStore.addChannelsAction(action.getChannels(), action.getCount());
        chatStoreEventSource.push(chatStore);
    }

    private void editUserAction(EditUserAction action) {
        authStore.editUserAction(action.getUser());
        authStoreEventSource.push(authStore);
    }

    private void editAddressAction(EditAddressAction t) {
        authStore.editAddressAction(t.getAddress());
        authStoreEventSource.push(authStore);
    }

    private void addTokenAction(AddTokenAction action) {
        authStore.addTokenAction(action.getToken(), action.getUser());
        authStoreEventSource.push(authStore);
    }

    private void addAddressAction(AddAddressAction t) {
        authStore.addAddressAction(t.getAddress());
        authStoreEventSource.push(authStore);
    }

    private void addCardAction(AddCardAction t) {
        authStore.addCardAction(t.getCards());
        authStoreEventSource.push(authStore);
    }

    private void GetCityAction(CityAction t) {
        authStore.GetCityAction(t.getCities());
        authStoreEventSource.push(authStore);
    }

    private void GetCityByCountryAction(GetCitiesByCountryAction t) {
        authStore.GetCityByCountryAction(t.getCities());
        authStoreEventSource.push(authStore);
    }

    private void GetAddressAction(GetAddressAction t) {
        authStore.GetAddressAction(t.getAddress());
        authStoreEventSource.push(authStore);
    }

    private void GetCityByComboAction(GetCityFromComboAction t) {
        authStore.GetCityByCombo(t.getCity());
        authStoreEventSource.push(authStore);
    }

    private void GetCountryAction(CountryAction t) {
        authStore.GetCountryAction(t.getCountries());
        authStoreEventSource.push(authStore);
    }


    private void GetCountryByCity(GetCountryByCityAction t) {
        authStore.GetCountryByCity(t.getCountry());
        authStoreEventSource.push(authStore);
    }

    private void GetCountryByName(GetCountryByNameAction t) {
        authStore.GetCountryByName(t.getCountry());
        authStoreEventSource.push(authStore);
    }

    private void GetCityAction(GetCityAction t) {
        authStore.GetCity(t.getCity());
        authStoreEventSource.push(authStore);
    }


}
