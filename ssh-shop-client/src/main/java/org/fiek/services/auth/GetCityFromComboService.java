package org.fiek.services.auth;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.City;
import org.fiek.models.Country;
import org.fiek.store.auth.CountryAction;
import org.fiek.store.auth.GetCityFromComboAction;
import org.fiek.store.auth.GetCountryByCityAction;
import org.fiek.store.auth.GetCountryByNameAction;
import org.fiek.utils.Ajax;

import java.util.ArrayList;

public class GetCityFromComboService extends Service<Void> implements View {

    private String name;
    private int countryID;
    public static ArrayList<City> cityTrg = new ArrayList<>();
    public GetCityFromComboService(String name, int countryID){

        this.name = name;
        this.countryID = countryID;

    }

    private void getCity() throws Exception {
        if(!cityTrg.isEmpty()) cityTrg.removeAll(cityTrg);
        Ajax request = new Ajax();
        JsonObject response = request.getAsJson("cities/" + name + "/" + countryID);
        String jsonCities = response.get("cities").toString();
        final City[] actionCity = new GsonBuilder().create().fromJson(jsonCities, City[].class);
        cityTrg.add(actionCity[0]);

    }





    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                getCity();
                return null;
            }
        };
    }
}
