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
    private JFXComboBox<String> countryComboId; // Value injected by FXMLLoader

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

        user = authStore.getUser();
        System.out.println("userID:" + user.getId());
        System.out.println("------------------------");

        System.out.println("Address id : " + addressId);
        Address addr = authStore.getSelectedAddress();
        System.out.println("Address:" + addr);
        if (addr != null) {
            streetId.setText(addr.getStreet());
            postalId.setText(addr.getPostal());

         // Momentin qe pe boj koment po rregullohet !
            // Momentin qe pe heku prej komentit po hin infinit loop!
            // Kue ke qata publish

            CountryService getCountryByCityService =
                    new CountryService(addr.getCityId());
            getCountryByCityService.start();
            getCountryByCityService.setOnSucceeded(event1 -> {
                System.out.println("test11");
                Country targetCountry = authStore.getCountry();
                System.out.println("Target Country" + targetCountry.getName());
                countryComboId.getSelectionModel().select(targetCountry.getName());
            });
        }

//    private void fillData() {
//        System.out.println("brenda fill data!");
//        System.out.println("Address id : " + addressId + ", userId: " + user.getId());
//        if(addressId!=null)
//        {
//        GetAddressService service = new GetAddressService(addressId, user.getId());
//        service.start();
//
//        streetId.setText("hahahah!");
//        service.setOnSucceeded(e->{
//
//            Address address = authStore.getAddress();
//            System.out.println("addressS:" + address);
//            System.out.println("street:" + address.getStreet());
//
//
//
//        });
//
//        service.setOnFailed(e->{
//            System.out.println("KEQ o!");
//        });
//    }
//
//
//    }
        }
    }
