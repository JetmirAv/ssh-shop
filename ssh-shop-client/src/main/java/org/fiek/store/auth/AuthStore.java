package org.fiek.store.auth;


import com.google.gson.GsonBuilder;
import eu.lestard.fluxfx.Store;
import org.fiek.models.Address;
import org.fiek.models.City;
import org.fiek.models.Country;
import org.fiek.models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class AuthStore extends Store {

    public String token;
    public User user;
    public Address address;
    private City city;
    private Country country;
    public ArrayList<Address> addresses = new ArrayList<>();
    public ArrayList<City> cities = new ArrayList<>();
    public ArrayList<Country> countries = new ArrayList<>();

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public ArrayList<City> getCities() {
        return cities;
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

    public void GetCityAction(String [] city) {
        for (String strCity : city) {
            final City actionCity = new GsonBuilder().create().fromJson(strCity, City.class);
            if (city != null) {
                this.city = actionCity;
                cities.add(this.city);
            }
        }
    }

    public void GetCountryAction(String [] country) {
        for (String strCountry : country) {
            final Country actionCountry = new GsonBuilder().create().fromJson(strCountry, Country.class);
            if (country != null) {
                this.country = actionCountry;
                countries.add(this.country);
            }
        }
    }
}
