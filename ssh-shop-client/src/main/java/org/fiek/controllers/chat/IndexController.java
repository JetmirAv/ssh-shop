/**
 * Sample Skeleton for 'index.fxml' Controller Class
 */

package org.fiek.controllers.chat;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.fiek.App;
import org.fiek.services.chat.FindAndCountChannelsService;
import org.fiek.store.BaseStore;
import org.fiek.store.chat.ChatStore;
import org.fiek.utils.Loading;

public class IndexController {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="container"
    private AnchorPane container; // Value injected by FXMLLoader

    @FXML // fx:id="chatContainer"
    private AnchorPane chatContainer; // Value injected by FXMLLoader

    Loading loading;
    ChatStore chatStore;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert chatContainer != null : "fx:id=\"chatContainer\" was not injected: check your FXML file 'index.fxml'.";

        fetchChats();

        baseStore.getChatStoreEventStream().subscribe(this::displayChat);

    }

    private void displayChat(ChatStore chatStore) {
        chatContainer.getChildren().clear();
        try {
            if (chatStore.getSelectedChannel() == null) {
                chatContainer.getChildren().add(App.loadFXML("views/chat/no-chat-selected"));
            } else {
                chatContainer.getChildren().add(App.loadFXML("views/chat/chat"));
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }

    }

    private void fetchChats() {
        FindAndCountChannelsService service = new FindAndCountChannelsService();
        service.start();

        service.setOnRunning(e -> {
            System.out.println("running");
            loading = new Loading();
            container.getChildren().add(loading);
        });

        service.setOnFailed(e -> {
            System.out.println("setOnFailed");
            container.getChildren().remove(loading);
        });

        service.setOnCancelled(e -> {
            System.out.println("setOnCancelled");
            container.getChildren().remove(loading);
        });

        service.setOnSucceeded(e -> {
            System.out.println("setOnSucceeded Bravo");
            container.getChildren().remove(loading);
        });
    }
}
