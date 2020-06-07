/**
 * Sample Skeleton for 'listCards.fxml' Controller Class
 */

package org.fiek.controllers.profile;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import eu.lestard.fluxfx.View;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.fiek.App;
import org.fiek.models.Address;
import org.fiek.models.Card;
import org.fiek.models.User;
import org.fiek.services.auth.AddressService;
import org.fiek.services.auth.CardService;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AddNewAddressAction;
import org.fiek.store.auth.AddNewCardAction;
import org.fiek.store.auth.AuthStore;
import org.fiek.utils.Loading;

public class ListCardController implements View {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    AuthStore authStore = baseStore.getAuthStore();
    User user = authStore.getUser();


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="createCardId"
    private JFXButton createCardId; // Value injected by FXMLLoader

    @FXML // fx:id="container"
    private ScrollPane container; // Value injected by FXMLLoader

    @FXML // fx:id="cardList"
    private VBox cardList; // Value injected by FXMLLoader

    FXMLLoader fxmlLoader;
    Loading loading = new Loading();



    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert createCardId != null : "fx:id=\"createCardId\" was not injected: check your FXML file 'listCards.fxml'.";
        assert container != null : "fx:id=\"container\" was not injected: check your FXML file 'listCards.fxml'.";
        assert cardList != null : "fx:id=\"cardList\" was not injected: check your FXML file 'listCards.fxml'.";
        renderCards(baseStore.getAuthStore());
        baseStore.getAuthStoreEventStream().subscribe(this::renderCards);
    }

    private void renderCards(AuthStore authStore) {
        fetchCards();
        ArrayList<Card> cards = authStore.getCards();
        if (cards != null && !cards.isEmpty()) {

            if (cards.size() >= 5) toogleDisable(true);

            cardList.getChildren().clear();
            for (Card card  : cards) {
                fxmlLoader = new FXMLLoader(App.class.getResource("views/profile/list-Item-Cards.fxml"));
                fxmlLoader.setController(new ListItemCardController(card));
                HBox hbox = null;
                try {
                    hbox = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cardList.getChildren().add(hbox);
            }
        }
    }

    void toogleDisable(Boolean bool) {
        createCardId.setDisable(bool);

    }


    private void fetchCards() {
        if (authStore.getCards() == null) {
            CardService cardService = new CardService(user);
            cardService.start();


            cardService.setOnRunning(e -> {
//                parent.getChildren().add(loading);
            });

            cardService.setOnFailed(e -> {
                System.out.println("Failed!");
//                parent.getChildren().remove(loading);
            });

            cardService.setOnCancelled(e -> {
//                parent.getChildren().remove(loading);
            });

            cardService.setOnSucceeded(e -> {
//                parent.getChildren().remove(loading);
            });
        }
    }


    @FXML
    void clickHandler(MouseEvent event) {
        Card newCardObj = new Card();
        String stringCard = newCardObj.toString();
        publishAction(new AddNewCardAction(stringCard));
    }
}
