package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;

public class GetCityFromComboAction implements Action {

    private final String city;


    public GetCityFromComboAction(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
