/**
 * Sample Skeleton for 'cards.fxml' Controller Class
 */

package org.fiek.controllers.profile;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.fiek.App;
import org.fiek.models.User;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;
import org.fiek.utils.Loading;

public class IndexCardController {
    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="container"
    private AnchorPane container; // Value injected by FXMLLoader

    @FXML // fx:id="contentContainer"
    private AnchorPane contentContainer; // Value injected by FXMLLoader

    Loading loading = new Loading();
    AuthStore authStore;
    User user;


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert container != null : "fx:id=\"container\" was not injected: check your FXML file 'cards.fxml'.";
        assert contentContainer != null : "fx:id=\"contentContainer\" was not injected: check your FXML file 'cards.fxml'.";
        authStore = baseStore.getAuthStore();
        user = authStore.getUser();
        baseStore.getAuthStoreEventStream().subscribe(this::displayListCards);
    }

    private void displayListCards(AuthStore authStore) {
        try {
            if (authStore.getSelectedCard() == null) {
                contentContainer.getChildren().add(App.loadFXML("views/profile/no-address-selected"));
            } else {
                contentContainer.getChildren().add(App.loadFXML("views/profile/cards-content"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

