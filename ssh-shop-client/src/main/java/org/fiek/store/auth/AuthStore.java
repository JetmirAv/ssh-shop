package org.fiek.store.auth;

import com.google.gson.GsonBuilder;
import eu.lestard.fluxfx.Store;
import org.fiek.models.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.fiek.models.User;
import java.util.Arrays;

import org.fiek.services.auth.GetAddressService;
import org.fiek.socket.AuthSocket;

public class AuthStore extends Store {

    public String token;
    public User user;
    public Address address;
    public Card card;

    public void setAddress(Address address) {
        this.address = address;
    }

    private Country country;
    public ArrayList<Address> addresses;
    public ArrayList<City> cities = new ArrayList<>();
    public ArrayList<Country> countries = new ArrayList<>();
    public ArrayList<Card> cards = new ArrayList<>();
    public City cityTarget;
    private City city;
    private Country countryTarget;
    private City cityFromCombo;
    Address selectedAddress;
    Integer selectedCard = null;

    public City getCityFromCombo() {
        return cityFromCombo;
    }

    public City getCityTarget() {
        return cityTarget;
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

    public Address getSelectedAddress() {
        return selectedAddress;
    }

    public Integer getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedAddress(Address selectedAddress) {
        if (this.selectedAddress == null || this.selectedAddress.getId() != selectedAddress.getId()) {

            this.selectedAddress = selectedAddress;
            if (selectedAddress.getId() > 0) {
                GetAddressService addressService = new GetAddressService(selectedAddress.getId(), user.getId());
                addressService.start();
                System.out.println("Mrenda");
            }
            System.out.println("Jasht");
        }
        System.out.println("Shum Jasht");
    }

    public void addTokenAction(String token, String user) {
        final User actionUser = new GsonBuilder().create().fromJson(user, User.class);
        if (token != null && !token.trim().isEmpty())
            this.token = token;
        if (user != null) {
            this.user = actionUser;
            AuthSocket.onSignIn(actionUser.getId());
        }

    }

    public Country getCountryTarget() {
        return countryTarget;
    }

    public void editUserAction(String user) {
        final User actionUser = new GsonBuilder().create().fromJson(user, User.class);

        if (user != null)
            this.user = actionUser;
    }

    public void editAddressAction(String address) {
        System.out.println("ne updatEE:" + address);

        final Address actionAddress = new GsonBuilder().create().fromJson(address, Address.class);
        System.out.println("objekti: " + actionAddress);
        for (Address addr : addresses) {
            if (actionAddress.getId() == (addr.getId())) {
                addr.setStreet(actionAddress.getStreet());
                addr.setPostal(actionAddress.getPostal());
                addr.setCityId(actionAddress.getCityId());
                addr.setCity(actionAddress.getCity());
                this.selectedAddress = addr;
            }

        }
    }

    public void addAddressAction(String addressList) {
        final Address[] addresses = new GsonBuilder().create().fromJson(addressList, Address[].class);
        // for (String addr : addressList) {
        // final Address actionAddress = new GsonBuilder().create().fromJson(addr,
        // Address.class);
        // this.address = actionAddress;
        // addresses.add(this.address);
        // }
        // this.addresses.push(addresses);
        this.addAddresses(Arrays.asList(addresses));
    }

    public void addNewAddress(String address){
        String address2 = address.replaceAll("Address","");

        final Address actionAddress = new GsonBuilder().create().fromJson(address2, Address.class);
        System.out.println("Address1 Obj:" + actionAddress);
        this.addresses.add(actionAddress);
        setSelectedAddress(actionAddress);

    }

    public void addAddresses(List<Address> addresses) {
        if (this.addresses == null)
            this.addresses = new ArrayList<>();

        this.addresses.addAll(addresses);
    }

    public void addCardAction(String[] cardsList) {
        for (String cardStr : cardsList) {
            final Card cardObj = new GsonBuilder().create().fromJson(cardStr, Card.class);
            this.card = cardObj;
            cards.add(this.card);
        }
    }

    public void GetCityAction(String[] city) {
        System.out.println("brenda getCityAction!");
        for (String strCity : city) {
            final City actionCity = new GsonBuilder().create().fromJson(strCity, City.class);
            System.out.println("Knej:" + actionCity);
            this.city = actionCity;
            cities.add(this.city);
        }
    }

    public void GetCountryAction(String[] country) {
        System.out.println("brenda getcountryAction!");
        for (String strCountry : country) {
            final Country actionCountry = new GsonBuilder().create().fromJson(strCountry, Country.class);
            this.country = actionCountry;
            countries.add(this.country);
        }
    }

    public void GetAddressAction(String address) {
        final Address actionAddress = new GsonBuilder().create().fromJson(address, Address.class);
        this.selectedAddress = actionAddress;
    }

    public void GetCountryByCity(String country) {
        final Country actionCountry = new GsonBuilder().create().fromJson(country, Country.class);
        this.country = actionCountry;
    }

    public void GetCityByCombo(String city) {
        final City actionCountry = new GsonBuilder().create().fromJson(city, City.class);
        this.cityFromCombo = actionCountry;
    }

    public void GetCountryByName(String country) {
        System.out.println("a po thirret qekjo?");
        final Country actionCountry = new GsonBuilder().create().fromJson(country, Country.class);
        System.out.println("Country by name:" + actionCountry.getName());
        this.countryTarget = actionCountry;
    }

    public void GetCity(String city) {
        System.out.println("testest:" + city);
        final City actionCity = new GsonBuilder().create().fromJson(city, City.class);
        System.out.println("test1test1:" + actionCity);
        int id = actionCity.getId();
        String name = actionCity.getName();
        int country = actionCity.getCountryId();
        this.cityTarget = new City(id, name, country);
    }

    public void GetCityByCountryAction(String[] city) {
        for (String strCity : city) {
            final City actionCity = new GsonBuilder().create().fromJson(strCity, City.class);
            System.out.println("Knej:" + actionCity);
            this.city = actionCity;
            cities.add(this.city);
        }
    }
}
