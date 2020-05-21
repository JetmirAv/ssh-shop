package org.fiek.controllers.modal;

/**
 * Sample Skeleton for 'modal.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.fiek.App;
import org.fiek.controllers.AbstractController;

public class ModalController extends AbstractController {

    private String messageText;
    private Boolean isError;

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setError(Boolean error) {
        isError = error;
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="parent"
    private AnchorPane parent; // Value injected by FXMLLoader

    @FXML // fx:id="button"
    private JFXButton button; // Value injected by FXMLLoader

    @FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader

    @FXML // fx:id="message"
    private Label message; // Value injected by FXMLLoader

    @FXML
    void onClickHandler(ActionEvent event) {
        Scene scene = parent.getScene();
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }

    Image imageFile;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws FileNotFoundException, URISyntaxException {
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'modal.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'modal.fxml'.";
        assert message != null : "fx:id=\"message\" was not injected: check your FXML file 'modal.fxml'.";

        message.setText(messageText);

        FileInputStream fileInputStream;
        if (isError) {
            fileInputStream = new FileInputStream(App.class.getResource("images/error.png").getPath());
        } else {
            fileInputStream = new FileInputStream(App.class.getResource("images/tick.png").getPath());
        }
        imageFile = new Image(fileInputStream);

        image.setImage(imageFile);

    }
}
