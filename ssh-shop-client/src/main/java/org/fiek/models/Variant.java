package org.fiek.models;

import java.util.ArrayList;

public class Variant {

    public String id;
    public String name;

    public ArrayList<String> options;

    public Variant(String id, String name,  ArrayList<String> options) {
        this.id = id;
        this.name = name;
        this.options = options;
    }

    public Variant() {
        this("", "", new ArrayList<String>());
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Variant{" +
                "ID=" + id +
                "name" + name +
                "options" + options +
                '}';
    }


}

