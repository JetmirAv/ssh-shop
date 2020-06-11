package org.fiek.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Variant {

    @Expose(serialize = false, deserialize = true)
    public  String _id;
    
    @Expose
    public String name;
    @Expose
    public ArrayList<String> options;


    public Variant(String id, String name,  ArrayList<String> options) {

        this._id = id;
        this.name = name;
        this.options = options;

    }

    public Variant() {
        this("", "", new ArrayList<String>());
    }

    public String getID() {
        return _id;
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

    public void setData(String name, ArrayList<String> options){
        this.name = name;
        this.options = options;
    }

    @Override
    public String toString() {
        return "Variant{" +
                "id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", options=" + options +
                '}';
    }
}
