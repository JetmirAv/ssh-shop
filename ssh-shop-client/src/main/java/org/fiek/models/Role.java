package org.fiek.models;

import java.util.ArrayList;

public class Role {
    final String tableName = "roles";

    public int ID;
    public String name;
    public String description;

    public ArrayList<User> users;

    public Role(int ID, String name, String description) {
        this.ID = ID;
        this.name = name;
        this.description = description;
    }

    public Role() {
        this(-1, "", "");
    }

    public String getTableName() {
        return tableName;
    }

    public int getID() {
        return ID;
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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
