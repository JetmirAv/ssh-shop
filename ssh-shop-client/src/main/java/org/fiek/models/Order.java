package org.fiek.models;

public class Order {
    final String tableName = "orders";

    public int id;
    public int user_id;
    public int card_id;
    public int address_id;
    public int variant_id;
    public int quantity;
    public float price;

    public User user;
    public Card card;
    public Address address;

    public Order(int id, int user_id, int card_id, int address_id, int variant_id, int quantity, float price) {
        this.id = id;
        this.user_id = user_id;
        this.card_id = card_id;
        this.address_id = address_id;
        this.variant_id = variant_id;
        this.quantity = quantity;
        this.price = price;
    }


    public Order() {
        this(-1, -1, -1, -1, -1, -1, -1);
    }

    public String getTableName() {
        return tableName;
    }

    public int getID() {
        return id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getCardId() {
        return card_id;
    }

    public void setCardId(int card_id) {
        this.card_id = card_id;
    }

    public int getAddressId() {
        return address_id;
    }

    public void setAddressId(int address_id) {
        this.address_id = address_id;
    }

    public int getVariantId() {
        return variant_id;
    }

    public void setVariantId(int variant_id) {
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
