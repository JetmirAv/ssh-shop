package org.fiek.controllers.layout;


/**
 * Sample Skeleton for 'profile.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.fiek.App;
import org.fiek.controllers.AbstractController;
import org.fiek.models.User;
import org.fiek.store.auth.AuthStore;

public class LayoutProfileController extends AbstractController {

    AuthStore authStore = AuthStore.getInstance();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="profile"
    private HBox profile; // Value injected by FXMLLoader

    @FXML // fx:id="firstName"
    private Label firstName; // Value injected by FXMLLoader

    @FXML // fx:id="lastName"
    private Label lastName; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'profile.fxml'.";
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'profile.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'profile.fxml'.";
//
//        firstName.textProperty().bind(new SimpleStringProperty(authStore.getUser().getFirstName()));
//        lastName.textProperty().bind(new SimpleStringProperty(authStore.getUser().getLastName()));

        firstName.textProperty().bind(Bindings.createStringBinding(() -> {

            System.out.println("Log user: " + authStore.getUser());
            System.out.println("Log token: " + authStore.getToken());

            authStore.getTokenSource().supply(() -> {
                System.out.println("palidhje");
                return "Jetmir";
            });

            if (authStore.getUser() != null) {
                System.out.println("Not null");
            } else {
                System.out.println("Null");
            }


            return "Jetmir";
        }));

        authStore.getUserSource().subscribe(this::profile);
    }

    private void profile(User user) {
        if (user != null) {
            System.out.println("Profile asfas");
            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
        }
    }


    @FXML
    public void navClick() throws IOException {
        BorderPane parent = (BorderPane) profile.getParent().getParent();
        ScrollPane scrollPane = (ScrollPane) ((AnchorPane) parent.getCenter()).getChildren().get(0);
        scrollPane.setContent(null);
        scrollPane.setContent(App.loadFXML("views/profile/index"));
    }

}
