/**
 * Sample Skeleton for 'sign-up.fxml' Controller Class
 */

package org.fiek.controllers.auth;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import eu.lestard.fluxfx.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.fiek.App;
import org.fiek.controllers.layout.NoAuthLayoutController;
import org.fiek.models.User;
import org.fiek.services.auth.RegisterService;
import org.fiek.store.auth.AddTokenAction;
import org.fiek.utils.ImageUploadHandler;
import org.fiek.utils.Tuple;
import org.fiek.utils.Utils;

public class RegisterController implements View {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="root"
    private AnchorPane root; // Value injected by FXMLLoader

    @FXML // fx:id="firstName"
    private JFXTextField firstName; // Value injected by FXMLLoader

    @FXML // fx:id="lastName"
    private JFXTextField lastName; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private JFXTextField email; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private JFXPasswordField password; // Value injected by FXMLLoader

    @FXML // fx:id="gender"
    private JFXComboBox<User.Gender> gender; // Value injected by FXMLLoader

    @FXML // fx:id="phoneNumber"
    private JFXTextField phoneNumber; // Value injected by FXMLLoader

    @FXML // fx:id="imageSelector"
    private ImageView imageSelector; // Value injected by FXMLLoader

    @FXML // fx:id="imageSelectorLabel"
    private Label imageSelectorLabel; // Value injected by FXMLLoader

    @FXML // fx:id="birthdate"
    private DatePicker birthdate; // Value injected by FXMLLoader




    @FXML
    void selectImage(MouseEvent event) throws FileNotFoundException {
        ImageUploadHandler imageUploadHandler = new ImageUploadHandler();
        Tuple<Image, String> customImage = imageUploadHandler.uploadImage();
        user.setAvatar(customImage.getSecond());
        imageSelector.setImage(customImage.getFirst());
        imageSelector.setPreserveRatio(false);
        imageSelectorLabel.setVisible(false);
    }

    User user;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'sign-up.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'sign-up.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'sign-up.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'sign-up.fxml'.";
        assert gender != null : "fx:id=\"gender\" was not injected: check your FXML file 'sign-up.fxml'.";
        assert phoneNumber != null : "fx:id=\"phoneNumber\" was not injected: check your FXML file 'sign-up.fxml'.";
        assert imageSelector != null : "fx:id=\"imageSelector\" was not injected: check your FXML file 'sign-up.fxml'.";
        assert imageSelectorLabel != null : "fx:id=\"imageSelectorLabel\" was not injected: check your FXML file 'sign-up.fxml'.";
        assert birthdate != null : "fx:id=\"birthdate\" was not injected: check your FXML file 'sign-up.fxml'.";
        user = new User();
        gender.getItems().addAll(User.Gender.values());

        Pane loading = new Pane(App.loadFXML("views/loading/loading"));
        root.getChildren().addAll(loading);
        AnchorPane.setBottomAnchor(loading, 0.0);
        AnchorPane.setTopAnchor(loading, 0.0);
        AnchorPane.setLeftAnchor(loading, 0.0);
        AnchorPane.setRightAnchor(loading, 0.0);



    }

    @FXML
    void clickHandler(ActionEvent event) throws IOException {
        NoAuthLayoutController.openSignIn();
    }

    @FXML
    void registerHandler(ActionEvent event) {
        user.setFirstName(firstName.getText());
        user.setLastName(lastName.getText());
        user.setEmail(email.getText());
        user.setPassword(password.getText());
        user.setGender(gender.getValue());
        user.setPhoneNumber(phoneNumber.getText());
        user.setBirthdate(Utils.formatDate(birthdate.getValue()));

        RegisterService registerService = new RegisterService(user);
        registerService.start();
        registerService.setOnRunning(e  -> {

        });
        registerService.setOnSucceeded(e -> {
            System.out.println("Tash");
            Tuple<String, String> response = registerService.getResponse();
            publishAction(new AddTokenAction(response.getSecond(), response.getFirst()));
            NoAuthLayoutController.stage.close();
        });


    }

//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            try {
////                Tuple<String, String> response = AuthServices.register(user);
//
//
//                Platform.runLater(() -> {
//
//                });
//
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//        }
//    };

}
