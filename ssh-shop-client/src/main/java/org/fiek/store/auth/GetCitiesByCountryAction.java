package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;

public class GetCitiesByCountryAction implements Action {

    private final String []  cities;


    public GetCitiesByCountryAction(String [] cities) {
        this.cities = cities;
    }


    public String [] getCities() {
        return cities;
    }
}
