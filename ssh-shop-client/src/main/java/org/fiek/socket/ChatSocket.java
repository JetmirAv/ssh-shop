package org.fiek.socket;

import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import io.socket.client.Socket;

public abstract class ChatSocket implements View {

    public static String MESSAGE = "message";

    public static void emitMessage(Integer channel_id, Integer author_id, String message) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("channel_id", channel_id);
        jsonObject.addProperty("author_id", author_id);
        jsonObject.addProperty("message", message);

        SocketClient.socket.emit(MESSAGE, jsonObject.toString());
    }

    public static void onMessage(Socket socket) {
        socket.on(MESSAGE, objects -> {

        });
    }

}
