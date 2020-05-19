/**
 * Sample Skeleton for 'sign-up.fxml' Controller Class
 */

package org.fiek.controllers.auth;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.fiek.controllers.layout.NoAuthLayoutController;

public class RegisterController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="imageSelector"
    private ImageView imageSelector; // Value injected by FXMLLoader

    @FXML // fx:id="ImageSelectorLabel"
    private Label imageSelectorLabel; // Value injected by FXMLLoader

    @FXML
    void selectImage(MouseEvent event) throws FileNotFoundException {

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        FileChooser imageChooser = new FileChooser();
        imageChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Images", "*.jpg")
        );
        File selectedImage = imageChooser.showOpenDialog(null);
        String imagePath = selectedImage.getPath();
        System.out.println(imagePath);
        FileInputStream imageHandler = new FileInputStream(imagePath);
        Image image = new Image(imageHandler);
        imageSelector.setImage(image);
        imageSelector.setPreserveRatio(false);
        imageSelectorLabel.setText("");



    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert imageSelector != null : "fx:id=\"imageSelector\" was not injected: check your FXML file 'sign-up.fxml'.";

    }
    @FXML
    void clickHandler(ActionEvent event) throws IOException {
        NoAuthLayoutController.openSignIn();
    }
}
