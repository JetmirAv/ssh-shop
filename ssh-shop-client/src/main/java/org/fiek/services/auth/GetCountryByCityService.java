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
import org.fiek.store.auth.CountryAction;
import org.fiek.store.auth.GetCountryByCityAction;
import org.fiek.utils.Ajax;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class GetCountryByCityService extends Service<Void> implements View {

    private int cityID;
    public GetCountryByCityService(int cityID) {
        this.cityID = cityID;
    }

    private void getCountryByCity() throws Exception {
        Ajax request = new Ajax();
        JsonObject response = request.getAsJson("cities/test/" + cityID);
        String jsonCities = response.get("countryName").toString();
        String jsonAddr = jsonCities.replaceAll("\\[", "").replaceAll("\\]", "");
        String jsonAddr1 = jsonAddr.replaceAll("},", "}},");
        String[] country = jsonAddr1.split("},");
        String countryValue = country[0];
        System.out.println("Value:" + countryValue);
        publishAction(new GetCountryByCityAction(countryValue));
    }



    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                getCountryByCity();
                return null;
            }
        };
    }
}
