package org.fiek.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Combinations {
    private ArrayList<Map<String, String>> combination;
    private float price;
    private int stock;
    private String photo;

    public Combinations(ArrayList<Map<String, String>> combination) {
        this.combination = combination;
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


}





