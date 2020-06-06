package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;

public class GetCountryByNameAction implements Action {

    private final String country;


    public GetCountryByNameAction(String country) {
        System.out.println("String country:" + country);
        this.country = country;
    }


    public String getCountry() {
        return country;
    }
}
