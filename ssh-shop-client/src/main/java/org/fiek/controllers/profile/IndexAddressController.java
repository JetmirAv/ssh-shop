package org.fiek.controllers.profile; /**
 * Sample Skeleton for 'address.fxml' Controller Class
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.fiek.App;
import org.fiek.models.Address;
import org.fiek.models.User;
import org.fiek.services.auth.AddressService;
import org.fiek.services.chat.FindAndCountChannelsService;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.chat.ChatStore;
import org.fiek.utils.Loading;

public class IndexAddressController {

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
    ArrayList<Address> addresses = null;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert container != null : "fx:id=\"container\" was not injected: check your FXML file 'address.fxml'.";
        authStore = baseStore.getAuthStore();
        user = authStore.getUser();
        baseStore.getAuthStoreEventStream().subscribe(this::displayListAddress);
    }

    private void displayListAddress(AuthStore authStore) {
        try {
            if (authStore.getSelectedAddress() == null) {
                contentContainer.getChildren().add(App.loadFXML("views/profile/no-address-selected"));
            } else {
                contentContainer.getChildren().add(App.loadFXML("views/profile/address-content"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}



