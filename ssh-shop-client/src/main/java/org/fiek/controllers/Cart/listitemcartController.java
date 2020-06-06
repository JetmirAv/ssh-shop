/**
 * Sample Skeleton for 'list-Item-Address.fxml' Controller Class
 */

package org.fiek.controllers.Cart;

import java.net.URL;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import eu.lestard.fluxfx.View;

import javafx.fxml.FXML;

import javafx.scene.control.Label;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.HBox;

import javafx.scene.text.Text;

import org.fiek.App;

public class listitemcartController implements View {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);

    AuthStore authStore = baseStore.getAuthStore();

    Cart cart;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="exitbutton"
    private JFXButton exitbutton; // Value injected by FXMLLoader

    @FXML // fx:id="priceLb"
    private Label priceLb; // Value injected by FXMLLoader


    @FXML // fx:id="prodLb"
    private Label prodLb; // Value injected by FXMLLoader

    @FXML // fx:id="quantityLb"
    private Label quantityLb; // Value injected by FXMLLoader


    @FXML
    private ImageView imageSelector;


public listitemcartController(Cart cart) {
        this.cart = cart;
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
            assert prodLb != null : "fx:id=\"prodLb\" was not injected: check your FXML file 'cart.fxml'.";
            assert imageSelector != null : "fx:id=\"imageSelector\" was not injected: check your FXML file 'cart.fxml'.";
            assert quantityLb != null : "fx:id=\"quantityLb\" was not injected: check your FXML file 'cart.fxml'.";
            assert priceLb != null : "fx:id=\"priceLb\" was not injected: check your FXML file 'cart.fxml'.";
            assert exitbutton != null : "fx:id=\"exitbutton\" was not injected: check your FXML file 'cart.fxml'.";
        prodLb.setText(cart.getProductname());
        priceLb.setText(cart.getPrice());
        quantityLb.setText(cart.getQuantity());
        exitbutton.setOnAction(this::handleButtonAction);
    };

    @FXML
    private void handleButtonAction(ActionEvent event){
         if(event.getSource() == exitbutton)
             System.out.println("Exitbutton pressed");






}
