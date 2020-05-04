package org.fiek.controllers.cards;

import javafx.fxml.FXML;
import org.fiek.App;
import org.fiek.controllers.AbstractController;

import java.io.IOException;

public class CardsController extends AbstractController {

    @FXML
    private void cards() throws IOException {
        App.setRoot("views/cards/cards");
    }
}
