package org.fiek.models;

import com.google.gson.annotations.Expose;

public class City {
    final String tableName = "cities";
    @Expose(serialize = false, deserialize = true)
    public int id;

    @Expose
    public String name;
    @Expose
    public int country_id;

    @Expose
    public Country country;

    public City(int id, String name, int country_id) {
        this.id = id;
        this.name = name;
        this.country_id = country_id;
    }

    public City() {
        this(-1, "", -1);
    }

    public String getTableName() {
        return tableName;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country_id=" + country_id +
                ", country=" + country +
                '}';
    }
}
