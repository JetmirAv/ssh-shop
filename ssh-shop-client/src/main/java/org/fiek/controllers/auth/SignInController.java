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

import eu.lestard.fluxfx.View;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.fiek.App;
import org.fiek.controllers.AbstractController;
import org.fiek.controllers.layout.NoAuthLayoutController;
import org.fiek.store.auth.AddTokenAction;
import org.fiek.utils.Ajax;

public class SignInController extends AbstractController implements View {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="username"
    private JFXTextField username; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private JFXPasswordField password; // Value injected by FXMLLoader

    @FXML // fx:id="logIn"
    private JFXButton logIn; // Value injected by FXMLLoader

    @FXML // fx:id="register"
    private JFXButton register; // Value injected by FXMLLoader

    @FXML
    void registerHandler(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        NoAuthLayoutController.stage.setScene(new Scene(App.loadFXML("views/auth/sign-up")));
        NoAuthLayoutController.stage.setTitle("Sign up");
    }

    @FXML
    void logInHandler(ActionEvent event) throws IOException   {
        new Thread(logInRequest).start();
    }

    public static Stage stage = new Stage();

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'sign-in.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'sign-in.fxml'.";
        assert logIn != null : "fx:id=\"logIn\" was not injected: check your FXML file 'sign-in.fxml'.";
        assert register != null : "fx:id=\"register\" was not injected: check your FXML file 'sign-in.fxml'.";

    }

    Runnable logInRequest = new Runnable() {
        @Override
        public void run() {
            JsonObject json = new JsonObject();
//            json.addProperty("email", username.getText());
//            json.addProperty("password", password.getText());

            json.addProperty("email", "jetmir99@hotmail.com");
            json.addProperty("password", "password");
            Ajax request = new Ajax("/auth/login", Ajax.methods.POST, json.toString());
            String response = null;
            try {
                response = request.post();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            JsonObject object = JsonParser.parseString(response).getAsJsonObject();

            String user = object.get("user").toString();
            String token = object.get("token").toString();
            token = token.substring(1, token.length() - 1);

            publishAction(new AddTokenAction(token, user));

            Platform.runLater(() -> {
                System.out.println("Tash");
                NoAuthLayoutController.stage.close();
            });

        }
    };

}
