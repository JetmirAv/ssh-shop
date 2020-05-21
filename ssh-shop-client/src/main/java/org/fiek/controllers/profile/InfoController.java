/**
 * Sample Skeleton for 'info.fxml' Controller Class
 */

package org.fiek.controllers.profile;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import org.fiek.models.User;
import org.fiek.store.auth.AuthStore;

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

    public InfoController(AuthStore authStore) {
        this.authStore = authStore;
    }

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

        User user = authStore.getUser();

        if (user != null){
            firstNameId.setText(user.getFirstName());
            lastNameId.setText(user.getLastName());
            emailId.setText(user.getEmail());
            birthdateId.setText(user.getBirthdate());
            genderComboId.setValue(user.getGender());
        }

        authStore.getUserSource().subscribe(this::profile);

    }

    private void profile(User user) {
        if (user != null){
            firstNameId.setText(user.getFirstName());
            lastNameId.setText(user.getLastName());
            emailId.setText(user.getEmail());
            birthdateId.setText(user.getBirthdate());
            genderComboId.setValue(user.getGender());
        }

    }



}
