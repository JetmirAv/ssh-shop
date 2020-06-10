package org.fiek.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Combinations {
    @Expose
    private ArrayList<Map<String, String>> combination;
    @Expose
    private float price;
    @Expose
    private int stock;
    @Expose
    private String photo;
    @Expose
    Map<String, Object> attributes;

    public Combinations(ArrayList<Map<String, String>> combination) {
        this.combination = combination;
    }


    public Combinations(Map<String, Object> attributes) {
        this.attributes = attributes;
    }


    public ArrayList<Map<String, String>> getCombinations(){
        return combination;
    }

    public void setCombinations(ArrayList<Map<String, String>> combinations){
        this.combination = combinations;
    }

    public float getPrice(){
        return this.price;
    }
    public void setPrice(Float price){
        this.price = price;
    }

    public int getStock(){
        return this.stock;
    }
    public void setStock(int stock){
        this.stock = stock;
    }

    public String getPhoto(){
        return this.photo;
    }
    public void setPhoto(String photo){
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Combinations{" +
                "attributes=" + attributes +
                '}';
    }
}
