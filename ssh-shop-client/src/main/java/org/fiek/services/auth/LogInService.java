package org.fiek.services.auth;

import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.store.auth.AddTokenAction;
import org.fiek.utils.Ajax;
import org.fiek.utils.Tuple;

import java.io.IOException;
import java.security.Provider;

public class LogInService extends Service<Void> implements View {
    private String email;
    private String password;

    public LogInService(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                JsonObject json = new JsonObject();
                json.addProperty("email", email);
                json.addProperty("password", password);
//                json.addProperty("email", "jetmir99@hotmail.com");
//                json.addProperty("password", "password");

                Ajax request = new Ajax("/auth/login", Ajax.methods.POST, json.toString());
                try {
                    JsonObject response = request.post();
                    String user = response.get("user").toString();
                    String token = response.get("token").toString();
                    token = token.substring(1, token.length() - 1);

                    publishAction(new AddTokenAction(token, user));
                } catch (IOException exception) {
                    exception.printStackTrace();
                }


                return null;
            }
        };
    }
}