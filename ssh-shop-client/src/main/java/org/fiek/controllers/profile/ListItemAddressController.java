/**
 * Sample Skeleton for 'list-Item-Address.fxml' Controller Class
 */

package org.fiek.controllers.profile;

import java.net.URL;
import java.util.ResourceBundle;

import eu.lestard.fluxfx.View;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.fiek.App;
import org.fiek.models.Address;
import org.fiek.models.Channel;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.auth.SetActiveAddressAction;
import org.fiek.store.chat.SetActiveChannelAction;

public class ListItemAddressController implements View {

    Address address;
    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    AuthStore authStore;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="addressName"
    private Text addressName; // Value injected by FXMLLoader


    public ListItemAddressController(Address address){
        this.address = address;
        this.authStore = baseStore.getAuthStore();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert addressName != null : "fx:id=\"addressName\" was not injected: check your FXML file 'list-Item-Address.fxml'.";
        System.out.println("List item u thirr!");
        addressName.setText(address.getStreet());

    }

    @FXML
    void clickHandler(MouseEvent event) {
        publishAction(new SetActiveAddressAction(address));
    }
}
