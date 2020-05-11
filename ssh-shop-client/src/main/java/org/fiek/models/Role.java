package org.fiek.models;

import java.util.ArrayList;

public class Role {
    final String tableName = "roles";

    public String name;
    public String description;

    public ArrayList<User> users;

    public Role(
            String name,
            String description,
            ArrayList<User> users) {
        this.name = name;
        this.description = description;
        this.users = users;
    }

    public Role() {
        this("", "", new ArrayList<User>());
    }

    public String getTableName() {
        return tableName;
    }


}
