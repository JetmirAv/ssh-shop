package org.fiek.models;

import java.util.ArrayList;

public class Country {
    final String tableName = "countries";

    public int ID;
    public String name;

    public ArrayList<City> cities;

    public Country(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Country() {
        this(-1, "");
    }

    public String getTableName() {
        return tableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }
}
