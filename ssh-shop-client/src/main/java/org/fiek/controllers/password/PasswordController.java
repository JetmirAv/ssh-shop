package org.fiek.controllers.password;

import javafx.fxml.FXML;
import org.fiek.App;
import org.fiek.controllers.AbstractController;

import java.io.IOException;

public class PasswordController extends AbstractController {

    @FXML
    private void password() throws IOException {
        App.setRoot("views/password/password");
    }
}
