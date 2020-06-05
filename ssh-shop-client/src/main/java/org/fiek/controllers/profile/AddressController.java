/**
 * Sample Skeleton for 'address.fxml' Controller Class
 */

package org.fiek.controllers.profile;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.lang.reflect.Array;
import java.net.URL;

import eu.lestard.fluxfx.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.fiek.models.Address;
import org.fiek.models.City;
import org.fiek.models.Country;
import org.fiek.models.User;
import org.fiek.services.auth.*;
import org.fiek.store.BaseStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.auth.GetCitiesByCountryAction;
import org.fiek.utils.Loading;
import org.fiek.utils.Tuple;

public class AddressController implements View {

    private final BaseStore baseStore;
    @FXML
    private VBox vboxList;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML
    private AnchorPane root;

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
    private JFXComboBox<String> countryComboId; // Value injected by FXMLLoader

    @FXML
    private JFXButton createBtn;

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
    Address addressObj = null;
    public ArrayList<Address> address = new ArrayList<>();
    public ArrayList<City> cities = new ArrayList<>();
    public ArrayList<Country> countries = new ArrayList<>();
    Country targetCountry;
    ArrayList<JFXButton> buttonsAddress;
    Loading loading;
    private int addressNr = -1;
    private int countryID = 0;
    City cityTarget = null;
    City cityTargetChanged = null;

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

    }

    // Edit Address
    public void EditHandler(ActionEvent event) {
        cities.removeAll(cities);
        int id = 0;
        int userID = user.getId();
        String street = "";
        String postal = "";
        String valueOfCity = "default";
        try {
            id = addressNr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            street = streetId.getText();
            postal = postalId.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            valueOfCity = cityComboId.getSelectionModel().getSelectedItem().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GetCityFromComboService cityInstance = new GetCityFromComboService(valueOfCity, countryID);
        cityInstance.start();
        String finalStreet = street;
        String finalPostal = postal;
        int finalId = id;
        cityInstance.setOnSucceeded(e -> {
            Address addrObj = null;
            cityTargetChanged = authStore.getCityFromCombo();
            addrObj = new Address(finalId, userID, finalStreet, finalPostal, user.getPhoneNumber(), cityTargetChanged.getID());
            UpdateAddressService updateAddressService = new UpdateAddressService(user, addrObj);
            updateAddressService.start();
            updateAddressService.setOnRunning(e2 -> {
                loading = new Loading();
                root.getChildren().add(loading);
            });
            updateAddressService.setOnSucceeded(e2 -> {
                System.out.println("update Successfully!");
                root.getChildren().remove(loading);
            });

            updateAddressService.setOnFailed(e2 -> {
                System.out.println("setOnFailed");
                root.getChildren().remove(loading);
            });

            updateAddressService.setOnCancelled(e2 -> {
                root.getChildren().remove(loading);
            });

        });

    }

    public void CreateHandler(ActionEvent event) {

        String street = "";
        String postal = "";
        String valueOfCity = "default";
        String value = "default";
        try {
            valueOfCity = cityComboId.getSelectionModel().getSelectedItem().toString();
            value = countryComboId.getSelectionModel().getSelectedItem().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addressObj = new Address();
        addressObj.setStreet(streetId.getText());
        addressObj.setPostal(postalId.getText());
        GetCountryByNameService countryServiceObj = new GetCountryByNameService(value);
        countryServiceObj.start();
        String finalValueOfCity = valueOfCity;
        String finalValue = value;
        countryServiceObj.setOnSucceeded(e5 -> {
            Country targetCountryChanged = authStore.getCountryTarget();
            countryID = targetCountryChanged.getId();
            GetCityFromComboService cityInstance = new GetCityFromComboService(finalValueOfCity, countryID);
            cityInstance.start();
            cityInstance.setOnSucceeded(e -> {
                cityTargetChanged = authStore.getCityFromCombo();
                addressObj.setCityId(cityTargetChanged.getID());
                addressObj.setUserId(user.getId());
                addressObj.setPhoneNumber(user.getPhoneNumber());
                System.out.println("INSTANCA:" + addressObj);
                System.out.println("ID e qytetit:" + cityTargetChanged.getID());
                CreateAddressService createInstance = new CreateAddressService(user.getId(), addressObj);
                createInstance.start();

                createInstance.setOnRunning(e2 -> {
                    loading = new Loading();
                    root.getChildren().add(loading);
                });

                createInstance.setOnSucceeded(e10 -> {
                    System.out.println("Create Successfully!");
                    root.getChildren().remove(loading);
                    JFXButton jfxButton = new JFXButton(street);
                    jfxButton.getStyleClass().add("address-switch-button");
                    vboxList.getChildren().add(jfxButton);
                    jfxButton.setOnAction(t -> {
                        streetId.setText(street);
                        postalId.setText(postal);
                        cityComboId.setValue(finalValueOfCity);
                        countryComboId.setValue(finalValue);
                    });
                });

                createInstance.setOnFailed(e11 -> {
                    System.out.println("Keq!");
                });
            });
        });
    }
}