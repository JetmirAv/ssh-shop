package org.fiek.services.auth;

import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.Address;
import org.fiek.models.User;
import org.fiek.store.auth.AddAddressAction;
import org.fiek.store.auth.AddCardAction;
import org.fiek.utils.Ajax;

import java.util.ArrayList;
import java.util.Arrays;

public class CardService extends Service<Void> implements View {
    private User user;
    public CardService(User user) {
        this.user = user;
    }

    private void getAllCards() throws Exception {
        Ajax request = new Ajax();
        JsonObject response = request.getAsJson("users/" + user.getId() + "/cards");
        String jsonAddress = response.get("card").toString();
        String jsonAddr = jsonAddress.replaceAll("\\[", "").replaceAll("\\]", "");
        String jsonAddr1 = jsonAddr.replaceAll("},", "}},");
        String[] cards = jsonAddr1.split("},");
        System.out.println("Cards:" + Arrays.toString(cards));
        publishAction(new AddCardAction(cards));
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                getAllCards();
                return null;
            }
        };
    }
}
