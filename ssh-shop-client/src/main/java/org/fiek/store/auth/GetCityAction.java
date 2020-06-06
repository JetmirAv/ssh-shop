package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;

public class GetCityAction implements Action {

    private final String city;


    public GetCityAction(String city) {
        this.city = city;
    }


    public String getCity() {
        return city;
    }
}
