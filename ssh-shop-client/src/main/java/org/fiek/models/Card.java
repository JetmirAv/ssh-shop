package org.fiek.models;

import com.google.gson.annotations.Expose;

public class Card {
    final String tableName = "cards";
    @Expose(serialize = false, deserialize = true)
    public int id;
    @Expose
    public int user_id;
    @Expose
    public String number;

    public void setId(int id) {
        this.id = id;
    }

    @Expose
    public String exp_month;
    @Expose
    public String exp_year;
    @Expose
    public String code;

    @Expose(serialize = false, deserialize = true)
    public User user;

    public Card(int id, int user_id, String number, String exp_month, String exp_year, String code) {
        this.id = id;
        this.user_id = user_id;
        this.number = number;
        this.exp_month = exp_month;
        this.exp_year = exp_year;
        this.code = code;
    }

    public Card() {
        this(-1, -1, "", "", "", "");
    }

    public String getTableName() {
        return tableName;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExp_month() {
        return exp_month;
    }

    public void setExp_month(String exp_month) {
        this.exp_month = exp_month;
    }

    public String getExp_year() {
        return exp_year;
    }

    public void setExp_year(String exp_year) {
        this.exp_year = exp_year;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", number='" + number + '\'' +
                ", exp_month='" + exp_month + '\'' +
                ", exp_year='" + exp_year + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
