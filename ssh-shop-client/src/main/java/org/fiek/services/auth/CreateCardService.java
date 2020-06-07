package org.fiek.services.auth;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import eu.lestard.easydi.EasyDI;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.Address;
import org.fiek.models.Card;
import org.fiek.models.User;
import org.fiek.store.auth.AddAddressAction;
import org.fiek.store.auth.AddTokenAction;
import org.fiek.store.auth.EditAddressAction;
import org.fiek.store.auth.EditCardAction;
import org.fiek.utils.Ajax;
import org.fiek.utils.Tuple;

import java.io.IOException;

public class CreateCardService extends Service<Void> implements View {
    private int userId;
    private Card card;

    public CreateCardService(int userId, Card card) {
        this.userId = userId;
        this.card = card;
    }

    public void createCard() throws Exception {
        final String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this.card, Card.class);
        Ajax request = new Ajax();
        JsonObject response = request.post("users/" + userId + "/cards", json);
        String jsonAddress = response.get("card").toString();
        String jsonAddr = jsonAddress.replaceAll("\\[", "").replaceAll("\\]", "");
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
                createCard();
                return null;
            }
        };
    }
}
