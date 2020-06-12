package org.fiek.services.auth;

import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.store.auth.GetCityAction;
import org.fiek.store.auth.GetCountryByCityAction;
import org.fiek.utils.Ajax;

import java.util.Arrays;

public class GetCityService extends Service<Void> implements View {

    private int cityID;

    public GetCityService(int cityID) {
        this.cityID = cityID;
    }


    private void getCity() throws Exception {
        Ajax request = new Ajax();
        JsonObject response = request.getAsJson("cities/getCity/" + cityID);
        String jsonCities = response.get("cities").toString();
        String jsonAddr = jsonCities.replaceAll("\\[", "").replaceAll("\\]", "");
        String jsonAddr1 = jsonAddr.replaceAll("},", "}},");
        String[] country = jsonAddr1.split("},");
        String countryValue = country[0];
        publishAction(new GetCityAction(countryValue));
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