package org.fiek.models;

public class Card {
    final String tableName = "cards";

    public int ID;
    public int user_id;
    public String number;
    public String exp_month;
    public String exp_year;
    public String code;

    public User user;

    public Card(int ID, int user_id, String number, String exp_month, String exp_year, String code, User user) {
        this.ID = ID;
        this.user_id = user_id;
        this.number = number;
        this.exp_month = exp_month;
        this.exp_year = exp_year;
        this.code = code;
        this.user = user;
    }

    public Card() {
        this(-1, -1, "", "", "", "", null);
    }

    public String getTableName() {
        return tableName;
    }

    public int getID() {
        return ID;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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
}
