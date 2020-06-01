package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;
import org.fiek.models.User;

public class AddTokenAction implements Action {

    private final String token;
    private final String  user;

    public AddTokenAction(String token, String user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }


    public String getUser() {
        return user;
    }
}
