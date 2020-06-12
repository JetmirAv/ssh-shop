/**
 * Sample Skeleton for 'cards-content.fxml' Controller Class
 */

package org.fiek.controllers.profile;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.ResourceBundle;

import eu.lestard.fluxfx.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.LoadException;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.fiek.App;
import org.fiek.models.Address;
import org.fiek.models.Card;
import org.fiek.models.User;
import org.fiek.services.auth.CreateAddressService;
import org.fiek.services.auth.CreateCardService;
import org.fiek.services.auth.UpdateAddressService;
import org.fiek.services.auth.UpdateCardService;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;
import org.fiek.utils.Loading;

public class CardContentController implements View {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    AuthStore authStore = baseStore.getAuthStore();
    Card card = authStore.getSelectedCard();
    User userAuth = authStore.getUser();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="rootCard"
    private AnchorPane rootCard; // Value injected by FXMLLoader

    @FXML // fx:id="gridrootCard"
    private GridPane gridrootCard; // Value injected by FXMLLoader

    @FXML // fx:id="expDateId"
    private JFXTextField expDateId; // Value injected by FXMLLoader

    @FXML // fx:id="csvId"
    private JFXTextField csvId; // Value injected by FXMLLoader

    @FXML // fx:id="editCardId"
    private JFXButton editCardId; // Value injected by FXMLLoader

    @FXML // fx:id="numberId"
    private JFXTextField numberId; // Value injected by FXMLLoader

    Loading loading = new Loading();


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert rootCard != null : "fx:id=\"rootCard\" was not injected: check your FXML file 'cards-content.fxml'.";
        assert gridrootCard != null : "fx:id=\"gridrootCard\" was not injected: check your FXML file 'cards-content.fxml'.";
        assert expDateId != null : "fx:id=\"expDateId\" was not injected: check your FXML file 'cards-content.fxml'.";
        assert csvId != null : "fx:id=\"csvId\" was not injected: check your FXML file 'cards-content.fxml'.";
        assert editCardId != null : "fx:id=\"editId\" was not injected: check your FXML file 'cards-content.fxml'.";


        if (card != null) {

            if (card.getId() == -1) {
                numberId.setText("");
                expDateId.setText("");
                csvId.setText("");
            } else {

                numberId.setText(card.getNumber());
                expDateId.setText(card.getExp_month() + "/" + card.getExp_year());
                csvId.setText(card.getCode());

            }
        }
    }


    @FXML
    void EditCardHandler(ActionEvent event) {
        String number = numberId.getText();
        String expDate = expDateId.getText();

        String exp_month = "";
        String exp_year = "";
        try {
            String[] parts = expDate.split("/");
            exp_month = parts[0];
            exp_year = parts[1];
        } catch (Exception e) {
            e.printStackTrace();
        }

        String code = csvId.getText();

        card.setNumber(number);
        card.setExp_month(exp_month);
        card.setExp_year(exp_year);
        card.setCode(code);

        if (card.getId() == -1) {
            CreateCardService serviceCreate = new CreateCardService(userAuth.getId(), card);
            serviceCreate.start();

            serviceCreate.setOnRunning(e1 -> {
                rootCard.getChildren().add(loading);
            });

            serviceCreate.setOnSucceeded(e2 -> {
                rootCard.getChildren().remove(loading);
            });
            serviceCreate.setOnFailed(e3 -> {
                rootCard.getChildren().remove(loading);
            });

        } else {

            UpdateCardService serviceUpdate = new UpdateCardService(card);
            serviceUpdate.start();

            serviceUpdate.setOnRunning(e4 -> {
                rootCard.getChildren().add(loading);
            });

            serviceUpdate.setOnSucceeded(e3 -> {

                rootCard.getChildren().remove(loading);
                Card card1 = authStore.getSelectedCard();
                numberId.setText(card1.getNumber());
                expDateId.setText(card1.getExp_month() + "/" + card1.getExp_year());
                csvId.setText(card1.getCode());

            });

            serviceUpdate.setOnFailed(e4 -> {
                rootCard.getChildren().remove(loading);
            });

            serviceUpdate.setOnCancelled(e4 -> {
                rootCard.getChildren().remove(loading);
            });

        }
    }
}
