package org.fiek.store.auth;


import com.google.gson.GsonBuilder;
import eu.lestard.fluxfx.Store;
import org.fiek.models.Address;
import org.fiek.models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class AuthStore extends Store {

    public String token;
    public User user;
    public Address address;
    public ArrayList<Address> addresses = new ArrayList<>();

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public Address getAddress() {
        return address;
    }


    public void addTokenAction(String token, String user) {
        final User actionUser = new GsonBuilder().create().fromJson(user, User.class);
        if (token != null && !token.trim().isEmpty())
            this.token = token;

        if (user != null)
            this.user = actionUser;

    }


    public void editUserAction(String user) {
        System.out.println("Useri ketu:" + user);
        final User actionUser = new GsonBuilder().create().fromJson(user, User.class);

        if (user != null)
            this.user = actionUser;

    }

    public void addAddressAction(String [] address) {
        for (String strAddr : address) {
            final Address actionAddress = new GsonBuilder().create().fromJson(strAddr, Address.class);
            if (address != null) {
                this.address = actionAddress;
                addresses.add(this.address);
            }
        }
    }
}
