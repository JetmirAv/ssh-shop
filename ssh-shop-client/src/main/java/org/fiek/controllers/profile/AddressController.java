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

public class AddressController {

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
    public ArrayList<Country> countries = new ArrayList<>();
    Country targetCountry;
    ArrayList<JFXButton> buttonsAddress;
    Loading loading;
    private int addressNr = -1;
    private int countryID;
    City cityTarget = null;
    City cityTargetChanged;

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



        AddressService addressService = new AddressService(user);
        addressService.start();


        addressService.setOnSucceeded(e -> {
            buttonsAddress = new ArrayList<>();
            address = authStore.getAddresses();
            user.setAddresses(address);
            int numberOfButtons = user.getAddresses().size();
            System.out.println("intigjer:" + numberOfButtons);
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
                for (Address addr : user.getAddresses()) {
                    addressesOfUser.add(addr);

                }
                int finalI1 = i;
                buttonsAddress.get(i).setOnAction(event -> {
                    addressNr = addressesOfUser.get(finalI).getId();
                    System.out.println("Address number:" + addressNr);
                    streetId.setText(addressesOfUser.get(finalI).getStreet());
                    postalId.setText(addressesOfUser.get(finalI).getPostal());
                    CountryService getCountryByCityService =
                            new CountryService(addressesOfUser.get(finalI).getCityId());
                    getCountryByCityService.start();
                    getCountryByCityService.setOnSucceeded(event1 -> {
                        System.out.println("test11");
                        targetCountry = authStore.getCountry();
                        System.out.println("Target Country" + targetCountry.getName());
                        countryComboId.getSelectionModel().select(targetCountry.getName());
                        countries.removeAll(countries);
                    });

                    GetCityService getCityService = new GetCityService(addressesOfUser.get(finalI).getCityId());
                    getCityService.start();
                    getCityService.setOnSucceeded(e1 -> {
                        cityTarget = authStore.getCityTarget();
                    });
                    countryComboId.setOnAction(e3 -> {
                        System.out.println("Klikove ne combobox");
                        String value = countryComboId.getSelectionModel().getSelectedItem().toString();
                        GetCountryByNameService countryServiceObj = new GetCountryByNameService(value);
                        countryServiceObj.start();
                        countryServiceObj.setOnSucceeded(e5 -> {
                            Country targetCountryChanged = authStore.getCountryTarget();
                            countryID = targetCountryChanged.getId();
                            cityComboId.getItems().clear();
                            GetCitiesByCountryService getCitiesByCountryService = new GetCitiesByCountryService(countryID);
                            getCitiesByCountryService.start();
                            getCitiesByCountryService.setOnSucceeded(e4 -> {
                                ArrayList<City> citiesByCountry = authStore.getCities();
                                for (int j = 0; j < cities.size(); j++) {
                                    cityComboId.getItems().add(citiesByCountry.get(j).getName());
                                }
                                cityComboId.getSelectionModel().select(cityTarget.getName());
                                citiesByCountry.removeAll(citiesByCountry);
                            });
                });
                // Put info in all fields


                    });
                });
            }
            user.clearAddresses();
            countries.removeAll(countries);
        });







        // Fill Combobox with all countries
        CountryService countryService = new CountryService();
        countryService.start();
        countryService.setOnSucceeded(e -> {
            countries = authStore.getCountries();
            System.out.println("Country size:" + countries.size());
            for (int i = 0; i < countries.size(); i++) {

                countryComboId.getItems().add(countries.get(i).getName());
            }
            countries.removeAll(countries);
        });

        // Fill combobox with all cities
        CityService cityService = new CityService();
        cityService.start();
        cityService.setOnSucceeded(e -> {
            cities = authStore.getCities();
            for (int i = 0; i < cities.size(); i++) {

                cityComboId.getItems().add(cities.get(i).getName());
            }
            cities.removeAll(cities);
            countries.removeAll(countries);
        });

//        GetCityService getCityService = new GetCityService(addressesOfUser.get(finalI).getCityId());
//        getCityService.start();
//        getCityService.setOnSucceeded(e1 -> {
//            cityTarget = authStore.getCityTarget();
//        });

        countryComboId.setOnAction(e3 -> {
            String value = countryComboId.getSelectionModel().getSelectedItem().toString();
            System.out.println("hahahahaha:" + value);
            GetCountryByNameService countryServiceObj = new GetCountryByNameService(value);
            countryServiceObj.start();
            countryServiceObj.setOnSucceeded(e5 -> {
                Country targetCountryChanged = authStore.getCountryTarget();
                countryID = targetCountryChanged.getId();
                cityComboId.getItems().clear();
                GetCitiesByCountryService getCitiesByCountryService = new GetCitiesByCountryService(countryID);
                getCitiesByCountryService.start();
                getCitiesByCountryService.setOnSucceeded(e4 -> {
                    ArrayList<City> citiesByCountry = authStore.getCities();
                    System.out.println("size of city:" + citiesByCountry.size());
                    for (int j = 0; j < citiesByCountry.size(); j++) {
                        cityComboId.getItems().add(citiesByCountry.get(j).getName());
                    }
                    citiesByCountry.removeAll(citiesByCountry);
                    System.out.println("madhesia:" + citiesByCountry.size());
                });
            });
        });

    }

    // Edit Address
    public void EditHandler(ActionEvent event) {
        cities.removeAll(cities);
        int id = addressNr;
        int userID = user.getId();
        String street;
        String postal;
        street = streetId.getText();
        postal = postalId.getText();
        String valueOfCity = cityComboId.getSelectionModel().getSelectedItem().toString();
        GetCityFromComboService cityInstance = new GetCityFromComboService(valueOfCity, countryID);
        cityInstance.start();
        cityInstance.setOnSucceeded(e -> {
            Address addrObj = null;
            cityTargetChanged = authStore.getCityFromCombo();
            addrObj = new Address(id, userID, street, postal, user.getPhoneNumber(), cityTargetChanged.getID());
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
            });

            updateAddressService.setOnCancelled(e2 -> {
                root.getChildren().remove(loading);
            });

        });

    }

}