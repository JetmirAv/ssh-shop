package org.fiek.services.auth;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.User;
import org.fiek.store.auth.AddTokenAction;
import org.fiek.store.auth.EditUserAction;
import org.fiek.utils.Ajax;
import org.fiek.utils.Tuple;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;

public class InfoService extends Service<Void> implements View {
    private User user;

    public InfoService(User user) {
        this.user = user;
    }

    public void edit() throws Exception {

        final String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this.user, User.class);
        Ajax request = new Ajax("users/" + user.getId(), Ajax.methods.PATCH, json);
        JsonObject response = request.patch();
        String jsonUser = response.get("user").toString();
        publishAction(new EditUserAction(jsonUser));

    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                edit();
                return null;
            }
        };
    }
}
