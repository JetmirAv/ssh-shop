package org.fiek.services.chat;

import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.store.chat.GetMessagesAction;
import org.fiek.utils.Ajax;

public class GetChannelMessagesService extends Service<Void> implements View {

    Integer channel_id;

    public GetChannelMessagesService(Integer channel_id) {
        this.channel_id = channel_id;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Ajax request = new Ajax();
                    JsonObject response = request.get("/channels/" + channel_id + "/messages");
                    String messages = response.get("messages").toString();
                    publishAction(new GetMessagesAction(messages));

                } catch (Exception exception) {
                    this.cancel();
                    exception.printStackTrace();
                }
                return null;
            }
        };
    }
}
