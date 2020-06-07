/**
 * Sample Skeleton for 'list-Item-Address.fxml' Controller Class
 */

package org.fiek.controllers.profile;

import java.net.URL;
import java.util.ResourceBundle;

import eu.lestard.fluxfx.View;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.fiek.App;
import org.fiek.models.Address;
import org.fiek.models.Channel;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.auth.SetActiveAddressAction;
import org.fiek.store.chat.SetActiveChannelAction;

public class ListItemAddressController implements View {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    AuthStore authStore = baseStore.getAuthStore();
    Address address;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="addressName"
    private Text addressName; // Value injected by FXMLLoader

    @FXML // fx:id="itemBox"
    private HBox itemBox; // Value injected by FXMLLoader




    public ListItemAddressController(Address address) {
        this.address = address;
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert addressName != null : "fx:id=\"addressName\" was not injected: check your FXML file 'list-Item-Address.fxml'.";

        addressName.setText(address.getStreet());
        System.out.println("TE DHENAT: \n");
        System.out.println("streetTest:" + address.getStreet() + "cityTest:" + address.getCity());
        Address selectedAddress = authStore.getSelectedAddress();
        System.out.println("selected Address:" + selectedAddress);
        if (selectedAddress != null && selectedAddress.getId() == address.getId())
            System.out.println("u be aktive !");
            itemBox.getStyleClass().add("active");
    }

    @FXML
    void clickHandler(MouseEvent event) {
        publishAction(new SetActiveAddressAction(address));
    }
}
