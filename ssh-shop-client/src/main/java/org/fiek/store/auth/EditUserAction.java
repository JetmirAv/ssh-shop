package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;
import org.fiek.models.User;

public class EditUserAction implements Action {

    private final String  user;

    public EditUserAction(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }
}
