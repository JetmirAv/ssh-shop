package org.fiek.controllers.secondary;

import java.io.IOException;
import javafx.fxml.FXML;
import org.fiek.App;
import org.fiek.controllers.AbstractController;

public class SecondaryController extends AbstractController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("views/primary/primary");
    }
}