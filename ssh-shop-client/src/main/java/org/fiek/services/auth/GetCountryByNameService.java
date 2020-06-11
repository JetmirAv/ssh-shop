package org.fiek.services.auth;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.Address;
import org.fiek.models.City;
import org.fiek.models.Country;
import org.fiek.store.auth.CountryAction;
import org.fiek.store.auth.GetCountryByCityAction;
import org.fiek.store.auth.GetCountryByNameAction;
import org.fiek.utils.Ajax;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class GetCountryByNameService extends Service<Void> implements View {


    private String name = "";

    public GetCountryByNameService(String name){
        this.name = name;
    }

    public static ArrayList<Country> countryTarget = new ArrayList<>();

    private void getCountryByName() throws Exception {
            if(!countryTarget.isEmpty()) countryTarget.removeAll(countryTarget);
            System.out.println("name is :" + name);
            Ajax request = new Ajax();
            JsonObject response = request.getAsJson("countries/" + name);
            String jsonCities = response.get("country").toString();
            System.out.println("jsonCity1:" + jsonCities);
            final Country actionAddress = new GsonBuilder().create().fromJson(jsonCities, Country.class);
            countryTarget.add(actionAddress);
        }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                getCountryByName();
                return null;
            }
        };
    }
}
