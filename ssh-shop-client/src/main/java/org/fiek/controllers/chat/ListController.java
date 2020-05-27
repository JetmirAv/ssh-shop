/**
 * Sample Skeleton for 'list.fxml' Controller Class
 */
package org.fiek.controllers.chat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.fiek.App;
import org.fiek.models.Channel;
import org.fiek.store.BaseStore;
import org.fiek.store.chat.ChatStore;

public class ListController {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="messageList"
    private VBox messageList; // Value injected by FXMLLoader

    FXMLLoader fxmlLoader;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert messageList != null : "fx:id=\"messageList\" was not injected: check your FXML file 'list.fxml'.";
        System.out.println("ListController init");
        renderChats(baseStore.getChatStore());
        baseStore.getChatStoreEventStream().subscribe(this::renderChats);

    }

    private void renderChats(ChatStore chatStore) {
        ArrayList<Channel> channels = chatStore.getChannels();
        messageList.getChildren().clear();
        try {
            for(Channel channel : channels){
                fxmlLoader = new FXMLLoader(App.class.getResource("views/chat/list-item.fxml"));
                fxmlLoader.setController(new ListItemController(channel));
                HBox hbox = fxmlLoader.load();
                messageList.getChildren().add(hbox);
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }


    }



}
