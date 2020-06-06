package org.fiek.services.home;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.User;
import org.fiek.store.auth.AddTokenAction;
import org.fiek.utils.Ajax;

public class HomeService extends Service<Void> implements View {

    private User user;

    public HomeService(User user) {
        this.user = user;
    }

    public void search() throws Exception {

        final String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this.user, User.class);

        Ajax request = new Ajax();
        JsonObject response = request.post("auth/search", json);

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
                search();
                return null;
            }
        };
    }
}
