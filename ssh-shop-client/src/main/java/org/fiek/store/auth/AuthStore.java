package org.fiek.store.auth;


import com.google.gson.GsonBuilder;
import eu.lestard.fluxfx.Store;
import org.fiek.models.User;
import org.fiek.utils.JsonHelper;
import org.reactfx.EventSource;
import org.reactfx.EventStream;

public class AuthStore extends Store {

    private static AuthStore authStore = null;

    private EventSource<String> tokenSource = new EventSource<>();
    private EventSource<User> userSource = new EventSource<>();


    private EventSource<String> changed = new EventSource();

    public EventStream<String> getChanged() {
        return changed;
    }

    private String token;
    private User user = null;


    public EventStream<String> getTokenSource() {
        return tokenSource;
    }

    public EventStream<User> getUserSource() {
        return userSource;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public AuthStore() {
        subscribe(AddTokenAction.class, this::addTokenAction);
        subscribe(EditUserAction.class, this::editUserAction);
    }

    public static AuthStore getInstance() {
        if (authStore == null)
            return new AuthStore();
        return authStore;
    }


    private void addTokenAction(AddTokenAction action) {
        final String token = action.getToken();
        final User user = new GsonBuilder().create().fromJson(action.getUser(), User.class);

        if (token != null && !token.trim().isEmpty()) {
            this.tokenSource.push(token);
            this.token = token;
        }

        if (user != null) {
            this.user = user;
            this.userSource.push(user);
        }

    }


    private void editUserAction(EditUserAction action) {
        final User user = new GsonBuilder().create().fromJson(action.getUser(), User.class);

        if (user != null) {
            this.user = user;
            this.userSource.push(user);
        }

    }
}
