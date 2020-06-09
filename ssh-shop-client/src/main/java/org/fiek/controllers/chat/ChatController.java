/**
 * Sample Skeleton for 'chat.fxml' Controller Class
 */

package org.fiek.controllers.chat;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import eu.lestard.fluxfx.View;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.fiek.App;
import org.fiek.models.Channel;
import org.fiek.models.Message;
import org.fiek.services.chat.GetChannelMessagesService;
import org.fiek.socket.ChatSocket;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.chat.ChatStore;
import org.fiek.store.chat.IncrementOffsetAction;
import org.fiek.utils.Loading;

public class ChatController implements View {
    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    AuthStore authStore = baseStore.getAuthStore();
    ChatStore chatStore = baseStore.getChatStore();
    Channel channel = chatStore.getSelectedChannel();


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="imageHolder"
    private Circle imageHolder; // Value injected by FXMLLoader

    @FXML // fx:id="productName"
    private Text productName; // Value injected by FXMLLoader

    @FXML // fx:id="contactName"
    private Text contactName; // Value injected by FXMLLoader

    @FXML // fx:id="messageBox"
    private TextField messageBox; // Value injected by FXMLLoader

    @FXML // fx:id="sendBtn"
    private Button sendBtn; // Value injected by FXMLLoader

    @FXML // fx:id="chatView"
    private ScrollPane chatView; // Value injected by FXMLLoader

    @FXML // fx:id="chatViewHolder"
    private AnchorPane chatViewHolder; // Value injected by FXMLLoader

    @FXML // fx:id="messageHolder"
    private VBox messageHolder; // Value injected by FXMLLoader

    @FXML
    void sendHandler(ActionEvent event) {
        if (messageBox.getText().length() > 0) {
            ChatSocket.emitMessage(channel.getId(), authStore.getUser().getId(), messageBox.getText());
            messageBox.setText("");
        }

    }

    Loading loading;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert imageHolder != null : "fx:id=\"imageHolder\" was not injected: check your FXML file 'chat.fxml'.";
        assert productName != null : "fx:id=\"productName\" was not injected: check your FXML file 'chat.fxml'.";
        assert contactName != null : "fx:id=\"contactName\" was not injected: check your FXML file 'chat.fxml'.";
        assert messageBox != null : "fx:id=\"messageBox\" was not injected: check your FXML file 'chat.fxml'.";
        assert sendBtn != null : "fx:id=\"sendBtn\" was not injected: check your FXML file 'chat.fxml'.";
        assert chatView != null : "fx:id=\"chatView\" was not injected: check your FXML file 'chat.fxml'.";
        assert messageHolder != null : "fx:id=\"messageHolder\" was not injected: check your FXML file 'chat.fxml'.";
        assert chatViewHolder != null : "fx:id=\"chatViewHolder\" was not injected: check your FXML file 'chat.fxml'.";


        productName.setText(channel.getProduct().getName());
        if (authStore.getUser().getId() == channel.getUserId()) {
            contactName.setText(channel.getProduct().getUser().getFirstName());
        } else {
            contactName.setText(channel.getUser().getFirstName());
        }

        productName.setOnMouseClicked(e -> {
            messageHolder.getChildren().add(addMessage(new Message("112", 1, 1, "teast")));
        });

        baseStore.getChatStoreEventStream().subscribeForOne(this::getMessagesToogle);
//        baseStore.getChatStoreEventStream().subscribe(this::getNewMessagesToogle);

        messageHolder.backgroundProperty().addListener(e -> {
            this.getMessages();
        });

//        messageHolder.widthProperty().addListener(e -> {
//            this.getNewMessage();
//        });

    }

    private void getNewMessage() {
        System.out.println("mrena toggle");
        int leftMessages = messageHolder.getChildren().size() - channel.getMessages().size();
        leftMessages = Math.abs(leftMessages);
        if (leftMessages > 0) {
            for (int i = leftMessages; i > 0; i--) {
                messageHolder.getChildren().add(addMessage(channel.messages.get(i - 1)));
            }
        }

    }

    private void getNewMessagesToogle(ChatStore chatStore) {
        System.out.println("toggle");
        messageHolder.setPrefWidth(messageHolder.getPrefWidth());
    }


    private void getMessagesToogle(ChatStore chatStore) {
        messageHolder.setBackground(Background.EMPTY);
    }

    private void getMessages() {
        System.out.println("get message po");

        if (channel != null && !channel.getMessages().isEmpty()) {
            messageHolder.setPrefHeight(200);
            ArrayList<Message> messages = channel.getMessages();
            ArrayList<HBox> messageHbox = new ArrayList<>();
            for (Message message : messages) {
                messageHbox.add(0, addMessage(message));
            }
            messageHolder.getChildren().addAll(messageHbox);
            Platform.runLater(this::scrollToBottom);
        }

    }

    public void scrollToBottom() {
        chatView.setVvalue(messageHolder.getHeight());
    }


    public HBox addMessage(Message message) {
        HBox hBox = new HBox();
        hBox.getStyleClass().add(message.getAuthorId() == authStore.getUser().getId() ? "rightHBchat" : "leftHBchat");
        Label label = new Label(message.getContent());
        hBox.getChildren().add(label);
        return hBox;
    }

}
