/**
 * Sample Skeleton for 'cards.fxml' Controller Class
 */

package org.fiek.controllers.profile;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.fiek.models.*;
import org.fiek.services.auth.AddressService;
import org.fiek.services.auth.CardService;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;
import org.fiek.utils.Loading;


public class CardsController {
    private final BaseStore baseStore;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML
    private VBox vboxList;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="numberId"
    private JFXTextField numberId; // Value injected by FXMLLoader

    @FXML // fx:id="expDateId"
    private JFXTextField expDateId; // Value injected by FXMLLoader

    @FXML // fx:id="csvId"
    private JFXTextField csvId; // Value injected by FXMLLoader

    @FXML // fx:id="saveBttnId"
    private JFXButton saveBttnId; // Value injected by FXMLLoader

    @FXML // fx:id="deleteBttnId"
    private JFXButton deleteBttnId; // Value injected by FXMLLoader

    public CardsController(BaseStore baseStore) {
        this.baseStore = baseStore;
    }

    AuthStore authStore;
    User user;
    ArrayList<JFXButton> buttonsCard;
    ArrayList<Card> cards = new ArrayList<Card>();
    Loading loading;
    private int cardsNr = -1;
    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert numberId != null : "fx:id=\"numberId\" was not injected: check your FXML file 'cards.fxml'.";
        assert expDateId != null : "fx:id=\"expDateId\" was not injected: check your FXML file 'cards.fxml'.";
        assert csvId != null : "fx:id=\"csvId\" was not injected: check your FXML file 'cards.fxml'.";
        assert saveBttnId != null : "fx:id=\"saveBttnId\" was not injected: check your FXML file 'cards.fxml'.";
        assert deleteBttnId != null : "fx:id=\"deleteBttnId\" was not injected: check your FXML file 'cards.fxml'.";

        authStore = baseStore.getAuthStore();
        user = authStore.getUser();

        CardService cardService = new CardService(user);
        cardService.start();

        cardService.setOnSucceeded(e -> {
            System.out.println("Get cards successfully");
            buttonsCard = new ArrayList<>();
            cards = authStore.getCards();
            user.setCards(cards);
            int numberOfButtons = user.getCards().size();
            System.out.println("Number of buttons : " + numberOfButtons);
            for (int i = 0; i < numberOfButtons; i++) {
                JFXButton jfxButton_i = new JFXButton();
                buttonsCard.add(jfxButton_i);
            }
            for (int i = 0; i < buttonsCard.size(); i++) {
                int s = 0;
                s = s + i ;
                buttonsCard.get(i).setText("CARD " + s);
                buttonsCard.get(i).getStyleClass().add("cards-switch-button");
                vboxList.getChildren().add(buttonsCard.get(i));
            }
            for (int i = 0; i < buttonsCard.size(); i++) {
                ArrayList<Card> cardsOfUser = new ArrayList<>();
                int finalI = i;
                for (Card card : user.getCards()) {
                    cardsOfUser.add(card);
                }
                buttonsCard.get(i).setOnAction(event -> {
                    cardsNr = cardsOfUser.get(finalI).getID();
                    System.out.println("Cards nr : " + cardsNr);
                    numberId.setText(cardsOfUser.get(finalI).getNumber());
                    expDateId.setText(cardsOfUser.get(finalI).getExp_month() + "/" +
                            cardsOfUser.get(finalI).getExp_year());
                    csvId.setText(cardsOfUser.get(finalI).getCode());
                });
            }
            user.clearCards();
        });
    }
}