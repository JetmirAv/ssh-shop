package org.fiek.services.chat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.Channel;
import org.fiek.store.chat.SetActiveChannelAction;
import org.fiek.utils.Ajax;

public class CreateChannelService extends Service<Void> implements View {

    final String product_id;

    public CreateChannelService(String product_id) {
        this.product_id = product_id;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Ajax request = new Ajax();
                    JsonObject response = request.get("/channels/" + product_id);
                    String rows = response.toString();
                    Gson gson = new Gson();
                    Channel channel = gson.fromJson(rows, Channel.class);
                    publishAction(new SetActiveChannelAction(channel));
                } catch (Exception exception) {
                    this.cancel();
                    exception.printStackTrace();
                }
                return null;
            }
        };
    }
}
