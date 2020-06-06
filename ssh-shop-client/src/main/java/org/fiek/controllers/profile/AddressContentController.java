package org.fiek.controllers.profile; /**
 * Sample Skeleton for 'address-content.fxml' Controller Class
 */

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import eu.lestard.fluxfx.View;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import org.fiek.App;
import org.fiek.models.Address;
import org.fiek.models.Channel;
import org.fiek.models.Country;
import org.fiek.models.User;
import org.fiek.services.auth.CountryService;
import org.fiek.services.auth.GetAddressService;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.auth.GetAddressAction;
import org.fiek.store.chat.ChatStore;

public class AddressContentController implements View {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    AuthStore authStore = baseStore.getAuthStore();
    Address address = authStore.getSelectedAddress();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="root"
    private AnchorPane root; // Value injected by FXMLLoader

    @FXML // fx:id="streetId"
    private JFXTextField streetId; // Value injected by FXMLLoader

    @FXML // fx:id="postalId"
    private JFXTextField postalId; // Value injected by FXMLLoader

    @FXML // fx:id="countryComboId"
    private JFXComboBox<Country> countryComboId; // Value injected by FXMLLoader

    @FXML // fx:id="cityComboId"
    private JFXComboBox<String> cityComboId; // Value injected by FXMLLoader

    Integer addressId;
    User user;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert root != null : "fx:id=\"root\" was not injected: check your FXML file 'address-content.fxml'.";
        assert streetId != null : "fx:id=\"streetId\" was not injected: check your FXML file 'address-content.fxml'.";
        assert postalId != null : "fx:id=\"postalId\" was not injected: check your FXML file 'address-content.fxml'.";
        assert countryComboId != null : "fx:id=\"countryComboId\" was not injected: check your FXML file 'address-content.fxml'.";
        assert cityComboId != null : "fx:id=\"cityComboId\" was not injected: check your FXML file 'address-content.fxml'.";

        if (address != null) {
            CountryService countryService = new CountryService();
            countryService.start();

            countryService.setOnSucceeded(e -> {
                countryComboId.setConverter(new StringConverter<Country>() {
                    @Override
                    public String toString(Country country) {
                        return country == null ? "" : country.getName();
                    }

                    @Override
                    public Country fromString(String s) {
                        return new Country(-1, s);
                    }
                });

                System.out.println("setOnSucceded: " + CountryService.countries.size());
                countryComboId.getItems().clear();
                countryComboId.getItems().addAll(CountryService.countries);
                countryComboId.valueProperty().setValue(address.getCity().getCountry());
            });


            streetId.setText(address.getStreet());
            postalId.setText(address.getPostal());
        }

    }
}
