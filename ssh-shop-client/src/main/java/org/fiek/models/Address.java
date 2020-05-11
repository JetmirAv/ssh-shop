package org.fiek.models;

public class Address {
    final String tableName = "addresses";

    public int user_id;
    public String street;
    public String postal;
    public String phone_number;
    public int city_id;

    public User user;
    public City city;
    
    public Address(
            int user_id,
            String street,
            String postal,
            String phone_number,
            int city_id,
            User user,
            City city) {
        this.user_id = user_id;
        this.street = street;
        this.postal = postal;
        this.phone_number = phone_number;
        this.city_id = city_id;
        this.user = user;
        this.city = city;
    }
    
    public Address() {
        this(null, "", "", "", null, null, null);
    }


    public String getTableName() {
        return tableName;
    }
    
    public int getUser_id() {
        return user_id;
    }
    
    public int getCity_id() {
        return city_id;
    }
}
