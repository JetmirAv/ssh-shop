package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;

public class BlankAddressAction implements Action {

    private final String []  addresses;


    public BlankAddressAction(String [] address) {
        this.addresses = address;
    }


    public String[] getAddress() {
        return addresses;
    }
}
