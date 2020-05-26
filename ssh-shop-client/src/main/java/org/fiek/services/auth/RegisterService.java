package org.fiek.services.auth;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import eu.lestard.easydi.EasyDI;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.User;
import org.fiek.store.auth.AddTokenAction;
import org.fiek.utils.Ajax;
import org.fiek.utils.Tuple;

import java.io.IOException;

public class RegisterService extends Service<Void> implements View {
    private User user;

    public RegisterService(User user) {
        this.user = user;
    }


    public void register() throws Exception {

        final String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this.user, User.class);

        Ajax request = new Ajax();
        JsonObject response = request.post("auth/register", json);

        String jsonUser = response.get("user").toString();
        String jsonToken = response.get("token").toString();
        jsonToken = jsonToken.substring(1, jsonToken.length() - 1);

        publishAction(new AddTokenAction(jsonToken, jsonUser));

    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                register();
                return null;
            }
        };
    }
}
