/**
 * Sample Skeleton for 'list-item.fxml' Controller Class
 */
package org.fiek.controllers.chat;

import java.net.URL;
import java.util.ResourceBundle;

import eu.lestard.fluxfx.View;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.fiek.App;
import org.fiek.models.Channel;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.chat.SetActiveChannelAction;

public class ListItemController implements View {

    Channel channel;
    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    AuthStore authStore;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="imageBox"
    private Circle imageBox; // Value injected by FXMLLoader

    @FXML // fx:id="productName"
    private Text productName; // Value injected by FXMLLoader

    @FXML // fx:id="sellerName"
    private Text sellerName; // Value injected by FXMLLoader

    public ListItemController(Channel channel){
        this.channel = channel;
        this.authStore = baseStore.getAuthStore();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert imageBox != null : "fx:id=\"imageBox\" was not injected: check your FXML file 'list-item.fxml'.";
        assert productName != null : "fx:id=\"productName\" was not injected: check your FXML file 'list-item.fxml'.";
        assert sellerName != null : "fx:id=\"sellerName\" was not injected: check your FXML file 'list-item.fxml'.";



        productName.setText(channel.getProduct().getName());
        if(authStore.getUser().getId() == channel.getUserId()){
            sellerName.setText(channel.getProduct().getUser().getFirstName());
        } else {
            sellerName.setText(channel.getUser().getFirstName());
        }
    }

    @FXML
    public void clickHandler(MouseEvent action){
        publishAction(new SetActiveChannelAction(channel.getId()));
    }
}
