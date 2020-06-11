package org.fiek.services.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.App;
import org.fiek.socket.ChatSocket;
import org.fiek.socket.SocketClient;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.chat.GetMessagesAction;
import org.fiek.utils.Ajax;

import java.awt.*;
import java.net.URI;

public class RequestRoomService extends Service<Void> {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    AuthStore authStore = baseStore.getAuthStore();
    final Integer channel_id;
    final Integer author_id;


    public RequestRoomService(Integer channel_id, Integer author_id) {
        this.channel_id = channel_id;
        this.author_id = author_id;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Ajax request = new Ajax();
                    JsonObject response = request.get("/channels/" + channel_id + "/call");
                    response = response.getAsJsonObject("room");
                    String room_id = response.get("_id").toString();
                    room_id = room_id.substring(1, room_id.length() - 1);

                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        Desktop.getDesktop().browse(new URI("https://192.168.0.11:5000/channels/" + room_id + "/meet/?token=" + authStore.getToken()));

                        Thread.sleep(5000);

                        ChatSocket.callRequest(channel_id, authStore.getUser().getId(), room_id);

                    }

                } catch (Exception exception) {
                    this.cancel();
                    exception.printStackTrace();
                }
                return null;
            }
        };
    }
}
