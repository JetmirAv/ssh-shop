package org.fiek.controllers.layout;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.fiek.App;
import org.fiek.controllers.AbstractController;

public class NoAuthLayoutController extends AbstractController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="profile"
    private HBox profile; // Value injected by FXMLLoader

    public static Stage stage = new Stage();

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'no-auth.fxml'.";
        openSignIn();
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    @FXML
    public void clickHandler(MouseEvent mouseEvent) throws IOException  {
        openSignIn();
        stage.showAndWait();

    }
    public static void openSignIn() throws IOException {
        stage.setScene(new Scene(App.loadFXML("views/auth/sign-in")));

        stage.setTitle("Sign in");
    }

}
