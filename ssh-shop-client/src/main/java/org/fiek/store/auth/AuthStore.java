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
import org.fiek.services.auth.GetCardService;
import org.fiek.socket.AuthSocket;

public class AuthStore extends Store {

    public String token;
    public User user;
    public Address address;
    public Card card;
    private Country country;
    public ArrayList<Address> addresses;
    public ArrayList<City> cities = new ArrayList<>();
    public ArrayList<Country> countries = new ArrayList<>();
    public ArrayList<Card> cards;
    public City cityTarget;
    private City city;
    private Country countryTarget;
    private City cityFromCombo;
    Address selectedAddress;
    Card selectedCard;

    public void setAddress(Address address) {
        this.address = address;
    }

    public Country getCountry() {
        return country;
    }

    public ArrayList<Card> getCards() {
        return cards;
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

    public Card getSelectedCard() {
        return selectedCard;
    }


    public void setSelectedAddress(Address selectedAddress) {
        if (this.selectedAddress == null || this.selectedAddress.getId() != selectedAddress.getId()) {

            this.selectedAddress = selectedAddress;
            if (selectedAddress.getId() > 0) {
                GetAddressService addressService = new GetAddressService(selectedAddress.getId(), user.getId());
                addressService.start();
            }
        }
    }

    public void setSelectedCard(Card selectedCard) {
        if (this.selectedCard == null || this.selectedCard.getId() != selectedCard.getId()) {

            this.selectedCard = selectedCard;
            if (selectedCard.getId() > 0) {
                GetCardService cardService = new GetCardService(selectedCard.getId(), user.getId());
                cardService.start();
            }
        }
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

    public void editUserAction(String user) {
        final User actionUser = new GsonBuilder().create().fromJson(user, User.class);

        if (user != null)
            this.user = actionUser;
    }

    public void editAddressAction(String address) {
        final Address actionAddress = new GsonBuilder().create().fromJson(address, Address.class);

        for (Address addr : addresses) {

            if (actionAddress.getId() == (addr.getId())) {
                addr.setStreet(actionAddress.getStreet());
                addr.setPostal(actionAddress.getPostal());
                addr.setCityId(actionAddress.getCityId());
                addr.setCity(actionAddress.getCity());
                this.selectedAddress = addr;
            } else {
                Address newAddress = getSelectedAddress();
                newAddress.setId(actionAddress.getId());
                newAddress.setStreet(actionAddress.getStreet());
                newAddress.setPostal(actionAddress.getPostal());
                newAddress.setCity(actionAddress.getCity());
            }

        }
    }

    public void editCardAction(String card) {
        final Card actionCard = new GsonBuilder().create().fromJson(card, Card.class);
        for (Card card1 : cards) {
            if (actionCard.getId() == (card1.getId())) {

                card1.setNumber(actionCard.getNumber());
                card1.setExp_month(actionCard.getExp_month());
                card1.setExp_year(actionCard.getExp_year());
                card1.setCode(actionCard.getCode());
                this.selectedCard = card1;
            } else {
                Card newCard = getSelectedCard();
                newCard.setId(actionCard.getId());
                newCard.setNumber(actionCard.getNumber());
                newCard.setExp_year(actionCard.getExp_year());
                newCard.setExp_month(actionCard.getExp_month());
                newCard.setCode(actionCard.getCode());

            }

        }
    }


    public void addAddressAction(String addressList) {
        final Address[] addresses = new GsonBuilder().create().fromJson(addressList, Address[].class);
        this.addAddresses(Arrays.asList(addresses));
    }

    public void addCardAction(String cardsList) {
        final Card[] cards = new GsonBuilder().create().fromJson(cardsList, Card[].class);
        this.addCards(Arrays.asList(cards));
    }

    public void addNewAddress(String address) {
        String address2 = address.replaceAll("Address", "");

        final Address actionAddress = new GsonBuilder().create().fromJson(address2, Address.class);
        this.addresses.add(actionAddress);
        setSelectedAddress(actionAddress);

    }

    public void addNewCard(String card) {
        String card1 = card.replaceAll("Card", "");

        final Card actionCard = new GsonBuilder().create().fromJson(card1, Card.class);
        this.cards.add(actionCard);
        setSelectedCard(actionCard);

    }

    public void addAddresses(List<Address> addresses) {
        if (this.addresses == null)
            this.addresses = new ArrayList<>();

        this.addresses.addAll(addresses);
    }

    public void addCards(List<Card> cards) {
        if (this.cards == null)
            this.cards = new ArrayList<>();

        this.cards.addAll(cards);
    }


    public void GetCityAction(String[] city) {
        for (String strCity : city) {
            final City actionCity = new GsonBuilder().create().fromJson(strCity, City.class);
            this.city = actionCity;
            cities.add(this.city);
        }
    }

    public void GetCountryAction(String[] country) {
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
        final Country actionCountry = new GsonBuilder().create().fromJson(country, Country.class);
        this.countryTarget = actionCountry;
    }

    public void GetCity(String city) {
        final City actionCity = new GsonBuilder().create().fromJson(city, City.class);
        int id = actionCity.getId();
        String name = actionCity.getName();
        int country = actionCity.getCountryId();
        this.cityTarget = new City(id, name, country);
    }

    public void GetCityByCountryAction(String[] city) {
        for (String strCity : city) {
            final City actionCity = new GsonBuilder().create().fromJson(strCity, City.class);
            this.city = actionCity;
            cities.add(this.city);
        }
    }
}
