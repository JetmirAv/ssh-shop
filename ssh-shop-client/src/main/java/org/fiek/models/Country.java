package org.fiek.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Country {
    final String tableName = "countries";

    @Expose(serialize = false, deserialize = true)
    public int id;
    @Expose
    public String name;
    @Expose
    public ArrayList<City> cities;

    public int getId() {
        return id;
    }

    public Country(int id, String name) {
        this.id = id;
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

    @Override
    public String toString() {
        return this.name;
    }
}
