package org.fiek.socket;

import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.fiek.models.Message;
import org.fiek.store.chat.NewMessageAction;

public class ChatSocket implements View {

    public static String MESSAGE = "message";

    public static void emitMessage(Integer channel_id, Integer author_id, String message) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("channel_id", channel_id);
        jsonObject.addProperty("author_id", author_id);
        jsonObject.addProperty("content", message);

        SocketClient.socket.emit(MESSAGE, jsonObject);
    }

    public void onMessage(Socket socket) {
        socket.on(MESSAGE, e -> {
            publishAction(new NewMessageAction(e[0].toString()));
        });
    }
}
