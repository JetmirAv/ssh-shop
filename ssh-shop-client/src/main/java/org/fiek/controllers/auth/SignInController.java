package org.fiek.controllers.auth;

/**
 * Sample Skeleton for 'sign-in.fxml' Controller Class
 */

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.fiek.App;
import org.fiek.controllers.AbstractController;
import org.fiek.controllers.layout.NoAuthLayoutController;
import org.fiek.services.auth.LogInService;
import org.fiek.utils.Loading;

public class SignInController extends AbstractController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="root"
    private AnchorPane root; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private JFXTextField username; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private JFXPasswordField password; // Value injected by FXMLLoader

    @FXML // fx:id="logIn"
    private JFXButton logIn; // Value injected by FXMLLoader

    Loading loading;

    @FXML
    void registerHandler(ActionEvent event) throws IOException {
        NoAuthLayoutController.stage.setScene(new Scene(App.loadFXML("views/auth/sign-up")));
        NoAuthLayoutController.stage.setTitle("Sign up");
    }

    @FXML
    void logInHandler(ActionEvent event) {

        LogInService logInService = new LogInService(username.getText(), password.getText());
        logInService.start();

        logInService.setOnRunning(e -> {
            loading = new Loading();
            root.getChildren().add(loading);
        });
        logInService.setOnSucceeded(e -> {
            NoAuthLayoutController.stage.close();
            root.getChildren().remove(loading);
        });

        logInService.setOnFailed(e -> {
            root.getChildren().remove(loading);
        });

        logInService.setOnCancelled(e -> {
            root.getChildren().remove(loading);
        });

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'sign-in.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'sign-in.fxml'.";
        assert logIn != null : "fx:id=\"logIn\" was not injected: check your FXML file 'sign-in.fxml'.";

    }


}
