package org.fiek.services.auth;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import eu.lestard.easydi.EasyDI;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.Address;
import org.fiek.models.User;
import org.fiek.store.auth.AddAddressAction;
import org.fiek.store.auth.AddTokenAction;
import org.fiek.utils.Ajax;
import org.fiek.utils.Tuple;

import java.io.IOException;

public class CreateAddressService extends Service<Void> implements View {
    private int userId;
    private Address address;

    public CreateAddressService(int userId, Address address) {
        this.userId = userId;
        this.address = address;
    }



    public void createAddress() throws Exception {

        final String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this.address, Address.class);
        Ajax request = new Ajax();
        JsonObject response = request.post("users/" + userId + "/address", json);
        String jsonAddress = response.get("address").toString();
        String jsonAddr = jsonAddress.replaceAll("\\[", "").replaceAll("\\]", "");
        String jsonAddr1 = jsonAddr.replaceAll("},", "}},");
        String[] addr = jsonAddr1.split("},");
        // publishAction(new AddAddressAction(addr));

    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                createAddress();
                return null;
            }
        };
    }
}
