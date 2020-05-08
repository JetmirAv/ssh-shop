package org.fiek.controllers.profile;

import javafx.fxml.FXML;
import org.fiek.App;
import org.fiek.controllers.AbstractController;

import java.io.IOException;

public class AddressController extends AbstractController {

    @FXML
    private void address() throws IOException {
        App.setRoot("views/address/address");
    }

}
