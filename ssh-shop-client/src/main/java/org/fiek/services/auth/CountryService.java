package org.fiek.services.auth;

import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.store.auth.CountryAction;
import org.fiek.store.auth.GetCountryByCityAction;
import org.fiek.utils.Ajax;

public class CountryService extends Service<Void> implements View {
    public int getCityID() {
        return cityID;
    }

    private int cityID;

    public CountryService(int cityID) {
        this.cityID = cityID;
    }
    public CountryService() {

    }
    private void getCountries() throws Exception {
        Ajax request = new Ajax();
        JsonObject response = request.getAsJson("/countries");
        String jsonCities = response.get("countries").toString();
        String jsonAddr = jsonCities.replaceAll("\\[", "").replaceAll("\\]", "");
        String jsonAddr1 = jsonAddr.replaceAll("},", "}},");
        String[] addr = jsonAddr1.split("},");
        publishAction(new CountryAction(addr));
    }

    private void getCountryByCity(int cityID) throws Exception {
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
                getCountries();
                cityID = getCityID();
                if(cityID!=0) getCountryByCity(cityID);

                return null;
            }
        };
    }
}
