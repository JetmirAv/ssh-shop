package org.fiek.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.Expose;

public class Product {

    @Expose(serialize = false, deserialize = true)
    final String tableName = "products";

    @Expose(serialize = false, deserialize = true)
    public String _id;

    @Expose(serialize = true, deserialize = true)
    public ArrayList<Variant> variants;

//    @Expose(serialize = false, deserialize = true)
//    ArrayList<Combinations> combination;
//
    @Expose
    private ArrayList<Map<String, String>> combinations;

    @Expose
    public int user_id;

    @Expose
    public String name;

    @Expose
    public String description;

    @Expose
    public int category_id;

    @Expose
    public float discount_pt;

    @Expose
    public User user;

    @Expose
    public Category category;


    @Expose
    private ArrayList<Map<String, String>> combination;


    public Product(String id, int user_id, String name, String description, int category_id, float discount_pt,
                   ArrayList<Variant> variants, ArrayList<Map<String, String>> combinations) {
        this._id = id;
        this.user_id = user_id;
        this.name = name;
        this.description = description;
        this.category_id = category_id;
        this.discount_pt = discount_pt;
        this.variants = new ArrayList<Variant>();
        this.combinations = new ArrayList<Map<String, String>>();;
    }


    public Product() {
        this("", -1, "", "", -1, -1, null, null);
    }

    public String getTableName() {
        return tableName;
    }

    public String getId() {
        return _id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return category_id;
    }

    public void setCategoryId(int category_id) {
        this.category_id = category_id;
    }

    public float getDiscount_pt() {
        return discount_pt;
    }

    public void setDiscount_pt(float discount_pt) {
        this.discount_pt = discount_pt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<Variant> getVariant() {
        return variants;
    }

    public void setVariant(ArrayList<Variant> variants) {
        this.variants = variants;
    }
    public ArrayList<Map<String, String>> getCombination() {
        return combinations;
    }

    public void setCombination(ArrayList<Map<String, String>> combinations) {
        this.combinations = combinations;
    }

    @Override
    public String toString() {
        return "Product{" +
                "_id='" + _id + '\'' +
                ", variants=" + variants +
                ", combinations=" + combinations +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category_id=" + category_id +
                ", discount_pt=" + discount_pt +
                ", user=" + user +
                ", category=" + category +
                '}';
    }
}