package org.fiek.store.auth;


import com.google.gson.GsonBuilder;
import eu.lestard.fluxfx.Store;
import org.fiek.models.User;
import org.fiek.socket.AuthSocket;


public class AuthStore extends Store {

    public String token;
    public User user;

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }


    public void addTokenAction(String token, String user) {
        final User actionUser = new GsonBuilder().create().fromJson(user, User.class);
        if (token != null && !token.trim().isEmpty())
            this.token = token;

        if (user != null) {
            this.user = actionUser;
            AuthSocket.onSignIn(actionUser.getId());
        }

    }


    public void editUserAction(String user) {
        final User actionUser = new GsonBuilder().create().fromJson(user, User.class);

        if (user != null)
            this.user = actionUser;

    }
}
