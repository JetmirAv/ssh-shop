package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;

import java.util.Arrays;

public class CityAction implements Action {

    private final String []  cities;


    public CityAction(String [] cities) {
        this.cities = cities;
    }



    public String[] getCities() {
        return cities;
    }
}
