package org.fiek.socket;

import com.google.gson.JsonObject;

import java.net.Socket;

public abstract class AuthSocket {

    public static String SIGN_IN = "sign_in";

    public static void onSignIn(Integer user_id) {

        System.out.println("AuthSocket");

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", user_id);
        SocketClient.socket.emit(SIGN_IN, jsonObject.toString());
    }

}
