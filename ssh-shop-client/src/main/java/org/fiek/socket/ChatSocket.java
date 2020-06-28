package org.fiek.socket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import eu.lestard.fluxfx.View;
import io.socket.client.Socket;
import org.fiek.App;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.chat.NewMessageAction;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ChatSocket implements View {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    AuthStore authStore = baseStore.getAuthStore();

    final public static String MESSAGE = "message";
    final public static String NEW_MESSAGE = "new-message";
    final public static String CALL_REQUEST = "call-request";
    final public static String INCOMING_CALL = "incoming-call";

    public static void emitMessage(Integer channel_id, Integer author_id, String message) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("channel_id", channel_id);
        jsonObject.addProperty("author_id", author_id);
        jsonObject.addProperty("content", message);

        SocketClient.socket.emit(MESSAGE, jsonObject);
    }

    public void onMessage(Socket socket) {
        socket.on(NEW_MESSAGE, e -> {
            publishAction(new NewMessageAction(e[0].toString()));
        });
    }

    public void onIncomingCall(Socket socket) {
        socket.on(INCOMING_CALL, e -> {
//            {"room_id":"5ee1247ba19f0334d4f835b6"}

            JsonObject json = (JsonObject)  JsonParser.parseString(e[0].toString());

            String room_id = json.get("room_id").toString();

            room_id = room_id.substring(1, room_id.length() - 1);


            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI("https://192.168.43.67:5000/channels/" + room_id + "/meet/?token=" + authStore.getToken()));
                } catch (IOException | URISyntaxException exception) {
                    exception.printStackTrace();
                }
            }

        });

    }

    public static void callRequest(Integer channel_id, Integer author_id, String room_id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("channel_id", channel_id);
        jsonObject.addProperty("author_id", author_id);
        jsonObject.addProperty("room_id", room_id);

        SocketClient.socket.emit(CALL_REQUEST, jsonObject);
    }

}
