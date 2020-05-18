package org.fiek.controllers.profile;
/**
 * Sample Skeleton for 'index.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.fiek.App;
import org.fiek.models.User;
import org.fiek.store.auth.AuthStore;

public class ProfileController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="navInfo"
    private Button navInfo; // Value injected by FXMLLoader

    @FXML // fx:id="navPassword"
    private Button navPassword; // Value injected by FXMLLoader

    @FXML // fx:id="navAddresses"
    private Button navAddresses; // Value injected by FXMLLoader

    @FXML // fx:id="navCards"
    private Button navCards; // Value injected by FXMLLoader

    @FXML // fx:id="profileBox"
    private Pane profileBox; // Value injected by FXMLLoader

    @FXML
    void navClick(ActionEvent event) {

    }

    private final AuthStore authStore;

    public ProfileController(AuthStore authStore) {
        this.authStore = authStore;
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        assert navInfo != null : "fx:id=\"navInfo\" was not injected: check your FXML file 'index.fxml'.";
        assert navPassword != null : "fx:id=\"navPassword\" was not injected: check your FXML file 'index.fxml'.";
        assert navAddresses != null : "fx:id=\"navAddresses\" was not injected: check your FXML file 'index.fxml'.";
        assert navCards != null : "fx:id=\"navCards\" was not injected: check your FXML file 'index.fxml'.";
        assert profileBox != null : "fx:id=\"profileBox\" was not injected: check your FXML file 'index.fxml'.";
        profileBox.getChildren().add(App.loadFXML("views/profile/info"));
    }
}
