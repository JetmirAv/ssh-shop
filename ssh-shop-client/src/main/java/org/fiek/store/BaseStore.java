package org.fiek.store;

import eu.lestard.fluxfx.Store;
import org.fiek.store.auth.AddTokenAction;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.auth.EditUserAction;
import org.fiek.store.home.HomeStore;
import org.reactfx.EventSource;
import org.reactfx.EventStream;

import javax.inject.Singleton;

@Singleton
public class BaseStore extends Store {

    private final EventSource<AuthStore> authStoreEventSource = new EventSource<>();
    private final AuthStore authStore;
    private final EventSource<HomeStore> homeStoreEventSource = new EventSource<>();
    private final HomeStore homeStore;

    public EventStream<AuthStore> getAuthStoreEventStream() {
        return authStoreEventSource;
    }

    public AuthStore getAuthStore() {
        return authStore;
    }

    public EventStream<HomeStore> getHomeStoreEventStream() {
        return homeStoreEventSource;
    }

    public HomeStore getHomeStore() {
        return homeStore;
    }

    public BaseStore() {
        authStore = new AuthStore();
        authStoreEventSource.push(authStore);
        homeStore = new HomeStore();
        homeStoreEventSource.push(homeStore);
        subscribe(AddTokenAction.class, this::addTokenAction);
        subscribe(EditUserAction.class, this::editUserAction);
    }

    private void editUserAction(EditUserAction t) {
        authStore.editUserAction(t.getUser());
        authStoreEventSource.push(authStore);
        homeStore.editUserAction(t.getUser());
        homeStoreEventSource.push(homeStore);
    }

    private void addTokenAction(AddTokenAction action) {
        authStore.addTokenAction(action.getToken(), action.getUser());
        authStoreEventSource.push(authStore);
        homeStore.addTokenAction(action.getToken(), action.getUser());
        homeStoreEventSource.push(homeStore);
    }


}
