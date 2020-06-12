package org.fiek.services.auth;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.Country;
import org.fiek.store.auth.CountryAction;
import org.fiek.store.auth.GetCountryByCityAction;
import org.fiek.store.auth.GetCountryByNameAction;
import org.fiek.utils.Ajax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryService extends Service<Void> implements View {

    public static ArrayList<Country> countries = new ArrayList<>();

    private void getCountries() throws Exception {
        if(countries == null || countries.isEmpty()){
            Ajax request = new Ajax();
            JsonObject response = request.getAsJson("/countries");
            String jsonCities = response.get("countries").toString();

            Country[] countries1 = new GsonBuilder().create().fromJson(jsonCities, Country[].class);
            countries.addAll(Arrays.asList(countries1));

        }
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                getCountries();
                return null;
            }
        };
    }
}
