package org.fiek.services.auth;

import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.Address;
import org.fiek.models.City;
import org.fiek.models.User;
import org.fiek.store.auth.AddAddressAction;
import org.fiek.store.auth.CityAction;
import org.fiek.utils.Ajax;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class CityService extends Service<Void> implements View {

    private void getCities() throws Exception {
        Ajax request = new Ajax();
        JsonObject response = request.getAsJson("/cities");
        String jsonCities = response.get("cities").toString();
        String jsonAddr = jsonCities.replaceAll("\\[", "").replaceAll("\\]", "");
        String jsonAddr1 = jsonAddr.replaceAll("},", "}},");
        String[] addr = jsonAddr1.split("},");
        publishAction(new CityAction(addr));
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                getCities();
                return null;
            }
        };
    }
}
