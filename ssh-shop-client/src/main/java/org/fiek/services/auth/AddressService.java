package org.fiek.services.auth;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import eu.lestard.easydi.EasyDI;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.Address;
import org.fiek.models.User;
import org.fiek.store.auth.AddTokenAction;
import org.fiek.store.auth.EditUserAction;
import org.fiek.utils.Ajax;
import org.fiek.utils.Tuple;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;

public class AddressService extends Service<Void> implements View {
    private User user;
    public AddressService(User user) {
        this.user = user;
    }

    private void getAllAddresses() throws Exception {

        Ajax request = new Ajax();
        String response = request.get("users/" + user.getId() + "/address");
        System.out.println("Adresat jane:" + response);



//        publishAction(new EditUserAction(jsonUser));

    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                getAllAddresses();
                return null;
            }
        };
    }
}
