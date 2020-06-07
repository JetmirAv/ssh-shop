package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;
import org.fiek.models.User;

public class EditAddressAction implements Action {

    private final String  address;

    public EditAddressAction(String address) {
        System.out.println("AddressNeUpdate:" + address);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
