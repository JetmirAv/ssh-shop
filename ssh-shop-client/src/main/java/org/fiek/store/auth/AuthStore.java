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
        System.out.println("Useri ketu:" + user);
        final User actionUser = new GsonBuilder().create().fromJson(user, User.class);

        if (user != null)
            this.user = actionUser;
    }

    public void addAddressAction(String [] addressList) {
        for (String strAddr : addressList) {
            strAddr = strAddr.replaceAll("\"","");
            Address a1 ;
            int id;int user_id; String street; String postal; String phone_number; int city_id;
            ArrayList<String> tt = new ArrayList<String>();
            String[] strAddr2 = strAddr.split(",");
                for (int i = 0; i < strAddr2.length; i++) {
                    String[] str2 = strAddr2[i].split(":");
                    for (int j = 1; j < str2.length; j+=2) {
                        tt.add(str2[j] + "");
                    }
                }
            id = Integer.parseInt(tt.get(0));
            user_id = Integer.parseInt(tt.get(1));
            street = tt.get(2);
            postal = tt.get(3);
            phone_number = tt.get(4);
            city_id = Integer.parseInt(tt.get(5));
            a1 = new Address(id,user_id,street,postal,phone_number,city_id);
            addresses.add(a1);
        }
    }

    public void addCardAction(String [] cardsList) {
        for (String strCard : cardsList) {
            strCard = strCard.replaceAll("\"", "");
            Card c1;
            int id;
            int user_id;
            String number;
            String exp_month;
            String exp_year;
            String code;
            ArrayList<String> tt = new ArrayList<String>();
            String[] strAddr2 = strCard.split(",");
            for (int i = 0; i < strAddr2.length; i++) {
                String[] str2 = strAddr2[i].split(":");
                for (int j = 1; j < str2.length; j += 2) {
                    tt.add(str2[j] + "");
                }
            }
            id = Integer.parseInt(tt.get(0));
            user_id = Integer.parseInt(tt.get(1));
            number = tt.get(2);
            exp_month = tt.get(3);
            exp_year = tt.get(4);
            code = tt.get(5);
            c1 = new Card(id, user_id, number, exp_month, exp_year, code);
            cards.add(c1);
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

    public void GetCountryByCity(String country) {
            final Country actionCountry = new GsonBuilder().create().fromJson(country, Country.class);
            System.out.println("HEH:" + actionCountry);
            if (country != null) {
                this.country = actionCountry;
            }
        }
    }

