package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;

public class AddAddressAction implements Action {

    private final String addresses;

    public AddAddressAction(String address) {
        this.addresses = address;
    }

    public String getAddress() {
        return addresses;
    }
}
