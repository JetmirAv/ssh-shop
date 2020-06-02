package org.fiek.store.auth;


import com.google.gson.GsonBuilder;
import eu.lestard.fluxfx.Store;
import org.fiek.models.*;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class AuthStore extends Store {

    public String token;
    public User user;
    public Address address;
    public Card card;
    private City city;
    private Country country;
    public ArrayList<Address> addresses = new ArrayList<>();
    public ArrayList<City> cities = new ArrayList<>();
    public ArrayList<Country> countries = new ArrayList<>();
    public ArrayList<Card> cards = new ArrayList<>();


    public Card getCard() {
        return card;
    }

    public City getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
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
        final User actionUser = new GsonBuilder().create().fromJson(user, User.class);

        if (user != null)
            this.user = actionUser;
    }

    public void addAddressAction(String [] addressList) {
        for (String addr : addressList) {
            final Address actionAddress = new GsonBuilder().create().fromJson(addr, Address.class);
                this.address = actionAddress;
                addresses.add(this.address);
            }
        }


    public void addCardAction(String [] cardsList) {
        for (String cardStr : cardsList) {
            final Card cardObj = new GsonBuilder().create().fromJson(cardStr, Card.class);
            this.card = cardObj;
            cards.add(this.card);
        }
    }

    public void GetCityAction(String [] city) {
        for (String strCity : city) {
            final City actionCity = new GsonBuilder().create().fromJson(strCity, City.class);
                this.city = actionCity;
                cities.add(this.city);
            }
        }

    public void GetCountryAction(String [] country) {
        for (String strCountry : country) {
            final Country actionCountry = new GsonBuilder().create().fromJson(strCountry, Country.class);
            this.country = actionCountry;
                countries.add(this.country);
            }
        }

    public void GetCountryByCity(String country) {
            final Country actionCountry = new GsonBuilder().create().fromJson(country, Country.class);
                this.country = actionCountry;
            }
        }

