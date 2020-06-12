package org.fiek.services.auth;

import com.google.gson.JsonObject;
import eu.lestard.easydi.EasyDI;
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
//                json.addProperty("email", email);
//                json.addProperty("password", password);
                json.addProperty("email",  email.equals("1") ? "laurent.arifaj@hotmail.com" : "arifajlaurent@hotmail.com");
               // json.addProperty("password", "password");
//                json.addProperty("email",  email.equals("1") ? "rijonpireva2@gmail.com" : "granit2@gmail.com");
                json.addProperty("password", "password");
                try {
                    Ajax request = new Ajax();
                    JsonObject response = request.post("/auth/login", json.toString());
                    String user = response.get("user").toString();
                    String token = response.get("token").toString();
                    token = token.substring(1, token.length() - 1);

                    publishAction(new AddTokenAction(token, user));
                } catch (Exception exception) {
                    this.cancel();
                    exception.printStackTrace();
                }


                return null;
            }
        };
    }
}
