package org.fiek.services.auth;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.City;
import org.fiek.models.Country;
import org.fiek.store.auth.CountryAction;
import org.fiek.store.auth.GetCountryByCityAction;
import org.fiek.store.auth.GetCountryByNameAction;
import org.fiek.utils.Ajax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CityService extends Service<Void> implements View {

    public static ArrayList<City> cities = new ArrayList<>();

    private void getCities() throws Exception {
        if(cities == null || cities.isEmpty()){
            Ajax request = new Ajax();
            JsonObject response = request.getAsJson("/cities");
            String jsonCities = response.get("cities").toString();

            City[] cities1 = new GsonBuilder().create().fromJson(jsonCities, City[].class);
            cities.addAll(Arrays.asList(cities1));

        }
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
