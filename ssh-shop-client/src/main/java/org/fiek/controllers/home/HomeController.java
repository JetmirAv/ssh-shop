package org.fiek.controllers.home;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import org.fiek.models.Product;
import org.fiek.models.User;
import org.fiek.services.auth.InfoService;
import org.fiek.store.BaseStore;
import org.fiek.store.home.HomeStore;
import org.fiek.utils.Loading;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController {

    private final BaseStore baseStore;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="homeFirstHbox"
    private HBox homeFirstHbox; // Value injected by FXMLLoader

    @FXML // fx:id="homeroot"
    private AnchorPane homeroot; // Value injected by FXMLLoader

    @FXML // fx:id="searchId"
    private JFXTextField searchId; // Value injected by FXMLLoader

    @FXML // fx:id="orderByComboId"
    private JFXComboBox<?> orderByComboId; // Value injected by FXMLLoader

    @FXML // fx:id="homeAllPrdouct"
    private FlowPane homeAllPrdouct; // Value injected by FXMLLoader

    @FXML // fx:id="categoryComboId"
    private JFXComboBox<Product.Category> categoryComboId; // Value injected by FXMLLoader

    public HomeController(BaseStore baseStore) {
        this.baseStore = baseStore;
    }

    HomeStore homeStore;
    User user;
    Product product;
    Loading loading;
    ArrayList<Product> products;


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert homeFirstHbox != null : "fx:id=\"homeFirstHbox\" was not injected: check your FXML file 'home.fxml'.";
        assert homeroot != null : "fx:id=\"homeroot\" was not injected: check your FXML file 'home.fxml'.";
        assert searchId != null : "fx:id=\"searchId\" was not injected: check your FXML file 'home.fxml'.";
        assert orderByComboId != null : "fx:id=\"orderByComboId\" was not injected: check your FXML file 'home.fxml'.";
        assert homeAllPrdouct != null : "fx:id=\"homeAllPrdouct\" was not injected: check your FXML file 'home.fxml'.";
        assert categoryComboId != null : "fx:id=\"categoryComboId\" was not injected: check your FXML file 'home.fxml'.";




        homeStore = baseStore.getHomeStore();

    }

    @FXML
    private void categoryHandler(ActionEvent event) {
        user.setProducts(searchId.getText());
        product.setCategory(categoryComboId.getValue());




    }










}
