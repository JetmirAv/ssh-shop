package org.fiek.controllers.secondary;

import java.io.IOException;
import javafx.fxml.FXML;
import org.fiek.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("Primary.primary");
    }
}