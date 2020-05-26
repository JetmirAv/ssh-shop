package org.fiek.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;


public class User implements Cloneable {
    final String tableName = "users";

    public enum Gender {
        MALE,
        FEMALE
    }

    @Expose(serialize = false, deserialize = true)
    public int id;

    @Expose(serialize = false, deserialize = true)
    public int role_id;

    @Expose
    public String first_name;
    @Expose
    public String last_name;
    @Expose
    public String email;
    @Expose
    public String password;
    @Expose
    public String forgot_password_token;
    @Expose
    public String birthdate;
    @Expose
    public Gender gender;
    @Expose
    public String avatar;
    @Expose
    public String phone_number;

    @Expose(serialize = false, deserialize = true)
    public Role role;
    @Expose(serialize = false, deserialize = true)
    public ArrayList<Card> cards;
    @Expose(serialize = false, deserialize = true)
    public ArrayList<Address> addresses;
    @Expose(serialize = false, deserialize = true)
    public ArrayList<Wishlist> wishlists;
    @Expose(serialize = false, deserialize = true)
    public ArrayList<Cart> carts;
    @Expose(serialize = false, deserialize = true)
    public ArrayList<Product> products;
    @Expose(serialize = false, deserialize = true)
    public ArrayList<Channel> channels;


    public User(int id, int role_id, String first_name, String last_name, String email,
                String password, String forgot_password_token,
                String birthdate, Gender gender, String avatar, String phone_number, Role role) {
        this.id = id;
        this.role_id = role_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.forgot_password_token = forgot_password_token;
        this.birthdate = birthdate;
        this.gender = gender;
        this.avatar = avatar;
        this.phone_number = phone_number;
        this.role = role;
    }

    public User() {
        this(-1, -1, "", "",
                "", "", "",
                "", null, "", "", null);
    }

    public String getTableName() {
        return tableName;
    }

    public int getId() {
        return id;
    }

    public int getRoleId() {
        return role_id;
    }

    public void setRoleId(int role_id) {
        this.role_id = role_id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getForgotPasswordToken() {
        return forgot_password_token;
    }

    public void setForgotPasswordToken(String forgot_password_token) {
        this.forgot_password_token = forgot_password_token;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        System.out.println("Avatar: " + avatar);
        this.avatar = avatar;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    public ArrayList<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(ArrayList<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }

    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<Cart> carts) {
        this.carts = carts;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<Channel> channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return this.first_name + " " + this.last_name + " " + this.email + " " + this.password + " " + this.phone_number + " " + this.gender + " " + this.birthdate + " " + this.avatar;
    }

    @Override
    protected User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
}
