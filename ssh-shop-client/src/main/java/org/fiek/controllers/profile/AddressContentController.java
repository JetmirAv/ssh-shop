package org.fiek.controllers.profile; /**
 * Sample Skeleton for 'address-content.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import eu.lestard.fluxfx.View;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import org.fiek.App;
import org.fiek.models.*;
import org.fiek.services.auth.*;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.auth.GetAddressAction;
import org.fiek.store.chat.ChatStore;
import org.fiek.utils.Loading;

public class AddressContentController implements View {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    AuthStore authStore = baseStore.getAuthStore();
    Address address = authStore.getSelectedAddress();
    User userAuth = authStore.getUser();


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
    private ComboBox<String> countryComboId; // Value injected by FXMLLoader
    @FXML
    private ComboBox<String> cityComboId;

    @FXML
    private GridPane gridRoot;

    @FXML
    private JFXButton editId;


    Integer addressId;
    User user;
    Integer countryID;
    City cityTarget;
    int countryTargetId;
    Loading loading = new Loading();

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert root != null : "fx:id=\"root\" was not injected: check your FXML file 'address-content.fxml'.";
        assert streetId != null : "fx:id=\"streetId\" was not injected: check your FXML file 'address-content.fxml'.";
        assert postalId != null : "fx:id=\"postalId\" was not injected: check your FXML file 'address-content.fxml'.";
        assert countryComboId != null : "fx:id=\"countryComboId\" was not injected: check your FXML file 'address-content.fxml'.";
        assert cityComboId != null : "fx:id=\"cityComboId\" was not injected: check your FXML file 'address-content.fxml'.";


        if (address != null) {

            if (address.getId() == -1) {
                streetId.setText("");
                postalId.setText("");
                showCitiesInCombo();
                showCountriesInCombo();
                countryComboId.setOnAction(e -> {
                    GetCities();
                });

            } else {

                showCountriesInCombo();
                showCitiesInCombo();
                streetId.setText(address.getStreet());
                postalId.setText(address.getPostal());
                countryComboId.getSelectionModel().select(address.getCity().getCountry().getName());
                cityComboId.getSelectionModel().select(address.getCity().getName());

                GetCities();

                countryComboId.setOnAction(e2 -> {
                    GetCities();
                });
            }
        }
    }

    private void showCitiesInCombo() {
        CityService cityService = new CityService();
        cityService.start();
        cityService.setOnSucceeded(e -> {
            ArrayList<City> citiesList = cityService.cities;
            for (int i = 0; i < citiesList.size(); i++) {

                cityComboId.getItems().add(citiesList.get(i).getName());
            }
            citiesList.removeAll(citiesList);
        });
    }

    private void showCountriesInCombo() {
        CountryService countryService = new CountryService();
        countryService.start();
        countryService.setOnSucceeded(e -> {
            countryComboId.getItems().clear();
            ArrayList<Country> countryList = CountryService.countries;
            for (int i = 0; i < countryList.size(); i++) {
                countryComboId.getItems().add(countryList.get(i).getName());
            }
            countryList.removeAll(countryList);

        });
    }


    private void GetCities() {
        String targetCountry1 = countryComboId.getSelectionModel().getSelectedItem().toString();
        GetCountryByNameService countryServiceObj = new GetCountryByNameService(targetCountry1);
        countryServiceObj.start();
        countryServiceObj.setOnSucceeded(e3 -> {
            ArrayList<Country> countryList = GetCountryByNameService.countryTarget;
            countryTargetId = countryList.get(0).getId();
            GetCitiesByCountryService getCitiesByCountryService =
                    new GetCitiesByCountryService(countryTargetId);
            getCitiesByCountryService.start();
            getCitiesByCountryService.setOnSucceeded(e4 -> {
                cityComboId.getItems().clear();
                ArrayList<City> cityList = GetCitiesByCountryService.cities;
                for (int i = 0; i < cityList.size(); i++) {
                    cityComboId.getItems().add(cityList.get(i).getName());
                }
                cityList.removeAll(cityList);
                countryList.removeAll(countryList);
                cityComboId.getSelectionModel().select(address.getCity().getName());


            });
        });

    }

    public void EditHandler(ActionEvent event) {

        int userID = address.getUserId();
        String street = "";
        String postal = "";
        try {
            street = streetId.getText();
            postal = postalId.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String valueOfCity = null;
        try {
            valueOfCity = cityComboId.getSelectionModel().getSelectedItem().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        GetCityFromComboService cityInstance = new GetCityFromComboService(valueOfCity, countryTargetId);
        cityInstance.start();
        String finalStreet = street;
        String finalPostal = postal;

        String finalValueOfCity = valueOfCity;
        cityInstance.setOnSucceeded(e -> {

            ArrayList<City> cityList = GetCityFromComboService.cityTrg;
            address.setStreet(finalStreet);
            address.setPostal(finalPostal);
            address.setPhoneNumber(userAuth.getPhoneNumber());
            address.setCityId(cityList.get(0).getId());

            if (address.getId() == -1) {
                CreateAddressService createAddressService = new CreateAddressService(userAuth.getId(), address);
                createAddressService.start();

                createAddressService.setOnRunning(e1 -> {
                    root.getChildren().add(loading);
                });

                createAddressService.setOnSucceeded(e2 -> {

                    root.getChildren().remove(loading);
                });
                createAddressService.setOnFailed(e3 -> {
                    root.getChildren().remove(loading);
                });

            } else {
                UpdateAddressService updateAddressService = new UpdateAddressService(address);
                updateAddressService.start();

                updateAddressService.setOnRunning(e4 -> {
                    root.getChildren().add(loading);
                });

                updateAddressService.setOnSucceeded(e3 -> {
                    root.getChildren().remove(loading);
                    Address address = authStore.getSelectedAddress();
                    cityComboId.getSelectionModel().select(address.getCity().getName());
                });

                updateAddressService.setOnFailed(e4 -> {
                    root.getChildren().remove(loading);
                });

                updateAddressService.setOnCancelled(e4 -> {
                    root.getChildren().remove(loading);
                });
            }
        });
    }


}



