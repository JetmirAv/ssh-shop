package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;

public class AddNewAddressAction implements Action {

    private final String addresses;

    public AddNewAddressAction(String address) {
        System.out.println("adresa ne action:" + address);
        this.addresses = address;
    }

    public String getAddress() {
        return addresses;
    }
}
