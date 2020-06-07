package org.fiek.store.auth;

import eu.lestard.fluxfx.Action;
import org.fiek.models.Address;

public class SetActiveAddressAction implements Action {

    Address address;
    public SetActiveAddressAction(Address address) {
        System.out.println("SetActiveAddressAction" + address);
        this.address = address;
    }

    public Address getActiveAddressAction(){
        return address;
    }


    public Address getAddress() {
        return address;
    }
}
