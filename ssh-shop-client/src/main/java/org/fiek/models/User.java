package org.fiek.models;
import java.util.ArrayList;


public class User {
    final String tableName = "users";
    public enum Gender {MALE, FEMALE};

    public int ID;
    public int role_id;
    public String first_name;
    public String last_name;
    public String email;
    public String password;
    public String forgot_password_token;
    public String birthdate;
    public Gender gender;
    public String avatar;
    public String phone_number;

    public Role role;
    public ArrayList<Card> cards;
    public ArrayList<Address> addresses;
    public ArrayList<Wishlist> wishlists;
    public ArrayList<Cart> carts;
    public ArrayList<Product> products;
    public ArrayList<Channel> channels;


    public User(int ID, int role_id, String first_name, String last_name, String email,
                String password, String forgot_password_token,
                String birthdate, Gender gender, String avatar, String phone_number, Role role) {
        this.ID = ID;
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
        this(-1,-1,"","",
                "","","",
                "",null,"","",null);
    }

    public String getTableName() {
        return tableName;
    }

    public int getID() {
        return ID;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
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

    public String getForgot_password_token() {
        return forgot_password_token;
    }

    public void setForgot_password_token(String forgot_password_token) {
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
        this.avatar = avatar;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
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

}
