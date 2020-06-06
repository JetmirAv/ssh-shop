package org.fiek.services.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.store.auth.AddTokenAction;
import org.fiek.store.chat.AddChannelsAction;
import org.fiek.utils.Ajax;

public class FindAndCountChannelsService extends Service<Void> implements View {

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Ajax request = new Ajax();
                    JsonObject response = request.get("/channels");
                    response = response.get("channels").getAsJsonObject();
                    String rows = response.get("rows").toString();
                    String count = response.get("count").toString();

                    publishAction(new AddChannelsAction(rows, count));

                } catch (Exception exception) {
                    this.cancel();
                    exception.printStackTrace();
                }
                return null;
            }
        };
    }
}
