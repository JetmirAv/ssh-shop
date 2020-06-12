package org.fiek.services.auth;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.Card;
import org.fiek.store.auth.EditCardAction;
import org.fiek.utils.Ajax;

public class UpdateCardService extends Service<Void> implements View {

    private Card cardObj;

    public UpdateCardService(Card cardObj) {
        this.cardObj = cardObj;
    }

    private void updateCard() throws Exception {
        final String json1 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this.cardObj, Card.class);
        Ajax request = new Ajax();
        JsonObject response = request.patch("users/" + cardObj.getUserId() + "/cards/" + cardObj.getId(), json1);
        String jsonCard = response.get("card").toString();
        String jsonAddr = jsonCard.replaceAll("\\[", "").replaceAll("\\]", "");
        String jsonAddr1 = jsonAddr.replaceAll("},", "}},");
        String[] addr = jsonAddr1.split("},");
        String card = addr[0];
        publishAction(new EditCardAction(card));
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateCard();
                return null;
            }
        };
    }
}
