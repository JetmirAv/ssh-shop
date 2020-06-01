package org.fiek.models;

public class City {
    final String tableName = "cities";

    public int ID;
    public String name;
    public int country_id;

    public Country country;

    public City(int ID, String name, int country_id) {
        this.ID = ID;
        this.name = name;
        this.country_id = country_id;
    }

    public City() {
        this(-1, "", -1);
    }

    public String getTableName() {
        return tableName;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return country_id;
    }

    public void setCountryId(int country_id) {
        this.country_id = country_id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }



}
