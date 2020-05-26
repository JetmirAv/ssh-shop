package org.fiek.controllers.profile;
/**
 * Sample Skeleton for 'index.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.fiek.App;
import org.fiek.models.User;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;

import static org.fiek.App.setRoot;

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
    void navClick(ActionEvent event) throws IOException {
        String id = ((Node) event.getSource()).getId();
        System.out.println(id);
        profileBox.getChildren().clear();

        removeActiveClass();

        switch (id) {
            case "navInfo":
                profileBox.getChildren().add(App.loadFXML("views/profile/info"));
                navInfo.getStyleClass().add("active");
                break;

            case "navPassword":
                profileBox.getChildren().add(App.loadFXML("views/profile/password"));
                navPassword.getStyleClass().add("active");
                break;

            case "navAddresses":
                profileBox.getChildren().add(App.loadFXML("views/profile/address"));
                navAddresses.getStyleClass().add("active");
                break;

            case "navCards":
                profileBox.getChildren().add(App.loadFXML("views/profile/cards"));
                navCards.getStyleClass().add("active");
                break;

        }

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

    void removeActiveClass() {
        navInfo.getStyleClass().removeAll("active");
        navPassword.getStyleClass().removeAll("active");
        navAddresses.getStyleClass().removeAll("active");
        navCards.getStyleClass().removeAll("active");
    }
}
