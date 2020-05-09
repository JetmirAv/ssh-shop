package org.fiek.models;
import java.util.ArrayList;


public class User {
    public String name;
    public int age;
    public ArrayList<String> emails;

    public User(String name, int age, ArrayList<String> emails) {
        this.name = name;
        this.age = age;
        this.emails = emails;
    }

    public User() {
        this("", 0, new ArrayList<>());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String email : emails) {
            sb.append(email);
            sb.append(",");
        }
        return String.format("name=%s age=%d emails=%s", name, age, sb.toString());
    }
}