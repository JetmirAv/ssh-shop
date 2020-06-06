package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;

public class GetCountryByCityAction implements Action {

    private final String country;


    public GetCountryByCityAction(String country) {
        this.country = country;
    }


    public String getCountry() {
        return country;
    }
}
