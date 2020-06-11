package org.fiek.services.auth;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.Address;
import org.fiek.models.City;
import org.fiek.models.User;
import org.fiek.store.auth.AddAddressAction;
import org.fiek.store.auth.CityAction;
import org.fiek.store.auth.GetCitiesByCountryAction;
import org.fiek.utils.Ajax;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class GetCitiesByCountryService extends Service<Void> implements View {

    private int countryId;
    public static ArrayList<City> cities = new ArrayList<>();

    public GetCitiesByCountryService(int countryId) {
        this.countryId = countryId;
    }

    private void GetCitiesByCountryService() throws Exception {
        if(!cities.isEmpty()) cities.removeAll(cities);
        Ajax request = new Ajax();
        JsonObject response = request.getAsJson("/countries/cities/" + countryId);
        String jsonCities = response.get("cities").toString();

        City[] cities1 = new GsonBuilder().create().fromJson(jsonCities, City[].class);
        cities.addAll(Arrays.asList(cities1));
        System.out.println("popo1: " + cities.size());
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                GetCitiesByCountryService();
                return null;
            }
        };
    }
}
