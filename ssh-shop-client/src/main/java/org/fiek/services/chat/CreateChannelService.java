package org.fiek.services.chat;

import com.google.gson.JsonObject;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.utils.Ajax;

public class CreateChannelService extends Service<Void> {

    final Integer product_id;

    public CreateChannelService(Integer product_id) {
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
                    String rows = response.get("channels").toString();

                } catch (Exception exception) {
                    this.cancel();
                    exception.printStackTrace();
                }
                return null;
            }
        };
    }
}
