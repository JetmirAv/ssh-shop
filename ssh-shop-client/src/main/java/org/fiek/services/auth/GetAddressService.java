package org.fiek.services.auth;

import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.store.auth.GetAddressAction;
import org.fiek.store.auth.GetCityAction;
import org.fiek.store.auth.GetCountryByCityAction;
import org.fiek.utils.Ajax;

import java.util.Arrays;

public class GetAddressService extends Service<Void> implements View {

    private int id;
    private int userId;


    public GetAddressService(int id, int userId) {
        System.out.println("ne konstruktor");
        this.id = id;
        this.userId = userId;
    }

    private void getAddressById() throws Exception {
        Ajax request = new Ajax();
        JsonObject response = request.getAsJson("/users/" + userId + "/address/" + id);
        String jsonCities = response.get("address").toString();
        publishAction(new GetAddressAction(jsonCities));
    }


    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                getAddressById();
                return null;
            }
        };
    }
}