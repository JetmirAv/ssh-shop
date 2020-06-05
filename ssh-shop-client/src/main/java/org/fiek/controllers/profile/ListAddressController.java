/**
 * Sample Skeleton for 'listAddress.fxml' Controller Class
 */

package org.fiek.controllers.profile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.fiek.App;
import org.fiek.controllers.chat.ListItemController;
import org.fiek.models.Address;
import org.fiek.models.Channel;
import org.fiek.models.User;
import org.fiek.services.auth.AddressService;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.chat.ChatStore;
import org.fiek.utils.Loading;

public class ListAddressController {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    AuthStore authStore = baseStore.getAuthStore();
    User user = authStore.getUser();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="addressList"
    private VBox addressList; // Value injected by FXMLLoader

    @FXML // fx:id="addressList"
    private ScrollPane container;

    FXMLLoader fxmlLoader;

    Loading loading = new Loading();

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert addressList != null : "fx:id=\"addressList\" was not injected: check your FXML file 'listAddress.fxml'.";

        renderAddresses(baseStore.getAuthStore());
        baseStore.getAuthStoreEventStream().subscribe(this::renderAddresses);
    }

    private void renderAddresses(AuthStore authStore) {
        fetchAddress();
        ArrayList<Address> addresses = authStore.getAddresses();
        if (addresses != null && !addresses.isEmpty()) {
            addressList.getChildren().clear();
            for (Address address : addresses) {
                fxmlLoader = new FXMLLoader(App.class.getResource("views/profile/list-Item-Address.fxml"));
                fxmlLoader.setController(new ListItemAddressController(address));
                HBox hbox = null;
                try {
                    hbox = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                addressList.getChildren().add(hbox);
            }
        }
    }


    private void fetchAddress() {
        if (authStore.getAddresses() == null) {
            AddressService addressService = new AddressService(user);
            addressService.start();

//            AnchorPane parent = (AnchorPane) container.getParent().getParent();

            addressService.setOnRunning(e -> {
//                parent.getChildren().add(loading);
            });

            addressService.setOnFailed(e -> {
                System.out.println("Failed!");
//                parent.getChildren().remove(loading);
            });

            addressService.setOnCancelled(e -> {
//                parent.getChildren().remove(loading);
            });

            addressService.setOnSucceeded(e -> {
//                parent.getChildren().remove(loading);
            });
        }
    }
}
