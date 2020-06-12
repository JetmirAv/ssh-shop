package org.fiek.services.auth;

import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.Address;
import org.fiek.models.User;
import org.fiek.store.auth.AddAddressAction;
import org.fiek.utils.Ajax;

import java.util.ArrayList;
import java.util.Arrays;

public class AddressService extends Service<Void> implements View {
    private User user;

    public AddressService(User user) {
        this.user = user;
    }

    private void getAllAddresses() throws Exception {
        Ajax request = new Ajax();
        JsonObject response = request.getAsJson("users/" + user.getId() + "/address");
        String jsonAddress = response.get("address").toString();
        // String jsonAddr = jsonAddress.replaceAll("\\[", "").replaceAll("\\]", "");
        // String jsonAddr1 = jsonAddr.replaceAll("},", "}},");
        // String[] addr = jsonAddr1.split("},");
        publishAction(new AddAddressAction(jsonAddress));
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
