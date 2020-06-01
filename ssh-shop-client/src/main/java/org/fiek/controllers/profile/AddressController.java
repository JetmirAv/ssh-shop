/**
 * Sample Skeleton for 'address.fxml' Controller Class
 */

package org.fiek.controllers.profile;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.lang.reflect.Array;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import org.fiek.models.Address;
import org.fiek.models.City;
import org.fiek.models.User;
import org.fiek.services.auth.AddressService;
import org.fiek.services.auth.CityService;
import org.fiek.services.auth.InfoService;
import org.fiek.store.BaseStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import org.fiek.store.auth.AuthStore;
import org.fiek.utils.Loading;
import org.fiek.utils.Tuple;

public class AddressController {

    private final BaseStore baseStore;
    @FXML
    private VBox vboxList;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="firstAddId"
    private JFXButton firstAddId; // Value injected by FXMLLoader

    @FXML // fx:id="secondAddId"
    private JFXButton secondAddId; // Value injected by FXMLLoader

    @FXML // fx:id="thirdAddId"
    private JFXButton thirdAddId; // Value injected by FXMLLoader

    @FXML // fx:id="fourthAddId"
    private JFXButton fourthAddId; // Value injected by FXMLLoader

    @FXML // fx:id="streetId"
    private JFXTextField streetId; // Value injected by FXMLLoader

    @FXML // fx:id="postalId"
    private JFXTextField postalId; // Value injected by FXMLLoader

    @FXML // fx:id="countryComboId"
    private JFXComboBox<?> countryComboId; // Value injected by FXMLLoader

    @FXML // fx:id="cityComboId"
    private JFXComboBox<String> cityComboId; // Value injected by FXMLLoader

    @FXML // fx:id="saveBttnId"
    private JFXButton saveBttnId; // Value injected by FXMLLoader

    @FXML // fx:id="deleteBttnId"
    private JFXButton deleteBttnId; // Value injected by FXMLLoader

    public AddressController(BaseStore baseStore) {
        this.baseStore = baseStore;
    }

    AuthStore authStore;
    User user;
    public ArrayList<Address> address = new ArrayList<>();
    public ArrayList<City> cities = new ArrayList<>();
    ArrayList<JFXButton> buttonsAddress;


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert firstAddId != null : "fx:id=\"firstAddId\" was not injected: check your FXML file 'address.fxml'.";
        assert secondAddId != null : "fx:id=\"secondAddId\" was not injected: check your FXML file 'address.fxml'.";
        assert thirdAddId != null : "fx:id=\"thirdAddId\" was not injected: check your FXML file 'address.fxml'.";
        assert fourthAddId != null : "fx:id=\"fourthAddId\" was not injected: check your FXML file 'address.fxml'.";
        assert streetId != null : "fx:id=\"streetId\" was not injected: check your FXML file 'address.fxml'.";
        assert postalId != null : "fx:id=\"postalId\" was not injected: check your FXML file 'address.fxml'.";
        assert countryComboId != null : "fx:id=\"countryComboId\" was not injected: check your FXML file 'address.fxml'.";
        assert cityComboId != null : "fx:id=\"cityComboId\" was not injected: check your FXML file 'address.fxml'.";
        assert saveBttnId != null : "fx:id=\"saveBttnId\" was not injected: check your FXML file 'address.fxml'.";
        assert deleteBttnId != null : "fx:id=\"deleteBttnId\" was not injected: check your FXML file 'address.fxml'.";
        authStore = baseStore.getAuthStore();
        user = authStore.getUser();

        CityService cityService = new CityService();
        cityService.start();
        cityService.setOnSucceeded(e -> {
            cities = authStore.getCities();
            for(int i=0; i<cities.size(); i++){
                cityComboId.getItems().add(cities.get(i).getName());
            }
            cities.removeAll(cities);
        });
        AddressService addressService = new AddressService(user);
        addressService.start();

        addressService.setOnSucceeded(e -> {
            buttonsAddress = new ArrayList<>();
            address = authStore.getAddresses();
            user.setAddresses(address);
            int numberOfButtons = user.getAddresses().size();
            for (int i = 0; i < numberOfButtons; i++) {
                JFXButton jfxButton_i = new JFXButton();
                buttonsAddress.add(jfxButton_i);
            }
            for (int i = 0; i < buttonsAddress.size(); i++) {
                buttonsAddress.get(i).setText(user.getAddresses().get(i).getStreet());
                buttonsAddress.get(i).getStyleClass().add("address-switch-button");
                vboxList.getChildren().add(buttonsAddress.get(i));
            }
            for (int i = 0; i < buttonsAddress.size(); i++) {
                ArrayList<Address> addressesOfUser = new ArrayList<>();
                int finalI = i;
                for(Address addr: user.getAddresses()){
                    addressesOfUser.add(addr);
                }
                buttonsAddress.get(i).setOnAction(event -> {
                    streetId.setText(addressesOfUser.get(finalI).getStreet());
                    postalId.setText(addressesOfUser.get(finalI).getPostal());

                });
            }
            user.clearAddresses();

        });

        }
    }

