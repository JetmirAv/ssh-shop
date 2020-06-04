package org.fiek.services.auth;

import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.store.auth.CountryAction;
import org.fiek.store.auth.GetCityFromComboAction;
import org.fiek.store.auth.GetCountryByCityAction;
import org.fiek.store.auth.GetCountryByNameAction;
import org.fiek.utils.Ajax;

public class GetCityFromComboService extends Service<Void> implements View {

    private String name;
    private int countryID;

    public GetCityFromComboService(String name, int countryID){

        this.name = name;
        this.countryID = countryID;

    }

    private void getCity() throws Exception {
        Ajax request = new Ajax();
        JsonObject response = request.getAsJson("cities/" + name + "/" + countryID);
        String jsonCities = response.get("cities").toString();
        String jsonAddr = jsonCities.replaceAll("\\[", "").replaceAll("\\]", "");
        String jsonAddr1 = jsonAddr.replaceAll("},", "}},");
        String[] country = jsonAddr1.split("},");
        String countryValue = country[0];
        publishAction(new GetCityFromComboAction(countryValue));

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
