package org.fiek.models;

public class City {
    final String tableName = "cities";

    public String name;
    public int country_id;

    public Country country;
    
    public City(
            String name,
            int country_id,
            Country country) {
        this.name = name;
        this.country_id = country_id;
        this.country = country;
    }
    
    public City() {
        this("", null, null);
    }

    public String getTableName() {
        return tableName;
    }
    
    public int getCountry_id() {
        return country_id;
    }
}
