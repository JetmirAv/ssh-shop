package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;

import java.util.Arrays;

public class CountryAction implements Action {

    private final String []  countries;


    public CountryAction(String [] countries) {
        this.countries = countries;
    }




    public String[] getCountries() {
        return countries;
    }
}
