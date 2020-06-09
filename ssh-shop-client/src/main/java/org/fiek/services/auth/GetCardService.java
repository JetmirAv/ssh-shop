package org.fiek.services.auth;

import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.store.auth.GetCardAction;
import org.fiek.utils.Ajax;

public class GetCardService extends Service<Void> implements View {

    private int id;
    private int userId;

    public GetCardService(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    private void getCardById() throws Exception {
        Ajax request = new Ajax();
        JsonObject response = request.getAsJson("/users/" + userId + "/cards    /" + id);
        String jsonCard = response.get("card").toString();
        publishAction(new GetCardAction(jsonCard));
    }


    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                getCardById();
                return null;
            }
        };
    }
}