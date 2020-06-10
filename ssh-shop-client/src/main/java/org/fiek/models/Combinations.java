package org.fiek.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Combinations {

    @Expose(serialize = false, deserialize = true)
    private String _id;
    @Expose
    private int price;
    @Expose
    private int stock;
    @Expose
    private String photo;
    @Expose
    List<Map<String, String>> combination;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Map<String, String>> getCombination() {
        return combination;
    }

    public void setCombination(List<Map<String, String>> combination) {
        this.combination = combination;
    }

    @Override
    public String toString() {
        return "Combinations{" +
                "_id='" + _id + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", photo='" + photo + '\'' +
                '}';
    }
}
