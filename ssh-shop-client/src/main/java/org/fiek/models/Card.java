package org.fiek.models;

public class Card {
    final String tableName = "cards";

    public int user_id;
    public String number;
    public String exp_month;
    public String exp_year;
    public String code;

    public User user;

    public Card(
            int user_id,
            String number,
            String exp_month,
            String exp_year,
            String code,
            User user) {
        this.user_id = user_id;
        this.number = number;
        this.exp_month = exp_month;
        this.exp_year = exp_year;
        this.code = code;
        this.user = user;
    }

    public Card() {
        this(null, "", "", "", "", null);
    }

    public String getTableName() {
        return tableName;
    }

    public int getUser_id() {
        return user_id;
    }

}
