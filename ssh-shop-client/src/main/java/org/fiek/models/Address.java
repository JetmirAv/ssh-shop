package org.fiek.models;

public class Address {
    final String tableName = "addresses";

    public int ID;
    public int user_id;
    public String street;
    public String postal;
    public String phone_number;
    public int city_id;

    public User user;
    public City city;

    public Address(int ID, int user_id, String street, String postal, String phone_number, int city_id) {
        this.ID = ID;
        this.user_id = user_id;
        this.street = street;
        this.postal = postal;
        this.phone_number = phone_number;
        this.city_id = city_id;
    }

    public Address() {
        this(-1, -1, "", "", "", -1);
    }

    public String getTableName() {
        return tableName;
    }

    public int getID() {
        return ID;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getCityId() {
        return city_id;
    }


    public void setCityId(int city_id) {
        this.city_id = city_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "ID=" + ID +
                ", user_id=" + user_id +
                ", street='" + street + '\'' +
                ", postal='" + postal + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", city_id=" + city_id +
                ", user=" + user +
                ", city=" + city +
                '}';
    }


}
