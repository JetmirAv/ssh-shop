package org.fiek.services;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.fiek.models.User;
import org.fiek.utils.Ajax;
import org.fiek.utils.Tuple;

import java.io.IOException;

public abstract class AuthServices {

    public static Tuple<String, String> register(User user) throws IOException {
        final String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(user, User.class);

        Ajax request = new Ajax("auth/register", Ajax.methods.POST, json);
        JsonObject response = request.post();

        String jsonUser = response.get("user").toString();
        String jsonToken = response.get("token").toString();
        jsonToken = jsonToken.substring(1, jsonToken.length() - 1);

        System.out.println("User: " + jsonUser);
        System.out.println("Token: " + jsonToken);

        return new Tuple<>(jsonUser, jsonToken);
    }

}
