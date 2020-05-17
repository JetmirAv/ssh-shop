package org.fiek.controllers.profile;

import javafx.fxml.FXML;
import org.fiek.App;
import org.fiek.controllers.AbstractController;

import java.io.IOException;

public class InfoController extends AbstractController {


    @FXML
    private void profile() throws IOException {
        App.setRoot("views/profile/info");
    }

}
