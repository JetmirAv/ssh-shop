package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;

public class GetAddressAction implements Action {

    private final String address;


    public GetAddressAction(String address) {
        this.address = address;
    }


    public String getAddress() {
        return address;
    }
}
