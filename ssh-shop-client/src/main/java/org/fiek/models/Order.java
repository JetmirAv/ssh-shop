package org.fiek.models;

public class Order {
    final String tableName = "orders";

    public int ID;
    public int user_id;
    public int card_id;
    public int address_id;
    public int variant_id;
    public int quantity;
    public float price;

    public User user;
    public Card card;
    public Address address;

    public Order(int ID,
                 int user_id,
                 int card_id,
                 int address_id,
                 int variant_id,
                 int quantity,
                 float price,
                 User user,
                 Card card,
                 Address address) {
        this.ID = ID;
        this.user_id = user_id;
        this.card_id = card_id;
        this.address_id = address_id;
        this.variant_id = variant_id;
        this.quantity = quantity;
        this.price = price;
        this.user = user;
        this.card = card;
        this.address = address;
    }


    public Order() {
        this(-1, -1, -1, -1, -1, -1, -1, null, null, null);
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

    public int getCard_id() {
    return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getVariant_id() {
        return variant_id;
    }

    public void setVariant_id(int variant_id) {
        this.variant_id = variant_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
