/**
 * Sample Skeleton for 'cart.fxml' Controller Class
 */




package org.fiek.controllers.Cart;



import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXComboBox;

import com.jfoenix.controls.JFXTextField;

import java.io.FileNotFoundException;

import java.net.URL;

import java.util.ResourceBundle;

import eu.lestard.fluxfx.Action;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Label;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

import org.fiek.controllers.layout.NoAuthLayoutController;

import org.fiek.models.User;

import org.fiek.services.auth.InfoService;

import org.fiek.services.auth.LogInService;

import org.fiek.store.BaseStore;

import org.fiek.store.auth.AuthStore;

import org.fiek.utils.ImageUploadHandler;

import org.fiek.utils.Loading;

import org.fiek.utils.Tuple;

import org.fiek.controllers.AbstractController;

import org.fiek.store.BaseStore;

public class CartController {


    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;


    @FXML // fx:id="root"
    private AnchorPane root; // Value injected by FXMLLoader


    @FXML // fx:id="buybutton"
    private JFXButton buybutton; // Value injected by FXMLLoader


    @FXML // fx:id="totalLb"
    private Label totalLb; // Value injected by FXMLLoader


    FXMLLoader fxmlLoader;
    BaseStore baseStore = App.context.getInstance(BaseStore.class);

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert totalLb != null : "fx:id=\"totalLb\" was not injected: check your FXML file 'cart.fxml'.";
        assert buybutton != null : "fx:id=\"buybutton\" was not injected: check your FXML file 'cart.fxml'.";

    };
};