package org.fiek.controllers.profile; /**
 * Sample Skeleton for 'list-item-Cards.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;

import eu.lestard.fluxfx.View;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.fiek.App;
import org.fiek.models.Address;
import org.fiek.models.Card;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.auth.SetActiveAddressAction;
import org.fiek.store.auth.SetActiveCardAction;

public class ListItemCardController implements View {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    AuthStore authStore = baseStore.getAuthStore();
    Card card;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="itemBox"
    private HBox itemBox; // Value injected by FXMLLoader

    @FXML // fx:id="cardName"
    private Text cardName; // Value injected by FXMLLoader

    public ListItemCardController(Card card) {
        this.card = card;
    }




    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        assert itemBox != null : "fx:id=\"itemBox\" was not injected: check your FXML file 'list-item-Cards.fxml'.";
        assert cardName != null : "fx:id=\"cardName\" was not injected: check your FXML file 'list-item-Cards.fxml'.";

        String number = "Card";
        cardName.setText(number);
        Card selectedCard = authStore.getSelectedCard();

        if (selectedCard != null && selectedCard.getId() == card.getId())
            itemBox.getStyleClass().add("active");

    }

    @FXML
        void clickHandler(MouseEvent event) {
            publishAction(new SetActiveCardAction(card));
        }
    }


