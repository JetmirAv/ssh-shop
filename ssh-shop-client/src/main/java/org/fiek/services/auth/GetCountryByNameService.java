package org.fiek.services.auth;

import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.store.auth.CountryAction;
import org.fiek.store.auth.GetCountryByCityAction;
import org.fiek.store.auth.GetCountryByNameAction;
import org.fiek.utils.Ajax;

public class GetCountryByNameService extends Service<Void> implements View {


    private String name;

    public GetCountryByNameService(String name){
        this.name = name;
    }

    private void getCountryByName() throws Exception {
        Ajax request = new Ajax();
        JsonObject response = request.getAsJson("countries/" + name);
        String jsonCities = response.get("country").toString();
        String jsonAddr = jsonCities.replaceAll("\\[", "").replaceAll("\\]", "");
        String jsonAddr1 = jsonAddr.replaceAll("},", "}},");
        String[] country = jsonAddr1.split("},");
        String countryValue = country[0];
        System.out.println("test --" + countryValue);
        publishAction(new GetCountryByNameAction(countryValue));
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
