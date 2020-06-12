/**
 * Sample Skeleton for 'password.fxml' Controller Class
 */

package org.fiek.controllers.profile;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.fiek.controllers.AbstractController;
import org.fiek.services.auth.PasswordService;
import org.fiek.utils.Loading;

public class PasswordController extends AbstractController {

    @FXML // fx:id="root"
    private AnchorPane root; // Value injected by FXMLLoader

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="oldPasswordId"
    private JFXPasswordField oldPasswordId; // Value injected by FXMLLoader

    @FXML // fx:id="newPasswordId"
    private JFXPasswordField newPasswordId; // Value injected by FXMLLoader

    @FXML // fx:id="confirmPasswordId"
    private JFXPasswordField confirmPasswordId; // Value injected by FXMLLoader

    @FXML // fx:id="saveBttnId"
    private JFXButton saveBttnId; // Value injected by FXMLLoader

    @FXML // fx:id="deleteBttnId"
    private JFXButton deleteBttnId; // Value injected by FXMLLoader

    Loading loading;


    @FXML
    void editPasswordHandler(ActionEvent event) {

        PasswordService passwordService = new PasswordService
                (oldPasswordId.getText(), newPasswordId.getText(),
                confirmPasswordId.getText());
        passwordService.start();

        passwordService.setOnRunning(e -> {
            loading = new Loading();
            root.getChildren().add(loading);
        });
        passwordService.setOnSucceeded(e -> {
            root.getChildren().remove(loading);
        });

        passwordService.setOnFailed(e -> {
            root.getChildren().remove(loading);
        });

        passwordService.setOnCancelled(e -> {
            root.getChildren().remove(loading);
        });
    }




    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert oldPasswordId != null : "fx:id=\"oldPasswordId\" was not injected: check your FXML file 'password.fxml'.";
        assert newPasswordId != null : "fx:id=\"newPasswordId\" was not injected: check your FXML file 'password.fxml'.";
        assert confirmPasswordId != null : "fx:id=\"confirmPasswordId\" was not injected: check your FXML file 'password.fxml'.";
        assert saveBttnId != null : "fx:id=\"saveBttnId\" was not injected: check your FXML file 'password.fxml'.";
        assert deleteBttnId != null : "fx:id=\"deleteBttnId\" was not injected: check your FXML file 'password.fxml'.";

    }
}
