/**
 * Sample Skeleton for 'info.fxml' Controller Class
 */

package org.fiek.controllers.profile;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import eu.lestard.fluxfx.Action;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.fiek.controllers.layout.NoAuthLayoutController;
import org.fiek.models.User;
import org.fiek.services.auth.InfoService;
import org.fiek.services.auth.LogInService;
import org.fiek.store.auth.AuthStore;
import org.fiek.utils.ImageUploadHandler;
import org.fiek.utils.Loading;
import org.fiek.utils.Tuple;

public class InfoController {

    private final AuthStore authStore;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="changeAvatarId"
    private JFXButton changeAvatarId; // Value injected by FXMLLoader

    @FXML // fx:id="firstNameId"
    private JFXTextField firstNameId; // Value injected by FXMLLoader

    @FXML // fx:id="lastNameId"
    private JFXTextField lastNameId; // Value injected by FXMLLoader

    @FXML // fx:id="emailId"
    private JFXTextField emailId; // Value injected by FXMLLoader

    @FXML // fx:id="birthdateId"
    private JFXTextField birthdateId; // Value injected by FXMLLoader

    @FXML // fx:id="genderComboId"
    private JFXComboBox<User.Gender> genderComboId; // Value injected by FXMLLoader

    @FXML // fx:id="saveBttnId"
    private JFXButton saveBttnId; // Value injected by FXMLLoader

    @FXML
    private ImageView imageSelector;

    public InfoController(AuthStore authStore) {
        this.authStore = authStore;
    }



    User user;
    Tuple<Image, String> customImage;



    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert changeAvatarId != null : "fx:id=\"changeAvatarId\" was not injected: check your FXML file 'info.fxml'.";
        assert firstNameId != null : "fx:id=\"firstNameId\" was not injected: check your FXML file 'info.fxml'.";
        assert lastNameId != null : "fx:id=\"lastNameId\" was not injected: check your FXML file 'info.fxml'.";
        assert emailId != null : "fx:id=\"emailId\" was not injected: check your FXML file 'info.fxml'.";
        assert birthdateId != null : "fx:id=\"birthdateId\" was not injected: check your FXML file 'info.fxml'.";
        assert genderComboId != null : "fx:id=\"genderComboId\" was not injected: check your FXML file 'info.fxml'.";
        assert saveBttnId != null : "fx:id=\"saveBttnId\" was not injected: check your FXML file 'info.fxml'.";

        genderComboId.getItems().addAll(User.Gender.values());

        user = authStore.getUser();
        if (user != null) {
            firstNameId.setText(user.getFirstName());
            lastNameId.setText(user.getLastName());
            emailId.setText(user.getEmail());
            birthdateId.setText(user.getBirthdate());
            genderComboId.setValue(user.getGender());
        }

        authStore.getUserSource().subscribe(this::profile);

    }
    @FXML
    private void editHandler(ActionEvent event) {
        user.setFirstName(firstNameId.getText());
        user.setLastName(lastNameId.getText());
        user.setBirthdate(birthdateId.getText());
        user.setEmail(emailId.getText());
        user.setGender(genderComboId.getValue());
        user.setAvatar(customImage.getSecond());
        InfoService infoService = new InfoService(user);
        infoService.start();

        infoService.setOnSucceeded(e -> {
            System.out.println("update Successfully!");
        });
    }

    @FXML
    void selectImage(ActionEvent event) throws FileNotFoundException {
        ImageUploadHandler imageUploadHandler = new ImageUploadHandler();
        customImage = imageUploadHandler.uploadImage();
        imageSelector.setImage(customImage.getFirst());
        imageSelector.setPreserveRatio(false);
        user.setAvatar(customImage.getSecond());

    }


    private void profile(User user) {
        if (user != null) {
            firstNameId.setText(user.getFirstName());
            lastNameId.setText(user.getLastName());
            emailId.setText(user.getEmail());
            birthdateId.setText(user.getBirthdate());
            genderComboId.setValue(user.getGender());

        }

    }




}
