package org.fiek.models;

import java.util.ArrayList;

public class Country {
    final String tableName = "countries";

    public String name;

    public ArrayList<City> cities;

    public Country(
            String name,
            ArrayList<City> cities) {
        this.name = name;
        this.cities = cities;
    }

    public Country() {
        this("", new ArrayList<City>());
    }

    public String getTableName() {
        return tableName;
    }
}
