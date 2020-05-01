package org.fiek.controllers.primary;

import java.io.IOException;
import javafx.fxml.FXML;
import org.fiek.App;

public class PrimaryController extends AbstractMethodError {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("views/secondary/secondary");
    }
}
